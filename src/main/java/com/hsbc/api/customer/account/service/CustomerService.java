package com.hsbc.api.customer.account.service;

import com.hsbc.api.customer.account.model.Account;
import com.hsbc.api.customer.account.model.AccountSummary;
import com.hsbc.api.customer.account.repository.AccountRepository;
import com.hsbc.api.customer.account.repository.AccountSummaryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final AccountSummaryRepository accountSummaryRepository;
    private final AccountRepository accountRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    /* private final CustomerRepository customerRepository;*/

    @Value("${account-url}")
    private String accountUrl;

    public List<Account> getAccounts(String customerId) {
        List<Account> accounts = new ArrayList<>();
        List<String> accountIds = accountRepository.findAccountIdsByCustomer(customerId);
        for (String accountId : accountIds) {
            ResponseEntity<Account> response = restTemplate.getForEntity(accountUrl, Account.class, accountId);
            if (response.getStatusCode().is2xxSuccessful()) {
                accounts.add(response.getBody());
            }
        }
        return accounts;
    }

    /*public List<Account> getAccounts(String customerId) {
        return customerRepository.findAccountList(customerId);

    }*/

    public AccountSummary getAccountSummary(String customerId) {
        return accountSummaryRepository
                .findSummaryByCustomer(customerId)
                .orElseThrow(() -> new RuntimeException("Invalid customer id"));
    }
}
