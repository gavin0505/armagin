package cc.forim.armagin.server.action;

import cc.forim.armagin.server.pipeline.BusinessProcess;
import cc.forim.armagin.server.pipeline.TransformContext;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 21:43
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestAction implements BusinessProcess {

    @Override
    public void process(TransformContext context) {
        System.out.println(DateUtil.now());
        System.out.println("TestAction-hashcode:" + hashCode());
    }
}