package cc.forim.armagin.common.lock;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static cc.forim.armagin.common.enums.RedisConstant.DISTRIBUTED_LOCK_PATH_PREFIX;

/**
 * 分配分布式锁的工厂
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/14 10:33
 */
@Component
@RequiredArgsConstructor
public class DistributeLockFactory {

    private static final String COLON = ":";
    @Resource
    private RedissonClient redissonClient;

    public DistributedLock provideDistributedLock(String lockKey) {

        // 分布式锁key
        String lockPath = DISTRIBUTED_LOCK_PATH_PREFIX.getKey() + COLON + lockKey;
        return new RedissonDistributedLock(redissonClient, lockPath);
    }
}