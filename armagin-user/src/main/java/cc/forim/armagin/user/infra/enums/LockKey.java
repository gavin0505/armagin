package cc.forim.armagin.user.infra.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 锁枚举
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/6 21:20
 */
@RequiredArgsConstructor
@Getter
public enum LockKey {

    /**
     * 创建新账号
     */
    REGISTER_NEW_ACCOUNT("armagin:user:account:create", "创建新账号", 0L, 10000L),

    ;

    private final String code;
    private final String desc;
    private final long waitTime;
    private final long releaseTime;
}