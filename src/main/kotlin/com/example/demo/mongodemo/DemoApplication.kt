package com.example.demo.mongodemo

import com.example.demo.mongodemo.model.User
import com.example.demo.mongodemo.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class MongoDemoApplication {

	@Bean
	fun runner(userRepository: UserRepository) = CommandLineRunner {

		val user = User(name = "Tanmay", age = 20)
		userRepository.save(user)

		println("Saved user: $user")

		val users = userRepository.findAll()
		println("All users: $users")
	}
}

fun main(args: Array<String>) {
	runApplication<MongoDemoApplication>(*args)
}
