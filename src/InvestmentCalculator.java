import java.text.DecimalFormat;
import java.util.Scanner;

public class InvestmentCalculator {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        double[] results = calculateInvestment(initialAmount, monthlyContribution, years, annualReturnRate, taxRate, monthlyFee);
        System.out.println("Total Amount: £" + df.format(results[0]));
        System.out.println("Total Profit After Tax: £" + df.format(results[1]));
        System.out.println("Total Tax: £" + df.format(results[2]));
    }

    public static double[] calculateInvestment(double initialAmount, double monthlyContribution, int years, double annualReturnRate, double taxRate, double monthlyFee) {
        double totalAmount = initialAmount;
        for (int month = 0; month < years * 12; month++) {
            totalAmount += monthlyContribution;
            totalAmount += totalAmount * (annualReturnRate / 100) / 12;
            totalAmount -= totalAmount * (monthlyFee / 100);
        }
        double totalProfit = totalAmount - (initialAmount + monthlyContribution * years * 12);
        double totalTax = totalProfit * (taxRate / 100);
        double totalProfitAfterTax = totalProfit - totalTax;
        return new double[]{totalAmount, totalProfitAfterTax, totalTax};
    }
}
