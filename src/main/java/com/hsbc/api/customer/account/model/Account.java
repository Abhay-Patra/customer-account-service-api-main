package com.hsbc.api.customer.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Account {
    @Id
    String accountId;

    @Column
    String accountName;

    @Enumerated(EnumType.STRING)
    @Column
    AccountType accountType;

    @Column
    BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column
    CurrencyCode currencyCode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    Customer customer;

    @JsonIgnore
    @Column
    long accountSummaryId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "accountId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Transaction> transactions;
}
