/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pract6_2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLController implements Initializable {

    @FXML
    private LineChart<?, ?> lineChart;
    @FXML
    private NumberAxis lineChart_yAxis;
    @FXML
    private CategoryAxis lineChart_xAxis;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private NumberAxis barChart_yAxis;
    @FXML
    private CategoryAxis barChart_xAxis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int hist[] = generate_histogram(10);
        int hist2[] = generate_histogram(10);
        int hist3[] = generate_histogram(10);
        int hist4[] = generate_histogram(10);
        
        // Declare and initialize the observable lists
        ObservableList<PieChart.Data> pie_chart_data = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String,Number>> data = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String,Number>> data2 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String,Number>> data3 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<String,Number>> data4 = FXCollections.observableArrayList();
        for (int i = 0; i < hist.length; i++)
        {
            String aux = i + "-" + (i+1);
            data.add(new XYChart.Data<String,Number>(aux, hist[i]));
            data2.add(new XYChart.Data<String,Number>(aux, hist2[i]));
            data3.add(new XYChart.Data<String,Number>(aux, hist3[i]));
            data4.add(new XYChart.Data<String,Number>(aux, hist4[i]));
            pie_chart_data.add ( new PieChart.Data(aux, hist[i]) );
        }
        
        
        // XYChart.Series used in LineChart
        XYChart.Series s1 = new XYChart.Series(data);
        s1.setName("hist1");
        XYChart.Series s2 = new XYChart.Series(data2);
        s2.setName("hist2");
        
        // Populate the LineChart
        lineChart.getData().addAll(s1, s2);
        // LineChart Axes names
        lineChart_xAxis.setLabel("Ranges");
        lineChart_yAxis.setLabel("Frequencies");
        
        // XYChart.Series used in BarChart
        XYChart.Series s3 = new XYChart.Series(data3);
        s1.setName("hist3");
        XYChart.Series s4 = new XYChart.Series(data4);
        s2.setName("hist4");
        
        // Populate the BarChart
        barChart.getData().addAll(s3, s4);
        // BarChart Axes names
        barChart_xAxis.setLabel("Ranges");
        barChart_yAxis.setLabel("Frequencies");
    }    
    
    public int[] generate_histogram(int lenght ){
        int hist[] = new int[lenght];
        // Generate histogram
        for (int j = 0; j < 1000; j++) {
            double value = Math.random() * lenght;
            for (int i = 0; i < hist.length; i++){
                if (i <= value && value < i+1) {
                    hist[(int)i]++;
                    break;
                }
            }
        }
        return hist;
}
    
}
