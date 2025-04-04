package com.example.current_account_service.exception

class CustomerNotFoundException(private val customerId: Long) : AbstractNotFoundException() {
    override val message: String
        get() = "Customer $customerId Not Found!"
}