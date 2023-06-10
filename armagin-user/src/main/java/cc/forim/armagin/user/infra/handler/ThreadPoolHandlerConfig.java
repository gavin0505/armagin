package cc.forim.armagin.user.infra.handler;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置类
 *
 * @author Gavin Zhang
 * @date 2023/1/3 16:11
 */
public class ThreadPoolHandlerConfig {

    /**
     * 线程池配置
     */
    public static ThreadPoolExecutor getExecutor() {
        return new ThreadPoolExecutor(
                2,
                2,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(128),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }
}
