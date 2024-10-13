package com.hsbc.api.customer.account.service;

import com.hsbc.api.customer.account.model.Account;
import com.hsbc.api.customer.account.model.Transaction;
import com.hsbc.api.customer.account.repository.AccountRepository;
import com.hsbc.api.customer.account.repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private static final int LIMIT_TO = 5;

    private final AccountRepository accountRepository;
    private final TransactionsRepository transactionsRepository;

    public Account getAccount(String accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Invalid accountId"));
    }

    public List<Transaction> getTransactions(String accountId) {
        return transactionsRepository.findTopXByAccountIdOrderByTransactionTimeDesc(accountId, LIMIT_TO);
    }
}
