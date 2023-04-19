package cc.forim.armagin.server.infra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Redis缓存键枚举
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/19 13:25
 */

@Getter
@ToString
@AllArgsConstructor
public enum CacheKeyEnum {

    /**
     * 可访问的压缩码映射前缀
     */
    ACCESS_CODE_HASH_PREFIX("armagin:server:access:code:"),

    /**
     * 转换事件记录的临时缓存
     */
    TER_TEMP_CACHE_LIST("armagin:server:tcl"),

    /**
     * 黑名单列表
     */
    BLACK_IP_SET("armagin:server:block:ip:set")
    ;
    private final String key;
}