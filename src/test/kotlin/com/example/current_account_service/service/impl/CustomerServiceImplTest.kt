package com.example.current_account_service.service.impl

import com.example.current_account_service.entity.Customer
import com.example.current_account_service.exception.CustomerNotFoundException
import com.example.current_account_service.repository.CustomerRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*
import kotlin.test.assertFailsWith

@ExtendWith(MockitoExtension::class)
class CustomerServiceImplTest {

    @Mock
    private lateinit var customerRepository: CustomerRepository

    @InjectMocks
    private lateinit var customerService: CustomerServiceImpl

    @Test
    fun findCustomerByIdTest() {
        val customer = Customer(id = 1L, name = "John", surname = "Doe")
        `when`(customerRepository.findById(1L)).thenReturn(Optional.of(customer))

        val result = customerService.findByCustomerId(1L)

        kotlin.test.assertEquals(1L, result.id)
        kotlin.test.assertEquals("John", result.name)
        kotlin.test.assertEquals("Doe", result.surname)
    }

    @Test
    fun findCustomerByIdThrowsExceptionTest() {
        `when`(customerRepository.findById(99L)).thenReturn(Optional.empty())

        assertFailsWith<CustomerNotFoundException> {
            customerService.findByCustomerId(99L)
        }
    }
}