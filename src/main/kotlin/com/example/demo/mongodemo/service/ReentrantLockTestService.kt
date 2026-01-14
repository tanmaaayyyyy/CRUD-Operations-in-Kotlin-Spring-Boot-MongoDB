package com.example.demo.mongodemo.service

import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

@Service
class ReentrantLockTestService {
    private val lock = ReentrantLock()

    fun criticalTask(taskname: String){
        println("$taskname trying to acquire lock")

        if (lock.tryLock(2, TimeUnit.SECONDS)) {
            try {
                println("$taskname acquire the lock")
                Thread.sleep(1000)
            }
            finally {
                println("$taskname releasing the lock")
                lock.unlock()
            }
        }
        else{
            println("$taskname could not acquire the lock (timeout)")
        }
    }
}