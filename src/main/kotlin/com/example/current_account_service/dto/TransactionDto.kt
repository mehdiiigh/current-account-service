package com.example.current_account_service.dto;

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class TransactionDto(
    val amount: Double,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    val date: LocalDateTime
)
