package com.hsbc.api.customer.account.repository;

import com.hsbc.api.customer.account.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<Account, String> {

    @Query("SELECT accounts FROM Account accounts WHERE accounts.customer.customerId = :customerId")
    List<Account> findAccountList(String customerId);
}
