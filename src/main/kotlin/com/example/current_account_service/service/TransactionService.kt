package com.example.current_account_service.service

import com.example.current_account_service.entity.Account

interface TransactionService {
    fun chargeAccount(account: Account, amount: Double)
}