import java.text.DecimalFormat;
import java.util.Scanner;

public class InvestmentCalculator {
    public static final DecimalFormat df = new DecimalFormat("0.00");

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
