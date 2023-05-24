package cc.forim.armagin.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ApiModelProperty(value = "账号名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "登录类型", required = true)
    private Integer type;
}
