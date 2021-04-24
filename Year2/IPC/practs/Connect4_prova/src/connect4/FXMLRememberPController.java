/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Player;
import model.Connect4;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLRememberPController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private Button bOK;
    @FXML
    private Label onErrorLabel;
    @FXML
    private Button bClose;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bOK.setOnMouseClicked((MouseEvent m) -> {
            
         
            try {
            model.Connect4 api = model.Connect4.getSingletonConnect4();
            
                
            
            
            String usr = txtUsername.textProperty().getValue();
            String email = txtEmail.textProperty().getValue();
            
            if (!api.exitsNickName(usr)) {
                onErrorLabel.textProperty().set("This username does not exist. Please check your details");
                //exit
                return;
            }
            
            Player user = api.getPlayer(usr);
            
            if (!email.equals(user.getEmail()) ) {
                onErrorLabel.textProperty().set("This email does not correspond to the provided username");
                return;
            }
            
            onErrorLabel.textProperty().set("An Email has been sent to reset your password");
            } catch (Connect4DAOException ex) {
            }
            
            
        });
                
           bClose.setOnMouseClicked((MouseEvent event) -> {bClose.getScene().getWindow().hide();});
    }    
    
}
