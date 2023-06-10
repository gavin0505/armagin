package cc.forim.armagin.user.infra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 角色枚举
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/10 13:39
 */

@Getter
@ToString
@AllArgsConstructor
public enum RoleEnum {

    /**
     * 超级管理员
     */
    SUPER_ADMIN(1, "超级管理员"),

    /**
     * 管理员
     */
    ADMIN(2, "管理员"),

    /**
     * 用户
     */
    USER(3, "用户");

    /**
     * 角色id
     */
    private final Integer id;

    /**
     * 角色名称
     */
    private final String role;
}
