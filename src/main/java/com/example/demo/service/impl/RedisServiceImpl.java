package com.example.demo.service.impl;

import com.example.demo.service.RedisService;
import jakarta.persistence.ManyToOne;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void saveKeyValue(String key, String value, int exp) {
        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, exp, TimeUnit.MINUTES);
    }

    @Override
    public String getValueByKey(String key) {
        return "";
    }

    @Override
    public void deleteByKey(String key) {

    }
}
