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
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    
    private ObservableList<Player> observablePlayerData;
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
        ArrayList<Player> playerData = new ArrayList<Player>();
        observablePlayerData = FXCollections.observableArrayList(playerData);
        /*
        if (!txtNameP1.textProperty().getValue().equals("Player1")) {
            StringProperty player1Property = new SimpleStringProperty(observablePlayerData.get(0).getNickName());

            StringBinding player1binding = (StringBinding) player1Property.concat("");
            txtNameP1.textProperty().bind(player1binding);
        }
        */
        //-------------------------------------------------------------------------------------------------------------------
        BooleanBinding player1Loged = txtNameP1.textProperty().isEqualTo("Player1");
        BooleanBinding player2Loged = txtNameP2.textProperty().isEqualTo("Player2");
        bPlayAI.disableProperty().bind(player1Loged);
        bPlayFriend.disableProperty().bind(player2Loged);
        //-------------------------------------------------------------------------------------------------------------------
        
        // test
        try {            
            String nickName = "nickName";
            String email = "email@gmail.com";
            String password = "1234";
            LocalDate birthdate = LocalDate.now().minusYears(18);
            int points = 10;
            Connect4 c = Connect4.getSingletonConnect4();
            Player result = c.registerPlayer(nickName, email, password, birthdate, points);
                                    
        } catch (Connect4DAOException ex) {
            Logger.getLogger(FXMLStartController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bPlayFriend.setOnMouseClicked((MouseEvent event)-> 
        {System.out.println(observablePlayerData.get(0));}
        );
        
        // updates the P1 nickName label
        bLogInP1.setOnMouseExited((MouseEvent event)-> 
            {
                if(!observablePlayerData.isEmpty()) txtNameP1.textProperty().setValue(observablePlayerData.get(0).getNickName());  

            }
        );
        // updates the P2 nickName label
        bLogInP2.setOnMouseExited((MouseEvent event)-> 
            {
                if(!observablePlayerData.isEmpty()) txtNameP2.textProperty().setValue(observablePlayerData.get(1).getNickName());                
            }
        );
       
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
            detailsController.initData(observablePlayerData, true);
        } 
        if (bLogInP2.isHover()) {
            detailsController.initData(observablePlayerData, false);
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
        if (bLogInP1.isHover()) {
            detailsController.initData(observablePlayerData, true);
        } 
        if (bLogInP2.isHover()) {
            detailsController.initData(observablePlayerData, false);
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
    private void handleButtonPlayWithIA(ActionEvent event) throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLGameBoard.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLGameBoardController detailsController = myLoader.<FXMLGameBoardController>getController();          
        
        detailsController.initData(observablePlayerData, false);

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log out");
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleButtonPlayFriend(ActionEvent event) throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLGameBoard.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLGameBoardController detailsController = myLoader.<FXMLGameBoardController>getController();          
           
        detailsController.initData(observablePlayerData, true);

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log out");
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML
    private void handleButtonPlayWithIA(ActionEvent event) throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLGameBoard.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLGameBoardController detailsController = myLoader.<FXMLGameBoardController>getController();          
        
        detailsController.initData(observablePlayerData, false);

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log out");
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handleButtonPlayFriend(ActionEvent event) throws IOException {
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLGameBoard.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLGameBoardController detailsController = myLoader.<FXMLGameBoardController>getController();          
           
        detailsController.initData(observablePlayerData, true);

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log out");
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show();
    }
}
