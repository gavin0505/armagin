package cc.forim.armagin.user.infra.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 登录信息VO
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/4 22:05
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("登录信息VO")
public class UserInfoVo {

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "角色", required = true)
    private List<String> role;
}