package org.example;


import java.util.Map;
public class PortfolioSummary {

    public static void printStatement(Map<String, ShareHolding> holdings) {
        System.out.println("------ Portfolio Summary (USD) ------");
        for (Map.Entry<String, ShareHolding> entry : holdings.entrySet()) {
            ShareHolding currentShare = entry.getValue();
            if (currentShare.shares > 0) {
                System.out.println("Ticker: " + entry.getKey()
                        + " | Shares: " + currentShare.shares
                        + " | Net Investment (USD): "
                        + currentShare.totalUsdInvestment);
            }
        }
    }
}

