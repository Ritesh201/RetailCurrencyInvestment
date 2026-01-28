package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Transaction> transactions = List.of(
                new Transaction(LocalDate.of(2026,1,12), "TATASTEEL",
                        TransactionType.BUY, 10, new BigDecimal("150"), "EUR"),
                new Transaction(LocalDate.of(2026,1,15), "TATASTEEL",
                        TransactionType.SELL, 5, new BigDecimal("155"), "EUR"),
                new Transaction(LocalDate.of(2026,1,22), "RELIANCE",
                        TransactionType.BUY, 3, new BigDecimal("700"), "INR")
        );

        List<ExchangeRate> rates = List.of(
                new ExchangeRate(LocalDate.of(2026,1,12), "EUR",
                        new BigDecimal("1.10")),
                new ExchangeRate(LocalDate.of(2026,1,15), "EUR",
                        new BigDecimal("1.12")),
                new ExchangeRate(LocalDate.of(2026,1,22), "INR",
                        new BigDecimal("1.30"))
        );

        Map<String, ShareHolding> netHoldings =
                PortfolioManager.processTransactions(transactions, rates);

        PortfolioSummary.printStatement(netHoldings);
    }
}