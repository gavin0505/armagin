package cc.forim.armagin.user.infra.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用户登录载体
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 23:04
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户登录信息")
public class LoginDto {

    @Pattern(regexp = "^[a-zA-Z0-9_-]{4,16}$", message = "用户名长度4-16位，必须由[字母，数字，下划线，减号]组成")
    @ApiModelProperty(value = "账号名", required = true)
    private String username;

    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$",
            message = "密码长度8-16位，必须包含大小写字母和数字的组合，可以使用特殊字符")
    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @NotNull(message = "不能为空")
    @Range(min = 1, max = 2, message = "登录类型不合法")
    @ApiModelProperty(value = "登录类型", required = true)
    private Integer type;
}
