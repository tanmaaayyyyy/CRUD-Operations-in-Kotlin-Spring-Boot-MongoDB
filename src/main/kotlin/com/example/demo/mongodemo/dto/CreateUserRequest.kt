//is only for incoming requests
package com.example.demo.mongodemo.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(

    @field:NotBlank(message = "Name cannot be empty")
    val name: String,

    @field:Min(value = 0, message = "Age must be non-negative")
    val age: Int
)
