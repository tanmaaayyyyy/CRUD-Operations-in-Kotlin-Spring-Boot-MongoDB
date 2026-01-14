package com.example.demo.controller

import com.example.demo.service.RedisTestService
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/redis")
class RedisTestController(
    private val redisTestService: RedisTestService,
    private val redisTemplate: RedisTemplate<String, String>
) {

    @GetMapping("/test")
    fun test(): String {
        return redisTestService.testRedis()
    }
}

