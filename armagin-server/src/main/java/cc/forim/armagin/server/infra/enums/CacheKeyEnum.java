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
     * 映射前缀
     */
    UM("armagin:server:um:");
    private final String key;
}