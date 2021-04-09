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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
 

    private ObservableList<Persona> observablePersonData;

    @FXML
    private Button bDelete;
    @FXML
    private Button bAdd;
    @FXML
    private Button bModify;
    @FXML
    private TableColumn<Persona, String> NameColumn;
    @FXML
    private TableColumn<Persona, String> SurnameColumn;
    @FXML
    private TableView<Persona> tableView;
    @FXML
    private TableColumn<Persona, String> ImageColumn;


    @FXML
    private void handleOnActionButtonAdd(ActionEvent event) throws IOException {
        

         //Load the IU objects
         FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/view/FXMLDetailsView.fxml"));
         Pane root = (Pane) myLoader.load();

         //Get the controller of the UI
         FXMLDetailsViewController detailsController = myLoader.<FXMLDetailsViewController>getController();          
         //We pass the data to the cotroller. Passing the observableList we 
         //give controll to the modal for deleting/adding/modify the data 
         //we see in the listView
         detailsController.addInitData(observablePersonData);

         Scene scene = new Scene (root);
         Stage stage = new Stage();
         stage.setScene(scene);
         stage.setTitle("Person addition");
         stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
         stage.setResizable(false);
         stage.show();
            
        
        
    }
    
    @FXML
    private void handleOnActionButtonModify(ActionEvent event) throws IOException {
        
        //Get the selected item index
        int index = tableView.getSelectionModel().selectedIndexProperty().getValue();
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
    private void delete(ActionEvent event) {
        observablePersonData.remove(tableView.getSelectionModel().getSelectedItem());
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
        personData.add(new Persona ("Pepe","Navarro","/images/Lloroso.png"));
        personData.add(new Persona ("Amparo","García","/images/Pregunta.png"));
        observablePersonData = FXCollections.observableArrayList(personData);
        NameColumn.setCellValueFactory(new PropertyValueFactory<Persona,String>("Nombre"));
        SurnameColumn.setCellValueFactory((c)->{return c.getValue().ApellidosProperty();});
 
        //Assign the url of the image as value
        ImageColumn.setCellValueFactory((c) -> {return c.getValue().pathImageProperty();});
        //We decide how to show the url: loading the corresponding image
    
        ImageColumn.setCellFactory((c)->{
            return new TableCell<Persona,String>(){
                @Override
                protected void updateItem(String item, boolean empty){
                    super.updateItem(item, empty);
                    if(item == null || empty)
                        setGraphic(null);
                    else{
                        ImageView imageView = new ImageView();
                        imageView.setImage(new Image(item,40,40,true,true));
                        setGraphic(imageView);
                    }
                        
                }
            };
        });
        
        tableView.setItems(observablePersonData);
        //tableView.setCellFactory((c)->{return new PersonListCell();});
        
        //Boton de borrar solo está disponible cuando un elemento está seleccionado
        bModify.disableProperty().bind(Bindings.equal(-1,tableView.getSelectionModel().selectedIndexProperty()));
        
     
    }    
    
}
