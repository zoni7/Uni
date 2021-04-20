/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.Player;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLLogoutController implements Initializable {

    @FXML
    private Button bNo;
    @FXML
    private Button bYes;
    
    private boolean isLeft;
    
    private ObservableList<Player> observableAux;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void initData(ObservableList<Player> lplayer, boolean isLeft) {
        this.isLeft =  isLeft;
        this.observableAux = lplayer;       
    }
    @FXML
    private void No(ActionEvent event) {
        // Close the screen
        bNo.getScene().getWindow().hide(); 
    }

    @FXML
    private void Yes(ActionEvent event) {
        if (isLeft){
                observableAux.remove(0);   
                // Close the screen
            bYes.getScene().getWindow().hide(); 
            } else {                
                
                observableAux.remove(1);
                // Close the screen
            bYes.getScene().getWindow().hide(); 
                
            }
    }
    
}
