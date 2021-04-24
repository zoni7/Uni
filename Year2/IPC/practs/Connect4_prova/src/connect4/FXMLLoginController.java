/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private Button bRemember;
    @FXML
    private Button bOk;
    @FXML
    private Label onWrongLogin;
    
    private Player loginUser; //this must be returned to the previous window using the observable
    
    private boolean isLeft;
    
    private Player[] playerArray; // loginUser is added here
    
    private FXMLStartController stageStart;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
        bOk.setOnMouseClicked((MouseEvent m) -> {
            //get user and password from input
        try{
            Connect4 api = Connect4.getSingletonConnect4();
            
            String usr = txtUsername.textProperty().getValue();
            String pwd = txtPassdword.textProperty().getValue();
                   
            
            //check user
            if (!api.exitsNickName(usr)) {
                onWrongLogin.textProperty().set("Wrong username. Please check your details");
                //exit
                return;
            }
            loginUser = api.getPlayer(usr);
            
            //check pwd
            if (!loginUser.checkCredentials(usr,pwd)) {
                System.out.println(pwd);
                System.out.println(loginUser);
                onWrongLogin.textProperty().set("Incorrect password");
                //reset user
                loginUser = null;
                //exit
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
            stageStart.initData(playerArray);
            //close window, return Player object             
            bOk.getScene().getWindow().hide();            
        });
    }    
    /**
     *  Initialize the data needed to move the loginUser to the main screen (Start)    
     */
    public void initDataLogIn(FXMLStartController s, Player[] a, boolean isLeft) {
        this.stageStart = s;
        this.isLeft =  isLeft;
        this.playerArray = a;       
    }

    
    /**
     * Opens a screen which will help to remember the user password
     * It will open FXMLRememberP
     */
    @FXML
    private void rememberPassword(ActionEvent event) throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLRememberP.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLRememberPController detailsController = myLoader.<FXMLRememberPController>getController();          
        

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Remember password");
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }
   
}
