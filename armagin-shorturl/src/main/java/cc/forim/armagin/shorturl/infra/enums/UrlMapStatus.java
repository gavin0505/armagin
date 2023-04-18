package cc.forim.armagin.shorturl.infra.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * UrlMap可用状态
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 16:55
 */
@RequiredArgsConstructor
@Getter
public enum UrlMapStatus {

    /**
     * 可用状态
     */
    AVAILABLE(1),

    /**
     * 非法状态
     */
    INVALID(2),

    ;

    private final Integer value;
}