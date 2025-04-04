package com.example.current_account_service.service.impl

import com.example.current_account_service.entity.Account
import com.example.current_account_service.entity.Transaction
import com.example.current_account_service.repository.TransactionRepository
import com.example.current_account_service.service.TransactionService
import org.springframework.stereotype.Service

@Service
class TransactionServiceImpl(private val transactionRepository: TransactionRepository) : TransactionService {

    override fun chargeAccount(account: Account, amount: Double) {
        transactionRepository.save(Transaction(amount = amount, account = account))
    }
}