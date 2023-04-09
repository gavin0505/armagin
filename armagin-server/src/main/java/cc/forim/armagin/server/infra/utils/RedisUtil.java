package cc.forim.armagin.server.infra.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

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
    public Boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 左添加list
     */
    public void lPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取list长度
     */
    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 右弹出size个元素
     */
    public List<Object> rPop(String key, Long size) {
        return redisTemplate.opsForList().rightPop(key, size);
    }

}