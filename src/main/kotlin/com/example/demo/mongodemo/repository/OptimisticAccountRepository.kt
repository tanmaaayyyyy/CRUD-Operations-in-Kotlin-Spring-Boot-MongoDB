package com.example.demo.mongodemo.repository

import com.example.demo.mongodemo.dto.Account
import org.springframework.data.mongodb.repository.MongoRepository

interface OptimisticAccountRepository : MongoRepository<Account, String>
