/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Player;
import model.Connect4;


/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassdword;
    @FXML
    private Button bOk;
    @FXML
    private Label onWrongLogin;
    
    private Player loginUser; //this must be returned to the previous window using the observable
    
    private boolean isLeft;
    
    private Player[] playerArray; // loginUser is added here
    
    private String namePlayer;
    
    private FXMLGameBoardController controller;
    @FXML
    private Button bClose;
    @FXML
    private Hyperlink bRemember;
    @FXML
    private Hyperlink bSignUp;
    
    private String currentCSS;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        // Initialize bindings
        bindings();
    }    
    /**
     *  Initialize the data needed to move the loginUser to the main screen (Start)    
     */
    public void initData(Player[] a, FXMLGameBoardController c, boolean isLeft, String currentCSS) {
        this.isLeft =  isLeft;
        this.playerArray = a;
        this.controller = c;
        this.currentCSS = currentCSS;
        
    }
    /**
     * Bindings
     */
    private void bindings() {        
        // BooleanBindings to Username and password text fields
         BooleanBinding usrEmpty = txtUsername.textProperty().isEmpty() ;
         BooleanBinding pswEmpty = txtPassdword.textProperty().isEmpty();
         // Bindings for Accept button
         bOk.disableProperty().bind(usrEmpty.or(pswEmpty));         
    }
    /**
     * Opens a screen which will help to remember the user password
     * It will open FXMLRememberP
     */
    private void performRememberPsw() throws IOException{
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLRememberP.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLRememberPController detailsController = myLoader.<FXMLRememberPController>getController();          
        
        detailsController.initData(this.currentCSS);
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Remember password");
        stage.getIcons().add(new Image("images/Icon.png"));
        scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());

        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    private void rememberPassword(ActionEvent event) throws IOException {
        performRememberPsw();
    }
    
    @FXML
    private void EnterRememberPassword(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER)     
            performRememberPsw();
    }
    
    /**
     * Methods to close the windows and log in
     */
    private void performAccept() {
        //get user and password from input
        try{
            Connect4 api = Connect4.getSingletonConnect4();
            
            String usr = txtUsername.textProperty().getValue();
            String pwd = txtPassdword.textProperty().getValue();
                   
            
            //check user
            if (!api.exitsNickName(usr)) {
                // For security the program don't specify if the error is in username or in password
                onWrongLogin.textProperty().set("Wrong username or Password. Please check your details");
                //exit
                return;
            }
            loginUser = api.getPlayer(usr);
            
            //check pwd
            if (!loginUser.checkCredentials(usr,pwd)) {                
                // For security the program don't specify if the error is in username or in password
                onWrongLogin.textProperty().set("Wrong username or Password. Please check your details");
                //reset user
                loginUser = null;
                //exit
                return;
            }
            
            Player other;
            if (isLeft) {
                other = playerArray[1];
            } else {
                other = playerArray[0];
            }
            //Player does not have an equals method :(
            //so we use hashcode as a workaround
            if ( other != null && loginUser.hashCode() == other.hashCode()) {
                onWrongLogin.textProperty().set("That user is already logged in");
                loginUser = null;
                return;
            }
            
            //if we get here, everything was correct and we can get player
            
            loginUser = Connect4.getSingletonConnect4().loginPlayer(usr, pwd);
            if (isLeft){
               playerArray[0] = loginUser;                
            } else {                
               playerArray[1] = loginUser; 
            }
                        
        } catch(Connect4DAOException c4daoex) {c4daoex.printStackTrace();} 
        
        // Change the name of the default profile
        namePlayer = loginUser.getNickName();
        controller.initName(namePlayer, isLeft);
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
        bOk.getScene().getWindow().hide();
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

    private void performSignUp() throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLSignUp.fxml"));
        Pane root = (Pane) myLoader.load();

               
        FXMLSignUpController detailsController = myLoader.<FXMLSignUpController>getController();
        detailsController.initData(currentCSS);
        
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign up");
        scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());
        stage.getIcons().add(new Image("images/Icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML
    private void signUp(ActionEvent event) throws IOException {
        performSignUp();
    }
    
    @FXML
    private void handleEnterSignUp(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER)
             performSignUp();
    }
    
    
}
