package cc.forim.armagin.shorturl.infra.dto;

import lombok.Data;

import java.util.Date;


/**
 * 内部流转UrlMap -> Cache
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 16:50
 */

@Data
public class UrlMapCacheDto {

    private Long id;

    private String shortUrl;

    private String longUrl;

    private String compressionCode;

    private Date expireTime;

    private String bizType;

    private String description;

    private Boolean enable;

    public Long userId;
}