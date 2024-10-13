package com.hsbc.api.customer.account.controller;

import com.hsbc.api.customer.account.model.Account;
import com.hsbc.api.customer.account.model.Transaction;
import com.hsbc.api.customer.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Tag(name = "Accounts", description = "Account management APIs")
public class AccountController {

    private final AccountService accountService;

    @Operation(
            summary = "Details of a Particular Account (Account Profile)",
            description = "Retrieves detailed information about a specific account by its ID.")
    @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                        schema = @Schema(implementation = Account.class),
                        mediaType = "application/json")
            })
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(
            @PathVariable("accountId") String accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(account);
    }

    @Operation(
            summary = "Balance of Each Account",
            description = "Retrieves the current balance of a specific account.")
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(
                            schema = @Schema(implementation = BigDecimal.class),
                            mediaType = "application/json")
            })
    @GetMapping("/{accountId}/balance")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable("accountId") String accountId) {
        Account account = accountService.getAccount(accountId);
        return ResponseEntity.ok(account.getBalance());
    }

    @Operation(
            summary = "Last 5 Transactions for a Particular Account",
            description = "Retrieves the last 5 transactions for a specific account.")
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(
                            schema = @Schema(implementation = Transaction[].class),
                            mediaType = "application/json")
            })
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getAccounts(
            @PathVariable("accountId") String accountId) {
        List<Transaction> transactions = accountService.getTransactions(accountId);
        return ResponseEntity.ok(transactions);
    }
}
