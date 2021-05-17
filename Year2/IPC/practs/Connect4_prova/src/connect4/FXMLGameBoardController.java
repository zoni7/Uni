/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Player;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.Effect;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Joan y Jorge uwu
 */
public class FXMLGameBoardController implements Initializable {
    
    public class InnerWaitTask extends Task<Void> {
    
           @Override
           public Void call() throws Exception {
               try {
                   int col = ThreadLocalRandom.current().nextInt(0, 8);
                   int h = 6;
                   int[] hArr = gameGrid[col];
                   while(hArr[h] != 0) {h--;}
                   gameGrid[col][h] = P_RIGHT_TOK;
                   
                   //safety: prevent more clicks/interaction while the machine is "thinking"
                   grid.setDisable(true);

                   //be consistent, put color effects on every game
                   
                   //now we are active, set colors
                   boxP1.setStyle("-fx-background-color: ganesBoro");
                   boxP2.setStyle("-fx-background-color :  LightBLUE" );
                   
                   //thinking...
                   Thread.sleep(700);
                   circleGrid[col][h].setVisible(true);
                   circleGrid[col][h].setFill(Color.BLUE);
                   
                   //no longer active, eliminate color, swap turn
                   boxP2.setStyle("-fx-background-color : ganesBoro");
                   boxP1.setStyle("-fx-background-color :  #ffe6e6" );
                   grid.setDisable(false);
               } catch (InterruptedException intex) {}
               return null;
           }
    }

    /**
     * Initializes the controller class.
     */
    private model.Connect4 api;

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

    private Rectangle[] rTop = new Rectangle[8];

    @FXML
    private Label txtWinner;
    @FXML
    private Label txtCongrats;

    private String nameP1;

    private String nameP2;

    private FXMLGameBoardController controller;

    @FXML
    private Button bLogP1;
    @FXML
    private Button bLogP2;
    @FXML
    private Button bPlayAI;
    @FXML
    private Button bPlayFriend;

    private InnerShadow effect;
    @FXML
    private Label txtPointsP2;
    @FXML
    private Label txtPointsP1;
    @FXML
    private Rectangle rTop1;
    @FXML
    private Rectangle rTop2;
    @FXML
    private Rectangle rTop3;
    @FXML
    private Rectangle rTop4;
    @FXML
    private Rectangle rTop5;
    @FXML
    private Rectangle rTop6;
    @FXML
    private Rectangle rTop7;
    @FXML
    private Rectangle rTop0;
    @FXML
    private VBox boxP1;
    @FXML
    private VBox boxP2;
    @FXML
    private ImageView imageP1;
    @FXML
    private ImageView imageP2;

    // We create a counter to see if the grid is full
    private int cont;

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
    @FXML
    private ToggleGroup theme;
    @FXML
    private RadioMenuItem radioMenuDark;

    private final String darkCSS = "dark.css";

    private final String lightCSS = "light.css";

    private String currentCSS = lightCSS; //default
    @FXML
    private RadioMenuItem radioMenuLight;
    @FXML
    private Button bModifyP1;
    @FXML
    private Button bModifyP2;
    @FXML
    private MenuItem bRanking;
    
    private Player machine;
    

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // initialize the Connect4 variable

