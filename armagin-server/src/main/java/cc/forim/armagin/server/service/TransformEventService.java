package cc.forim.armagin.server.service;

import cc.forim.armagin.server.infra.entity.TransformEventRecord;

/**
 * 转换事件记录服务
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/28 11:08
 */
public interface TransformEventService {

    /**
     * 事件转换
     *
     * @param record 转换事件记录的实体
     */
    void recordTransform(TransformEventRecord record);
}