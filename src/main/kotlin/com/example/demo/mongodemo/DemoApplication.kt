package com.example.demo.mongodemo

import com.example.demo.mongodemo.model.User
import com.example.demo.mongodemo.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(scanBasePackages = ["com.example.demo"])
@EnableScheduling
class MongoDemoApplication {

fun main(args: Array<String>) {
	runApplication<MongoDemoApplication>(*args)
	}
}
