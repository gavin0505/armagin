package cc.forim.armagin.shorturl.service;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.shorturl.infra.dto.ShortUrlGenerateDto;
import cc.forim.armagin.shorturl.infra.vo.ShortUrlCreationVo;

/**
 * 短链接操作服务
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/17 23:42
 */
public interface ShortUrlService {

    /**
     * 创建短链接业务
     *
     * @param dto 请求生成短链的传输数据
     * @return 短链接创建结果
     */
    ResultVo<ShortUrlCreationVo> createShortUrlBiz(ShortUrlGenerateDto dto);
}