package cc.forim.armagin.shorturl.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.OffsetDateTime;

/**
 * URL映射实体类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 13:50
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("url_map")
public class UrlMap {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String shortUrl;

    private String longUrl;

    private String shortUrlDigest;

    private String longUrlDigest;

    private String compressionCode;

    private String description;

    private Integer urlStatus;

    private String creator;

    private String editor;

    private OffsetDateTime createTime;

    private OffsetDateTime editTime;

    private Long version;

    private Integer deleted;
}
