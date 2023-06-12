package cc.forim.armagin.server.infra.entity;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 转换事件记录实体类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/31 14:09
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Accessors(chain = true)
@TableName("transform_event_record")
public class TransformEventRecord implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty(name = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 唯一身份标识,SHA-1(客户端IP-UA)
     */
    @ApiModelProperty(name = "唯一身份标识,SHA-1(客户端IP-UA)")
    @Builder.Default
    private String uniqueIdentity = "";

    /**
     * 客户端IP
     */
    @ApiModelProperty(name = "客户端IP")
    @Builder.Default
    private String clientIp = "";

    /**
     * 短链接URL
     */
    @ApiModelProperty(name = "短链接URL")
    @Builder.Default
    private String shortUrl = "";

    /**
     * 长链接URL
     */
    @ApiModelProperty(name = "长链接URL")
    @Builder.Default
    private String longUrl = "";

    /**
     * 短链摘要
     */
    @ApiModelProperty(name = "短链摘要")
    @Builder.Default
    private String shortUrlDigest = "";

    /**
     * 长链摘要
     */
    @ApiModelProperty(name = "长链摘要")
    @Builder.Default
    private String longUrlDigest = "";

    /**
     * 压缩码
     */
    @ApiModelProperty(name = "压缩码")
    @Builder.Default
    private String compressionCode = "";

    /**
     * 记录时间戳
     */
    @ApiModelProperty(name = "记录时间戳")
    @Builder.Default
    private Date recordTime = DateUtil.date();

    /**
     * UA
     */
    @ApiModelProperty(name = "UA")
    @Builder.Default
    private String userAgent = "";

    /**
     * cookie
     */
    @ApiModelProperty(name = "cookie")
    @Builder.Default
    private String cookieValue = "";

    /**
     * URL参数
     */
    @ApiModelProperty(name = "URL参数")
    @Builder.Default
    private String queryParam = "";

    /**
     * 省份
     */
    @ApiModelProperty(name = "省份")
    @Builder.Default
    private String province = "";

    /**
     * 城市
     */
    @ApiModelProperty(name = "城市")
    @Builder.Default
    private String city = "";

    /**
     * 手机型号
     */
    @ApiModelProperty(name = "手机型号")
    @Builder.Default
    private String phoneType = "";

    /**
     * 浏览器类型
     */
    @ApiModelProperty(name = "浏览器类型")
    @Builder.Default
    private String browserType = "";

    /**
     * 浏览器版本号
     */
    @ApiModelProperty(name = "浏览器版本号")
    @Builder.Default
    private String browserVersion = "";

    /**
     * 操作系统型号
     */
    @ApiModelProperty(name = "操作系统型号")
    @Builder.Default
    private String osType = "";

    /**
     * 设备型号
     */
    @ApiModelProperty(name = "设备型号")
    @Builder.Default
    private String deviceType = "";

    /**
     * 操作系统版本号
     */
    @ApiModelProperty(name = "操作系统版本号")
    @Builder.Default
    private String osVersion = "";

    /**
     * 转换状态,1:转换成功,2:转换失败,3:重定向成功,4:重定向失败
     */
    @ApiModelProperty(name = "转换状态,1:转换成功,2:转换失败,3:重定向成功,4:重定向失败")
    @Builder.Default
    private Integer transformStatus = 0;

    /**
     * 创建者
     */
    @ApiModelProperty(name = "创建者")
    @Builder.Default
    private String creator = "admin";

    /**
     * 更新者
     */
    @ApiModelProperty(name = "更新者")
    @Builder.Default
    private String editor = "admin";

    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间")
    @Builder.Default
    private Date createTime = DateUtil.date();

    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间")
    @Builder.Default
    private Date editTime = DateUtil.date();

    /**
     * 版本号
     */
    @ApiModelProperty(name = "版本号")
    @Builder.Default
    private Long version = 1L;

    /**
     * 软删除标识
     */
    @ApiModelProperty(name = "软删除标识")
    @Builder.Default
    private Integer deleted = 0;
}
