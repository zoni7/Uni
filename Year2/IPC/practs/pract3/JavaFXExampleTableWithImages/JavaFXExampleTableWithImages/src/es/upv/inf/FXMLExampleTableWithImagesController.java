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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author svalero
 */
public class FXMLExampleTableWithImagesController implements Initializable {

    @FXML
    private TableView<Person> tablePersons;
    @FXML
    private TableColumn<Person, Integer> colId;
    @FXML
    private TableColumn<Person, String> colFullName;
    @FXML
    private TableColumn<Person, Residence> colCity;
    @FXML
    private TableColumn<Person, String> colProfileImage;
    
    private ObservableList<Person> myObservableData = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Data is stored directly in an observable listt
        myObservableData.add(new Person(1000, "John Doe", new Residence("Museros", "Valencia"), "/images/Lloroso.png"));
        myObservableData.add(new Person(1001, "Jane Doe", new Residence("Buñol", "Valencia"), "/images/Pregunta.png"));
        myObservableData.add(new Person(1002, "Pete Doe", new Residence("Torrent", "Valencia"), "/images/Sonriente.png"));
        
        colId.setCellValueFactory(new PropertyValueFactory<Person,Integer>("id"));
        colFullName.setCellValueFactory(new PropertyValueFactory<Person,String>("fullName"));
        //Value to show
        colCity.setCellValueFactory((c)->{return c.getValue().residenceProperty();});
        //How to show the value
    
        colCity.setCellFactory((c)->{
            return new TableCell<Person, Residence>(){
            @Override
            protected void updateItem(Residence item,boolean empty){
               super.updateItem(item, empty);
               if(item == null || empty)
                   setText(null);
               else
                   setText(item.getCity());
            }
            };
           
        });
         //Assign the url of the image as value
        colProfileImage.setCellValueFactory((c) -> {return c.getValue().pathImageProperty();});
        //We decide how to show the url: loading the corresponding image
    
        colProfileImage.setCellFactory((c)->{
            return new TableCell<Person,String>(){
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
        
        // this line is very important, if not it doesn't work
        tablePersons.setItems(myObservableData);
        
        
        
    }    
    
}
