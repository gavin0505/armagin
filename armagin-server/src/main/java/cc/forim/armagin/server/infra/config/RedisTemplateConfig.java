package cc.forim.armagin.server.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/3/18 23:15
 */

@Configuration
public class RedisTemplateConfig {
    /**
     * redisTemplate 序列化使用的jdk Serializable, 存储二进制字节码, 所以自定义序列化类
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //实例化这个Bean
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        //把工厂设置给Template
        template.setConnectionFactory(redisConnectionFactory);
        //配置Template主要配置序列化的方式，因为写的是java程序，得到的是java类型的数据，最终要这个数据存储到数据库里面
        //就要指定一种序列化的方式，或者说数据转换的方式
        //设置key的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式
        template.setValueSerializer(RedisSerializer.json());
        //设置hash的key的序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        //设置hash的value的序列化方式
        template.setHashValueSerializer(RedisSerializer.json());

        //使上面参数生效
        template.afterPropertiesSet();
        return template;
    }
}