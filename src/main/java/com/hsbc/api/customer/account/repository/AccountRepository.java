package com.hsbc.api.customer.account.repository;

import com.hsbc.api.customer.account.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

    @Query("SELECT account.accountId FROM Account account WHERE account.customer.customerId = :customerId")
    List<String> findAccountIdsByCustomer(String customerId);

}
