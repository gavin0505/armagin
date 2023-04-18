package cc.forim.armagin.shorturl.service.impl;

import cc.forim.armagin.shorturl.dao.CompressionCodeMapper;
import cc.forim.armagin.shorturl.dao.UrlMapMapper;
import cc.forim.armagin.shorturl.infra.entity.CompressionCode;
import cc.forim.armagin.shorturl.infra.entity.UrlMap;
import cc.forim.armagin.shorturl.service.TransactionalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 事务管理实现类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 15:08
 */

@Service("transactionalServiceImpl")
@Slf4j
public class TransactionalServiceImpl implements TransactionalService {

    @Resource(name = "compressionCodeMapper")
    private CompressionCodeMapper compressionCodeMapper;

    @Resource(name = "urlMapMapper")
    private UrlMapMapper urlMapMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveUrlMapAndUpdateCompressCode(UrlMap urlMap, CompressionCode compressionCode) {
        compressionCodeMapper.updateByPrimaryKeySelective(compressionCode);
        urlMapMapper.insert(urlMap);
    }
}