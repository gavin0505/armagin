package cc.forim.armagin.user.controller;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.dto.RegisterMsgDto;
import cc.forim.armagin.user.dto.VerifyCodeDto;
import cc.forim.armagin.user.service.AccountService;
import cc.forim.armagin.user.service.VerifyCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 注册控制器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 22:41
 */

@Api(tags = "注册 API")
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource(name = "accountServiceImpl")
    private AccountService accountService;

    @Resource(name = "verifyCodeServiceImpl")
    private VerifyCodeService verifyCodeService;

    @ApiOperation("注册")
    @PostMapping("/doRegister")
    public ResultVo<String> register(@RequestBody RegisterMsgDto registerMsgDto) {
        return accountService.register(registerMsgDto);
    }

    @ApiOperation("获取验证码")
    @PostMapping("/getVerifyCode")
    public ResultVo<String> getVerifyCode(@RequestBody VerifyCodeDto verifyCodeDto) {
        return verifyCodeService.getVerifyCode(verifyCodeDto);
    }
}