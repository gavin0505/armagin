package cc.forim.armagin.server.infra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 短链转换状态
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/18 23:18
 */

@Getter
@ToString
@AllArgsConstructor
public enum TransformStatus {

    /**
     * 转换成功
     */
    TRANSFORM_SUCCESS(1),

    /**
     * 转换失败
     */
    TRANSFORM_FAIL(2),

    /**
     * 重定向成功
     */
    REDIRECTION_SUCCESS(3),

    /**
     * 重定向失败
     */
    REDIRECTION_FAIL(4),

    ;
    private final Integer code;
}