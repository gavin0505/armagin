package cc.forim.armagin.user.infra.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.same.SaSameUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册 Sa-Token 全局过滤器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/24 22:11
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                // 放行swagger3
                .addExclude("/swagger**/**", "/webjars/**", "/v3/**",
                        "/doc.html/**", "/error", "/favicon.ico")
                .setAuth(obj -> {
                    // 校验 Same-Token 身份凭证     —— 以下两句代码可简化为：SaSameUtil.checkCurrentRequestToken();
                    String token = SaHolder.getRequest().getHeader(SaSameUtil.SAME_TOKEN);
                    SaSameUtil.checkToken(token);
                })
                .setError(e -> {
                    return SaResult.error(e.getMessage());
                })
                ;
    }
}