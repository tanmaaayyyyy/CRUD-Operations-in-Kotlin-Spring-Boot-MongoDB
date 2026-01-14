package com.example.demo.mongodemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
open class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .baseUrl("https://fakerapi.it")
            .build()
    }
}
