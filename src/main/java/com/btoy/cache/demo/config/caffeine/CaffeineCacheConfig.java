package com.btoy.cache.demo.config.caffeine;

/*
 * @created 26/08/2025 ~~ 21:49
 * author: batu
 */

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Configuration
public class CaffeineCacheConfig {

    @Bean("caffeineCacheManager")
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager =  new CaffeineCacheManager();
        caffeineCacheManager.setCacheNames(List.of("product", "category"));
        caffeineCacheManager
                .setCaffeine(Caffeine.newBuilder()
                        .initialCapacity(100)
                        .maximumSize(5000)
                        .expireAfterWrite(Duration.ofMinutes(1)) // TTL Bound! (Time TO Live !)
                        //.refreshAfterWrite(Duration.ofMinutes(1)) // TTI (Time TO Idle !)
                        .recordStats());
        return caffeineCacheManager;
    }
}
