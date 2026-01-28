package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        List<Transaction> transactions = List.of(
                new Transaction(LocalDate.of(2024,1,10), "AAPL",
                        TransactionType.BUY, 10, new BigDecimal("150"), "EUR"),
                new Transaction(LocalDate.of(2024,1,15), "AAPL",
                        TransactionType.SELL, 5, new BigDecimal("155"), "EUR"),
                new Transaction(LocalDate.of(2024,1,20), "TSLA",
                        TransactionType.SELL, 3, new BigDecimal("700"), "GBP")
        );

        List<ExchangeRate> rates = List.of(
                new ExchangeRate(LocalDate.of(2024,1,10), "EUR",
                        new BigDecimal("1.10")),
                new ExchangeRate(LocalDate.of(2024,1,15), "EUR",
                        new BigDecimal("1.12")),
                new ExchangeRate(LocalDate.of(2024,1,20), "GBP",
                        new BigDecimal("1.30"))
        );

        Map<String, ShareHolding> netHoldings =
                PortfolioManager.processTransactions(transactions, rates);

        PortfolioSummary.printStatement(netHoldings);
    }
}