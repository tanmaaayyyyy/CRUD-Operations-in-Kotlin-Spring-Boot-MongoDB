package com.example.demo.mongodemo.controller

import com.example.demo.mongodemo.dto.external.PersonDto
import com.example.demo.mongodemo.service.FakerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FakerTestController(
    private val fakerService: FakerService
) {
    @GetMapping("/test/faker")
    fun testFaker(): PersonDto {
        return fakerService.getFakePerson()
    }
    @GetMapping("/test/faker/multi")
    fun testMultiple(): List<PersonDto> {
        return fakerService.getMultipleFakePersons(5)
    }
}
