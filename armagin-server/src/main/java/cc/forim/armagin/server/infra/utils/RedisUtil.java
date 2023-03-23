package cc.forim.armagin.server.infra.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis工具类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/23 13:40
 */
@Component
public final class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 判断value是否在Set中
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}