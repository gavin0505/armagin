package cc.forim.armagin.server.infra.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 分布式锁KEY
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 09:39
 */

@RequiredArgsConstructor
@Getter
public enum LockKeyEnum {

    /**
     * 访问统计任务
     */
    VISITOR_STATS_TASK("armagin:server:access:code", "访问统计任务", 0L, 10000L),
    ;

    private final String code;
    private final String desc;
    private final long waitTime;
    private final long releaseTime;
}