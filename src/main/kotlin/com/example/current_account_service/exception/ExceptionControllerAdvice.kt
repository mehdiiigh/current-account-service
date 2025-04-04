package com.example.current_account_service.exception

import jakarta.validation.ValidationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun handleAbstractException(abstractException: AbstractException): ResponseEntity<ErrorMessageModel> {
        return ResponseEntity(ErrorMessageModel(
            abstractException.status.value(),
            abstractException.message),
            abstractException.status
        )
    }

    @ExceptionHandler
    fun handleValidationException(methodArgumentNotValidException: MethodArgumentNotValidException): ResponseEntity<ErrorMessageModel> {
        return ResponseEntity(ErrorMessageModel(
            HttpStatus.BAD_REQUEST.value(),
            methodArgumentNotValidException.fieldError?.defaultMessage),
            HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handelRuntimeException(ex: RuntimeException): ResponseEntity<ErrorMessageModel> {
        return ResponseEntity(ErrorMessageModel(message = ex.message), HttpStatus.BAD_REQUEST)
    }
}