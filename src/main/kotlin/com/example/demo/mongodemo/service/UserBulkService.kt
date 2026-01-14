package com.example.demo.mongodemo.service

import com.example.demo.mongodemo.dto.BulkUserUpdateRequest
import com.example.demo.mongodemo.exception.UsersNotFoundException
import com.example.demo.mongodemo.model.User
import org.springframework.stereotype.Service
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.BulkOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update

@Service
class UserBulkService(
    private val mongoTemplate: MongoTemplate
) {

    fun bulkUpdateUsers(requests: List<BulkUserUpdateRequest>) {

        if (requests.isEmpty()) {
            throw IllegalArgumentException("Bulk update list cannot be empty")
        }

        val bulkOps = mongoTemplate.bulkOps(
            BulkOperations.BulkMode.UNORDERED,
            User::class.java
        )

        requests.forEach { req ->
            val query = Query(Criteria.where("_id").`is`(req.id))
            val update = Update()

            req.name?.let { update.set("name", it) }
            req.age?.let { update.set("age", it) }

            bulkOps.updateOne(query, update)
        }

        val result = bulkOps.execute()

        if (result.matchedCount == 0) {
            throw UsersNotFoundException("No users found with the given id")
        }
    }
}
