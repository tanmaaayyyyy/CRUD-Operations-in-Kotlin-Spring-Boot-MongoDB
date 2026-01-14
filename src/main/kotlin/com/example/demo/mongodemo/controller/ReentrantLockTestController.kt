package com.example.demo.mongodemo.controller

import com.example.demo.mongodemo.service.ReentrantLockTestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class ReentrantLockTestController(private val lockTestService: ReentrantLockTestService) {
    @GetMapping("/lock-test")
    fun lockTest(): String {
        Thread { lockTestService.criticalTask("Task-A") }.start()
        Thread { lockTestService.criticalTask("Task-B") }.start()
        return "started tasks"
    }
}