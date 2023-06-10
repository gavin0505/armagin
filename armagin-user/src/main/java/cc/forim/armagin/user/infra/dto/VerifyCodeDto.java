package cc.forim.armagin.user.infra.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

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

    @NotNull(message = "不能为空")
    @Length(min = 2, max = 60, message = "账号长度：[1, 50]")
    @ApiModelProperty(value = "渠道接收账号", required = true)
    private String account;
}
