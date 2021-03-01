/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiktaktoepract1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author USUARIO
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button bReset;
    @FXML
    private Button bStart;
    private Button bTable;
    @FXML
    private TextField txtScore1;
    @FXML
    private TextField txtScore2;
    @FXML
    private Button bTable0_1;
    @FXML
    private Button bTable1_0;
    @FXML
    private Button bTable2_1;
    @FXML
    private Button bTable0_2;
    @FXML
    private Button bTable1_2;
    @FXML
    private Button bTable2_2;
    @FXML
    private Button bTable0_0;
    @FXML
    private Button bTable2_0;
    @FXML
    private Button bTable1_1;
    
    private boolean turn = true;
    
    
    class BConvenientInnerActionHandler implements  EventHandler<ActionEvent> {
        @Override

        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();
            if (clickedButton.textProperty().getValue().isEmpty()){
                if(turn) { 
                    clickedButton.textProperty().setValue("X"); 
                    turn = false;
                }
                else { 
                    clickedButton.textProperty().setValue("O");
                    turn = true;
                }
            }
            }
        }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        public void handle(ActionEvent event) {
            Button clickedButton = (Button) event.getSource();
            if (clickedButton.textProperty().getValue().isEmpty()){
                if(turn) { 
                    clickedButton.textProperty().setValue("X"); 
                    turn = false;
                }
                else { 
                    clickedButton.textProperty().setValue("O");
                    turn = true;
                }
            }
            }
    }    

    @FXML
    private void startGame(ActionEvent event) {
        txtScore1.textProperty().setValue("0");
        txtScore2.textProperty().setValue("0");
        
        bTable0_0.textProperty().setValue("");
        bTable1_0.textProperty().setValue("");
        bTable2_0.textProperty().setValue("");
        
        bTable0_1.textProperty().setValue("");
        bTable1_1.textProperty().setValue("");
        bTable2_1.textProperty().setValue("");
        
        bTable0_2.textProperty().setValue("");
        bTable1_2.textProperty().setValue("");
        bTable2_2.textProperty().setValue("");
        
    }
    
    
}
