package cc.forim.armagin.shorturl.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/11 23:57
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        // 创建 Docket 对象
        // 文档类型，使用 Swagger2
        return new Docket(DocumentationType.OAS_30)
                // 设置 API 信息
                .apiInfo(this.apiInfo())
                // 扫描 Controller 包路径，获得 API 接口
                .select()
                .apis(RequestHandlerSelectors.basePackage("cc.forim.armagin.shorturl.controller"))
                .paths(PathSelectors.any())
                // 构建出 Docket 对象
                .build();
    }

    /**
     * 创建 API 信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("shorturl接口文档示例")
                .description("mp")
                // 版本号
                .version("1.0.0")
                // 联系人
                .contact(new Contact("apdo", "https://www.zxb996.icu", "zxb_worky@163.com"))
                .build();
    }
}