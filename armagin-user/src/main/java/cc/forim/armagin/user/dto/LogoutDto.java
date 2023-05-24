package cc.forim.armagin.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登出载体
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/23 11:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("用户登出信息")
public class LogoutDto {

    @ApiModelProperty(value = "用户token", required = true)
    private String token;
}