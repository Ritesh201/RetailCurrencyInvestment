package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExchangeRate {
    LocalDate date;
    String currency;
    BigDecimal rateToUSD;

    public ExchangeRate(LocalDate date, String currency, BigDecimal rateToUSD) {
        this.date = date;
        this.currency = currency;
        this.rateToUSD = rateToUSD;
    }
}
