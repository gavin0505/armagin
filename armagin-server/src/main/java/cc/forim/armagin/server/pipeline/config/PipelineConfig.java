package cc.forim.armagin.server.pipeline.config;

import cc.forim.armagin.server.action.TestAction;
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
        TestAction testAction = applicationContext.getBean(TestAction.class);
        ProcessTemplate processTemplate = new ProcessTemplate();
        // todo 组装短链处理
        processTemplate.setProcessList(Arrays.asList(testAction));
        return processTemplate;
    }
}