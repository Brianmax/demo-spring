package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;


@Configuration
public class RedisTemplateConfig {
    
    @Bean
    public StringRedisTemplate redisTemplate() {
        return new StringRedisTemplate();
    }
}
