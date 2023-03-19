package cc.forim.armagin.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 错误页面控制器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/19 14:27
 */
@RestController
public class ErrorController {

    @GetMapping("/error")
    public String error() {
        return "出错了！您访问的地址无效";
    }
}