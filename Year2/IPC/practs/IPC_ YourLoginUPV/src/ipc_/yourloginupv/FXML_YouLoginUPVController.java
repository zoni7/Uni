/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipc_.yourloginupv;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

/**
 *
 * @author USUARIO
 */
public class FXML_YouLoginUPVController implements Initializable {
    
    @FXML
    private ListView<String> lDVDs;
    @FXML
    private Button bDelete;
    @FXML
    private TextField txtTitle;
    @FXML
    private Button bAdd;
    @FXML
    private TextField txtYear;
    private ObservableList<String> data ;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Bindings
        bindings();
        ArrayList<String> myData = new ArrayList<String>();
        myData.add("Joan Matarredona Coloma 6200");
        
        data = FXCollections.observableArrayList(myData);
        lDVDs.setItems(data);
        lDVDs.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        bDelete.setOnAction((event) -> {
            ObservableList<String> selectedFilms;
        selectedFilms = lDVDs.getSelectionModel().getSelectedItems();
        data.removeAll(selectedFilms);
        });
    }    
    
    private void bindings() {
        BooleanBinding titleEmpty = txtTitle.textProperty().isEmpty();
        BooleanBinding yearEmpty = txtYear.textProperty().isEmpty();
        
        bAdd.disableProperty().bind(Bindings.or(titleEmpty, yearEmpty));
        bDelete.disableProperty().bind(Bindings.equal(-1,lDVDs.getSelectionModel().selectedIndexProperty()));
    }

    @FXML
    private void performAdd(ActionEvent event) {
        // check
        String format = txtTitle.textProperty().getValue() + ", " + txtYear.textProperty().getValue();
       
        if (!(txtYear.textProperty().getValue().length() == 4) ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // or AlertType.WARNING or AlertType.ERROR or AlertType.CONFIRMATION
            alert.setTitle("Adding a film Error");
            
            // or null if we do not want a header
            alert.setContentText("The year should contain only 4 digits \nExample: 2009");
            Optional<ButtonType> result = alert.showAndWait();
            
           
        } 
        else if (txtTitle.textProperty().length().equals(0)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // or AlertType.WARNING or AlertType.ERROR or AlertType.CONFIRMATION
            alert.setTitle("Adding a film Error");
            
            // or null if we do not want a header
            alert.setContentText("It must have a title");
            Optional<ButtonType> result = alert.showAndWait();
        } 
        else if (data.contains(format)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            // or AlertType.WARNING or AlertType.ERROR or AlertType.CONFIRMATION
            alert.setTitle("Adding a film Error");
            
            // or null if we do not want a header
            alert.setContentText("The film is already in the list. You can't add it twice.");
            Optional<ButtonType> result = alert.showAndWait();
        }
        else {
           data.add(format);
           txtTitle.setText("");
           txtYear.setText("");
        }
    }

   
}
