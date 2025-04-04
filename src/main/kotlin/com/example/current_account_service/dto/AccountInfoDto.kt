package com.example.current_account_service.dto

data class AccountInfoDto(
    val customer: CustomerDto,
    val accounts: List<AccountDto>
)