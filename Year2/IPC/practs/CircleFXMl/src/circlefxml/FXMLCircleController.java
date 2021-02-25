/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circlefxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;


/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLCircleController implements Initializable {

    @FXML
    private Circle myNode;
    // variable for identifying the row
    private int row;
    // variable for identifying the colum
    private int colum;
    
  
    @FXML
    private GridPane miGrid;
    //private GridPane GridPane;

    /**
     * Initializes the controller class.
     */
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Example of adding a button handler using a convenient method and a reference to a method"
         
        myNode.setOnKeyPressed((event)->{
            if (event.getCode().equals(KeyCode.RIGHT)) {
                colum = GridPane.getColumnIndex(myNode);
                // Method to change the column number in which a node is displayed
                miGrid.setColumnIndex(myNode, colum+1);
                
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                colum = GridPane.getColumnIndex(myNode);
                // Method to change the column number in which a node is displayed
                miGrid.setColumnIndex(myNode, colum-1);
                
            } else if (event.getCode().equals(KeyCode.UP)) {
                row = GridPane.getRowIndex(myNode);
                // Method to change the row number in which a node is displayed
                miGrid.setRowIndex(myNode, row-1);
            } else if (event.getCode().equals(KeyCode.DOWN)) {
                row = GridPane.getRowIndex(myNode);
                // Method to change the row number in which a node is displayed
                miGrid.setRowIndex(myNode, row+1);
                
            } else if (event.getCode().equals(KeyCode.ESCAPE))  {
                // Presing ESCAPE will close the window
                myNode.getScene().getWindow().hide();
                        
            }      
         });
     
    }    
    
}
