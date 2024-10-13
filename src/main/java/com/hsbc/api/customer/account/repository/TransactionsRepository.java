package com.hsbc.api.customer.account.repository;

import com.hsbc.api.customer.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT trans FROM Transaction trans WHERE trans.accountId = :accountId ORDER BY trans.transactionTime DESC LIMIT :limit")
    List<Transaction> findTopXByAccountIdOrderByTransactionTimeDesc(String accountId, int limit);
}
