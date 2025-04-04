package com.example.current_account_service.dto;

import java.time.LocalDateTime

data class TransactionDto(
    val amount: Double,
    val date: LocalDateTime
)
