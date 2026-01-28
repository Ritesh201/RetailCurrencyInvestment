package org.example;

import java.math.BigDecimal;
import java.util.*;

public class PortfolioManager {

    // Key: date + currency → exchange rate
    private static Map<String, BigDecimal> getMapOfRateConversion(List<ExchangeRate> exchangeRateList) {
        Map<String, BigDecimal> map = new HashMap<>();
        for (ExchangeRate exchangeRate : exchangeRateList) {
            map.put(exchangeRate.date + "-" + exchangeRate.currency, exchangeRate.rateToUSD);
        }
        return map;
    }

    public static Map<String, ShareHolding> processTransactions(
            List<Transaction> transactions,
            List<ExchangeRate> exchangeRates) {

        Map<String, BigDecimal> rateMap = getMapOfRateConversion(exchangeRates);
        Map<String, ShareHolding> holdings = new HashMap<>();

        for (Transaction currentTransaction : transactions) {
            String rateKey = currentTransaction.date + "-" + currentTransaction.currency;
            BigDecimal rateValue = rateMap.get(rateKey);

            if (rateValue == null) {
                System.err.println("Error: Missing exchange rate for " + rateKey);
                continue;
            }

            BigDecimal usdAmount = currentTransaction.localPrice
                    .multiply(BigDecimal.valueOf(currentTransaction.quantity))
                    .multiply(rateValue);

            ShareHolding currentPosition =
                    holdings.computeIfAbsent(currentTransaction.ticker, k -> new ShareHolding());

            if (currentTransaction.type == TransactionType.BUY) {
                currentPosition.shares += currentTransaction.quantity;
                currentPosition.totalUsdInvestment =
                        currentPosition.totalUsdInvestment.add(usdAmount);

            } else { // SELL
                if (currentPosition.shares < currentTransaction.quantity) {
                    System.err.println("Error: Short-sell attempt blocked for ticker: "
                            + currentTransaction.ticker);
                    continue;
                }
                currentPosition.shares -= currentTransaction.quantity;
                currentPosition.totalUsdInvestment =
                        currentPosition.totalUsdInvestment.subtract(usdAmount);
            }
        }
        return holdings;
    }
}


