package com.example.current_account_service.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class OpenAccountDto(
    @field:NotNull(message = "customer id should not be null")
    val customerId: Long? = null,
    @field:Positive(message = "The initial value should be positive")
    val initialCredit: Double = 0.0
)