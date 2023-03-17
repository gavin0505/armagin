package cc.forim.armagin.server.pipeline.config;

import cc.forim.armagin.server.action.HeaderAction;
import cc.forim.armagin.server.action.RedirectUrlAction;
import cc.forim.armagin.server.action.TransformEventAction;
import cc.forim.armagin.server.action.UrlTransformAction;
import cc.forim.armagin.server.pipeline.ProcessTemplate;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 责任链流程装配
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 20:59
 */
@Component
public class PipelineConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 短链处理流程
     */
    public ProcessTemplate urlTransformProcessTemplate() {
        HeaderAction headerAction = applicationContext.getBean(HeaderAction.class);
        UrlTransformAction urlTransformAction = applicationContext.getBean(UrlTransformAction.class);
        RedirectUrlAction redirectUrlAction = applicationContext.getBean(RedirectUrlAction.class);
        TransformEventAction transformEventAction = applicationContext.getBean(TransformEventAction.class);

        ProcessTemplate processTemplate = new ProcessTemplate();
        // 组装责任链
        processTemplate.setProcessList(Arrays.asList(headerAction, urlTransformAction, redirectUrlAction, transformEventAction));
        return processTemplate;
    }
}