package cc.forim.armagin.shorturl.infra.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 压缩码状态
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 13:18
 */
@RequiredArgsConstructor
@Getter
public enum CompressionCodeStatus {

    /**
     * 可用
     */
    AVAILABLE(1),

    /**
     * 已经使用
     */
    USED(2),

    /**
     * 已失效
     */
    INVALID(3),

    ;

    private final Integer value;
}