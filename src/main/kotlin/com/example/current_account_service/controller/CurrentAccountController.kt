package com.example.current_account_service.controller

import com.example.current_account_service.dto.AccountInfoDto
import com.example.current_account_service.dto.OpenAccountDto
import com.example.current_account_service.service.AccountService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/current-account")
class CurrentAccountController(val accountService: AccountService) {

    @PostMapping
    @ResponseStatus(CREATED)
    fun openCurrentAccount(@RequestBody @Valid openAccountDto: OpenAccountDto): ResponseEntity<Void> {
        accountService.openAccount(openAccountDto)
        return ResponseEntity.created(URI("/api/current-account")).build()
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(OK)
    fun getCurrentAccounts(@PathVariable customerId: Long): ResponseEntity<AccountInfoDto> {
        return ResponseEntity.ok(accountService.getCustomerAccounts(customerId))
    }
}