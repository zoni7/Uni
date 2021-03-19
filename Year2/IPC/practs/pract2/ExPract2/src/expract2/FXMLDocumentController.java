/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expract2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import javax.print.attribute.standard.MediaSize.Other;

/**
 *
 * @author USUARIO
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Slider mySlider;
    @FXML
    private Label myRate;
    @FXML
    private TextField myInput;
    @FXML
    private TextField myOutput;
    @FXML
    private Button bConvert;
    @FXML
    private Button bClear;
    @FXML
    private CheckBox myCheckBox;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Enlazamaos el valor de la label a lo que valga el campo de edición
        //Bind the label value with the edit field
        //labelValue.textProperty().bind(textFieldValue.textProperty());
        
        //El campo de edición y el slider están conectados, un cambio en el valor de uno repercute en el otro
        //The edit field and the slider are connected, a change in the value of them affects the other one
        myRate.textProperty().bindBidirectional(mySlider.valueProperty(), new NumberStringConverter());
        
    }    

    @FXML
    private void convert(ActionEvent event) {
        try{
            
            double num1 = Double.parseDouble(myInput.textProperty().getValue());
            double num2 = mySlider.valueProperty().getValue();
            double op =Math.round((num1 * num2)* 100);
            String res = Double.toString(op / 100);
            myOutput.textProperty().setValue(res);
            
        } catch (Exception e) {
            System.out.println("No introducido un numero");
        }
    
    }

    @FXML
    private void clear(ActionEvent event) {
        myOutput.textProperty().setValue("");
        myInput.textProperty().setValue("");
        myRate.textProperty().setValue("");
    }

    @FXML
    private void automatize(ActionEvent event) {
        if (myCheckBox.isSelected()) {
            bConvert.setDisable(true);
            double num2 = Double.parseDouble(myInput.textProperty().getValue());
            NumberBinding num1 = mySlider.valueProperty().multiply(num2);
            myOutput.textProperty().bind(num1.asString());
            
            
        } else {
            bConvert.setDisable(false);
            myOutput.textProperty().unbind();
        }
    }
    
}
