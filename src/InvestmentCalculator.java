import java.text.DecimalFormat;

public class InvestmentCalculator {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        double initialAmount = 1000;
        double monthlyContribution = 100;
        int years = 10;
        double annualReturnRate = 5;
        double taxRate = 15;
        double monthlyFee = 1;

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
