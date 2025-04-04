package com.example.current_account_service.service.impl

import com.example.current_account_service.dto.*
import com.example.current_account_service.entity.Account
import com.example.current_account_service.entity.Customer
import com.example.current_account_service.repository.AccountRepository
import com.example.current_account_service.service.AccountService
import com.example.current_account_service.service.CustomerService
import com.example.current_account_service.service.TransactionService
import com.example.current_account_service.entity.Transaction
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CurrentAccountServiceImpl(val accountRepository: AccountRepository,
                                val customerService: CustomerService,
                                val transactionService: TransactionService) : AccountService {

    @Transactional
    override fun openAccount(openAccountDto: OpenAccountDto) {
        val customer = customerService.findByCustomerId(openAccountDto.customerId!!)

        val account = Account(customer = customer)
        accountRepository.save(account)

        if(openAccountDto.initialCredit > 0.0){
            transactionService.chargeAccount(account, openAccountDto.initialCredit)
        }
    }

    override fun getCustomerAccounts(customerId: Long): AccountInfoDto {
        val customer = customerService.findByCustomerId(customerId)
        return customer.toInvoiceDto()
    }

    private fun Customer.toInvoiceDto(): AccountInfoDto {
        return AccountInfoDto(
            customer = this.toCustomerDto(),
            accounts = this.accounts.map { it.toAccountDto() }
        )
    }

    private fun Customer.toCustomerDto(): CustomerDto {
        return CustomerDto(
            id = this.id,
            name = this.name,
            surname = this.surname
        )
    }

    private fun Account.toAccountDto(): AccountDto {
        return AccountDto(
            id = this.id,
            balance = calculateBalance(),
            transactions = this.transactions.map { it.toTransactionDto() }
        )
    }

    private fun Transaction.toTransactionDto(): TransactionDto {
        return TransactionDto(
            amount = this.amount,
            date = this.date
        )
    }

    private fun Account.calculateBalance(): Double {
        return this.transactions.sumOf { it.amount }
    }
}