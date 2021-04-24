/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Player;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Joan
 */
public class FXMLGameBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    @FXML
    private Label txtNameP1;
    @FXML
    private Label txtNameP2;
    
    private Player[] playerArray;
    
    private final int P_LEFT_TOK = -1;
    private final int P_RIGHT_TOK = 1;
    
    //if true, the game is pvp
    private boolean isCooperative;
    
    //this determines whose turn it is, by default, left player starts
    private boolean flipTurn;
    
    private int[][] gameGrid = new int[8][7];
    
    private Circle[][] circleGrid = new Circle[8][7];
    
    @FXML
    private Label txtWinner;
    @FXML
    private Label txtCongrats;
    
    @FXML
    private GridPane grid;
    @FXML
    private Circle c1_1;
    @FXML
    private Circle c0_1;
    @FXML
    private Circle c1_0;
    @FXML
    private Circle c0_2;
    @FXML
    private Circle c1_2;
    @FXML
    private Circle c0_3;
    @FXML
    private Circle c1_3;
    @FXML
    private Circle c0_4;
    @FXML
    private Circle c1_4;
    @FXML
    private Circle c0_5;
    @FXML
    private Circle c1_5;
    @FXML
    private Circle c0_6;
    @FXML
    private Circle c1_6;
    @FXML
    private Circle c2_6;
    @FXML
    private Circle c4_5;
    @FXML
    private Circle c4_6;
    @FXML
    private Circle c2_0;
    @FXML
    private Circle c2_1;
    @FXML
    private Circle c2_2;
    @FXML
    private Circle c2_3;
    @FXML
    private Circle c2_4;
    @FXML
    private Circle c2_5;
    @FXML
    private Circle c3_0;
    @FXML
    private Circle c3_1;
    @FXML
    private Circle c3_2;
    @FXML
    private Circle c3_3;
    @FXML
    private Circle c3_4;
    @FXML
    private Circle c3_5;
    @FXML
    private Circle c3_6;
    @FXML
    private Circle c4_0;
    @FXML
    private Circle c4_1;
    @FXML
    private Circle c4_2;
    @FXML
    private Circle c4_3;
    @FXML
    private Circle c4_4;
    @FXML
    private Circle c5_0;
    @FXML
    private Circle c5_1;
    @FXML
    private Circle c5_2;
    @FXML
    private Circle c5_3;
    @FXML
    private Circle c5_4;
    @FXML
    private Circle c5_5;
    @FXML
    private Circle c5_6;
    @FXML
    private Circle c6_0;
    @FXML
    private Circle c6_1;
    @FXML
    private Circle c6_2;
    @FXML
    private Circle c6_4;
    @FXML
    private Circle c6_5;
    @FXML
    private Circle c6_6;
    @FXML
    private Circle c7_0;
    @FXML
    private Circle c7_1;
    @FXML
    private Circle c7_2;
    @FXML
    private Circle c7_3;
    @FXML
    private Circle c7_4;
    @FXML
    private Circle c7_5;
    @FXML
    private Circle c0_0;
    @FXML
    private Circle c7_6;
    @FXML
    private Circle c6_3;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if(isCooperative) {
            if (playerArray.length == 2) {
                txtNameP1.textProperty().setValue(playerArray[0].getNickName());
                txtNameP2.textProperty().setValue(playerArray[1].getNickName());
            }
        } else {
            if (playerArray.length != 0) {
                txtNameP1.textProperty().setValue(playerArray[0].getNickName());
            }
        }
    
        grid.setOnMouseClicked((MouseEvent m) -> {
            //pvp
                Integer col = GridPane.getColumnIndex(m.getPickResult().getIntersectedNode());
                if (col == null) {col = 0;}
                System.out.println(col);

                //get height index of the first available position to set a token
                int h = 6;
                int[] hArr = gameGrid[col];
                while(hArr[h] != 0 && h>0) {h--;}
                if (hArr[0] != 0) {System.err.println("That column is full, pick another"); return;}

                //find out whose turn it is and set tok
                int tok;
                Color color;
                if (flipTurn) {tok = P_LEFT_TOK; color = Color.RED;}
                else {tok = P_RIGHT_TOK; color = Color.YELLOW;}
                gameGrid[col][h] = tok;
                circleGrid[col][h].setVisible(true);
                circleGrid[col][h].setFill(color);
                 
                
                if (areFourConnected(tok, gameGrid)) {
                    // Congrats the winner saying who has won
                    String nameP1 = txtNameP1.textProperty().getValue();
                    String nameP2 = txtNameP2.textProperty().getValue();
                    if (tok == -1) { txtWinner.textProperty().setValue(nameP1 + " is the winner"); }
                    else { txtWinner.textProperty().setValue(nameP2 + " is the winner"); }
                    txtCongrats.textProperty().setValue("Congratulations !!!");
                                         
                    // Don't permit more intectation with the game
                    grid.setDisable(true);
                }
                    
                if (isCooperative) {
                    flipTurn = !flipTurn;
                } else {
                    //"AI"
                    try {
                    Thread.currentThread().sleep(1000);
                    } catch (Exception e) {
                    }
                    col = ThreadLocalRandom.current().nextInt(0, 8);
                    h = 6;
                    hArr = gameGrid[col];
                    while(hArr[h] != 0) {h--;}
                    gameGrid[col][h] = P_RIGHT_TOK;
                    circleGrid[col][h].setVisible(true);
                    circleGrid[col][h].setFill(Color.BLUE);
                    
                    if (areFourConnected(P_RIGHT_TOK, gameGrid)) {
                        // Inform about the victory of the AI
                        txtWinner.textProperty().setValue("The AI is the winner");
                        txtCongrats.setTextFill(color.PURPLE);
                        txtCongrats.textProperty().setValue("GAME OVER");
                        // Don't permit more intectation with the game
                        grid.setDisable(true);
                    }
                }

        });
    } 
    public void initData(Player[] p, boolean b) {
        this.playerArray = p;       
        this.isCooperative = b;
        this.flipTurn = true;
        
        //  :(
        
        circleGrid[0][0] = c0_0;
        circleGrid[0][1] = c0_1;
        circleGrid[0][2] = c0_2;
        circleGrid[0][3] = c0_3;
        circleGrid[0][4] = c0_4;
        circleGrid[0][5] = c0_5;
        circleGrid[0][6] = c0_6;
        
        circleGrid[1][0] = c1_0;
        circleGrid[1][1] = c1_1;
        circleGrid[1][2] = c1_2;
        circleGrid[1][3] = c1_3;
        circleGrid[1][4] = c1_4;
        circleGrid[1][5] = c1_5;
        circleGrid[1][6] = c1_6;

        circleGrid[2][0] = c2_0;
        circleGrid[2][1] = c2_1;
        circleGrid[2][2] = c2_2;
        circleGrid[2][3] = c2_3;
        circleGrid[2][4] = c2_4;
        circleGrid[2][5] = c2_5;
        circleGrid[2][6] = c2_6;

        circleGrid[3][0] = c3_0;
        circleGrid[3][1] = c3_1;
        circleGrid[3][2] = c3_2;
        circleGrid[3][3] = c3_3;
        circleGrid[3][4] = c3_4;
        circleGrid[3][5] = c3_5;
        circleGrid[3][6] = c3_6;

        circleGrid[4][0] = c4_0;
        circleGrid[4][1] = c4_1;
        circleGrid[4][2] = c4_2;
        circleGrid[4][3] = c4_3;
        circleGrid[4][4] = c4_4;
        circleGrid[4][5] = c4_5;
        circleGrid[4][6] = c4_6;

        circleGrid[5][0] = c5_0;
        circleGrid[5][1] = c5_1;
        circleGrid[5][2] = c5_2;
        circleGrid[5][3] = c5_3;
        circleGrid[5][4] = c5_4;
        circleGrid[5][5] = c5_5;
        circleGrid[5][6] = c5_6;

        circleGrid[6][0] = c6_0;
        circleGrid[6][1] = c6_1;
        circleGrid[6][2] = c6_2;
        circleGrid[6][3] = c6_3;
        circleGrid[6][4] = c6_4;
        circleGrid[6][5] = c6_5;
        circleGrid[6][6] = c6_6;

        circleGrid[7][0] = c7_0;
        circleGrid[7][1] = c7_1;
        circleGrid[7][2] = c7_2;
        circleGrid[7][3] = c7_3;
        circleGrid[7][4] = c7_4;
        circleGrid[7][5] = c7_5;
        circleGrid[7][6] = c7_6;
    }
    
    // player is the int used to represent a token of each player in the board
    //eg: -1 for the left player and 1 for the right player
    public boolean areFourConnected(int player, int[][] board){

        // horizontalCheck 
        for (int j = 0; j<7-3 ; j++ ){
            for (int i = 0; i<8; i++){
                if (board[i][j] == player && board[i][j+1] == player && board[i][j+2] == player && board[i][j+3] == player){
                    return true;
                }           
            }
        }
        // verticalCheck
        for (int i = 0; i<8-3 ; i++ ){
            for (int j = 0; j<7; j++){
                if (board[i][j] == player && board[i+1][j] == player && board[i+2][j] == player && board[i+3][j] == player){
                    return true;
                }           
            }
        }
        // ascendingDiagonalCheck 
        for (int i=3; i<8; i++){
            for (int j=0; j<7-3; j++){
                if (board[i][j] == player && board[i-1][j+1] == player && board[i-2][j+2] == player && board[i-3][j+3] == player)
                    return true;
            }
        }
        // descendingDiagonalCheck
        for (int i=3; i<8; i++){
            for (int j=3; j<7; j++){
                if (board[i][j] == player && board[i-1][j-1] == player && board[i-2][j-2] == player && board[i-3][j-3] == player)
                    return true;
            }
        }
        return false;
    }
    
}
