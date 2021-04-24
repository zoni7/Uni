/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    private Player[] playerArray;
    
    private Player player = null;
    
    private FXMLStartController stageStart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initDataLogOut(FXMLStartController s, Player[] a, boolean isLeft) {
        this.stageStart = s;
        this.isLeft =  isLeft;
        this.playerArray = a;       
    }
    
    @FXML
    private void handleButtonNo(ActionEvent event) {        
        // Close the screen
        bNo.getScene().getWindow().hide(); 
    }

    @FXML
    private void handleButtonYes(ActionEvent event) {
        if (isLeft) playerArray[0] = null;
        else playerArray[1] = null;
        
        // Close the screen
        bYes.getScene().getWindow().hide(); 
    }
    
}
