package com.hsbc.api.customer.account.controller;

import com.hsbc.api.customer.account.model.Account;
import com.hsbc.api.customer.account.model.AccountSummary;
import com.hsbc.api.customer.account.service.CustomerService;
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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "Customer management APIs")
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "List of Accounts Based on Customer ID",
            description = "Retrieves a list of accounts associated with a particular customer ID.")
    @ApiResponse(
            responseCode = "200",
            content = {
                @Content(
                        schema = @Schema(implementation = Account[].class),
                        mediaType = "application/json")
            })
    @GetMapping("/{customerId}/accounts")
    public ResponseEntity<List<Account>> getAccounts(
            @PathVariable("customerId") String customerId) {
        List<Account> accounts = customerService.getAccounts(customerId);
        return ResponseEntity.ok(accounts);
    }

    @Operation(
            summary = "Summary of All Accounts",
            description = "Retrieves a summary of all accounts associated with a particular customer ID.")
    @ApiResponse(
            responseCode = "200",
            content = {
                    @Content(
                            schema = @Schema(implementation = AccountSummary.class),
                            mediaType = "application/json")
            })
    @GetMapping("/{customerId}/accounts/summary")
    public ResponseEntity<AccountSummary> getAccountSummary(
            @PathVariable("customerId") String customerId) {
        AccountSummary summary = customerService.getAccountSummary(customerId);
        return ResponseEntity.ok(summary);
    }
}
