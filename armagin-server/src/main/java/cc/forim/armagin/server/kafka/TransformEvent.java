package cc.forim.armagin.server.kafka;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 转换事件实体
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/27 14:27
 */

@Data
@Builder
@ToString
public class TransformEvent {

    /**
     * 客户端IP
     */
    private String clientIp;

    /**
     * 压缩码
     */
    private String compressionCode;

    /**
     * user-agent
     */
    private String userAgent;

    /**
     * cookie
     */
    private String cookieValue;

    /**
     * 记录时间戳
     */
    private Long timestamp;

    /**
     * 短链字符串
     */
    private String shortUrlString;

    /**
     * 长链字符串
     */
    private String longUrlString;

    /**
     * 转换状态值
     */
    private Integer transformStatusValue;
}