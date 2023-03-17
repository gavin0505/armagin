package cc.forim.armagin.server.service.impl;

import cc.forim.armagin.server.pipeline.ProcessController;
import cc.forim.armagin.server.pipeline.TransformContext;
import cc.forim.armagin.server.service.UrlTransformService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 短链转换服务实现类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 21:18
 */

@Service("urlTransformServiceImpl")
@Slf4j
public class UrlTransformServiceImpl implements UrlTransformService, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void processTransform(TransformContext context) {
        // todo 构建责任链上下文
        context.setNeedBreak(false);
        ProcessController processController = applicationContext.getBean(ProcessController.class);
        processController.transform(context);
    }
}