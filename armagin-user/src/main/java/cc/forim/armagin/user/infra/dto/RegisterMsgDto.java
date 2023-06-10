package cc.forim.armagin.user.infra.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 注册信息载体
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 22:45
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("注册信息")
public class RegisterMsgDto {

    @Pattern(regexp = "^[A-Za-z0-9\\-_]+[A-Za-z0-9\\.\\-_]*[A-Za-z0-9\\-_]+@[A-Za-z0-9]+[A-Za-z0-9\\.\\-_]*(\\.[A-Za-z0-9\\.\\-_]+)*[A-Za-z0-9]+\\.[A-Za-z0-9]+[A-Za-z0-9\\.\\-_]*[A-Za-z0-9]+$",
            message = "正确的邮箱格式：xxx@example.com")
    @ApiModelProperty(value = "邮箱", required = true)
    private String email;

    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$", message = "用户名长度4-16位，必须由[字母，数字，下划线，减号]组成")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$",
            message = "密码长度8-16位，必须包含大小写字母和数字的组合，可以使用特殊字符")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotNull(message = "不能为空")
    @Length(min = 6, max = 6, message = "验证码必须为6位")
    @ApiModelProperty(value = "验证码")
    private String code;
}