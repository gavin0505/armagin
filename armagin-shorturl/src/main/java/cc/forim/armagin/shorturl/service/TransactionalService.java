package cc.forim.armagin.shorturl.service;

import cc.forim.armagin.shorturl.infra.entity.CompressionCode;
import cc.forim.armagin.shorturl.infra.entity.UrlMap;

/**
 * 事务管理
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 15:06
 */

public interface TransactionalService {

    /**
     * 保存短链映射和更新压缩码状态
     *
     * @param urlMap          url映射
     * @param compressionCode 压缩码
     */
    void saveUrlMapAndUpdateCompressCode(UrlMap urlMap, CompressionCode compressionCode);

}