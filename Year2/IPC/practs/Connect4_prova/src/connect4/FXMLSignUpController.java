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
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

/**
 * FXML Controller class
 *
 * @author Joan
 */
public class FXMLSignUpController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassw;
    @FXML
    private PasswordField txtConfirmPassw;
    @FXML
    private Label onWrongLogin;
    @FXML
    private Button bOk;
    @FXML
    private Button bClose;
    @FXML
    private Label infoText;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Tooltip ttUser;
    @FXML
    private Tooltip ttPwd;
    @FXML
    private Button bAvatar;
    @FXML
    private ImageView myAvatar;
    
    private String currentCSS;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the bindings needed
        bindings();        
    }  
    
    public void initData(String currentCSS) {
        this.currentCSS  = currentCSS;
    }

    private void bindings() {
        BooleanBinding usrEmpty = txtUsername.textProperty().isEmpty() ;
        BooleanBinding emailEmpty = txtEmail.textProperty().isEmpty();
        BooleanBinding pswEmpty = txtPassw.textProperty().isEmpty();
        BooleanBinding pswConfirmEmpty = txtConfirmPassw.textProperty().isEmpty();
        BooleanBinding dateEmpty = datePicker.valueProperty().isNull();
        
        
        bOk.disableProperty().bind(usrEmpty.or(emailEmpty).or(pswEmpty).or(pswConfirmEmpty).or(dateEmpty));
    }
    
    public void updateAvatar(Image i) {
        myAvatar.setImage(i);
    }
    
    private void performAccept() {
        try {
            model.Connect4 api = model.Connect4.getSingletonConnect4();
            infoText.setTextFill(Color.RED);
            String usr = txtUsername.getText();
            if (api.getPlayer(usr) != null) {
                infoText.setText("The user " + usr + " is already taken");
                return;
            }
            String email = txtEmail.getText();
            if (!model.Player.checkEmail(email)) {
                infoText.setText("Invalid email format");
                return;
            }
            
            String pwd = txtPassw.getText();
            
            if (!model.Player.checkPassword(pwd)) {
                infoText.setText("Invalid password format");
                return;
            }
            
            if (!txtConfirmPassw.getText().equals(pwd)) {
                infoText.setText("The passwords do not match");
                return;
            }
            
            LocalDate d = datePicker.getValue();
            LocalDate now = LocalDate.now();
            if (Period.between(d,now).getYears() < 12) {
                infoText.setText("You must be 12 years or older");
            }
       
            infoText.setTextFill(Color.GREEN);
            infoText.setText("Success!!");
            api.registerPlayer(usr, email, pwd, myAvatar.getImage(), d, 0);

        } catch (Connect4DAOException cdaoex) {}
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
    /**
     * Methods to pick one avatar from the ones given
     */
    private void performPickAvatar() throws IOException {
       //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLAvatarPicker.fxml"));
        Pane root = (Pane) myLoader.load();
        //Get the controller of the UI
        FXMLAvatarPickerController detailsController = myLoader.<FXMLAvatarPickerController>getController();
        detailsController.initData(this);
        
        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Pick an avatar");
        stage.getIcons().add(new Image("images/Icon.png"));
        scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());

        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.showAndWait();
        if (!detailsController.performCancel()) {
            updateAvatar(detailsController.updateAvatar());
        }
    }
    
    @FXML
    private void handleEnterPickAvatar(KeyEvent event) throws IOException {
        if(event.getCode() == KeyCode.ENTER)
            performPickAvatar();
    }

    @FXML
    private void handleButtonPickAvatar(ActionEvent event) throws IOException{
        performPickAvatar();
    }
    
}
