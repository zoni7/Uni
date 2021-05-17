/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialogs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;

/**
 *
 * @author USUARIO
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button button;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void alert(ActionEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        // or AlertType.WARNING or AlertType.ERROR or AlertType.CONFIRMATION
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Header");
        // or null if we do not want a header
        alert.setContentText("Here goes the text of the message");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
        System.out.println("OK");
        } else {
        System.out.println("CANCEL");
}
        
    }

    @FXML
    private void choice(ActionEvent event) {
        // create a list with its options
        List<String> choices = new ArrayList<>();
        choices.add("one");
        choices.add("two");
        choices.add("three");
        
        // Create the choice dialog
        ChoiceDialog<String> dialog = new ChoiceDialog<>("two", choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Header");
        dialog.setContentText("Choose a number");
        Optional<String> result = dialog.showAndWait();
        // Before Java 8
        if (result.isPresent()) {
        System.out.println("Your choice: " + result.get());
        }
        // Getting the result with a lambda
        result.ifPresent(number-> System.out.println("Your choice: " + number));
    }

    @FXML
    private void imput(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog("John"); // Default value
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Header");
        dialog.setContentText("Enter your name:");
        Optional<String> result = dialog.showAndWait();
        // Obtain the result (before Java 8)
        if (result.isPresent()){
        System.out.println("Hello " + result.get());
        }
        // Obtain the result with a lambda expression (Java 8 and later)
        result.ifPresent(name -> System.out.println("Hello " + name));
    }
    
}
