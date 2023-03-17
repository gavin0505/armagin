package cc.forim.armagin.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/14 21:15
 */

@RestController
@RequestMapping("/h")
public class HelloController {

    @GetMapping("/hi")
    public String hi() {
        return "hi, web";
    }
}
