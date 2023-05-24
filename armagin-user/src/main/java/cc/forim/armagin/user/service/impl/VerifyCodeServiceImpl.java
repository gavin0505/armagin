package cc.forim.armagin.user.service.impl;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.user.dto.VerifyCodeDto;
import cc.forim.armagin.user.service.VerifyCodeService;
import org.springframework.stereotype.Service;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/23 22:32
 */

@Service("verifyCodeServiceImpl")
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Override
    public ResultVo<String> getVerifyCode(VerifyCodeDto verifyCodeDto) {
        return null;
    }
}