package com.example.current_account_service.service.impl

import com.example.current_account_service.entity.Customer
import com.example.current_account_service.exception.CustomerNotFoundException
import com.example.current_account_service.repository.CustomerRepository
import com.example.current_account_service.service.CustomerService
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val customerRepository: CustomerRepository) : CustomerService {

    override fun findByCustomerId(customerId: Long): Customer {
        return customerRepository.findById(customerId).orElseThrow { CustomerNotFoundException(customerId) }
    }
}