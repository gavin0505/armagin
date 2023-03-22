package cc.forim.armagin.server.infra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * HttpHeader的枚举
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/21 22:58
 */

@Getter
@AllArgsConstructor
@ToString
public enum HttpHeaderEnum {

    /**
     * User-Agent
     */
    UA("User-Agent"),
    /**
     * Cookies
     */
    COOKIES("Cookies")

    ;
    private final String value;
}