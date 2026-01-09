package com.example.demo.mongodemo.exception

import org.apache.logging.log4j.message.Message
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice //catches all the exceptions thrown from any controller
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class) //Run the method below ONLY when this exception occurs
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(ex: MethodArgumentNotValidException): Map<String, String> {  //Spring passes the thrown exception into this method, then return a response body
        val errors = mutableMapOf<String, String>()

        ex.bindingResult.fieldErrors.forEach {   //ex means exception, bindingResult means validation results, fieldErrors means list of all failed errors
            errors[it.field] = it.defaultMessage ?: "Invalid value/ Something went wrong"
            /*This line does a lot:
                it.field → "name" or "age"
                it.defaultMessage → message from annotation
                ?: → Kotlin Elvis operator (fallback)
              So:
                If message exists → use it
                Else → "Invalid value"*/
        }
        return errors
    }

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUserNotFound(ex: UserNotFoundException): Map<String, String> =
        mapOf("error" to ex.message!!)

    @ExceptionHandler(DuplicateUserException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleDuplicate(ex: DuplicateUserException): Map<String, String> =
        mapOf("error" to ex.message!!)

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGeneric(ex: Exception): Map<String, String> =
        mapOf("error" to "Something went wrong. Please try again later.")

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgument(ex: IllegalArgumentException): Map<String, String> {
        return mapOf(
            "error" to "Invalid user id format"
        )
    }

}