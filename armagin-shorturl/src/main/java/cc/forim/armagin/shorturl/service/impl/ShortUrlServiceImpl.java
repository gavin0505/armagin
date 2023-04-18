package cc.forim.armagin.shorturl.service.impl;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.common.enums.CommonConstant;
import cc.forim.armagin.common.lock.DistributeLockFactory;
import cc.forim.armagin.common.lock.DistributedLock;
import cc.forim.armagin.shorturl.cache.UrlMapCacheManager;
import cc.forim.armagin.shorturl.dao.CompressionCodeMapper;
import cc.forim.armagin.shorturl.dao.DomainConfMapper;
import cc.forim.armagin.shorturl.infra.dto.ShortUrlGenerateDto;
import cc.forim.armagin.shorturl.infra.entity.CompressionCode;
import cc.forim.armagin.shorturl.infra.entity.DomainConf;
import cc.forim.armagin.shorturl.infra.entity.UrlMap;
import cc.forim.armagin.shorturl.infra.enums.CompressionCodeStatus;
import cc.forim.armagin.shorturl.infra.enums.LockKey;
import cc.forim.armagin.shorturl.infra.enums.ResultStatusEnum;
import cc.forim.armagin.shorturl.infra.keygen.SequenceGenerator;
import cc.forim.armagin.shorturl.infra.utils.ConversionUtils;
import cc.forim.armagin.shorturl.infra.vo.ShortUrlCreationVo;
import cc.forim.armagin.shorturl.service.ShortUrlService;
import cc.forim.armagin.shorturl.service.TransactionalService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 短链接操作服务实现类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/17 23:42
 */
@Service("shortUrlServiceImpl")
@Slf4j
public class ShortUrlServiceImpl implements ShortUrlService {
    @Resource(name = "distributeLockFactory")
    private DistributeLockFactory distributeLockFactory;

    @Resource(name = "snowflakeSequenceGenerator")
    private SequenceGenerator sequenceGenerator;

    @Resource(name = "compressionCodeMapper")
    private CompressionCodeMapper compressionCodeMapper;

    @Resource(name = "domainConfMapper")
    private DomainConfMapper domainConfMapper;

    @Resource(name = "transactionalServiceImpl")
    private TransactionalService transactionalService;

    @Resource(name = "urlMapCacheManager")
    private UrlMapCacheManager urlMapCacheManager;

    private final UrlValidator urlValidator = new UrlValidator(new String[]{CommonConstant.HTTP_PROTOCOL,
            CommonConstant.HTTPS_PROTOCOL});

    /**
     * 单次生成压缩码数量
     */
    @Value("${armagin.shortUrl.compress.code.batch}")
    private Integer compressCodeBatch;

    /**
     * 压缩码位数
     */
    @Value("${armagin.shortUrl.compress.code.bits}")
    private Integer bits;

    @Override
    public ResultVo<ShortUrlCreationVo> createShortUrlBiz(ShortUrlGenerateDto dto) {
        // 1. 解析订单

        // 2. 库存管理

        // 3. 创建短链
        String shortUrl = createRandomShortUrl(dto);
        // 4. 返回结果
        if (StrUtil.isNotBlank(shortUrl)) {
            // 设置VO
            ShortUrlCreationVo vo = new ShortUrlCreationVo();
            vo.setShortUrl(shortUrl);
            vo.setRequestId(dto.getRequestId());

            return ResultVo.success(ResultStatusEnum.CREATE_SHORT_URL_SUCCESS.getCode(), ResultStatusEnum.CREATE_SHORT_URL_SUCCESS.getDescription(), vo);
        }
        return ResultVo.error(ResultStatusEnum.CREATE_SHORT_URL_FAILED.getCode(), ResultStatusEnum.CREATE_SHORT_URL_FAILED.getDescription());
    }

    /**
     * 创建随机短链接
     *
     * @param dto 请求生成短链的传输数据
     * @return 短链创建结果
     */
    public String createRandomShortUrl(ShortUrlGenerateDto dto) {

        // 创建分布式锁
        DistributedLock lock = distributeLockFactory.provideDistributedLock(LockKey.CREATE_URL_MAP.getCode());
        try {
            // 上锁
            lock.lock(LockKey.CREATE_URL_MAP.getReleaseTime(), TimeUnit.MILLISECONDS);

            // 获取压缩码
            CompressionCode compressionCode = getAvailableCompressCode();

            Assert.isTrue(Objects.nonNull(compressionCode) &&
                            CompressionCodeStatus.AVAILABLE.getValue().equals(compressionCode.getCodeStatus()),
                    "压缩码不存在或者已经被使用");

            // 校验长链接
            String longUrl = dto.getLongUrl();
            Assert.isTrue(urlValidator.isValid(longUrl), String.format("链接[%s]非法", longUrl));

            // 判断短链域名和对应服务类别存在
            DomainConf domainConf = domainConfMapper.selectByDomain(dto.getDomain(), dto.getBizType());
            Assert.notNull(domainConf, String.format("域名不存在[c:%s]", dto.getDomain()));

            // URL映射配置
            UrlMap urlMap = new UrlMap();
            urlMap.setLongUrl(dto.getLongUrl());
            String code = compressionCode.getCompressionCode();
            // 短链格式：（例）https://4im.cc/v/ABCD1234
            String shortUrl = String.format("%s://%s/%s/%s", domainConf.getProtocol(), domainConf.getDomainValue(), domainConf.getBizType(), code);
            urlMap.setShortUrl(shortUrl);
            urlMap.setCompressionCode(code);
            urlMap.setUrlStatus(CompressionCodeStatus.AVAILABLE.getValue());
            urlMap.setDescription(dto.getDescription());

            // 长短链的摘要
            urlMap.setShortUrlDigest(DigestUtils.sha1Hex(urlMap.getShortUrl()));
            urlMap.setLongUrlDigest(DigestUtils.sha1Hex(urlMap.getLongUrl()));
            urlMap.setBizType(dto.getBizType());
            CompressionCode updater = new CompressionCode();
            updater.setCodeStatus(CompressionCodeStatus.USED.getValue());
            updater.setId(compressionCode.getId());
            // 事务，保存短链映射和更新压缩码状态
            transactionalService.saveUrlMapAndUpdateCompressCode(urlMap, updater);
            // 刷新缓存
            urlMapCacheManager.refreshUrlMapCache(urlMap);

            return shortUrl;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 获取一个可用的压缩码
     *
     * @return 可用的压缩码
     */
    private CompressionCode getAvailableCompressCode() {
        CompressionCode compressionCode = compressionCodeMapper.getLatestAvailableCompressionCode();
        if (Objects.nonNull(compressionCode)) {
            return compressionCode;
        } else {
            generateBatchCompressionCodes();
            return Objects.requireNonNull(compressionCodeMapper.getLatestAvailableCompressionCode());
        }
    }

    /**
     * 批量生成压缩码
     */
    private void generateBatchCompressionCodes() {
        for (int i = 0; i < compressCodeBatch; i++) {
            // 生成整型序列
            long sequence = sequenceGenerator.generate();
            CompressionCode compressionCode = new CompressionCode();
            compressionCode.setSequenceValue(String.valueOf(sequence));

            // 压缩码进制转换（10 -> 62）
            String code = ConversionUtils.X.encode62(sequence);

            // 截取后n位为压缩码
            code = code.substring(code.length() - bits);
            compressionCode.setCompressionCode(code);
            compressionCodeMapper.insert(compressionCode);
            log.info("[生成压缩码：{}, 序列：{}]", compressionCode.getCompressionCode(), compressionCode.getSequenceValue());
        }
    }
}