package com.example.demo.mongodemo.controller

import com.example.demo.mongodemo.service.PaymentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class PaymentController(
    private val paymentService: PaymentService
) {

    @GetMapping("/process")
    fun process(): Unit {
        return paymentService.processPayments()
    }
}
