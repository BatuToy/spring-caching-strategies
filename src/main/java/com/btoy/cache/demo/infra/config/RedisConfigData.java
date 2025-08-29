package com.btoy.cache.demo.infra.config;

/*
 * @created 29/08/2025 ~~ 10:54
 * author: batu
 */

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cache.redis.props")
public class RedisConfigData {

    private String host;
    private int port;

}
