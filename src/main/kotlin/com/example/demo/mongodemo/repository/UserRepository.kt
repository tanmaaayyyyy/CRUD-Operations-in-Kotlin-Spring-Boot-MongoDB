package com.example.demo.mongodemo.repository

import com.example.demo.mongodemo.dto.UserResponse
import com.example.demo.mongodemo.model.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>{

    fun findByName(name: String): List<User>
    fun findByAgeGreaterThan(age: Int): List<User>
    fun findByNameAndAge(name: String, age: Int): List<User>
    fun findByNameContainingIgnoreCase(name: String): List<User>
    fun findUserById(id: String): UserResponse
}