package com.example.current_account_service.service.impl

import com.example.current_account_service.entity.Account
import com.example.current_account_service.entity.Customer
import com.example.current_account_service.entity.Transaction
import com.example.current_account_service.repository.TransactionRepository
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class TransactionServiceImplTest {

    @Mock
    private lateinit var transactionRepository: TransactionRepository

    @InjectMocks
    private lateinit var transactionService: TransactionServiceImpl

    @Captor
    private lateinit var transactionCaptor: ArgumentCaptor<Transaction>

    @Test
    fun chargeAccountTest() {

        val customer = Customer(id = 1L, name = "John", surname = "Doe")
        val account = Account(id = 1L, customer = customer)

        transactionService.chargeAccount(account, 150.0)

        verify(transactionRepository).save(transactionCaptor.capture())

        val savedTransaction = transactionCaptor.value
        kotlin.test.assertEquals(150.0, savedTransaction.amount)
        kotlin.test.assertEquals(account, savedTransaction.account)
    }
}