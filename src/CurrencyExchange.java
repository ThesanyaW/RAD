import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.text.DecimalFormat;

public class CurrencyExchange {
    private static final double MIN_AMOUNT = 250;
    private static final double MAX_AMOUNT = 10000;
    private static final DecimalFormat df = new DecimalFormat("0.00");

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter base currency (e.g., GBP, USD, EUR, JPY, CAD, AUD): ");
        String fromCurrency = scanner.next().toUpperCase();

        System.out.println("Enter target currency (e.g., GBP, USD, EUR, JPY, CAD, AUD): ");
        String toCurrency = scanner.next().toUpperCase();

        System.out.println("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        scanner.close();

        double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
        double fee = calculateFee(amount);

        System.out.println("Converted Amount: " + df.format(convertedAmount));
        System.out.println("Transaction Fee: " + df.format(fee));
    }
}
