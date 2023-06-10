package cc.forim.armagin.user.infra.pending;

import cc.forim.armagin.user.infra.handler.ThreadPoolHandlerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务支持处理器
 *
 * @author Gavin Zhang
 * @date 2023/1/3 16:03
 */
@Component("taskPendingHolder")
@Slf4j
public class TaskPendingHolder {


    /**
     * 线程池保存器，以GroupId为键
     */
    private final Map<String, ExecutorService> taskPendingHolder = new HashMap<>(32);

    private static final int SIXTY_SECONDS = 60;

    /**
     * 线程池初始化
     */
    @PostConstruct
    public void init() {
        taskPendingHolder.put("armagin_user_register_verify_code", ThreadPoolHandlerConfig.getExecutor());
    }

    /**
     * 线程池关闭
     */
    @PreDestroy
    public void destroy() {
        ExecutorService pool = taskPendingHolder.get("armagin_user_register_verify_code");
        pool.shutdown();
        try {
            if (!pool.awaitTermination(SIXTY_SECONDS, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(SIXTY_SECONDS, TimeUnit.SECONDS)) {
                    log.error("线程池未能关闭！");
                }
            }
        } catch (InterruptedException ie) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 获取对应线程池
     *
     * @return 线程池
     */
    public ExecutorService route() {
        return taskPendingHolder.get("armagin_user_register_verify_code");
    }

}
