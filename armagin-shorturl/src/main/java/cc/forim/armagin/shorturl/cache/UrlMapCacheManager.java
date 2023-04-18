package cc.forim.armagin.shorturl.cache;

import cc.forim.armagin.common.utils.RedisUtil;
import cc.forim.armagin.shorturl.infra.dto.UrlMapCacheDto;
import cc.forim.armagin.shorturl.infra.entity.UrlMap;
import cc.forim.armagin.shorturl.infra.enums.CacheKey;
import cc.forim.armagin.shorturl.infra.enums.UrlMapStatus;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;

/**
 * UrlMap缓存管理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 14:34
 */

@Component
public class UrlMapCacheManager {
    private static final String HASH = "hash";

    private static final String COLON = ":";

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

        return urlMapCacheDto;
    };
}