package cc.forim.armagin.server.action;

import cc.forim.armagin.server.infra.enums.CacheKeyEnum;
import cc.forim.armagin.server.infra.enums.TransformEnum;
import cc.forim.armagin.server.infra.enums.TransformStatus;
import cc.forim.armagin.server.infra.exceptions.RedirectToErrorPageException;
import cc.forim.armagin.server.pipeline.BusinessProcess;
import cc.forim.armagin.server.pipeline.TransformContext;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.server.ServerWebExchange;

import javax.annotation.Resource;

import static cc.forim.armagin.server.infra.enums.CommonConstant.PROTOCOL_SP;

/**
 * 【2】Url映射处理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/17 22:46
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Slf4j
public class UrlTransformAction implements BusinessProcess {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public void process(TransformContext context) {

        // 通过上下文获取请求
        ServerWebExchange exchange = context.getParam(TransformEnum.SWE.getValue());

        // 截取的字符串："/业务类型/{compressionCode}"
        String[] path = exchange.getRequest().getURI().getPath().trim().split(PROTOCOL_SP);
        Assert.hasLength(path[1], "path[1]一定有长度，且是一个字母");
        // 因为path[0]为""，因此path[1]为初值，即业务类型
        String alpha = path[1];

        // 组建Redis的键名
        String key = CacheKeyEnum.LUM.getKey() + alpha;
        // 获取压缩码
        String compressionCode = context.getCompressionCode();
        // 在Redis映射压缩码获取长链接
        String longUrl = String.valueOf(redisTemplate.opsForHash().get(key, compressionCode));

        // 获取短链接
        String shortUrlKey = CacheKeyEnum.SUM.getKey() + alpha;
        String shortUrl = String.valueOf(redisTemplate.opsForHash().get(shortUrlKey, compressionCode));

        context.setTransformStatus(TransformStatus.TRANSFORM_FAIL);

        if (!StrUtil.equals(shortUrl, TransformEnum.NULL.getValue())) {
            context.setParam(TransformEnum.SU.getValue(), shortUrl);
        }

        if (!StrUtil.equals(longUrl, TransformEnum.NULL.getValue())) {
            context.setParam(TransformEnum.LU.getValue(), longUrl);
            context.setTransformStatus(TransformStatus.TRANSFORM_SUCCESS);
        } else {
            log.info("短链压缩码有误: {}", compressionCode);
            throw new RedirectToErrorPageException(String.format("[c:%s]", compressionCode));
        }
    }
}