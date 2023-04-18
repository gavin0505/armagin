package cc.forim.armagin.shorturl.cache;

import cc.forim.armagin.shorturl.dao.UrlMapMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * UrlMap缓存管理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 14:34
 */

@Component
public class UrlMapCacheManager {

    @Resource(name = "urlMapMapper")
    private UrlMapMapper urlMapMapper;
}