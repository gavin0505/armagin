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
     * 长Url
     */
    private String longUrl;

    /**
     * 短链保持时间
     */
    private String durationTime;

    /**
     * 描述
     */
    private String description;
}