/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLAvatarPickerController implements Initializable {

    @FXML
    private Button bOk;
    @FXML
    private Button bClose;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView avatar_0_0;
    @FXML
    private ImageView avatar_1_2;
    @FXML
    private ImageView avatar_0_1;
    @FXML
    private ImageView avatar_0_2;
    @FXML
    private ImageView avatar_1_1;
    @FXML
    private ImageView avatar_1_0;
    
    private Image myAvatar;
    
    private FXMLSignUpController signUpController;
    
    private FXMLModifyPController modifyPController;
    @FXML
    private TextField txtPath;
    @FXML
    private Button bUpload;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize bindings
        //bindings();
        // Disable without using bindings
        // It is enabled when the user picks a
        bOk.setDisable(true);
    }  
    
    public void initData(FXMLSignUpController c) {
        signUpController = c;
    }
    
    public void initDataModify(FXMLModifyPController c) {
        modifyPController = c;
    }
    
    /*  WORK IN PROGRESS
    private void bindings() {
        
        bOk.disableProperty().bind(new SimpleBooleanProperty(myAvatar.equals(null)));
    }
      */ 
    
    
    /**
     * Method to select an Avatar from the ones given by the game
     */
    @FXML
    private void handlerOnMouseClick(MouseEvent event) {
        try {
            ImageView source = (ImageView )event.getTarget();
            myAvatar = source.getImage();  
            //source.setStyle("-fx-border-width: 16px;");
            // Enable the button accept when an avatar is selected
            bOk.setDisable(false);
        } catch (ClassCastException e) { }
       
    }   
    
    /**
     * Methods to upload an image from your computer
     */
    private void performOpenImage(Event event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open resource");
        fileChooser.getExtensionFilters().addAll(           
            new ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog( 
            ((Node)event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            txtPath.setText(selectedFile.getAbsolutePath());
            myAvatar = new Image("file:" + selectedFile.getAbsolutePath());
            bOk.setDisable(false);
        } 
        
    }
    @FXML
    private void handleEnterOpenImage(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)     
            performOpenImage(event);
    }

    @FXML
    private void handleButtonOpenImage(ActionEvent event) {
        performOpenImage(event);
    }
    /**
     * Methods to send the image to the previous window and close the actual one
     */
    private void performAccept() {
        if (signUpController != null) {
            signUpController.updateAvatar(myAvatar);
        } else {
            modifyPController.updateAvatar(myAvatar);
        }
        //close window, return Player object            
        bOk.getScene().getWindow().hide();
    }
    
    
    @FXML
    private void handleButtonAccept(ActionEvent event) {
        performAccept();    
    }
    
    @FXML
    private void handleEnterAccept(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)     
            performAccept();
    }   
    /**
     * Methods to close the windows
     */
    private void performCancel() {
        //close window, return Player object            
        bClose.getScene().getWindow().hide();
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
