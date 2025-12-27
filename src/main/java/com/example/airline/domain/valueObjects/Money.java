package com.example.airline.domain.valueObjects;

import java.math.BigDecimal;

// Money = amount + currency -> value object
public class Money {

    private BigDecimal amount;
    private String currency;

    protected Money(){}

    public Money(BigDecimal amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    // immutable -> no setter
}
