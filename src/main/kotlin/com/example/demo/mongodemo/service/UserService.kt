package com.example.demo.mongodemo.service

import com.example.demo.mongodemo.dto.CreateUserRequest
import com.example.demo.mongodemo.dto.UserResponse
import com.example.demo.mongodemo.exception.UserNotFoundException
import com.example.demo.mongodemo.model.User
import com.example.demo.mongodemo.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun createUser(request: CreateUserRequest): UserResponse {
        val user = User(name = request.name, age = request.age)
        val savedUser = userRepository.save(user)
        return UserResponse(id = savedUser.id!!, name = request.name, age = savedUser.age)
    }

    fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map{
            UserResponse(id = it.id!!, name = it.name, age = it.age)
        }
    }

    fun getUsersByName(name: String): List<User> {
        return userRepository.findByName(name)
    }

    fun getUsersOlderThan(age: Int): List<User> {
        return userRepository.findByAgeGreaterThan(age)
    }

    fun getUsersByAgeAndName(age: Int,name: String): List<User> {
        return userRepository.findByAgeGreaterThan(age)
    }

    fun getUsersByNameContainingIgnoreCase(name: String): List<User> {
        return userRepository.findByNameContainingIgnoreCase(name)
    }

    fun getUserById(id: String): UserResponse {

        val user = userRepository.findById(id)
            .orElseThrow {
                UserNotFoundException("User with id $id not found")
            }

        return UserResponse(
            id = user.id!!,
            name = user.name,
            age = user.age
        )
    }
}
