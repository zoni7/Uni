/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exextrapract2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.text.Text;

/**
 *
 * @author USUARIO
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Label myNumber;
    @FXML
    private Button bPlay;
    @FXML
    private Button bCheck;
    @FXML
    private Label txtInput;
    @FXML
    private Slider hundredsSlider;
    @FXML
    private Slider tensSlider;
    @FXML
    private Slider UnitsSlider;
    @FXML
    private Label myInput1;
    @FXML
    private Label myInput2;
    @FXML
    private Label myInput3;
    @FXML
    private Label myInput;
    @FXML
    private Text txtResult;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Bind the enabled property of the Check button to the enabled property of the Play button
        bCheck.disableProperty().bind(bPlay.disableProperty().not());              
        
        int value;
        hundredsSlider.valueProperty().addListener((observable,oldValue,newValue) ->{
            hundredsSlider.setValue(newValue.intValue());
            myInput1.textProperty().setValue(newValue.intValue() + "");
        });
        tensSlider.valueProperty().addListener((observable,oldValue,newValue) ->{
            tensSlider.setValue(newValue.intValue());
            myInput2.textProperty().setValue(newValue.intValue() + "");
        });
        UnitsSlider.valueProperty().addListener((observable,oldValue,newValue) ->{
            UnitsSlider.setValue(newValue.intValue());
            myInput3.textProperty().setValue(newValue.intValue() + "");
        });
        
         //Adding a button handler using a convenient method and a lambda expression
         bCheck.setOnAction((event)->{
               String  res = myInput1.getText() + myInput2.getText() + myInput3.getText();
               String number = myNumber.getText();

               txtInput.textProperty().setValue("Input number: ");
               myInput.textProperty().setValue(res);
               // It is necessary to add 0s if the number only has two or one numbers 
               if (number.length() == 2) { number = 0 + number;}
               if (number.length() == 1) { number = 0 + 0 + number;}
               // Compare number and res
               if (number.equals(res)) { txtResult.textProperty().setValue("Well done!"); }
               else { txtResult.textProperty().setValue("Sorry, try again"); }
               // Change the button usability
               bPlay.setDisable(false);
         });
    }    

    @FXML
    private void playGame(ActionEvent event) {
        int value = (int) Math.round(Math.random()*999);
        myNumber.textProperty().setValue(value + "");
        
        bPlay.setDisable(true);
        // Reset the value of the sliders
        hundredsSlider.setValue(0);
        tensSlider.setValue(0);
        UnitsSlider.setValue(0);
    
    }

}
