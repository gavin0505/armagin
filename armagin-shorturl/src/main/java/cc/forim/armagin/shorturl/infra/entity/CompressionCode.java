package cc.forim.armagin.shorturl.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 压缩码实体类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 10:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("compression_code")
public class CompressionCode {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String compressionCode;

    private Integer codeStatus;

    private Long domainConfId;

    private String sequenceValue;

    private String strategy;

    private String creator;

    private String editor;

    private Date createTime;

    private Date editTime;

    private Long version;

    private Integer deleted;

}