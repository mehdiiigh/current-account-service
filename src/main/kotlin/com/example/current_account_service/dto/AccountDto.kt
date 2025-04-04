package com.example.current_account_service.dto

data class AccountDto(
    val id: Long?,
    val balance: Double?,
    val transactions: List<TransactionDto>
)