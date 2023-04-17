package cc.forim.armagin.shorturl.controller;

import cc.forim.armagin.common.ResultVo;
import cc.forim.armagin.shorturl.infra.dto.ShortUrlGenerateDto;
import cc.forim.armagin.shorturl.infra.vo.ShortUrlCreationVo;
import cc.forim.armagin.shorturl.service.ShortUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 短链接操作服务接口
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/17 23:09
 */

@Api(tags = "短链接操作服务接口")
@RestController
@RequestMapping("/shortUrl")
public class ShortUrlController {

    @Resource(name = "shortUrlServiceImpl")
    private ShortUrlService shortUrlService;

    @ApiOperation("创建短URL接口")
    @PostMapping("/createShortUrl")
    public ResultVo<ShortUrlCreationVo> createShortUrl(@RequestBody ShortUrlGenerateDto dto) {
        return shortUrlService.createShortUrl(dto);
    }
}