package cc.forim.armagin.user.controller;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.infra.dto.GetUserInfoByTokenDto;
import cc.forim.armagin.user.service.TokenService;
import cc.forim.armagin.user.infra.vo.UserInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/4 21:57
 */

@RestController
@Api(tags = "Token管理 API")
@RequestMapping("/token")
public class TokenController {

    @Resource(name = "tokenServiceImpl")
    private TokenService tokenService;

    @ApiOperation("通过Token获取用户信息")
    @PostMapping("/getUserInfoByToken")
    public ResultVo<UserInfoVo> getUserInfoByToken(@RequestBody GetUserInfoByTokenDto getUserInfoByTokenDto) {
        return tokenService.getUserInfoByToken(getUserInfoByTokenDto);
    }
}