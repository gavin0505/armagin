package cc.forim.armagin.server.controller;

import cc.forim.armagin.server.enums.TransformEnum;
import cc.forim.armagin.server.pipeline.TransformContext;
import cc.forim.armagin.server.service.UrlTransformService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Objects;
import java.util.Set;

import static org.springframework.web.cors.CorsConfiguration.ALL;

/**
 * 短链映射转换控制器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/15 23:04
 */

@Controller
@RequestMapping("/v")
public class TransformUrlController {

    @Resource(name = "urlTransformServiceImpl")
    private UrlTransformService urlTransformService;

    @GetMapping("/{compressionCode}")
    @ResponseStatus(HttpStatus.FOUND)
    public Mono<Void> redirectExample2(@PathVariable("compressionCode") String compressionCode, ServerWebExchange exchange) {

        ServerHttpRequest request = exchange.getRequest();

        // 构建初始化上下文
        TransformContext context = new TransformContext();
        context.setCompressionCode(compressionCode);
        context.setParam(TransformEnum.SWE.getValue(), exchange);

        // 设置请求主机名
        if (Objects.nonNull(request.getRemoteAddress())) {
            context.setParam(TransformEnum.RHN.getValue(), request.getRemoteAddress().getHostName());
        }
        // 获取请求头
        HttpHeaders httpHeaders = request.getHeaders();
        // 获取请求头的键
        Set<String> headerNames = httpHeaders.keySet();
        // 保存请求头的键-值
        if (!CollectionUtils.isEmpty(headerNames)) {
            headerNames.forEach(headerName -> {
                String headerValue = httpHeaders.getFirst(headerName);
                context.setHeader(headerName, headerValue);
            });
        }

        // 执行责任链，进行短链转换
        urlTransformService.processTransform(context);

        return Mono.fromRunnable(new Thread(() -> {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FOUND);
            response.getHeaders().setLocation(URI.create("https://www.baidu.com"));
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALL);
            response.getHeaders().set(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, Boolean.TRUE.toString());
        }));

//        return Mono.fromRunnable(context.getRedirection());
    }
}