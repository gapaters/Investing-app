import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by gareth on 06/12/16.
 */
public class AnalyticsGUI {
    private JPanel mainWindow;
    private JLabel numberOfSharesLabel;
    private JTextField numberOfSharesInput;
    private JButton numberOfSharesEnter;
    private JLabel sharePriceLabel;
    private JTextField sharePriceInput;
    private JButton sharePriceEnter;
    private JLabel fiftyTwoWeekHighLabel;
    private JTextField fiftyTwoWeekHighInput;
    private JButton fiftyTwoWeekHighEnter;
    private JLabel fiftyTwoWeekLowLabel;
    private JTextField fiftyTwoWeekLowInput;
    private JButton fiftyTwoWeekLowEnter;
    private JLabel totalAssetsLabel;
    private JTextField totalAssetsInput;
    private JButton totalAssetsEnter;
    private JLabel totalLiabilitiesLabel;
    private JTextField totalLiabilitiesInput;
    private JButton totalLiabilitiesEnter;
    private JTextField netIncomeInput;
    private JLabel netIncomeLabel;
    private JButton netIncomeEnter;
    private JLabel dividendsPayableLabel;
    private JTextField dividendsPayableInput;
    private JButton dividendsPayableEnter;
    private JLabel dataTitle;
    private JButton calculateButton;
    private JLabel analyticsResultsLabel;
    private JLabel priceBookLabel;
    private JLabel pricePerBookResult;
    private JLabel priceEarningsLabel;
    private JLabel pricePerEarningsResult;
    private JLabel dividendPercentageLabel;
    private JLabel dividendPercentageResult;
    private JLabel dividendSafetyLabel;
    private JLabel dividendsSafetyResult;

    Analytics analytics = new Analytics();

    public static void main(String[] args) {
        JFrame frame = new JFrame("AnalyticsGUI");
        frame.setContentPane(new AnalyticsGUI().mainWindow);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public AnalyticsGUI() {
        numberOfSharesEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setNumberOfShares(Long.parseLong(numberOfSharesInput.getText()));
            }
        });
        sharePriceEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setSharePrice(Double.parseDouble(sharePriceInput.getText()));
            }
        });
        fiftyTwoWeekHighEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setFiftyTwoWeekHigh(Double.parseDouble(fiftyTwoWeekHighInput.getText()));
            }
        });
        fiftyTwoWeekLowEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setFiftyTwoWeekLow(Double.parseDouble(fiftyTwoWeekLowInput.getText()));
            }
        });
        totalAssetsEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setTotalAssets(Long.parseLong(totalAssetsInput.getText()));
            }
        });
        totalLiabilitiesEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setTotalLiabilities(Long.parseLong(totalLiabilitiesInput.getText()));
            }
        });
        netIncomeEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setNetIncome(Long.parseLong(netIncomeInput.getText()));
            }
        });
        dividendsPayableEnter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setDividendsPayable(Long.parseLong(dividendsPayableInput.getText()));
            }
        });
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                analytics.setPricePerBook();
                analytics.setPricePerEarnings();
                analytics.setDividendPercentage();
                analytics.setdividendSafety();

                pricePerBookResult.setText(Double.toString(analytics.getPricePerBook()));
                pricePerEarningsResult.setText(Double.toString(analytics.getPricePerEarnings()));
                dividendPercentageResult.setText(Double.toString(analytics.getDividendPercentage()));
                dividendsSafetyResult.setText(Double.toString(analytics.getdividendSafety()));
            }
        });
    }
}
