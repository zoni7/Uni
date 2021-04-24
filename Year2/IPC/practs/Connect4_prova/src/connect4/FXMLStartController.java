/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
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
public class FXMLStartController implements Initializable {

    @FXML
    private Button bLogInP1;
    @FXML
    private Button bLogOutP1;
    @FXML
    private Button bLogInP2;
    @FXML
    private Button bLogOutP2;
    @FXML
    private Button bPlayAI;
    @FXML
    private Button bPlayFriend;

    private Player pLeft;

    private Player pRight;

    private Player[] playerArray;

    FXMLStartController  stageStart;  
    
    @FXML
    private ImageView imageP1;
    @FXML
    private Label txtNameP1;
    @FXML
    private ImageView imageP2;
    @FXML
    private Label txtNameP2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // Create the list to get player1 and player2
        //ArrayList<Player> playerData = new ArrayList<Player>();
        //observablePlayerData = FXCollections.observableArrayList(playerData);
        playerArray = new Player[2];
        stageStart = this;
        
        BooleanBinding player1Loged = txtNameP1.textProperty().isEqualTo("Player1");
        BooleanBinding player2Loged = txtNameP2.textProperty().isEqualTo("Player2");        
        bLogOutP1.disableProperty().bind(player1Loged);
        bLogOutP2.disableProperty().bind(player2Loged);
        
        bLogInP1.disableProperty().bind(player1Loged.not());
        bLogInP2.disableProperty().bind(player2Loged.not());

        // test
            // nickName = "nickName";
            // email = "email@gmail.com";
            // password = "1234";


        

        // updates the P1 nickName label
        bLogInP1.setOnMouseExited((MouseEvent event)->
            {
                if(playerArray[0] != null) txtNameP1.textProperty().setValue(playerArray[0].getNickName());

            }
        );
        // updates the P2 nickName label
        bLogInP2.setOnMouseExited((MouseEvent event)->
            {
                if(playerArray[1] != null) txtNameP2.textProperty().setValue(playerArray[1].getNickName());
            }
        );
        
        bLogOutP1.setOnMouseExited((MouseEvent event)->
            {
                if(playerArray[0] == null) txtNameP1.textProperty().setValue("Player1");

            }
        );
        // updates the P2 nickName label
        bLogOutP2.setOnMouseExited((MouseEvent event)->
            {
                if(playerArray[1] == null) txtNameP2.textProperty().setValue("Player2");
            }
        );

    }
    
    public void initData(Player[] a) {
        playerArray = a;
    }
    
    public void logOutPlayer(boolean b) {
        if (b) ;
    }

    /**
     * Opens a screen to log in the a user
     * It will open FXMLLogin
     */
    @FXML
    private void handleButtonLogIn(ActionEvent event) throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLLoginController detailsController = myLoader.<FXMLLoginController>getController();
        //We pass the data to the cotroller. Passing the observableList we
        //give controll to the modal for deleting/adding/modify the data
        //we see in the listView
        if (bLogInP1.isHover()) {
            detailsController.initDataLogIn(stageStart ,playerArray, true);
        }
        if (bLogInP2.isHover()) {
            detailsController.initDataLogIn(stageStart ,playerArray, false);
        }

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log in");
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();





    }

    @FXML
    private void handleButtonLogout(ActionEvent event) throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLLogout.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLLogoutController detailsController = myLoader.<FXMLLogoutController>getController();
        //We pass the data to the cotroller. Passing the observableList we
        //give controll to the modal for deleting/adding/modify the data
        //we see in the listView
        if (bLogOutP1.isHover()) {
            detailsController.initDataLogOut(stageStart,playerArray, true);
        }
        if (bLogOutP2.isHover()) {
            detailsController.initDataLogOut(stageStart, playerArray, false);
        }

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log out");
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleButtonPlay(ActionEvent event) throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLGameBoard.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLGameBoardController detailsController = myLoader.<FXMLGameBoardController>getController();          
        
        if (bPlayAI.isHover()) {
            detailsController.initData(playerArray, false);
        } 
        if  (bPlayFriend.isHover()) {
            detailsController.initData(playerArray, true);
        }

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log out");
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }

    

}
