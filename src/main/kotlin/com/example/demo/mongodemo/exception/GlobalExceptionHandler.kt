package com.example.demo.mongodemo.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(ex: MethodArgumentNotValidException): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        ex.bindingResult.fieldErrors.forEach {
            errors[it.field] = it.defaultMessage ?: "Invalid value"
        }

        return errors
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgument(ex: IllegalArgumentException): Map<String, String> {
        return mapOf("error" to ex.message!!)
    }

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFound(ex: UserNotFoundException): Map<String, String> =
        mapOf("error" to ex.message!!)

    @ExceptionHandler(DuplicateUserException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleDuplicateUser(ex: DuplicateUserException): Map<String, String> =
        mapOf("error" to ex.message!!)

    // Bulk update: users not found
    @ExceptionHandler(UsersNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUsersNotFound(ex: UsersNotFoundException): Map<String, String> =
        mapOf("error" to ex.message!!)

    // Fallback (last resort)
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGeneric(ex: Exception): Map<String, String> =
        mapOf("error" to "Something went wrong. Please try again later.")
}
