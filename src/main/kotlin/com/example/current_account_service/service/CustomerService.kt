package com.example.current_account_service.service

import com.example.current_account_service.entity.Customer

interface CustomerService {
    fun findByCustomerId(customerId: Long) : Customer
}