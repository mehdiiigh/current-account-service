package com.example.current_account_service.controller

import com.example.current_account_service.dto.AccountInfoDto
import com.example.current_account_service.dto.OpenAccountDto
import com.example.current_account_service.service.AccountService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@Tag(name = "Current Account")
@RestController
@RequestMapping("/api/current-account")
class CurrentAccountController(val accountService: AccountService) {

    @Operation(summary = "Open new current account")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "201", description = "Successful Operation"),
            ApiResponse(responseCode = "400", description = "Validation error"),
            ApiResponse(responseCode = "404", description = "Customer does not exist")
        ]
    )
    @PostMapping
    @ResponseStatus(CREATED)
    fun openCurrentAccount(@RequestBody @Valid openAccountDto: OpenAccountDto): ResponseEntity<Void> {
        accountService.openAccount(openAccountDto)
        return ResponseEntity.created(URI("/api/current-account")).build()
    }

    @Operation(summary = "Retrieve current account details")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation"),
            ApiResponse(responseCode = "404", description = "Customer does not exist")
        ]
    )
    @GetMapping("/{customerId}")
    @ResponseStatus(OK)
    fun getCurrentAccounts(@PathVariable customerId: Long): ResponseEntity<AccountInfoDto> {
        return ResponseEntity.ok(accountService.getCustomerAccounts(customerId))
    }
}