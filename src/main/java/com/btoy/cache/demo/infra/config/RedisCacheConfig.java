package com.btoy.cache.demo.infra.cache.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisCacheConfig<K, V> {

    private final RedisConfigData redisConfigData;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisConfigData.getHost(), redisConfigData.getPort());
    }

    @Bean
    public ObjectMapper cacheObjectMapper() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Bean
    public RedisTemplate<K, V> templateConfig(RedisConnectionFactory factory) {
        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        final StringRedisSerializer keySerializer = StringRedisSerializer.UTF_8;
        final GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer(cacheObjectMapper());
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(jsonRedisSerializer);
        template.setDefaultSerializer(RedisSerializer.string());
        return template;
    }
}
