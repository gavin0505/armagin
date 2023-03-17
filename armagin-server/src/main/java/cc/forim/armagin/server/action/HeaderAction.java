package cc.forim.armagin.server.action;

import cc.forim.armagin.server.pipeline.BusinessProcess;
import cc.forim.armagin.server.pipeline.TransformContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 【1】Header处理流程
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/17 17:22
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HeaderAction implements BusinessProcess {

    @Override
    public void process(TransformContext context) {
    }
}