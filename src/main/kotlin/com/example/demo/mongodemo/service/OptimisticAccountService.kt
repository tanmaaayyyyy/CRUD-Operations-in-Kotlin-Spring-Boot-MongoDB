package com.example.demo.mongodemo.service

import com.example.demo.mongodemo.dto.Account
import com.example.demo.mongodemo.exception.UserNotFoundException
import com.example.demo.mongodemo.repository.OptimisticAccountRepository
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Service

@Service
class OptimisticAccountService(
    private val mongoTemplate: MongoTemplate,
    private val accountRepository: OptimisticAccountRepository
) {

    fun withdraw(accountId: String, amount: Int, expectedVersion: Long) {

        val query = Query(
            Criteria.where("_id").`is`(accountId)
                .and("version").`is`(expectedVersion)
        )

        val update = Update()
            .inc("version", 1)
            .inc("balance", -amount)

        val result = mongoTemplate.updateFirst(query, update, Account::class.java)

        if (result.matchedCount == 0L) {
            throw RuntimeException("Optimistic lock conflict detected")
        }
    }
    fun getAccount(accountId: String): Account {
        return accountRepository.findById(accountId)
            .orElseThrow { UserNotFoundException("Account not found") }
    }

}
