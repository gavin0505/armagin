package cc.forim.armagin.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 短链服务需要用到的一些枚举
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 20:17
 */
@Getter
@ToString
@AllArgsConstructor
public enum TransformEnum {

    /**
     * Server_Web_Exchange
     */
    SWE("SWE"),

    /**
     * Remote_Host_Name
     */
    RHN("RHN"),

    /**
     * Cookie
     */
    COOKIE("COOKIE"),

    ;
    private final String value;
}