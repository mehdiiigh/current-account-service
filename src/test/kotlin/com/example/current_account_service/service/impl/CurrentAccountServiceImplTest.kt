package com.example.current_account_service.service.impl

import com.example.current_account_service.dto.OpenAccountDto
import com.example.current_account_service.entity.Account
import com.example.current_account_service.entity.Customer
import com.example.current_account_service.entity.Transaction
import com.example.current_account_service.repository.AccountRepository
import com.example.current_account_service.service.CustomerService
import com.example.current_account_service.service.TransactionService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CurrentAccountServiceImplTest {

    @Mock
    private lateinit var accountRepository: AccountRepository

    @Mock
    private lateinit var customerService: CustomerService

    @Mock
    private lateinit var transactionService: TransactionService

    @InjectMocks
    private lateinit var currentAccountServiceImpl: CurrentAccountServiceImpl

    @Test
    fun openAccountTest() {
        val customerId = 1L
        val initialCredit = 100.0
        val customer = Customer(id = 1L, name = "John", surname = "Doe")
        val openAccountDto = OpenAccountDto(customerId = customerId, initialCredit = initialCredit)
        val savedAccount = Account(id = 1L, customer = customer)

        `when`(customerService.findByCustomerId(customerId)).thenReturn(customer)
        `when`(accountRepository.save(any(Account::class.java))).thenReturn(savedAccount)

        currentAccountServiceImpl.openAccount(openAccountDto)

        verify(customerService).findByCustomerId(customerId)
        verify(accountRepository).save(any(Account::class.java))
        verify(transactionService).chargeAccount(Account(customer = customer), initialCredit)
    }

    @Test
    fun getCustomerAccountsTest() {
        val customerId = 1L
        val customer = Customer(
            id = customerId,
            name = "John",
            surname = "Doe",
        )
        val account1 = Account(id = 1L, customer = customer)
        val account2 = Account(id = 2L, customer = customer)

        account1.transactions.add(Transaction(1L, 100.0, account = account1))
        account2.transactions.add(Transaction(1L, 200.0, account = account2))

        customer.accounts.add(account1)
        customer.accounts.add(account2)

        `when`(customerService.findByCustomerId(customerId)).thenReturn(customer)

        val result = currentAccountServiceImpl.getCustomerAccounts(customerId)

        assertEquals(customerId, result.customer.id)
        assertEquals(2, result.accounts.size)
        assertEquals(100.0, result.accounts[0].balance)
        assertEquals(200.0, result.accounts[1].balance)
    }
}