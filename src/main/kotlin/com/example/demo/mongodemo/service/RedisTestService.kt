package com.example.demo.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service

@Service
class RedisTestService(
    private val redisTemplate: StringRedisTemplate
) {

    fun testRedis(): String {
        redisTemplate.opsForValue().set("test-key", "hello-redis")
        return redisTemplate.opsForValue().get("test-key") ?: "NOT FOUND"
    }
}
