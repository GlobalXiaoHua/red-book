package com.lateautumncoder.rbauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by 40973 ON 2024-10-20 20:01
 */
@Configuration
public class RedisTemplateConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> jsonRedis = new Jackson2JsonRedisSerializer<>(Object.class);

        // 使用 StringRedisSerializer 来序列化和反序列化 redis 的 key 值，确保 key 是可读的字符串
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        // 使用 Jackson2JsonRedisSerializer 来序列化和反序列化 redis 的 value 值, 确保存储的是 JSON 格式
        template.setValueSerializer(jsonRedis);
        template.setHashKeySerializer(jsonRedis);

        template.afterPropertiesSet();
        return template;
    }

}
