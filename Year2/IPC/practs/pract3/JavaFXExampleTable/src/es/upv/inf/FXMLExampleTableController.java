/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upv.inf;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author svalero
 */
public class FXMLExampleTableController implements Initializable {

    @FXML
    private TableView<Persona> tablaPersonas;
 
    private ObservableList<Persona> observablePersonData;
    @FXML
    private TableColumn<Persona, String> colNombreTabla;
    @FXML
    private TableColumn<Persona, String> colApellidosTabla;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Creamos los datos de partida
        ArrayList<Persona> personData = new ArrayList<Persona>();
        personData.add(new Persona ("Pepe","Navarro"));
        personData.add(new Persona ("Amparo","García"));
        //Los envolvemos en una lista observable
        observablePersonData = FXCollections.observableArrayList(personData);
        //Asignamos el valor a mostrar en cada columna, podemos hacerlo de dos formas:         
        colNombreTabla.setCellValueFactory(new PropertyValueFactory<Persona,String>("Nombre")); 
        //Usando una lambda, ya que persona tiene un método que devuelve una StringProperty para apellidos:
        colApellidosTabla.setCellValueFactory((c)->{return c.getValue().ApellidosProperty();});
        //Asignamos el array a la tabla
        tablaPersonas.setItems(observablePersonData);
        
     
        
    }    
    
}
