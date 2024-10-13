package com.hsbc.api.customer.account.repository;

import com.hsbc.api.customer.account.model.AccountSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountSummaryRepository extends JpaRepository<AccountSummary, Long> {

    @Query("SELECT summary FROM AccountSummary summary WHERE summary.customer.customerId = :customerId")
    Optional<AccountSummary> findSummaryByCustomer(String customerId);
}
