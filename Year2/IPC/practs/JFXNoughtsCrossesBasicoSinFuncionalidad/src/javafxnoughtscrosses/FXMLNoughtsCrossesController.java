/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxnoughtscrosses;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Soledad
 */
public class FXMLNoughtsCrossesController implements Initializable {
    
    private Label label;
    @FXML
    private GridPane pscore;
    @FXML
    private Label lFirstPlayer;
    @FXML
    private TextField tFirstPlayerScore;
    @FXML
    private Label lSecondPlayer;
    @FXML
    private TextField tsecondPlayerScore;
    @FXML
    private Button b00;
    @FXML
    private Button b01;
    @FXML
    private Button b02;
    @FXML
    private Button b10;
    @FXML
    private Button b20;
    @FXML
    private Button b11;
    @FXML
    private Button b12;
    @FXML
    private Button b21;
    @FXML
    private Button b22;   
  
    @FXML
    private GridPane gamblingTable;
    
    private String playerSymbols[];    
    final int NUM_PLAYERS = 2;
    final int PLAYER1 = 0;
    final int PLAYER2 = 1;
    final int POSSIBLE_MOVIMENTS = 9; //Number of moviments in a game
    final int IN_DRAW = -2;
    final int CONTINUE = -1;    
    final int POSITIONS = 3; //Number of rows and columns
    final int CENTRAL_CELL = 1; //column and field of the central field
    private int currentPlayer;
    private int rowCounters[];
    private int rowFreeCells[];
    private int columnFreeCells[];
    private int diagonalFreeCells[];
    private int columnCounters[];
    private int diagonalCounter[];
    private int moviments;
    @FXML
    private Button bStart;
    @FXML
    private Button breset;
    
    /**
     * Resets the cleanGamblingTable
     */
    private void cleanGamblingTable(){      
 
        gamblingTable.getChildren().forEach((Node button) -> {
            button.disableProperty().setValue(Boolean.FALSE);
            if(button instanceof  Button){
                ((Button)button).textProperty().setValue("");
            }
        });

    }
    private String getPlayerSymbol(int numPlayer){
        if(numPlayer<playerSymbols.length)
            return  playerSymbols[numPlayer];
        else
            return "";
    }
    
    private Color getPlayerColor(int numPlayer){
        if(numPlayer == PLAYER1)
            return Color.valueOf("#6495ed"); //blue
        else
            return Color.valueOf("#808080"); //gray, also color by default
        
    
    }
    private void updateCurrentPlayer(){
       currentPlayer = (currentPlayer+1)%NUM_PLAYERS;

    }
    /**
     * Start button: It cleans the gambling table. Use the convenient method
     * setOnAction and a reference to a method.
     * @param event 
     */
    private void startGame(ActionEvent event) {
        cleanGamblingTable();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Init player sympbols
        playerSymbols = new String[NUM_PLAYERS];
        playerSymbols[0] = "X";
        playerSymbols[1] = "O";        
        currentPlayer = 0;             
        
        tFirstPlayerScore.textProperty().setValue("0");
        tsecondPlayerScore.textProperty().setValue("0");
        
        //Scene builder doesn't initialize the column and row of each button, only one of the fields. 
        // We need both properties!!
        GridPane.setRowIndex(b00, 0);
        GridPane.setRowIndex(b01, 0);
        GridPane.setRowIndex(b02, 0);
        GridPane.setRowIndex(b10, 1);
        GridPane.setRowIndex(b11, 1);
        GridPane.setRowIndex(b12, 1);
        GridPane.setRowIndex(b20, 2);
        GridPane.setRowIndex(b21, 2);
        GridPane.setRowIndex(b22, 2);
        GridPane.setColumnIndex(b00, 0);
        GridPane.setColumnIndex(b01, 1);
        GridPane.setColumnIndex(b02, 2);
        GridPane.setColumnIndex(b10, 0);
        GridPane.setColumnIndex(b11, 1);
        GridPane.setColumnIndex(b12, 2);
        GridPane.setColumnIndex(b20, 0);
        GridPane.setColumnIndex(b21, 1);
        GridPane.setColumnIndex(b22, 2);    
        
        //Initialize Gambling Table
        cleanGamblingTable();
        
        /**
         * Reset button: It also cleans the gambling table and sets the scores to 0.
         */
        breset.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 cleanGamblingTable();
                 tFirstPlayerScore.textProperty().setValue("0");
                 tsecondPlayerScore.textProperty().setValue("0");
             }
          }
          );
        
        // Use the convenient method setOnAction and a reference to startGame method.
        bStart.setOnAction(this::startGame);
          
    }        

    @FXML
    
   /**
    *  Buttons should add the symbol of the player(X,O)
    * 
    * How to get the button which causes the event:
    * Button clickedButton = (Button) event.getSource();
    */   

    private void playerMove(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
            if (clickedButton.textProperty().getValue().isEmpty()){
                if(currentPlayer == PLAYER1) { 
                    clickedButton.textProperty().setValue(getPlayerSymbol(PLAYER1)); 
                    updateCurrentPlayer();
                }
                else { 
                    clickedButton.textProperty().setValue(getPlayerSymbol(PLAYER2));
                    updateCurrentPlayer();
                }
            }
    }
   
    
}
