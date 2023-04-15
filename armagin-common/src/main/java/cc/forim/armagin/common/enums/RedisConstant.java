package cc.forim.armagin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Redis键枚举
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/15 23:11
 */
@Getter
@ToString
@AllArgsConstructor
public enum RedisConstant {

    /**
     * 分布式锁title
     */
    DISTRIBUTED_LOCK_PATH_PREFIX("armagin:dl")

    ;

    private final String key;

}