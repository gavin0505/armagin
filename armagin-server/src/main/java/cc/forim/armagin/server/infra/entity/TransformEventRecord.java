package cc.forim.armagin.server.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

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
public class TransformEventRecord {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(name = "主键", notes = "")
    private Integer id;

    /**
     * 唯一身份标识,SHA-1(客户端IP-UA)
     */
    @ApiModelProperty(name = "唯一身份标识,SHA-1(客户端IP-UA)", notes = "")
    private String uniqueIdentity;

    /**
     * 客户端IP
     */
    @ApiModelProperty(name = "客户端IP", notes = "")
    private String clientIp;

    /**
     * 短链接URL
     */
    @ApiModelProperty(name = "短链接URL", notes = "")
    private String shortUrl;

    /**
     * 长链接URL
     */
    @ApiModelProperty(name = "长链接URL", notes = "")
    private String longUrl;

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
     * 压缩码
     */
    @ApiModelProperty(name = "压缩码", notes = "")
    private String compressionCode;

    /**
     * 记录时间戳
     */
    @ApiModelProperty(name = "记录时间戳", notes = "")
    private Date recordTime;

    /**
     * UA
     */
    @ApiModelProperty(name = "UA", notes = "")
    private String userAgent;

    /**
     * cookie
     */
    @ApiModelProperty(name = "cookie", notes = "")
    private String cookieValue;

    /**
     * URL参数
     */
    @ApiModelProperty(name = "URL参数", notes = "")
    private String queryParam;

    /**
     * 省份
     */
    @ApiModelProperty(name = "省份", notes = "")
    private String province;

    /**
     * 城市
     */
    @ApiModelProperty(name = "城市", notes = "")
    private String city;

    /**
     * 手机型号
     */
    @ApiModelProperty(name = "手机型号", notes = "")
    private String phoneType;

    /**
     * 浏览器类型
     */
    @ApiModelProperty(name = "浏览器类型", notes = "")
    private String browserType;

    /**
     * 浏览器版本号
     */
    @ApiModelProperty(name = "浏览器版本号", notes = "")
    private String browserVersion;

    /**
     * 操作系统型号
     */
    @ApiModelProperty(name = "操作系统型号", notes = "")
    private String osType;

    /**
     * 设备型号
     */
    @ApiModelProperty(name = "设备型号", notes = "")
    private String deviceType;

    /**
     * 操作系统版本号
     */
    @ApiModelProperty(name = "操作系统版本号", notes = "")
    private String osVersion;

    /**
     * 转换状态,1:转换成功,2:转换失败,3:重定向成功,4:重定向失败
     */
    @ApiModelProperty(name = "转换状态,1:转换成功,2:转换失败,3:重定向成功,4:重定向失败", notes = "")
    private Integer transformStatus;

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
    private Integer version;

    /**
     * 软删除标识
     */
    @ApiModelProperty(name = "软删除标识", notes = "")
    private Integer deleted;
}
