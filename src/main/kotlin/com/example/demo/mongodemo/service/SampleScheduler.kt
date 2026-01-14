package com.example.demo.mongodemo.service

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SampleScheduler {

    @Scheduled(cron = "0 0 * * * *")
    fun runEveryFiveSeconds() {
        println("Scheduler running at: ${System.currentTimeMillis()}")
    }

    @Scheduled(cron = "0 */5 * * * *")
    fun runEveryTenSecondsCron() {
        println("Scheduler running at: ${System.currentTimeMillis()}")
    }

    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Kolkata")
    fun morningJob(){
        println("Good morning India")
    }
}