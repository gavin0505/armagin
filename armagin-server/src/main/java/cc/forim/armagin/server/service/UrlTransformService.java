package cc.forim.armagin.server.service;

import cc.forim.armagin.server.pipeline.TransformContext;

/**
 * 短链转换服务
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/16 20:42
 */
public interface UrlTransformService {

    /**
     * 短链转换处理
     *
     * @param context 短链上下文
     */
    void processTransform(TransformContext context);
}