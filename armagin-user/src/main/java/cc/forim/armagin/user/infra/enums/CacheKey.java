package cc.forim.armagin.user.infra.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 缓存KEY
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/27 22:56
 */

@RequiredArgsConstructor
@Getter
public enum CacheKey {

    /**
     * 用户注册验证码前缀
     */
    USER_REGISTER_VERIFY_CODE_STRING_PREFIX("armagin:user:register:verify:", "注册验证码", 120),

    ;

    private final String key;
    private final String description;
    private final long expireSeconds;
}
