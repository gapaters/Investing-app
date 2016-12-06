/**
 * Created by gareth on 23/11/16.
 */
import javafx.application.Application;
import java.util.Scanner;
import static java.lang.System.out;
import static java.lang.System.err;

public class Analytics{
    private long numberOfShares, totalAssets, totalLiabilities, netIncome, dividendsPayable;
    private double sharePrice, fiftyTwoWeekHigh, fiftyTwoWeekLow, pricePerBook, pricePerEarnings, dividendPercentage, dividendSafety;

    public static void main(String[] args) {
        Analytics analytics = new Analytics();
        Scanner scanner = new Scanner(System.in);
        out.println("This is a lightweight script for generating simple investing analytics. "+
                "\nPlease enter the following information as numerical values.\n");

        while (true) {
            try {
                out.println("Enter the total number of shares floated: ");
                String input = scanner.nextLine();
                analytics.setNumberOfShares(Long.parseLong(input));

                out.println("Enter the share price: ");
                input = scanner.nextLine();
                analytics.setSharePrice(Double.parseDouble(input));

                out.println("Enter the 52 week high: ");
                input = scanner.nextLine();
                analytics.setFiftyTwoWeekHigh(Double.parseDouble(input));

                out.println("Enter the 52 week low: ");
                input = scanner.nextLine();
                analytics.setFiftyTwoWeekLow(Double.parseDouble(input));

                out.println("Enter the total assets: ");
                input = scanner.nextLine();
                analytics.setTotalAssets(Long.parseLong(input));

                out.println("Enter the total liabilities: ");
                input = scanner.nextLine();
                analytics.setTotalLiabilities(Long.parseLong(input));

                out.println("Enter the net income over the last 12 months: ");
                input = scanner.nextLine();
                analytics.setNetIncome(Long.parseLong(input));

                out.println("Enter the dividends payable: ");
                input = scanner.nextLine();
                analytics.setDividendsPayable(Long.parseLong(input));
                break;
            } catch (NumberFormatException e) {
                err.println("You have entered a non-numerical character, " +
                "please enter the information again");
            }
        }

        analytics.setPricePerBook(analytics.getNumberOfShares(), analytics.getSharePrice(),
                analytics.getTotalAssets(), analytics.getTotalLiabilities());
        analytics.setPricePerEarnings(analytics.getNumberOfShares(), analytics.getSharePrice(),
                analytics.getNetIncome());
        analytics.setDividendPercentage(analytics.getDividendsPayable(), analytics.getNumberOfShares(),
                analytics.getSharePrice());
        analytics.setdividendSafety(analytics.getDividendsPayable(), analytics.getNetIncome());

        analytics.printValues();
        PriceGaugeBarGraph graph = new PriceGaugeBarGraph();
        graph.setHigh(analytics.getFiftyTwoWeekHigh());
        graph.setCurrent(analytics.getSharePrice());
        graph.setLow(analytics.getFiftyTwoWeekLow());
        Application.launch(PriceGaugeBarGraph.class);
    }

    public long getNumberOfShares() {
        return numberOfShares;
    }

    public void setNumberOfShares(long numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public long getTotalAssets() {
        return totalAssets;
    }

    public void setTotalAssets(long totalAssets) {
        this.totalAssets = totalAssets;
    }

    public long getTotalLiabilities() {
        return totalLiabilities;
    }

    public void setTotalLiabilities(long totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }

    public long getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(long netIncome) {
        this.netIncome = netIncome;
    }

    public long getDividendsPayable() {
        return dividendsPayable;
    }

    public void setDividendsPayable(long dividendsPayable) {
        this.dividendsPayable = dividendsPayable;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(double sharePrice) {
        this.sharePrice = sharePrice;
    }

    public double getFiftyTwoWeekHigh() {
        return fiftyTwoWeekHigh;
    }

    public void setFiftyTwoWeekHigh(double fiftyTwoWeekHigh) {
        this.fiftyTwoWeekHigh = fiftyTwoWeekHigh;
    }

    public double getFiftyTwoWeekLow() {
        return fiftyTwoWeekLow;
    }

    public void setFiftyTwoWeekLow(double fiftyTwoWeekLow) {
        this.fiftyTwoWeekLow = fiftyTwoWeekLow;
    }

    public double getPricePerBook() {
        return pricePerBook;
    }

    public void setPricePerBook(long shares, double price, double assets, double liabilities) {
        try{
            pricePerBook = (shares * price) / (assets - liabilities);
        } catch (ArithmeticException e) {
            pricePerBook = Double.NaN;
        }
    }

    public double getPricePerEarnings() {
        return pricePerEarnings;
    }

    public void setPricePerEarnings(long shares, double price, long income) {
        try {
            pricePerEarnings = (shares * price) / income;
        } catch (ArithmeticException e) {
            pricePerEarnings = Double.NaN;
        }
    }

    public double getDividendPercentage() {
        return dividendPercentage;
    }

    public void setDividendPercentage(long payable, long shares, double price) {
        try {
            dividendPercentage = payable / (shares * price);
        } catch (ArithmeticException e) {
            dividendPercentage = Double.NaN;
        }
    }

    public double getdividendSafety() {
        return dividendSafety;
    }

    public void setdividendSafety(long payable, long income) {
        try {
            dividendSafety = payable / income;
        } catch (ArithmeticException e) {
            dividendSafety = Double.NaN;
        }
    }

    public void printValues() {
        if (Double.isNaN(pricePerBook) || Double.isInfinite(pricePerBook)) {
            out.println("The price/book is undefined");
        } else {
            out.printf("The price/book is %f\n", pricePerBook);
        }

        if (Double.isNaN(pricePerEarnings) || Double.isInfinite(pricePerEarnings)) {
            out.println("The price/earnings is undefined");
        } else {
            out.printf("The price/earnings is %f\n", pricePerEarnings);
        }

        if (Double.isNaN(dividendPercentage) || Double.isInfinite(dividendPercentage)) {
            out.println("The dividend percentage is undefined");
        } else {
            out.printf("The dividend percentage is %f\n", dividendPercentage);
        }

        if (Double.isNaN(dividendSafety) || Double.isInfinite(dividendSafety)) {
            out.println("The dividend safety is undefined");
        } else {
            out.printf("The dividend safety is %f\n", dividendPercentage);
        }
    }
}
