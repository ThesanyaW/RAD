import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

public class CurrencyExchange {
    private static final double MIN_AMOUNT = 250;
    private static final double MAX_AMOUNT = 10000;
    public static final DecimalFormat df = new DecimalFormat("0.00"); // Changed to public

    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        exchangeRates.put("GBP", 1.0);
        exchangeRates.put("USD", 1.35);
        exchangeRates.put("EUR", 1.18);
        exchangeRates.put("JPY", 150.0);
        exchangeRates.put("CAD", 1.70);
        exchangeRates.put("AUD", 1.85);
    }

    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("Amount must be between " + MIN_AMOUNT + " and " + MAX_AMOUNT);
        }

        double fromRate = exchangeRates.get(fromCurrency);
        double toRate = exchangeRates.get(toCurrency);
        return (amount / fromRate) * toRate;
    }

    public static double calculateFee(double amount) {
        if (amount <= 500) {
            return amount * 0.04;
        } else if (amount <= 2000) {
            return amount * 0.03;
        } else {
            return amount * 0.02;
        }
    }
}
