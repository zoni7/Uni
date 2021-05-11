/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pract6;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

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
    }    
    
}
