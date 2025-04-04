package com.example.current_account_service.exception

import org.springframework.http.HttpStatus

abstract class AbstractException : RuntimeException() {

    abstract override val message: String

    abstract val status: HttpStatus
}