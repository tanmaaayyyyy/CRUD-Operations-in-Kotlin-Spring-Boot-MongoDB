package com.example.demo.mongodemo.dto.external

data class FakerResponse<T> (
    val status: String,
    val code: Int,
    val total: Int,
    val data: List<T>
)