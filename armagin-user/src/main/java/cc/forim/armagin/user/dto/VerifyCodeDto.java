package cc.forim.armagin.user.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册验证码载体
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 22:54
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("注册验证码信息")
public class VerifyCodeDto {

    @ApiModelProperty(value = "获取验证码的渠道: 1-邮箱；2-手机号", required = true)
    private Integer type;

    @ApiModelProperty(value = "渠道接收账号", required = true)
    private String account;
}
