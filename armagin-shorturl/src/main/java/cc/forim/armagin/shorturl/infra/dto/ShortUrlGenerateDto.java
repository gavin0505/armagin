package cc.forim.armagin.shorturl.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求生成短链的传输数据
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/17 23:24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlGenerateDto {

    /**
     * 本次任务id
     */
    private String requestId;

    /**
     * 短链域名
     */
    private String domain;

    /**
     * 长Url
     */
    private String longUrl;

    /**
     * 短链类别标识
     */
    private String bizType;

    /**
     * 短链保持时间
     */
    private Integer durationTime;

    /**
     * 描述
     */
    private String description;

    /**
     * 用户id
     */
    private Long userId;
}