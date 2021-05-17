/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
    
    private String currentCSS;
    
    private String generatedCode;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        bindings();
    }
    
    public void initData(String currentCSS) {
        this.currentCSS = currentCSS;
    }
    
     /**
     * Bindings
     */
    private void bindings() {        
        // BooleanBindings to Username and password text fields
         BooleanBinding usrEmpty = txtUsername.textProperty().isEmpty() ;
         BooleanBinding eEmpty = txtEmail.textProperty().isEmpty();
         // Bindings for Accept button
         bOK.disableProperty().bind(usrEmpty.or(eEmpty));         
    }  
    
    private String generateCode() {
       
        
        //if (this.generatedCode == null) {
        String s = Long.toHexString(Double.doubleToLongBits(Math.random()));
        this.generatedCode= s.substring(s.length()-5,s.length());
        //}

        return generatedCode;
    }
    
    /**
     * Methods to close the window and Accept
     */
    private void performAccept() throws IOException {
    
        try {
        model.Connect4 api = model.Connect4.getSingletonConnect4();
        
        String usr = txtUsername.textProperty().getValue();
        String email = txtEmail.textProperty().getValue();
        
        // Check if the username typed is not correct
        if (!api.exitsNickName(usr)) {
            onErrorLabel.textProperty().set("Wrong username or email. Please check your details");
            //exit
            return;
        }

        Player user = api.getPlayer(usr);
        // Check if the email typed is not correct
        if (!email.equals(user.getEmail()) ) {
            onErrorLabel.textProperty().set("Wrong username or email. Please check your details");
            return;
        }
        
        Alert alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("An Email has been sent to reset your password.");
        alert.initOwner(bOK.getScene().getWindow());
        Optional<ButtonType> result = alert.showAndWait(); 
        
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLRememberP_2.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLRememberP_2Controller detailsController = myLoader.<FXMLRememberP_2Controller>getController();
        
        generateCode();
        detailsController.initData(user,generatedCode);

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Remember Password");
        stage.getIcons().add(new Image("images/Icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());
        
                // Alert the user about the codecgenerated
        alert= new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Your code is:  " + generatedCode);
        alert.initModality(Modality.NONE);
        alert.initOwner(stage);
        stage.show();
        alert.show();
        } catch (Connect4DAOException ex) {
        }
        // Close the window
        performCancel();
    }
    
    @FXML
    private void handleButtonAccept(ActionEvent event) throws IOException {
        performAccept();
    }
    
    @FXML
    private void handleEnterAccept(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER)   
            performAccept();
    }
    
    /**
     * Methods to cancel the operation
     */
    private void performCancel() {
        bClose.getScene().getWindow().hide();
    }
    
    @FXML
    private void handleButtonCancel(ActionEvent event) {
        performCancel();
    }
    
    @FXML
    private void handleEnterCancel(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)       
            performCancel();
    }
}
