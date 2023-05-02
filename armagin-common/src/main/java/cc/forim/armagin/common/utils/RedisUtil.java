package cc.forim.armagin.common.utils;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Redis工具类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/23 13:40
 */
public class RedisUtil {

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

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     */
    public void hSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public Long hDel(String key, Object item) {
        try {
            return redisTemplate.opsForHash().delete(key, item);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     */
    public Long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 移除值为value的Set
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long setDel(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 有序集合添加
     *
     * @param key   键
     * @param value 值
     * @param score 权重
     * @return 添加是否成功
     */
    public Boolean zAdd(String key, Object value, Double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 有序集合获取
     *
     * @param key 键
     * @param min 最小score
     * @param max 最大score
     * @return score在[min, max]的值
     */
    public Set<Object> zRangeByScore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    /**
     * 有序集合获取排名
     *
     * @param key   集合名称
     * @param value 值
     */
    public Long zRank(String key, Object value) {
        try {
            return redisTemplate.opsForZSet().rank(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    /**
     * 移除值为value的ZSet
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long zSetDel(String key, Object... values) {
        try {
            return redisTemplate.opsForZSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }
}