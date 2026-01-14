package com.example.demo.mongodemo.service

import com.example.demo.mongodemo.dto.external.FakerResponse
import com.example.demo.mongodemo.dto.external.PersonDto
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import com.example.demo.mongodemo.config.WebClientConfig
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration

@Service
class FakerService(
    private val webClient: WebClient
) {

    fun getFakePerson(): PersonDto {

        val response = webClient
            .get()
            .uri("/api/v1/persons?_quantity=1")
            .retrieve()
            .bodyToMono(
                object : ParameterizedTypeReference<FakerResponse<PersonDto>>() {}
            )
            .block()

        return response!!.data[0]
    }

    fun getMultipleFakePersons(count: Int): List<PersonDto> {

        val start = System.currentTimeMillis()

        val result = Flux.range(1, count)
            .flatMap {
                webClient
                    .get()
                    .uri("/api/v1/persons?_quantity=1")
                    .retrieve()
                    .bodyToMono(
                        object : ParameterizedTypeReference<FakerResponse<PersonDto>>() {}
                    )
                    .map { it.data[0] }
            }
            .collectList()
            .block()

        val end = System.currentTimeMillis()
        println("Total time taken: ${end - start} ms")

        return result!!
    }
}
