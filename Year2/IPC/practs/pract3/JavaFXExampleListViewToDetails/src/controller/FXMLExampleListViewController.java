/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import model.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author svalero
 */
public class FXMLExampleListViewController implements Initializable {

    private TextField editNombre;
    private TextField editApellidos;
    @FXML
    private ListView<Persona> listViewPerson;
 

    private ObservableList<Persona> observablePersonData;

    @FXML
    private Button buttonDetails;


    private void handleOnActionButtonAñadir(ActionEvent event) throws IOException {
        
        //Get the selected item index
        int index = listViewPerson.getSelectionModel().selectedIndexProperty().getValue();
        if (index>=0){
           //If there is an item selected, a new modal window is opened
           
            //Load the IU objects
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/view/FXMLDetailsView.fxml"));
            Pane root = (Pane) myLoader.load();
            
            //Get the controller of the UI
            FXMLDetailsViewController detailsController = myLoader.<FXMLDetailsViewController>getController();          
            //We pass the data to the cotroller. Passing the observableList we 
            //give controll to the modal for deleting/adding/modify the data 
            //we see in the listView
            detailsController.initData(observablePersonData,index);
          
            Scene scene = new Scene (root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Person addition");
            stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
            stage.setResizable(false);
            stage.show();
            
        }
        
    }
    
    @FXML
    private void handleOnActionButtonModificar(ActionEvent event) throws IOException {
        
        //Get the selected item index
        int index = listViewPerson.getSelectionModel().selectedIndexProperty().getValue();
        if (index>=0){
           //If there is an item selected, a new modal window is opened
           
            //Load the IU objects
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/view/FXMLDetailsView.fxml"));
            Pane root = (Pane) myLoader.load();
            
            //Get the controller of the UI
            FXMLDetailsViewController detailsController = myLoader.<FXMLDetailsViewController>getController();          
            //We pass the data to the cotroller. Passing the observableList we 
            //give controll to the modal for deleting/adding/modify the data 
            //we see in the listView
            detailsController.initData(observablePersonData,index);
          
            Scene scene = new Scene (root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Person modification");
            stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
            stage.setResizable(false);
            stage.show();
            
        }
        
    }

    @FXML
    private void handleOnActionButtonDetails(ActionEvent event) {
    }
    
    class PersonListCell extends ListCell<Persona> {
        @Override
        protected void updateItem(Persona item, boolean empty){
            super.updateItem(item, empty);
            if(item==null || empty)
                setText(null);
            else
                setText(item.getApellidos() + "," + item.getNombre());
            
        }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<Persona> personData = new ArrayList<Persona>();
        personData.add(new Persona ("Pepe","Navarro"));
        personData.add(new Persona ("Amparo","García"));
        observablePersonData = FXCollections.observableArrayList(personData);
        listViewPerson.setItems(observablePersonData);
        listViewPerson.setCellFactory((c)->{return new PersonListCell();});
        
        //Boton de borrar solo está disponible cuando un elemento está seleccionado
        buttonDetails.disableProperty().bind(Bindings.equal(-1,listViewPerson.getSelectionModel().selectedIndexProperty()));
        
     
    }    
    
}