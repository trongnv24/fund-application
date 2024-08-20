package aibles.java.spring.boot.fund.service.impl;

import aibles.java.spring.boot.fund.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    public RedisServiceImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void saveOTP(String userName, String otp, Long duration) {
        String key = "active_otp:" + userName;
        redisTemplate.opsForValue().set(key, otp, duration, TimeUnit.MINUTES);
        log.info("OTP {} saved to Redis for username {} with duration {} minutes", otp, userName, duration);
    }

    @Override
    public String findOtp(String username) {
        String key = "active_otp:" + username;
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void clearActiveOtp(String username) {
        String key = "active_otp:" + username;
        redisTemplate.delete(key);
        log.info("OTP for username {} cleared from Redis", username);
    }

    @Override
    public void save(String accessTokenKey, String accessToken, Long expirationMs) {
        redisTemplate.opsForValue().set(accessTokenKey, accessToken, expirationMs, TimeUnit.MILLISECONDS);
        log.info("Access token saved to Redis with key: {}, expiration: {} milliseconds", accessTokenKey, expirationMs);
    }
}