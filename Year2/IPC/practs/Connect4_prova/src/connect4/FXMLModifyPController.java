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
import java.time.Period;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Player;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLModifyPController implements Initializable {

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassw;
    @FXML
    private Tooltip ttPwd;    
    @FXML
    private DatePicker datePicker;
    @FXML
    private ImageView myAvatar;
    @FXML
    private Button bAvatar;
    @FXML
    private Label onWrongLogin;
    @FXML
    private Label infoText;
    @FXML
    private Button bOk;
    @FXML
    private Button bClose;
    
    private Player player;
    @FXML
    private Label txtUsername;
    
    private String currentCSS;
    
    private FXMLGameBoardController gameBoardController;
    
    private boolean isLeft;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Initialize all the details
        
        
    } 
    
    public void initData(Player p, boolean b, FXMLGameBoardController c, String css) {
        player = p;
        isLeft = b;
        gameBoardController = c;
        txtUsername.textProperty().setValue(player.getNickName() + "  details:");
        txtEmail.textProperty().setValue(player.getEmail());
        txtPassw.textProperty().setValue(player.getPassword());
        datePicker.setValue(player.getBirthdate());
        myAvatar.setImage(player.getAvatar());
        
        this.currentCSS = css;
    }
    
    public void updateAvatar(Image i) {
        myAvatar.setImage(i);
    }
    
    private void performAceptar() {
        //update all details
        try {
            model.Connect4 api = model.Connect4.getSingletonConnect4();
            infoText.setTextFill(Color.RED);
            
            String email = txtEmail.getText();
            if (!model.Player.checkEmail(email)) {
                infoText.setText("Invalid email format");
                
                return;
            }
            player.setEmail(email);
            api.getConnect4DAO().updateEmailPlayer(player);
            
            String pwd = txtPassw.getText();
            
            if (!model.Player.checkPassword(pwd)) {
                infoText.setText("Invalid password format");
                
                return;
            }            
            player.setPassword(pwd);
            api.getConnect4DAO().updatePasswordPlayer(player);
            
            LocalDate d = datePicker.getValue();
            LocalDate now = LocalDate.now();
            if (Period.between(d,now).getYears() < 12) {
                infoText.setText("You must be 12 years or older");
            }
            
            player.setBirthdate(d);
            api.getConnect4DAO().updateBirthDatePlayer(player);
            
            player.setAvatar(myAvatar.getImage());
            api.getConnect4DAO().updateAvatarPlayer(player);
            infoText.setTextFill(Color.GREEN);
            infoText.setText("Success!!");
            
            

        } catch (Connect4DAOException cdaoex) {}
        if (isLeft) {
            // for player 1
            gameBoardController.initName(player.getNickName(), true);
        }  else {
            // for player 2
            gameBoardController.initName(player.getNickName(), false);
        }
        //close window, return Player object            
        bOk.getScene().getWindow().hide();
    }
    @FXML
    private void handleEnterAccept(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
            performAceptar();
    }
    @FXML
    private void handleButtonAccept(ActionEvent event) {
        performAceptar();
    }
    
    /**
     * Methods to pick one avatar from the ones given
     */
    private void performPickAvatar() throws IOException {
       //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLAvatarPicker.fxml"));
        Pane root = (Pane) myLoader.load();
        //Get the controller of the UI
        FXMLAvatarPickerController detailsController = myLoader.<FXMLAvatarPickerController>getController();
        detailsController.initDataModify(this);
        
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Pick an avatar");
        scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());
        stage.getIcons().add(new Image("images/Icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.show(); 
    }
    
    @FXML
    private void handleEnterPickAvatar(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER)
            performPickAvatar();
    }

    @FXML
    private void handleButtonPickAvatar(ActionEvent event) throws IOException {
        performPickAvatar();;
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
