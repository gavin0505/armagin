package cc.forim.armagin.shorturl.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.time.OffsetDateTime;

/**
 * 域名配置实体类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 13:36
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("domain_conf")
public class DomainConf {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String domainValue;

    private String protocol;

    private String bizType;

    private Integer domainStatus;

    private OffsetDateTime createTime;

    private OffsetDateTime editTime;

    private String creator;

    private String editor;

    private Long version;

    private Integer deleted;

}
