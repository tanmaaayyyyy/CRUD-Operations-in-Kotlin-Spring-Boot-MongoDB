package com.example.demo.mongodemo.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import org.springframework.data.annotation.Id


data class BulkUserUpdateRequest (
    val id: String,
    var name: String?,
    var age: Int?
)