package cc.forim.armagin.shorturl.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 返回结果状态枚举
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/17 23:44
 */

@Getter
@ToString
@AllArgsConstructor
public enum ResultStatusEnum {

    /**
     * 创建短链接失败
     */
    CREATE_SHORT_URL_FAILED("500", "创建短链接失败");

    private final String code;

    private final String description;
}