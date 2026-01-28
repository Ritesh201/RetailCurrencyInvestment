package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    LocalDate date;
    String ticker;
    TransactionType type;
    int quantity;
    BigDecimal localPrice;
    String currency;

    public Transaction(LocalDate date, String ticker, TransactionType type,
                       int quantity, BigDecimal localPrice, String currency) {
        this.date = date;
        this.ticker = ticker;
        this.type = type;
        this.quantity = quantity;
        this.localPrice = localPrice;
        this.currency = currency;
    }
}

