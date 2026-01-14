package com.example.demo.mongodemo.service

import org.springframework.stereotype.Service
import java.util.UUID


@Service
class PaymentService(
    private val redisLockService: RedisLockService
) {

    fun processPayments() {
        val lockKey = "payments-lock"
        val ownerId = UUID.randomUUID().toString()

        val acquired = redisLockService.acquireLock(
            lockKey,
            ownerId,
            ttlMillis = 30_000
        )

        if (!acquired) {
            println("Another instance is processing payments")
            return
        }

        try {
            println("Processing payments...")
            Thread.sleep(5000)
        } finally {
            redisLockService.releaseLock(lockKey, ownerId)
        }
    }
}
