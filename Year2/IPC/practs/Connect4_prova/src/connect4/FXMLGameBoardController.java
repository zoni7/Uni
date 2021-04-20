/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Player;

/**
 * FXML Controller class
 *
 * @author Joan
 */
public class FXMLGameBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    @FXML
    private Label txtNameP1;
    @FXML
    private Label txtNameP2;
    
    private ObservableList<Player> observableAux;
    
    private boolean isCooperative;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if(isCooperative) {
            txtNameP1.textProperty().setValue(observableAux.get(0).getNickName());
            txtNameP2.textProperty().setValue(observableAux.get(1).getNickName());
        } else  System.out.println(observableAux.get(0));//txtNameP1.textProperty().setValue(observableAux.get(0).getNickName());
    } 
    
    public void initData(ObservableList<Player> lplayer, boolean b) {
        this.observableAux = lplayer;       
        this.isCooperative = b;
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
