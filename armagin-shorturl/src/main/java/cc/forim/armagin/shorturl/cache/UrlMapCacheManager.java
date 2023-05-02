package cc.forim.armagin.shorturl.cache;

import cc.forim.armagin.common.utils.RedisUtil;
import cc.forim.armagin.shorturl.infra.dto.UrlMapCacheDto;
import cc.forim.armagin.shorturl.infra.entity.UrlMap;
import cc.forim.armagin.shorturl.infra.enums.CacheKey;
import cc.forim.armagin.shorturl.infra.enums.UrlMapStatus;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.function.Function;

import static cc.forim.armagin.shorturl.infra.enums.CommonConstant.*;

/**
 * UrlMap缓存管理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 14:34
 */

@Component
public class UrlMapCacheManager {

    /**
     * 时间戳（秒）：2023-01-01 00:00:00
     */
    private final static long START_TIME = 1672502400L;

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    /**
     * 刷新缓存中的UrlMap
     *
     * @param urlMap 新的urlMap
     */
    public void refreshUrlMapCache(UrlMap urlMap) {
        if (ObjectUtil.isNotEmpty(urlMap)) {
            refreshUrlMapCache(function.apply(urlMap));
        }
    }

    private void refreshUrlMapCache(UrlMapCacheDto dto) {
        // 格式：armagin:server:access:code:业务类型:hash
        redisUtil.hSet(CacheKey.ACCESS_CODE_HASH_PREFIX.getKey() + dto.getBizType() + COLON + HASH, dto.getCompressionCode(), dto);

        // LocalDateTime转换
        LocalDateTime localDateTime = LocalDateTimeUtil.of(dto.getExpireTime());

        // 计算过期时长
        Double score = Double.parseDouble(
                String.valueOf(localDateTime.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond() - START_TIME)
        );
        // Redis模拟延迟队列，加入对应hash的field的过期时间，格式：armagin:server:expire:access:code:业务类型:zset
        redisUtil.zAdd(CacheKey.EXPIRE_ACCESS_CODE_ZSET_PREFIX.getKey() + dto.getBizType() + COLON + ZSET, dto.getCompressionCode(), score);
    }

    /**
     * 转换工具：UrlMap -> UrlMapCacheDto
     */
    private final Function<UrlMap, UrlMapCacheDto> function = urlMap -> {
        UrlMapCacheDto urlMapCacheDto = new UrlMapCacheDto();

        urlMapCacheDto.setId(urlMap.getId());
        urlMapCacheDto.setDescription(urlMap.getDescription());
        urlMapCacheDto.setLongUrl(urlMap.getLongUrl());
        urlMapCacheDto.setShortUrl(urlMap.getShortUrl());
        urlMapCacheDto.setCompressionCode(urlMap.getCompressionCode());
        urlMapCacheDto.setBizType(urlMap.getBizType());
        urlMapCacheDto.setEnable(UrlMapStatus.AVAILABLE.getValue().equals(urlMap.getUrlStatus()));
        urlMapCacheDto.setExpireTime(urlMap.getExpireTime());

        return urlMapCacheDto;
    };
}