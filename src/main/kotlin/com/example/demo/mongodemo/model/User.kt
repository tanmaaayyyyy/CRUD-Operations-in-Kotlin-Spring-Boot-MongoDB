package com.example.demo.mongodemo.model

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
data class User(

    @Id
    val id: String? = null,

    @field:NotBlank(message = "Name is required")
    val name: String,

    @field:Min(value = 0, message = "Age must be non negative number")
    val age: Int
)
