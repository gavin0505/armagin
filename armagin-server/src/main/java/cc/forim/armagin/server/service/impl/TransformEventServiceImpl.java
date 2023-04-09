package cc.forim.armagin.server.service.impl;

import cc.forim.armagin.server.infra.entity.TransformEventRecord;
import cc.forim.armagin.server.infra.utils.RedisUtil;
import cc.forim.armagin.server.infra.utils.UserAgentUtils;
import cc.forim.armagin.server.service.TransformEventService;
import cn.hutool.core.util.StrUtil;
import eu.bitwalker.useragentutils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;
import java.util.Optional;

import static cc.forim.armagin.server.infra.enums.CacheKeyEnum.TER_TEMP_CACHE_LIST;

/**
 * 转换事件记录服务实现类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/28 11:08
 */

@Service("transformEventServiceImpl")
@Slf4j
public class TransformEventServiceImpl implements TransformEventService {

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    @Override
    public void recordTransform(TransformEventRecord record) {

        // 身份唯一标识,算法:SHA-1(客户端IP + '-' + UserAgent)
        String uniqueIdentity = DigestUtils.sha1Hex(record.getClientIp() + "-" + record.getUserAgent());
        record.setUniqueIdentity(uniqueIdentity);
        record.setShortUrlDigest(DigestUtils.sha1Hex(record.getShortUrl()));
        record.setLongUrlDigest(DigestUtils.sha1Hex(record.getLongUrl()));
        try {
            URL url = new URL(record.getShortUrl());
            // 短链的附加参数
            if (StrUtil.isNotEmpty(url.getQuery())) {
                record.setQueryParam(url.getQuery());
            }
        } catch (Exception e) {
            log.warn("解析TransformEvent事件中短链的查询参数异常,事件内容:{}", record, e);
        }
        // 解析User-Agent
        if (StrUtil.isNotEmpty(record.getUserAgent())) {
            try {
                UserAgent userAgent = UserAgent.parseUserAgentString(record.getUserAgent());
                OperatingSystem operatingSystem = userAgent.getOperatingSystem();
                // 操作系统
                Optional.ofNullable(operatingSystem).ifPresent(x -> {
                    record.setOsType(x.getName());
                    record.setOsVersion(x.getName());
                    Optional.ofNullable(x.getDeviceType()).ifPresent(deviceType -> {
                        record.setDeviceType(deviceType.getName());
                        // 操作系统组别作为手机类型 - 具体的手机型号
                        if (DeviceType.MOBILE == deviceType) {
                            UserAgentUtils.UserAgentExtractResult result
                                    = UserAgentUtils.X.extractSystemType(record.getUserAgent());
                            record.setPhoneType(result.getPhoneType());
                            record.setOsType(result.getSystemType());
                            record.setOsVersion(result.getSystemVersion());
                        }
                    });
                });
                // 浏览器类型
                Browser browser = userAgent.getBrowser();
                Optional.ofNullable(browser).ifPresent(x -> record.setBrowserType(x.getGroup().getName()));
                // 浏览器版本
                Version browserVersion = userAgent.getBrowserVersion();
                Optional.ofNullable(browserVersion).ifPresent(x -> record.setBrowserVersion(x.getVersion()));

                // 聚合到redis
                redisUtil.lPush(TER_TEMP_CACHE_LIST.getKey(), record);
                log.info("处理完成:{} ", record);
            } catch (Exception e) {
                log.error("解析TransformEvent中的UserAgent异常,事件内容:{}", record, e);
            }
        }
    }
}