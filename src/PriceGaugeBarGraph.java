import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PriceGaugeBarGraph extends Application {
    private static double high, low, current;

    @Override public void start(Stage stage) {
        stage.setTitle("52 Week Price Gauge");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("52 Week Price Gauge");
        xAxis.setLabel("Price Point");
        yAxis.setLabel("Price");

        XYChart.Series series1 = new XYChart.Series();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        series1.setName(dateFormat.format(date));

        final XYChart.Data<String,Number> dataHigh = new XYChart.Data<String,Number>("52 Week High", high);
        final XYChart.Data<String,Number> dataCurrent = new XYChart.Data<String,Number>("Current Price", current);
        final XYChart.Data<String,Number> dataLow = new XYChart.Data<String,Number>("52 Week Low", low);
        series1.getData().add(dataHigh);
        series1.getData().add(dataCurrent);
        series1.getData().add(dataLow);

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
        dataHigh.getNode().setStyle("-fx-bar-fill: red;");
        dataCurrent.getNode().setStyle("-fx-bar-fill: green;");
        dataLow.getNode().setStyle("-fx-bar-fill: blue;");
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }
}
