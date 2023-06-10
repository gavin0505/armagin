package cc.forim.armagin.user.service.impl;

import cc.forim.armagin.user.dao.UserMapper;
import cc.forim.armagin.user.infra.dto.RegisterMsgDto;
import cc.forim.armagin.user.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static cc.forim.armagin.user.infra.enums.RoleEnum.USER;

/**
 * 事务管理服务
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/6 21:03
 */

@Service("transactionServiceImpl")
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Resource
    private UserMapper userMapper;

    private static final int NULL = 0;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveNewRegisterAccount(RegisterMsgDto registerMsgDto) {

        if (userMapper.insertUser(registerMsgDto) > NULL) {
            Integer id = userMapper.selectIdByUsername(registerMsgDto.getUsername());
            // 默认分配user角色
            userMapper.insertUserRole(id, USER.getId());
        }
    }
}