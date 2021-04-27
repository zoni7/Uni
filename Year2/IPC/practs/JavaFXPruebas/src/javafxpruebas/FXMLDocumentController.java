/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxpruebas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 *
 * @author Soledad
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Circle myCircle;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
    /*  myCircle.setCenterX(0);
      myCircle.setCenterY(0);
      myCircle.setTranslateX(50);
      myCircle.setTranslateY(50);*/
      GridPane.setConstraints(myCircle, 0, 0);
      TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300) , myCircle);   
      translateTransition.setToX(myCircle.getCenterX());
      translateTransition.setToY(myCircle.getCenterY()+100);
      translateTransition.play();
     
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
