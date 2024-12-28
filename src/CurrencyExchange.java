import java.util.HashMap;
import java.util.Map;

public class CurrencyExchange {
    private static final double MIN_AMOUNT = 250;
    private static final double MAX_AMOUNT = 10000;

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
        double convertedAmount = (amount / fromRate) * toRate;

        return convertedAmount;
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

    public static void main(String[] args) {
        String fromCurrency = "USD";
        String toCurrency = "EUR";
        double amount = 1000;

        double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
        double fee = calculateFee(amount);

        System.out.println("Converted Amount: " + convertedAmount);
        System.out.println("Transaction Fee: " + fee);
    }
}
