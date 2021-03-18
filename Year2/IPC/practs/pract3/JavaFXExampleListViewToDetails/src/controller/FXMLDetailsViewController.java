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
    private Button buttonOk;
    
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
    /**
     * Modifies an existing or adds a new element
     * @param event 
     */
    @FXML
    private void onActionButtonOk(ActionEvent event) {
        if (modify) {
            observableListPersonData.remove(selectedIndex);
            observableListPersonData.add( selectedIndex,new Persona(editName.textProperty().getValue().toString(),editSurname.textProperty().getValue()));
        } else { observableListPersonData.add( new Persona(editName.textProperty().getValue().toString(),editSurname.textProperty().getValue())); }
        
        //Close the window
        ((Node) event.getSource()).getScene().getWindow().hide();
    }
    
    /**
     * Initializes data for the modify button
     * @param listaPersonas 
     */
    public void initData ( ObservableList<Persona> listaPersonas , int index){
        observableListPersonData = listaPersonas;
        editName.textProperty().setValue(observableListPersonData.get(index).getNombre());
        editSurname.textProperty().setValue(observableListPersonData.get(index).getApellidos());       
        selectedIndex = index;
        modify = true;
  
    }
    /**
     * Initializes data for the add button
     * @param listaPersonas 
     */
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
