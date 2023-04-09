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
     * 长链映射前缀
     */
    LUM("armagin:server:lum:"),

    /**
     * 短链映射前缀
     */
    SUM("armagin:server:sum:"),

    /**
     * 转换事件记录的临时缓存
     */
    TER_TEMP_CACHE_LIST("armagin:server:tcl"),

    /**
     * 黑名单列表
     */
    BLACK_IP("armagin:server:black:ip")
    ;
    private final String key;
}