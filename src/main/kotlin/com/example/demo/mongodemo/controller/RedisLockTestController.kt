package com.example.demo.mongodemo.controller

import com.example.demo.mongodemo.service.RedisLockService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class RedisLockTestController(
    private val redisLockService: RedisLockService
) {

    @GetMapping("/redis-lock-test")
    fun test(): String {
        val owner = UUID.randomUUID().toString()

        if (redisLockService.acquireLock("test-lock", owner, 10_000)) {
            try {
                Thread.sleep(3000)
                return "Lock acquired"
            } finally {
                redisLockService.releaseLock("test-lock", owner)
            }
        }
        return "Lock not acquired"
    }
}
