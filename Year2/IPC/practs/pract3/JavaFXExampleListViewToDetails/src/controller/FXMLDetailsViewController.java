    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Persona;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author svalero
 */
public class FXMLDetailsViewController implements Initializable {


    

    private ObservableList<Persona> observableListPersonData;
    private int selectedIndex;
    @FXML
    private TextField editName;
    @FXML
    private TextField editSurname;
    
    @FXML
    private Button buttonAdd;
    
    boolean modify;
    @FXML
    private Button buttonCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }   

    @FXML
    private void onActionButtonAdd(ActionEvent event) {
        if (modify) {
            observableListPersonData.remove(selectedIndex);
            observableListPersonData.add( selectedIndex,new Persona(editName.textProperty().getValue().toString(),editSurname.textProperty().getValue()));
        } else { observableListPersonData.add( new Persona(editName.textProperty().getValue().toString(),editSurname.textProperty().getValue())); }
        
        //Close the window
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    
    public void initData ( ObservableList<Persona> listaPersonas , int index){
        observableListPersonData = listaPersonas;
        editName.textProperty().setValue(observableListPersonData.get(index).getNombre());
        editSurname.textProperty().setValue(observableListPersonData.get(index).getApellidos());       
        selectedIndex = index;
        buttonAdd.textProperty().setValue("Modify");
        modify = true;
  
    }
    
    public void addInitData ( ObservableList<Persona> listaPersonas){
        observableListPersonData = listaPersonas;
        modify = false;
        
    }


    @FXML
    private void onActionButtonCancel(ActionEvent event) {
        //Close the window
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
            
    
}
