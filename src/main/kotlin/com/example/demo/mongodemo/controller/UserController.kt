package com.example.demo.mongodemo.controller

import com.example.demo.mongodemo.dto.BulkUserUpdateRequest
import com.example.demo.mongodemo.dto.CreateUserRequest
import com.example.demo.mongodemo.dto.UserResponse
import com.example.demo.mongodemo.model.User
import com.example.demo.mongodemo.repository.UserRepository
import com.example.demo.mongodemo.service.UserBulkService
import com.example.demo.mongodemo.service.UserService
import org.springframework.web.bind.annotation.*
import jakarta.validation.*
import org.springframework.data.mongodb.core.BulkOperations

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService,
    private val userBulkService: UserBulkService
) {

    @PostMapping
    fun createUser(@Valid @RequestBody request: CreateUserRequest): UserResponse {
        return userService.createUser(request)
    }

    @PostMapping("/bulk-update")
    fun bulkUpdateUsers(@RequestBody requests: List<BulkUserUpdateRequest>){
        return userBulkService.bulkUpdateUsers(requests)
    }

    @GetMapping
    fun getAllUsers(): List<UserResponse> {
        return userService.getAllUsers()
    }

    @GetMapping("/name/{name}")
    fun getUserByName(@PathVariable name: String): List<User> {
        return userService.getUsersByName(name)
    }

    @GetMapping("/Greater-than/{age}")
    fun getAgeGreaterThan(@PathVariable age: Int): List<User>{
        return userService.getUsersOlderThan(age)
    }

    @GetMapping("/search")
    fun getByAgeAndName(@RequestParam age: Int, @RequestParam name: String): List<User> {
        return userService.getUsersByAgeAndName(age, name)
    }

    @GetMapping("/search/nameContains")
    fun getUsersByNameContains(@RequestParam name: String): List<User> {
        return userService.getUsersByNameContainingIgnoreCase(name)
    }

    @GetMapping("/id/{id:[0-9a-fA-F]{24}}")
    fun getUserById(@PathVariable id: String): UserResponse {
        return userService.getUserById(id)
    }



}