        try {
            api = model.Connect4.getSingletonConnect4();

           //api.createDemoData(5, 7, 4);
            //api.removeAllData();
        } catch (Connect4DAOException ex) {
            Logger.getLogger(FXMLGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Initialize the array of maximum of two players
        playerArray = new Player[2];
        // initialize the controller of the gameboard
        controller = this;

        grid.setDisable(true);
        effect = new InnerShadow();

        // Bindings
        setBindings();
        // Ajust board
        ajustBoard();

        // Initialize the counter to 0
        cont = 0;

        // create machine        
        machine = new Player("machine", "o", "o", LocalDate.now(), 0);

        // PLAY CONNECT4
        

                // ------------------------------------ WORK IN PROGRESS -----------------------------------------------
                // animations of the gameboard
                /*
                Circle toMove = circleGrid[col][h];
                //store destination
                double xdest = toMove.getCenterX();
                double ydest = toMove.getCenterY();
                //double finaldest =  ydest * (double)grid.heightProperty().intValue() / 6.0;
                GridPane.setConstraints(toMove, col, 0);
                toMove.setVisible(true);
                toMove.setFill(color);
                TranslateTransition translateTransition = new TranslateTransition(Duration.millis(300) , toMove);
                translateTransition.setToX(xdest);
                translateTransition.setToY(ydest + grid.heightProperty().intValue());
                translateTransition.play();
                */
    }
    /**
     * Updates the Player1 and Player2 details shown in the main screen
     * @param n name of the user logged
     * @param b boolean that indicates if the user logged is the left or the right one, if its true is the feft
     *          otherwise is the right one
     */
    public void initName(String n, boolean b){
        if (b) {
            // Update the user's name
            txtNameP1.textProperty().setValue(n);
            // Update points
            txtPointsP1.textProperty().setValue(playerArray[0].getPoints() + "");
            // Update the Avatar
            imageP1.imageProperty().setValue(playerArray[0].getAvatar());
        }
        else {
            // Update the user's name
            txtNameP2.textProperty().setValue(n);
            // Update points
            txtPointsP2.textProperty().setValue(playerArray[1].getPoints() + "");
            // Update the Avatar
            imageP2.imageProperty().setValue(playerArray[1].getAvatar());
        }

    }
    private void initDataGame(boolean b) {

        this.isCooperative = b;
        this.flipTurn = true;

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

        rTop[0] = rTop0;
        rTop[1] = rTop1;
        rTop[2] = rTop2;
        rTop[3] = rTop3;
        rTop[4] = rTop4;
        rTop[5] = rTop5;
        rTop[6] = rTop6;
        rTop[7] = rTop7;
    }

    private void setBindings() {
        // Bindings

            //esto es super trampa
            BooleanBinding player1NotLoged = txtNameP1.textProperty().isEqualTo("Player1");
            BooleanBinding player2NotLoged = txtNameP2.textProperty().isEqualTo("Player2");
            
            BooleanBinding bPlayAIActive= bPlayAI.disableProperty().not();
            BooleanBinding bPlayFriendActive = bPlayFriend.disableProperty().not();

            // Log Out buttons bindings
            //bLogOutP1.disableProperty().bind(player2NotLoged.not().or(player1NotLoged).or(bPlayAI.disableProperty().and(bPlayFriend.disableProperty())));
            //bLogOutP2.disableProperty().bind(player2NotLoged.or(bPlayAI.disableProperty().and(bPlayFriend.disableProperty())));

            // Log in buttons bindings
            bLogP1.disableProperty().bind(player2NotLoged.not());
            bLogP2.disableProperty().bind(player1NotLoged);
            // Play with a Friend button binding
            bPlayFriend.disableProperty().bind(player1NotLoged.not().and(player2NotLoged));
            // Play with the AI binding
            bPlayAI.disableProperty().bind(player2NotLoged.not());



            bModifyP1.disableProperty().bind(player1NotLoged.or(bPlayAIActive.not().and(bPlayFriendActive.not())));
            bModifyP2.disableProperty().bind(player2NotLoged.or(bPlayAIActive.not().and(bPlayFriendActive.not())));

    }
    
    @FXML
    private void playGame(MouseEvent m) throws Connect4DAOException {
            //pvp
                Integer col = GridPane.getColumnIndex(m.getPickResult().getIntersectedNode());
                if (col == null) {col = 0;}

                //get height index of the first available position to set a token
                int h = 6;
                int[] hArr = gameGrid[col];
                while(hArr[h] != 0 && h>0) {h--;}
                if (hArr[0] != 0) {
                    Alert alert= new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("This column is full. Please pick another");
                alert.initModality(Modality.APPLICATION_MODAL);
                Optional<ButtonType> result = alert.showAndWait();
                return;
                }

                //find out whose turn it is and set tok
                int tok;
                Color color;
                if (flipTurn) {tok = P_LEFT_TOK; color = Color.RED;}
                else {tok = P_RIGHT_TOK; color = Color.YELLOW;}
                // increment counter
                cont++;
                gameGrid[col][h] = tok;

                circleGrid[col][h].setVisible(true);
                circleGrid[col][h].setFill(color);


                if (areFourConnected(tok, gameGrid)) {
                    // Congrats the winner saying who has won
                    String nameP1 = txtNameP1.textProperty().getValue();
                    String nameP2 = txtNameP2.textProperty().getValue();

                    // Player1 is the winner
                    if (tok == -1) {
                        txtWinner.textProperty().setValue(nameP1 + " is the winner");
                        try {
                            if (playerArray[0] != null && isCooperative) {
                                // Increase points of the winner, in this case player1
                                // Increase 5 points because there are two players
                                playerArray[0].plusPoints(5);
                                txtPointsP1.textProperty().setValue(playerArray[0].getPoints() + "");
                                api.regiterRound(LocalDateTime.now(), playerArray[0], playerArray[1]);

                            } else if (playerArray[0] != null && !isCooperative) {
                                //Increase 1 point because there is just one player against the AI
                                playerArray[0].plusPoints(1);
                                txtPointsP1.textProperty().setValue(playerArray[0].getPoints() + "");
                                api.regiterRound(LocalDateTime.now(), playerArray[0] , machine);
                            }
                        } catch (Connect4DAOException ex) {
                            Logger.getLogger(FXMLGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    // Player2 is the winner
                    else {
                        txtWinner.textProperty().setValue(nameP2 + " is the winner");
                        try {
                            if (playerArray[1] != null && isCooperative) {
                                // Increase points of the winner, in this case player2
                                // Increase 5 points because there are two players
                                playerArray[1].plusPoints(5);
                                txtPointsP2.textProperty().setValue(playerArray[1].getPoints() + "");
                                api.regiterRound(LocalDateTime.now(), playerArray[1], playerArray[0]);
                            }
                        } catch (Connect4DAOException ex) {
                            Logger.getLogger(FXMLGameBoardController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    txtCongrats.textProperty().setValue("Congratulations !!!");
                    // Restart the game and cleans the board
                    reStartGame();
                return ;
                }

                // Restart game if the board is full
                if (cont >= 56) {
                    reStartGame();
                    txtCongrats.textProperty().setValue("You tied");
                }

                if (isCooperative) {
                    flipTurn = !flipTurn;
                    // change the colour of the grid every turn
                    if (!flipTurn) {
                        boxP1.setStyle("-fx-background-color :  ganesBoro");
                        effect.setColor(Color.YELLOW);
                        boxP2.setStyle("-fx-background-color :  #ffffe6" );

                    }
                    else {
                        boxP2.setStyle("-fx-background-color :  ganesBoro");
                        effect.setColor(Color.RED);
                        boxP1.setStyle("-fx-background-color :  #ffe6e6" );

                    }
                    if (!grid.isDisabled()) grid.setEffect(effect);
                } else {
                    //"AI"
                    
                    
                    InnerWaitTask iwt = new InnerWaitTask();
                    
                    try{
                        Thread t = new Thread(iwt);
                        t.setDaemon(true);
                        t.start();                        
                    } catch (Exception e) {}                                       
                    
                    if (areFourConnected(P_RIGHT_TOK, gameGrid)) {
                        // Inform about the victory of the AI
                        txtWinner.textProperty().setValue("The AI is the winner");
                        txtCongrats.setTextFill(color.PURPLE);
                        txtCongrats.textProperty().setValue("GAME OVER");
                        // Register the Round
                        api.regiterRound(LocalDateTime.now(), machine , playerArray[0]);
                        // Don't permit more intectation with the game
                        grid.setDisable(true);
                        // Restart the game and cleans the board
                        reStartGame();
                    }

                }
    }
    /**
     * Method to prepare the gameboard  to begin an other match after a win
     */
    private void reStartGame() {
        // Don't permit more intectation with the game
        grid.setDisable(true);
        //reenable login and gamemodes, the game has ended
        bPlayAI.setDisable(false);
        bPlayFriend.setDisable(false);
        bLogP1.setDisable(false);
        bLogP2.setDisable(false);
        // Restore the bindings of log In and log out
        setBindings();

        // Eliminate the color of both boxes
        effect.setColor(Color.BLACK);
        boxP1.setStyle("-fx-background-color :  ganesBoro");
        boxP2.setStyle("-fx-background-color :  ganesBoro");

    }
    /**
     * This method allows the FULL SCREEN option without destroying the gameboard
     */
    private void ajustBoard(){
        grid.getChildren().forEach(
                    (Node n) -> {
                        if (n instanceof Rectangle) {
                            ((Rectangle) n).heightProperty().bind(Bindings.divide(this.grid.heightProperty(),7));
                            ((Rectangle) n).widthProperty().bind(Bindings.divide(this.grid.widthProperty(),8));
                        }
                    }
            );
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
    /**
     * methods to LOG IN the user
     * @throws IOException
     */
    private void performLogIn(Event event) throws IOException{
        //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLLogin.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLLoginController detailsController = myLoader.<FXMLLoginController>getController();

        Button b = ((Button)event.getSource());

        if (bLogP1.equals(b)) {
            detailsController.initData(playerArray,controller, true, this.currentCSS);
        }
        if (bLogP2.equals(b)) {
            detailsController.initData(playerArray,controller, false, this.currentCSS);
        }

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Log in");
        scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());
        stage.getIcons().add(new Image("images/Icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    private void handleEnterLogIn(KeyEvent event) throws IOException{
        if(event.getCode() == KeyCode.ENTER) {
            Button source = ((Button)event.getSource());
            //playerArray index is null -> log in
            if (source.equals(bLogP1) && playerArray[0] == null) {
                performLogIn(event);
            }

            else if (source.equals(bLogP2) && playerArray[1] == null) {
                performLogIn(event);
            }

            // else log out
            else {performLogOut(event);}

            //if after perfom log in we don't have null in the position, we were successful
            if (source.equals(bLogP1) && playerArray[0] != null) {
                source.setText("Log Out");
            }
            else if (source.equals(bLogP2) && playerArray[1] != null) {
                source.setText("Log Out");
            }

            else { source.setText("Log In");}
            }

    }

    @FXML
    private void handleButtonLogIn(ActionEvent event) throws IOException {
        Button source = ((Button)event.getSource());
        //playerArray index is null -> log in
        if (source.equals(bLogP1) && playerArray[0] == null) {
            performLogIn(event);
        }

        else if (source.equals(bLogP2) && playerArray[1] == null) {
            performLogIn(event);
        }

        // else log out
        else {performLogOut(event);}

        //if after perfom log in we don't have null in the position, we were successful
        if (source.equals(bLogP1) && playerArray[0] != null) {
            source.setText("Log Out");
        }
        else if (source.equals(bLogP2) && playerArray[1] != null) {
            source.setText("Log Out");
        }

        else { source.setText("Log In");}

    }


    /**
     * methods to LOG OUT the user
     * @param event
     * @throws IOException
     */
    private void performLogOut(Event event) throws IOException{
        Alert alert= new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Log out");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure that you want to log out, you will not save your future points.");
        alert.initOwner(bLogP1.getScene().getWindow());
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK){
            // if player1 wants to log out
            if (event.getSource().equals(bLogP1)) {
                playerArray[0] = null;
                txtNameP1.textProperty().setValue("Player1");
                imageP1.setImage(new Image("avatars/default.png"));
                txtPointsP1.textProperty().setValue("0");
            }
            // if player2 wants to log out
            if (event.getSource().equals(bLogP2)) {
                playerArray[1] = null;
                txtNameP2.textProperty().setValue("Player2");
                imageP2.setImage(new Image("avatars/default.png"));
                txtPointsP2.textProperty().setValue("0");
            }
        }
    }
    private void handleButtonLogOut(ActionEvent event) throws IOException {
        performLogOut(event);
    }

    private void handleEnterLogOut(KeyEvent event) throws IOException{
        if(event.getCode() == KeyCode.ENTER)
            performLogOut(event);
    }

    /**
     * methods to select the option play with a AI
     */
    private void performPlayIA() {
        // Restart the game
        grid.setEffect(effect);
        gameGrid = new int[8][7];
        grid.getChildren().forEach((Node circle) -> {
            if(circle instanceof  Circle){
                ((Circle)circle).setVisible(false);
            }
        });
        txtWinner.textProperty().setValue("");
        txtCongrats.textProperty().setValue("");
        // set the the BLACK colour to the grid
        effect.setColor(Color.BLACK);
        // Get player points if it is logged
        if (playerArray[0] != null) {
            txtPointsP1.textProperty().setValue(playerArray[0].getPoints() + "");
        }
        // disable the buttons logIn
        bLogP1.disableProperty().unbind();
        bLogP2.disableProperty().unbind();
        bLogP1.setDisable(true);
        bLogP2.setDisable(true);
        //do not select other game type if you've already selected one
        bPlayAI.disableProperty().unbind();
        bPlayFriend.disableProperty().unbind();
        bPlayAI.setDisable(true);
        bPlayFriend.setDisable(true);
        //stop more log ins if the game started
        bLogP1.setDisable(true);
        bLogP2.setDisable(true);
        // initialize the data needed
        initDataGame(false);
        grid.setDisable(false);
        // Restart the counter
        cont = 0;
    }
    @FXML
    private void handleButtonPlayIA(ActionEvent event) {
        performPlayIA();
    }
    @FXML
    private void handleEnterPlayIA(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
            performPlayIA();
    }

    /**
     * methods to select the option play with a friend
     */
    private void performPlayFriend() {
        // Restart the game
        grid.setEffect(effect);
        gameGrid = new int[8][7];
        grid.getChildren().forEach((Node circle) -> {
            if(circle instanceof  Circle){
                ((Circle)circle).setVisible(false);
            }
        });
        txtWinner.textProperty().setValue("");
        txtCongrats.textProperty().setValue("");
        // Set the red colour which indicates that the turn is from the player1
        effect.setColor(Color.RED);
        boxP1.setStyle("-fx-background-color :  #ffe6e6" );
        // Get players points if they are logged
        if (playerArray[0] != null) {
            txtPointsP1.textProperty().setValue(playerArray[0].getPoints() + "");
        }
        if (playerArray[1] != null) {
            txtPointsP2.textProperty().setValue(playerArray[1].getPoints()+ "");
        }

        // disable the buttons logIn
        bLogP1.disableProperty().unbind();
        bLogP2.disableProperty().unbind();
        bLogP1.setDisable(true);
        bLogP2.setDisable(true);

        // initialize the data needed
        initDataGame(true);
        grid.setDisable(false);

        //do not select other game type if you've already selected one
        bPlayAI.disableProperty().unbind();
        bPlayFriend.disableProperty().unbind();
        bPlayAI.setDisable(true);
        bPlayFriend.setDisable(true);
        //stop more log ins if the game started
        bLogP1.setDisable(true);
        bLogP2.setDisable(true);
        // Restart the counter
        cont = 0;
    }
    @FXML
    private void handleButtonPlayFriend(ActionEvent event) {
        performPlayFriend();
    }
    @FXML
    private void handleEnterPlayFriend(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER)
            performPlayFriend();
    }

    @FXML
    private void handleChangeTheme(ActionEvent event) {
        RadioMenuItem radioMenuItem = (RadioMenuItem) event.getSource();
        
        if ( radioMenuItem.equals(radioMenuDark) ) {
            this.currentCSS = darkCSS; 
          
        }
        else {
            this.currentCSS = lightCSS;
           
        }
        bLogP2.getScene().getStylesheets().clear();
        bLogP2.getScene().getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());
       
      //  scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());

    }
    private void performModify(Event event) throws IOException {
         //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLModifyP.fxml"));
        Pane root = (Pane) myLoader.load();

        //Get the controller of the UI
        FXMLModifyPController detailsController = myLoader.<FXMLModifyPController>getController();
        // initialize data in the next screen
        if (bModifyP1.isHover()) {
            detailsController.initData(playerArray[0], true, this, currentCSS);
        } else {
            detailsController.initData(playerArray[1], false, this, currentCSS);
        }

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Modify profile");
        scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());
        stage.getIcons().add(new Image("images/Icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.showAndWait();
    }
    @FXML
    private void handleButtonModify(ActionEvent event) throws IOException {
        performModify(event);
    }

    @FXML
    private void moveRanking(ActionEvent event) throws IOException {
         //Load the IU objects
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("FXMLInfo.fxml"));
        Pane root = (Pane) myLoader.load();          

        Scene scene = new Scene (root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ranking");
        scene.getStylesheets().add(getClass().getResource(currentCSS).toExternalForm());
        stage.getIcons().add(new Image("images/Icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL); // The modal avoid to used the rest of the app if we don't close the new window
        stage.setResizable(false);
        stage.showAndWait();
    }

}
