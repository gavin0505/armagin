package cc.forim.armagin.shorturl.infra.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 缓存KEY
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 16:56
 */
@RequiredArgsConstructor
@Getter
public enum CacheKey {

    /**
     * 客户端黑名单列表
     */
    BLOCK_IP_SET("armagin:server:block:ip:set", "禁用的客户端IP", -1L),

    /**
     * 可访问短链域名白名单列表
     */
    ACCESS_DOMAIN_SET("armagin:server:access:domain:set", "启用的短链域名", -1L),

    /**
     * 可访问的压缩码映射前缀
     */
    ACCESS_CODE_HASH_PREFIX("armagin:server:access:code:", "可访问的压缩码映射", -1L),

    /**
     * 可访问的压缩码映射的过期时间前缀
     */
    EXPIRE_ACCESS_CODE_ZSET_PREFIX("armagin:server:expire:access:code:", "可访问的压缩码映射", -1L);

    private final String key;
    private final String description;
    private final long expireSeconds;
}