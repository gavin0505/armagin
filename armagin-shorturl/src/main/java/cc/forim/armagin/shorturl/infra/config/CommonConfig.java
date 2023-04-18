package cc.forim.armagin.shorturl.infra.config;

import cc.forim.armagin.common.utils.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 基本工具装配
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/4/18 16:39
 */
@Configuration
public class CommonConfig {

    /**
     * redisTemplate 序列化使用的jdk Serializable, 存储二进制字节码, 所以自定义序列化类
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //实例化这个Bean
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 使用Jackson序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //把工厂设置给Template
        template.setConnectionFactory(redisConnectionFactory);
        //配置Template主要配置序列化的方式，因为写的是java程序，得到的是java类型的数据，最终要这个数据存储到数据库里面
        //就要指定一种序列化的方式，或者说数据转换的方式
        //设置key的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式
        template.setValueSerializer(jackson2JsonRedisSerializer);
        //设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        //设置hash的value的序列化方式
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        //使上面参数生效
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 装配RedisUtil
     */
    @Bean
    public RedisUtil redisUtil() {
        return new RedisUtil();
    }
}