package cc.forim.armagin.shorturl.infra.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/26 23:03
 */
public interface DistributedLock {

    /**
     * 阻塞加锁
     *
     * @param leaseTime leaseTime
     * @param unit      unit
     */
    void lock(long leaseTime, TimeUnit unit);

    /**
     * 尝试加锁
     *
     * @param waitTime  waitTime
     * @param leaseTime leaseTime
     * @param unit      unit
     * @return boolean
     */
    boolean tryLock(long waitTime, long leaseTime, TimeUnit unit);

    /**
     * 解锁
     */
    void unlock();

    /**
     * 是否锁定
     *
     * @return boolean
     */
    boolean isLock();

    /**
     * 当前线程是否持有锁
     *
     * @return boolean
     */
    boolean isHeldByCurrentThread();
}
