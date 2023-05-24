package cc.forim.armagin.user.service.impl;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.dao.UserMapper;
import cc.forim.armagin.user.dto.LoginDto;
import cc.forim.armagin.user.dto.LogoutDto;
import cc.forim.armagin.user.dto.RegisterMsgDto;
import cc.forim.armagin.user.service.AccountService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * 账户服务实现类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 22:48
 */

@Service("accountServiceImpl")
@Slf4j
public class AccountServiceImpl implements AccountService {

    private static final int LOGIN_BY_USERNAME = 1;

    private static final int NULL = 0;

    @Resource
    private UserMapper userMapper;

    @Override
    public ResultVo<String> register(RegisterMsgDto registerMsgDto) {
        return null;
    }

    @Override
    public ResultVo<String> login(LoginDto loginDto) {

        // 通过Username登录
        if (loginDto.getType() == LOGIN_BY_USERNAME) {
            // 获取账号id
            Integer id = userMapper.login(loginDto.getUsername(), loginDto.getPassword());
            Optional<Integer> optionalValue = Optional.ofNullable(id);
            id = optionalValue.orElse(NULL);
            // 账号存在即登录
            if (id > NULL) {
                // todo 查找账号所属权限

                // todo sa-token的登录[使用相应权限的Stp模板]
                StpUtil.login(id);
                // 获取 Token
                String token = StpUtil.getTokenInfo().getTokenValue();
                // 更新最后登录时间
                userMapper.updateLogged(id, DateUtil.date());
                log.info("用户id: {} 登录成功", id);
                return ResultVo.success(token);
            }
        }
        return ResultVo.error("用户名或密码错误");
    }

    @Override
    public ResultVo<String> logout(LogoutDto logoutDto) {

        // 通过token获取用户id
        Object id = StpUtil.getLoginIdByToken(logoutDto.getToken());
        if (ObjectUtil.isNull(id)) {
            return ResultVo.error("token错误");
        }
        // sa-token登出
        StpUtil.logout(id);
        log.info("用户id: {} 登出成功", id);
        return ResultVo.success("用户登出成功！");
    }
}