package cc.forim.armagin.server.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/29 23:51
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Accessors(chain = true)
@TableName("visit_statistics")
public class VisitStatistics {

    @ApiModelProperty(name = "主键", notes = "")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 统计日期
     */
    @ApiModelProperty(name = "统计日期", notes = "")
    private LocalDate statisticsDate;

    /**
     * 页面流量数
     */
    @ApiModelProperty(name = "页面流量数", notes = "")
    private Long pvCount;

    /**
     * 独立访问数
     */
    @ApiModelProperty(name = "独立访问数", notes = "")
    private Long uvCount;

    /**
     * 独立IP数
     */
    @ApiModelProperty(name = "独立IP数", notes = "")
    private Long ipCount;

    /**
     * 有效跳转数
     */
    @ApiModelProperty(name = "有效跳转数", notes = "")
    private Long effectiveRedirectionCount;

    /**
     * 无效跳转数
     */
    @ApiModelProperty(name = "无效跳转数", notes = "")
    private Long ineffectiveRedirectionCount;

    /**
     * 压缩码
     */
    @ApiModelProperty(name = "压缩码", notes = "")
    private String compressionCode;

    /**
     * 短链摘要
     */
    @ApiModelProperty(name = "短链摘要", notes = "")
    private String shortUrlDigest;

    /**
     * 长链摘要
     */
    @ApiModelProperty(name = "长链摘要", notes = "")
    private String longUrlDigest;

    /**
     * 创建者
     */
    @ApiModelProperty(name = "创建者", notes = "")
    private String creator;

    /**
     * 更新者
     */
    @ApiModelProperty(name = "更新者", notes = "")
    private String editor;

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间", notes = "")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间", notes = "")
    private Date editTime;

    /**
     * 版本号
     */
    @ApiModelProperty(name = "版本号", notes = "")
    private Long version;

    /**
     * 软删除标识
     */
    @ApiModelProperty(name = "软删除标识", notes = "")
    private Integer deleted;
}