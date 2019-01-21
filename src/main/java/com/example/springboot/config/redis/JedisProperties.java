package com.example.springboot.config.redis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = JedisProperties.JEDIS_PREFIX)
public class JedisProperties {

    public static final String JEDIS_PREFIX = "redis.server";

    private String host;

    private int port;

    private String password;

    private int maxTotal;

    private int maxIdle;

    private int maxWaitMillis;

    private int timeOut;

}