package cc.forim.armagin.user.service;

import cc.forim.armagin.user.infra.dto.RegisterMsgDto;

/**
 * 事务管理接口
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/6/6 21:02
 */
public interface TransactionService {

    /**
     * 保存新用户注册数据
     *
     * @param registerMsgDto 注册数据
     */
    void saveNewRegisterAccount(RegisterMsgDto registerMsgDto);
}
