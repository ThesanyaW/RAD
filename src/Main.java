import java.util.Scanner;
import javax.crypto.SecretKey;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Currency Exchange");
            System.out.println("2. Investment Calculator");
            System.out.println("3. Client Data Manager");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    handleCurrencyExchange(scanner);
                    break;
                case 2:
                    handleInvestmentCalculator(scanner);
                    break;
                case 3:
                    handleClientDataManager(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void handleCurrencyExchange(Scanner scanner) {
        System.out.println("Enter base currency (e.g., GBP, USD, EUR, JPY, CAD, AUD): ");
        String fromCurrency = scanner.next().toUpperCase();

        System.out.println("Enter target currency (e.g., GBP, USD, EUR, JPY, CAD, AUD): ");
        String toCurrency = scanner.next().toUpperCase();

        System.out.println("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double convertedAmount = CurrencyExchange.convertCurrency(fromCurrency, toCurrency, amount);
        double fee = CurrencyExchange.calculateFee(amount);

        System.out.println("Converted Amount: " + CurrencyExchange.getDecimalFormat().format(convertedAmount));
        System.out.println("Transaction Fee: " + CurrencyExchange.getDecimalFormat().format(fee));
    }

    private static void handleInvestmentCalculator(Scanner scanner) {
        System.out.println("Enter initial investment amount: ");
        double initialAmount = scanner.nextDouble();

        System.out.println("Enter monthly contribution: ");
        double monthlyContribution = scanner.nextDouble();

        System.out.println("Enter investment type (1 for Basic Savings Plan, 2 for Growth Savings Plan, 3 for Advanced Portfolio Plan): ");
        int investmentType = scanner.nextInt();

        int years = 10; // Example period
        double annualReturnRate;
        double taxRate;
        double monthlyFee;
        double maxInvestment;
        double minMonthlyContribution;

        switch (investmentType) {
            case 1:
                annualReturnRate = 1.5; // Average return for Basic Savings Plan
                taxRate = 0;
                monthlyFee = 0.2;
                maxInvestment = 25000;
                minMonthlyContribution = 50;
                break;
            case 2:
                annualReturnRate = 4.5; // Average return for Growth Savings Plan
                taxRate = 5;
                monthlyFee = 0.4;
                maxInvestment = 40000;
                minMonthlyContribution = 100;
                break;
            case 3:
                annualReturnRate = 10; // Average return for Advanced Portfolio Plan
                taxRate = 17.5; // Average tax rate on profits
                monthlyFee = 1;
                maxInvestment = Double.MAX_VALUE; // Unlimited
                minMonthlyContribution = 250;
                break;
            default:
                System.out.println("Invalid investment type selected.");
                return;
        }

        if (initialAmount > maxInvestment) {
            System.out.println("Initial investment exceeds the maximum limit for the selected investment type.");
            return;
        }

        if (monthlyContribution < minMonthlyContribution) {
            System.out.println("Monthly contribution is below the minimum required for the selected investment type.");
            return;
        }

        double[] results = InvestmentCalculator.calculateInvestment(initialAmount, monthlyContribution, years, annualReturnRate, taxRate, monthlyFee);
        System.out.println("Total Amount: £" + InvestmentCalculator.df.format(results[0]));
        System.out.println("Total Profit After Tax: £" + InvestmentCalculator.df.format(results[1]));
        System.out.println("Total Tax: £" + InvestmentCalculator.df.format(results[2]));
    }

    private static void handleClientDataManager(Scanner scanner) {
        try {
            SecretKey secretKey = ClientDataManager.generateKey();

            System.out.println("Enter data to encrypt: ");
            String data = scanner.next();

            String encryptedData = ClientDataManager.encrypt(data, secretKey);
            System.out.println("Encrypted Data: " + encryptedData);

            String decryptedData = ClientDataManager.decrypt(encryptedData, secretKey);
            System.out.println("Decrypted Data: " + decryptedData);

            System.out.println("Enter name: ");
            String name = scanner.next();

            System.out.println("Enter email: ");
            String email = scanner.next();

            ClientDataManager.storeUserProfile(name, email, encryptedData);
            ClientDataManager.retrieveUserProfile(email);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An error occurred", e);
        }
    }
}
