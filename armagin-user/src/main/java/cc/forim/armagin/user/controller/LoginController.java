package cc.forim.armagin.user.controller;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.dto.LoginDto;
import cc.forim.armagin.user.dto.LogoutDto;
import cc.forim.armagin.user.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 登录控制器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 23:02
 */

@RestController
@Api(tags = "登录 API")
@RequestMapping("/login")
public class LoginController {

    @Resource(name = "accountServiceImpl")
    private AccountService accountService;

    @ApiOperation("登录")
    @PostMapping("/doLogin")
    public ResultVo<String> login(@RequestBody LoginDto loginDto) {
        return accountService.login(loginDto);
    }

    @ApiOperation("登出")
    @PostMapping("/doLogout")
    public ResultVo<String> logout(@RequestBody LogoutDto logoutDto) {
        return accountService.logout(logoutDto);
    }
}