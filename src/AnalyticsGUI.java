import com.sun.javafx.application.PlatformImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gareth on 06/12/16.
 */
public class AnalyticsGUI extends JPanel{
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
    static JFrame frame;
    JFXPanel jfxPanel;

    public static void main(String[] args) {
        frame = new JFrame("AnalyticsGUI");
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

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        PriceGaugeBarGraph barGraph = new PriceGaugeBarGraph();
                        barGraph.setVisible(true);
                    }
                });
            }
        });
    }


    class PriceGaugeBarGraph extends JFrame {
        JFXPanel jfxPanel;

        public PriceGaugeBarGraph(){
            initSwingComponents();
            initFxComponents();
        }

        private void initSwingComponents(){
            JPanel graphPanel = new JPanel(new BorderLayout());
            jfxPanel = new JFXPanel();
            graphPanel.add(jfxPanel, BorderLayout.CENTER);
            JLabel title = new JLabel("test label");
            graphPanel.add(title, BorderLayout.NORTH);

            this.add(graphPanel);
            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(800,400);
        }

        private void initFxComponents(){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    GridPane grid = new GridPane();
                    Scene scene = new Scene(grid, 800,400);

                    final CategoryAxis xAxis = new CategoryAxis();
                    final NumberAxis yAxis = new NumberAxis();
                    final BarChart<String, Number> bc =
                            new BarChart<String, Number>(xAxis, yAxis);
                    bc.setTitle("52 Week Price Gauge");
                    xAxis.setLabel("Price Point");
                    yAxis.setLabel("Price");

                    XYChart.Series series1 = new XYChart.Series();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                    Date date = new Date();
                    series1.setName(dateFormat.format(date));

                    XYChart.Data<String, Number> dataHigh = new XYChart.Data<String, Number>("52 Week High", analytics.getFiftyTwoWeekHigh());
                    XYChart.Data<String, Number> dataCurrent = new XYChart.Data<String, Number>("Current Price", analytics.getSharePrice());
                    XYChart.Data<String, Number> dataLow = new XYChart.Data<String, Number>("52 Week Low", analytics.getFiftyTwoWeekLow());
                    series1.getData().add(dataHigh);
                    series1.getData().add(dataCurrent);
                    series1.getData().add(dataLow);

                    bc.getData().addAll(series1);
                    grid.add(bc,0,0);
                    jfxPanel.setScene(scene);
                    dataHigh.getNode().setStyle("-fx-bar-fill: red;");
                    dataCurrent.getNode().setStyle("-fx-bar-fill: green;");
                    dataLow.getNode().setStyle("-fx-bar-fill: blue;");
                }
            });
        }
    }
}
