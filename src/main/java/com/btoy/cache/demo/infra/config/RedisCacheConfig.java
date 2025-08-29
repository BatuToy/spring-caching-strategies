package com.btoy.cache.demo.infra.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;
import java.time.Duration;

@Configuration
@RequiredArgsConstructor
public class RedisCacheConfig<K, V extends Serializable> {

    private final RedisConfigData redisConfigData;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(redisConfigData.getHost(), redisConfigData.getPort());
    }

    @Bean
    @Primary
    public CacheManager redisCacheManager() {
        final GenericJackson2JsonRedisSerializer jsonRedisSerializer =
                new GenericJackson2JsonRedisSerializer(cacheObjectMapper());
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(3))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer));
        return RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(config).build();
    }

    @Bean
    public ObjectMapper cacheObjectMapper() {
        return new ObjectMapper()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Bean("template")
    public RedisTemplate<K, V> templateConfig(RedisConnectionFactory factory) {
        RedisTemplate<K, V> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        final StringRedisSerializer keySerializer = StringRedisSerializer.UTF_8;
        final GenericJackson2JsonRedisSerializer jsonRedisSerializer =
                new GenericJackson2JsonRedisSerializer(cacheObjectMapper());
        template.setKeySerializer(keySerializer);
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashKeySerializer(keySerializer);
        template.setHashValueSerializer(jsonRedisSerializer);
        template.setDefaultSerializer(RedisSerializer.string());
        return template;
    }
}
