package com.example.demo.mongodemo.service

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class RedisLockService(
    private val redisTemplate: StringRedisTemplate
) {

    fun acquireLock(
        lockKey: String,
        ownerId: String,
        ttlMillis: Long
    ): Boolean {
        return redisTemplate.opsForValue().setIfAbsent(
            lockKey,
            ownerId,
            Duration.ofMillis(ttlMillis)
        ) == true
    }

    fun releaseLock(lockKey: String, ownerId: String) {

        val script = DefaultRedisScript<Long>()
        script.setScriptText(
            """
            if redis.call("GET", KEYS[1]) == ARGV[1] then
                return redis.call("DEL", KEYS[1])
            else
                return 0
            end
            """.trimIndent()
        )
        script.setResultType(Long::class.java)

        redisTemplate.execute(
            script,
            listOf(lockKey),
            ownerId
        )
    }
}
