package com.example.current_account_service.exception

import org.springframework.http.HttpStatus

abstract class AbstractNotFoundException : AbstractException() {
    override val status: HttpStatus
        get() = HttpStatus.NOT_FOUND
}