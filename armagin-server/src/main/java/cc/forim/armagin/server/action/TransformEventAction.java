package cc.forim.armagin.server.action;

import cc.forim.armagin.server.pipeline.BusinessProcess;
import cc.forim.armagin.server.pipeline.TransformContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 【4】转换事件发送流程
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/17 23:02
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TransformEventAction implements BusinessProcess {
    @Override
    public void process(TransformContext context) {

    }
}