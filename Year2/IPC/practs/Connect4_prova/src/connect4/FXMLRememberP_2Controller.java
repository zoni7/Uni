/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import model.Player;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLRememberP_2Controller implements Initializable {

    @FXML
    private TextField txtCode;
    @FXML
    private Button bOK;

    private Player player;
    //stored as attribute so it is easier to retrieve and compare to input later
    private String generatedCode;
        
    @FXML
    private Hyperlink txtResend;
    @FXML
    private Label txtError;
    @FXML
    private Button bCancel;
    
    private String currentCSS;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // bind Accept button to the empty Code text
        
        bindings();

    }
    
    public void initData(String currentCSS) {
        this.currentCSS = currentCSS;
    }
    
    /**
     * Bindings
     */
    private void bindings() {        
        // BooleanBindings to txtCode
         BooleanBinding codeEmpty = txtCode.textProperty().isEmpty() ;       
         // Bindings for Accept button
         bOK.disableProperty().bind(codeEmpty);
        
    }        
    
    /**
     * Gets the player who doesn't remember the password
     * @param p 
     */
    public void initData(Player p, String code) {
        player = p;
        generatedCode = code;
    }
    /**
     * Methods to close the window and Accept
     */
    private void performAccept() {
        if (txtCode.textProperty().getValue().equals(generatedCode)) {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Your password is :  " + player.getPassword());
            alert.initOwner(bOK.getScene().getWindow());
            Optional<ButtonType> result = alert.showAndWait();

            bOK.getScene().getWindow().hide();
        } else txtError.textProperty().setValue("The code is not correct");
    }
    @FXML
    private void handleEnterAccept(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
            performAccept();
    }

    @FXML
    private void handleButtonAccept(ActionEvent event) {
        performAccept();
    }
    /**
     * methods to Resend a new code 
     */
    private String generateCode() {
       
        
        //if (this.generatedCode == null) {
        String s = Long.toHexString(Double.doubleToLongBits(Math.random()));
        this.generatedCode= s.substring(s.length()-5,s.length());
        //}

        return generatedCode;
    }
    
    private void performResend() {
        // Alert the user about the codecgenerated
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Your code is:  " + generateCode());
        alert.initOwner(bOK.getScene().getWindow());
        alert.initModality(Modality.NONE);
        alert.show();        
    }
    
    @FXML
    private void handleEnterResend(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
            performResend();
    }

    @FXML
    private void handleButtonResend(ActionEvent event) {
        performResend();
    }
    
    /**
     * methods to close the window without doing nothing
     */
    private void performCancel() {
        bCancel.getScene().getWindow().hide();
    }
    @FXML
    private void handleEnterCancel(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
            performCancel();
    }

    @FXML
    private void handleButtonCancel(ActionEvent event) {        
        performCancel();
    }
}
