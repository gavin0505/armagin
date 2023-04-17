package cc.forim.armagin.shorturl.service.impl;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.shorturl.infra.ResultStatusEnum;
import cc.forim.armagin.shorturl.infra.dto.ShortUrlGenerateDto;
import cc.forim.armagin.shorturl.infra.vo.ShortUrlCreationVo;
import cc.forim.armagin.shorturl.service.ShortUrlService;
import org.springframework.stereotype.Service;

/**
 * 短链接操作服务实现类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/17 23:42
 */
@Service("shortUrlServiceImpl")
public class ShortUrlServiceImpl implements ShortUrlService {

    @Override
    public ResultVo<ShortUrlCreationVo> createShortUrl(ShortUrlGenerateDto dto) {

        return ResultVo.error(ResultStatusEnum.CREATE_SHORT_URL_FAILED.getCode(), ResultStatusEnum.CREATE_SHORT_URL_FAILED.getDescription());
    }
}
