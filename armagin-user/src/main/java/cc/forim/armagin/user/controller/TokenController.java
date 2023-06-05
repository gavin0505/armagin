package cc.forim.armagin.user.controller;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.dto.GetUserInfoByTokenDto;
import cc.forim.armagin.user.service.TokenService;
import cc.forim.armagin.user.vo.UserInfoVo;
import io.swagger.annotations.Api;
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

    @PostMapping("/getUserInfoByToken")
    public ResultVo<UserInfoVo> getUserInfoByToken(@RequestBody GetUserInfoByTokenDto getUserInfoByTokenDto) {
        return tokenService.getUserInfoByToken(getUserInfoByTokenDto);
    }
}