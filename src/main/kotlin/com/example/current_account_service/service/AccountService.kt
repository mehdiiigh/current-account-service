package com.example.current_account_service.service

import com.example.current_account_service.dto.AccountInfoDto
import com.example.current_account_service.dto.OpenAccountDto

interface AccountService {
    fun openAccount(openAccountDto: OpenAccountDto)
    fun getCustomerAccounts(customerId: Long): AccountInfoDto
}