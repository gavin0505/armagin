package cc.forim.armagin.shorturl.infra.lock;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

/**
 * 分布式锁工厂
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/26 23:04
 */

@Component
@RequiredArgsConstructor
public class DistributedLockFactory {

    private static final String DISTRIBUTED_LOCK_PATH_PREFIX = "dl:";
    private final RedissonClient redissonClient;

    /**
     * 提供分布式锁
     *
     * @param lockKey lockKey
     * @return DistributedLock
     */
    public DistributedLock provideDistributedLock(String lockKey) {
        String lockPath = DISTRIBUTED_LOCK_PATH_PREFIX + lockKey;
        return new RedissonDistributedLock(redissonClient, lockPath);
    }
}