package cc.forim.armagin.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "验证码")
    private String code;
}