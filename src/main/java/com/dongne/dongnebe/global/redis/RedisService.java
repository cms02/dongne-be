package com.dongne.dongnebe.global.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    public void setValues(String key, String data) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data);
    }

    public void setValues(String key, String data, int expirationTime, TimeUnit timeUnit) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        values.set(key, data, expirationTime, timeUnit);
    }

    public String getValues(String key) {
        ValueOperations<String, String> values = redisTemplate.opsForValue();
        return values.get(key);

    }
    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }

    public boolean hasUserViewedBoardToday(Long boardId, String userId) {
        String key = "user:" + userId + ":board:" + boardId;
        return redisTemplate.hasKey(key);
    }

    public void markUserViewedBoardToday(Long boardId, String userId) {
        String key = "user:" + userId + ":board:" + boardId;
        redisTemplate.opsForValue().set(key, "viewed", 24, TimeUnit.HOURS);
    }
}
