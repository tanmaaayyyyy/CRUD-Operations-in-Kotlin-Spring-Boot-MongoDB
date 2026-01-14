package com.example.demo.mongodemo.controller

import com.example.demo.mongodemo.exception.UserNotFoundException
import com.example.demo.mongodemo.service.OptimisticAccountService
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class OptimisticAccountController(
    private val accountService: OptimisticAccountService,
    private val version: MongoCustomConversions
) {

    @GetMapping("/optimistic-test/{id}")
    fun test(@PathVariable id: String): String {

        val account = accountService.getAccount(id)
            ?: throw UserNotFoundException("User not found")

        val version = account.version

        val t1 = Thread {
            accountService.withdraw(
                accountId = id,
                amount = 10,
                expectedVersion = version
            )
        }

        val t2 = Thread {
            accountService.withdraw(
                accountId = id,
                amount = 20,
                expectedVersion = version
            )
        }

        t1.start()
        t2.start()

        t1.join()
        t2.join()

        return "Done"
    }


}