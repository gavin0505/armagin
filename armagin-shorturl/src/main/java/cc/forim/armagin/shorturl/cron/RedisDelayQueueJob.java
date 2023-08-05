package cc.forim.armagin.shorturl.cron;

import cc.forim.armagin.common.utils.RedisUtil;
import cc.forim.armagin.shorturl.dao.UrlMapMapper;
import cc.forim.armagin.shorturl.infra.dto.UrlMapCacheDto;
import cc.forim.armagin.shorturl.infra.enums.CacheKey;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Set;

import static cc.forim.armagin.shorturl.infra.enums.CommonConstant.*;

/**
 * Redis 延迟队列的任务
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/1 22:31
 */

@Component
@EnableScheduling
@Slf4j
@RefreshScope
@ConfigurationProperties(prefix = "armagin")
@Data
public class RedisDelayQueueJob {

    /**
     * 最小score
     */
    private final static double MIN_SCORE = 0.0;

    @Resource(name = "redisUtil")
    private RedisUtil redisUtil;

    private String[] serviceType;

    @Resource
    private UrlMapMapper urlMapMapper;

    /**
     * 每1s执行一次，删除Redis中过期URL映射
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void deleteExpireUrlMap() {
        // 偏移后的当前的时间戳
        Double currentSeconds = Double.parseDouble(
                String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond() - START_TIME)
        );

        // 遍历每个服务类型
        for (String type :
                serviceType) {
            String zSetKey = CacheKey.EXPIRE_ACCESS_CODE_ZSET_PREFIX.getKey() + type + COLON + ZSET;
            String hashKey = CacheKey.ACCESS_CODE_HASH_PREFIX.getKey() + type + COLON + HASH;

            // 从Redis找到已过期的键
            Set<Object> expireKeys = redisUtil.zRangeByScore(zSetKey, MIN_SCORE, currentSeconds);

            if (!expireKeys.isEmpty()) {
                log.info("检测到【服务{}】的过期短链：{}", type, expireKeys);
                // 遍历删除Hash中的过期keys
                // todo 后期可改用流水线优化

                List<Long> expiredUrlMapIds = Lists.newArrayList();
                for (Object expireKey :
                        expireKeys) {

                    // 获取过期UrlMap的id
                    Object objUrlMapCacheDto = redisUtil.hGet(hashKey, expireKey);
                    String jsonUrlMapCacheDto = JSONUtil.toJsonStr(objUrlMapCacheDto);
                    UrlMapCacheDto urlMapCacheDto = JSONUtil.toBean(jsonUrlMapCacheDto, UrlMapCacheDto.class);
                    Long urlMapId = urlMapCacheDto.getId();

                    if (redisUtil.hDel(hashKey, expireKey) > 0 && redisUtil.zSetDel(zSetKey, expireKey) > 0) {
                        expiredUrlMapIds.add(urlMapId);
                        log.info("删除【服务{}】的过期短链：{}", type, expireKey);
                    } else {
                        log.warn("未能删除【服务{}】的过期短链：{}", type, expireKey);
                    }
                }
                // 数据库设置urlMap失效
                urlMapMapper.expiredByIds(expiredUrlMapIds);
            }
        }
    }
}