package cc.forim.armagin.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通过Token获取用户信息DTO
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/4 23:50
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("通过Token获取用户信息")
public class GetUserInfoByTokenDto {

    @ApiModelProperty(value = "用户token", required = true)
    private String token;
}