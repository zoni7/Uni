/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addinghandlersexample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Soledad
 */
public class FXMLAddingHandlersExampleController implements Initializable {

    @FXML
    private Button bSceneBuilder;
    @FXML
    private Label lSceneBuilder;
    @FXML
    private Button bConvenientInner;
    @FXML
    private Label lConvenientInner;
    @FXML
    private Button bAddAnon;
    @FXML
    private Label lAddAnon;
    @FXML
    private Button bConvenientLambda;
    @FXML
    private Label lConvenientLambda;
    @FXML
    private Button bConvenientMethod;
    @FXML
    private Label lConvenientMethod;
    @FXML
    private Button bGetSource;
    @FXML
    private Button bClose;

     
    /**
     * Initializes the controller class.
     */
    class BConvenientInnerActionHandler implements  EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
           if(lConvenientInner.textProperty().getValueSafe().isEmpty())
            lConvenientInner.textProperty().setValue("Example of adding a button handler using a convenient method and an inner class");
           else 
            lConvenientInner.textProperty().setValue("");   
        }
    }
    
    private void bConvenientMethodOnAction(ActionEvent event)
    {
        if(lConvenientMethod.textProperty().getValueSafe().isEmpty())
            lConvenientMethod.textProperty().setValue("Example of adding a button handler using a convenient method and a reference to a method");
        else
           lConvenientMethod.textProperty().setValue("");  
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //Example of adding a button handler using a convenient method and an inner class
         bConvenientInner.setOnAction(new BConvenientInnerActionHandler());
         
         //Example of adding a button handler using AddEventHandler and an anonymous class
         bAddAnon.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             if(lAddAnon.textProperty().getValueSafe().isEmpty())
                lAddAnon.textProperty().setValue("Example of adding a button handler using AddEventHandler and an anonymous class");
             else
                lAddAnon.textProperty().setValue(""); 
            }
         }
         );   
         
         //Example of adding a button handler using a convenient method and a lambda expression
         bConvenientLambda.setOnAction((event)->{
             if(lConvenientLambda.textProperty().getValueSafe().isEmpty())
                lConvenientLambda.textProperty().setValue("Example of adding a button handler using a convenient method and a lambda expression");
             else
                lConvenientLambda.textProperty().setValue("");
         });
         
         //Example of adding a button handler using a convenient method and a reference to a method"
         bConvenientMethod.setOnAction(this::bConvenientMethodOnAction);      
         
         
         
    }    

    @FXML
    private void bSceneBuilderHandlerOnaction(ActionEvent event) {
        lSceneBuilder.textProperty().setValue("Example of adding a button handler using scene builder");
    }
    @FXML
    private void bGetSourceHandlerOnAction(ActionEvent event) {
        Button sourceButton = (Button)event.getSource();
        sourceButton.textFillProperty().setValue(Paint.valueOf("#da1919"));
    }
    @FXML
    private void bCloseHandlerOnAction(ActionEvent event) {
        //Get the button which causes the event
         Button sourceButton = (Button)event.getSource();
         //we can get the scene in which a node is displayed. From the scene, we can get the window and close it.
         sourceButton.getScene().getWindow().hide();
    }

}
