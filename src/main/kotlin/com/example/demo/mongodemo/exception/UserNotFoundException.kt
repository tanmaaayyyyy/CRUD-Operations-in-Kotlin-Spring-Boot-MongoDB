package com.example.demo.mongodemo.exception

import org.apache.logging.log4j.message.Message

class UserNotFoundExceptio(message: String) : RuntimeException(message) {
}