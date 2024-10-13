package com.hsbc.api.customer.account.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class AccountSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToOne
    @JoinColumn
    Customer customer;

    @Column
    BigDecimal totalBalance;

    @Column
    String currencyCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountSummaryId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Account> accounts;
}
