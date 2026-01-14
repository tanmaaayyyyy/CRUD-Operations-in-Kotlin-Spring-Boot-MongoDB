package com.example.demo.mongodemo.dto

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "accounts")
data class Account(

    @Id
    val id: String,          // Mongo uses String/ObjectId

    var balance: Int = 0,

    var version: Long = 0    // MANUALLY managed version
)
