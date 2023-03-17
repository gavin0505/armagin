package cc.forim.armagin.server.pipeline;

import cc.forim.armagin.server.pipeline.config.PipelineConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * 责任链控制器
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 20:59
 */

@Slf4j
@Data
public class ProcessController {

    @Resource
    private PipelineConfig pipelineConfig;

    /**
     * 执行责任链
     *
     * @param context 责任链上下文
     */
    public void transform(TransformContext context) {

        /*
         * 遍历流程节点
         */
        List<BusinessProcess> processList = pipelineConfig.urlTransformProcessTemplate().getProcessList();
        for (BusinessProcess businessProcess : processList) {
            businessProcess.process(context);
            if (context.getNeedBreak()) {
                break;
            }
        }
    }
}