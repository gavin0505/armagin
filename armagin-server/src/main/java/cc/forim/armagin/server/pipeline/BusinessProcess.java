package cc.forim.armagin.server.pipeline;

/**
 * 业务流程接口
 *
 * <p>所有责任链处理模块都应该实现该接口</p>
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 21:01
 */
public interface BusinessProcess {

    /**
     * 真正处理逻辑
     *
     * @param context 责任链上下文
     */
    void process(TransformContext context);
}