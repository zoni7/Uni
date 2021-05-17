/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect4;

import DBAccess.Connect4DAOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Connect4;
import model.DayRank;
import model.Player;
import model.Round;

/**
 * FXML Controller class
 *
 * @author USUARIO
 */
public class FXMLInfoController implements Initializable {

    @FXML
    private TableView<Player> table1_8;
    @FXML
    private TableColumn<Player, Image> avatarColumn1_8;
    @FXML
    private TableColumn<Player, String> nicknameColumn1_8;
    @FXML
    private TableColumn<Player, Integer> ratingColumn1_8;
    @FXML
    private TextField txtSearch1_8;    
    private ObservableList<Player> observablePlayerData;
    private ArrayList<Player> playerData;
    @FXML
    private TableView<Round> table1_9;
    @FXML
    private TableColumn<Round, String> dayColumn1_9;
    @FXML
    private TableColumn<Round, String> timeColumn1_9;
    @FXML
    private TableColumn<Round, String> winnerColumn1_9;
    @FXML
    private TableColumn<Round, String> loserColumn1_9;
    @FXML
    private DatePicker startDate1_9;
    @FXML
    private DatePicker endDate1_9;
    @FXML
    private Button bSearch1_9;
    @FXML
    private TableView<Round> table1_10;
    @FXML
    private TableColumn<Round, String> dayColumn1_10;
    @FXML
    private TableColumn<Round, String> timeColumn1_10;
    @FXML
    private TableColumn<Round, String> winnerColumn1_10;
    @FXML
    private TableColumn<Round, String> loserColumn1_10;
    @FXML
    private DatePicker startDate1_10;
    @FXML
    private DatePicker endDate1_10;
    @FXML
    private Button bSearch1_10;
    @FXML
    private ChoiceBox<String> pickerPlayer1_10;
    @FXML
    private TableView<Round> table1_11;
    @FXML
    private TableColumn<Round, String> dayColumn1_11;
    @FXML
    private TableColumn<Round, String> timeColumn1_11;
    @FXML
    private TableColumn<Round, String> winnerColumn1_11;
    @FXML
    private TableColumn<Round, String> loserColumn1_11;
    @FXML
    private DatePicker startDate1_11;
    @FXML
    private DatePicker endDate1_11;
    @FXML
    private Button bSearch1_11;
    @FXML
    private ChoiceBox<String> pickerPlayer1_11;
    @FXML
    private TableView<Round> table1_12;
    @FXML
    private TableColumn<Round, String> dayColumn1_12;
    @FXML
    private TableColumn<Round, String> timeColumn1_12;
    @FXML
    private TableColumn<Round, String> winnerColumn1_12;
    @FXML
    private DatePicker startDate1_12;
    @FXML
    private DatePicker endDate1_12;
    @FXML
    private Button bSearch1_12;
    @FXML
    private ChoiceBox<String> pickerPlayer1_12;
    
    private  model.Connect4 api;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Create instance Connect4 lib
            api = model.Connect4.getSingletonConnect4();
            // Initialize all bindings of the window
            bindings();
            // Initialize actions for Ranking pane
            actionsRanking();
            // Initialize actions for Games Played pane
            actionsGamesPlayed();
            // Initialize actions for Player games pane
            actionsPlayerGames();
            // Initialize actions for Player wins pane
            actionsPlayerWins();
            // Initialize actions for Player loses pane
            actionsPlayerLoses();
        } catch (Connect4DAOException ex) {
            Logger.getLogger(FXMLInfoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private void bindings() {
        bSearch1_10.disableProperty().bind(pickerPlayer1_10.valueProperty().isNull().or(startDate1_10.valueProperty().isNull()).or(endDate1_10.valueProperty().isNull()));
        bSearch1_9.disableProperty().bind(startDate1_9.valueProperty().isNull().or(endDate1_9.valueProperty().isNull()));
    }
    
    /**
     * Method to load the list of players nicknames in each tab which need it
     * @param choiceBox 
     */
    private void choiceBox(ChoiceBox choiceBox) {
        ArrayList<Player> playerList = api.getConnect4Ranking();
        // Add elements to the choice box
        ArrayList<Player> playerlist = api.getConnect4Ranking();
        ArrayList<String> nicknameArray = new ArrayList<String>();
        for(int i = 0; i < playerList.size(); i++) {
            nicknameArray.add(playerlist.get(i).getNickName());
        }
        ObservableList<String> observablePlayerList;
        observablePlayerList = FXCollections.observableArrayList(nicknameArray);
        choiceBox.setItems(observablePlayerList);
    }
    
    private void actionsRanking() {                   
            // Create the the ranking array with all the data
            playerData = api.getConnect4Ranking();
            // Connect the observable with the array            
            observablePlayerData = FXCollections.observableArrayList(playerData);
            // Connect the colums with the different categories of items (Avatars, nicknames and points)
            nicknameColumn1_8.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getNickName()));
            ratingColumn1_8.setCellValueFactory(cellData-> new SimpleIntegerProperty(cellData.getValue().getPoints()).asObject());
            avatarColumn1_8.setCellValueFactory(new PropertyValueFactory<Player,Image>("Avatar"));
            // CellFactory for the Avatars
            avatarColumn1_8.setCellFactory((c)->{
                return new TableCell<Player,Image>(){
                    @Override
                    protected void updateItem(Image item, boolean empty){
                        super.updateItem(item, empty);
                        if(item == null || empty)
                            setGraphic(null);
                        else{
                            ImageView imageView = new ImageView();
                            
                            imageView.setImage(item);
                            imageView.setFitWidth(40);
                            imageView.setFitHeight(40);
                            
                            setGraphic(imageView);
                        }
                        
                    }
                };
            }
            );
            
            FilteredList<Player> filteredData = new FilteredList<>(observablePlayerData, p -> true);
            // Create listener for a search box
            txtSearch1_8.textProperty().addListener((observable, oldValue, newValue) -> {              
                 filteredData.setPredicate(player ->{
                    // If filter text is empty, display all persons.
                    if (newValue == null || newValue.isEmpty()) {
                            return true;
                    }

                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (player.getNickName().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches first name.
                    } else if (player.getNickName().toLowerCase().contains(lowerCaseFilter)) {
                            return true; // Filter matches last name.
                    }
                    return false; // Does not match.
                 });
            });
            
            // Wrap the FilteredList in a SortedList. 
            SortedList<Player> sortedData = new SortedList<>(filteredData);

            // Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(table1_8.comparatorProperty());

            // Add sorted (and filtered) data to the table.
            table1_8.setItems(sortedData);
                  
    }
    
    private void actionsGamesPlayed() {  
        bSearch1_9.setOnAction((event) -> performGamesPlayed());
    }
    
    @FXML
    private void performGamesPlayed() {                    
            
            Map<LocalDate, List<Round> > perDate = api.getRoundsPerDay();
            //since this map is sorted
            
            // x favor renombra las variables :3
            LocalDate start = startDate1_9.getValue();
            LocalDate end = endDate1_9.getValue();
            
            //generate days
            int lim = (int)start.until(end, ChronoUnit.DAYS);
            LocalDate current = start;
            List<Round> toShow = new ArrayList<Round>();
            
            for (int i = 0; i < lim; i++) {
                List<Round> l = perDate.get(current);   
                
                //"unwrap" list
                if (l != null) {
                    for (Round r : l) {
                        toShow.add(r);
                    }
                }
                
                
                current = current.plusDays(1);
            }
            
            //set grid view
            ObservableList<Round> obsList = FXCollections.observableArrayList(toShow);
            
            //set element per column
            dayColumn1_9.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getLocalDate().getDayOfMonth() + "/" +
                cellData.getValue().getLocalDate().getMonthValue() + "/" + cellData.getValue().getLocalDate().getYear())
            );
            timeColumn1_9.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getTimeStamp().getHour() + ":" +
                    cellData.getValue().getTimeStamp().getMinute() + ":" + cellData.getValue().getTimeStamp().getSecond()
            ));
            winnerColumn1_9.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getWinner().getNickName()));
            loserColumn1_9.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getLoser().getNickName()));

            //init
            table1_9.setItems(obsList);                            
    }
    
    /**
     * Methods to show all the games a player has made during a period of time
     * Methods used: actionsPlayerGames(), performPlayerGames()
     */
    private void actionsPlayerGames() {    
        // Initialize choice box
        choiceBox(pickerPlayer1_10);
        // Button search actions
        bSearch1_10.setOnAction((event) -> { performPlayerGames(); }); 
        bSearch1_10.setOnKeyPressed((event) -> { performPlayerGames(); });
    }
    
    private void performPlayerGames() {
        Player player = api.getPlayer(pickerPlayer1_10.getValue());
        LocalDate datePickerStart = startDate1_10.getValue();
        LocalDate datePickerEnd = endDate1_10.getValue();

        ArrayList<Round> roundList = api.getRoundsPlayer(player);

        // Search the start date
        int i = 0;
        while(i < roundList.size() && (roundList.get(i).getLocalDate().compareTo(datePickerStart) <= 0)) { i++; }
        // Add in a list just the elements needed
        ArrayList<Round> roundListSelected = new ArrayList<Round>();

        while (i < roundList.size()  && roundList.get(i).getLocalDate().compareTo(datePickerEnd) <= 0) {
            roundListSelected.add(roundList.get(i++));
        }
        ObservableList<Round> observableRoundList = FXCollections.observableArrayList(roundListSelected);

        dayColumn1_10.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getLocalDate().getDayOfMonth() + "/" +
                cellData.getValue().getLocalDate().getMonthValue() + "/" + cellData.getValue().getLocalDate().getYear())
        );
        timeColumn1_10.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getTimeStamp().getHour() + ":" +
                cellData.getValue().getTimeStamp().getMinute() + ":" + cellData.getValue().getTimeStamp().getSecond()
        ));
        winnerColumn1_10.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getWinner().getNickName()));
        loserColumn1_10.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getLoser().getNickName()));

        table1_10.setItems(observableRoundList);
    }
    
    private void actionsPlayerWins() {
        // Initialize choice box
        choiceBox(pickerPlayer1_11);
        bSearch1_11.setOnAction((event) -> {
            performPlayerWins();
        });
        bSearch1_11.setOnKeyPressed((event) -> {
            performPlayerWins();
        });
    }
    private void performPlayerWins() {
        Player player = api.getPlayer(pickerPlayer1_11.getValue());
        LocalDate datePickerStart = startDate1_11.getValue();
        LocalDate datePickerEnd = endDate1_11.getValue();

        ArrayList<Round> roundList = api.getWinnedRoundsPlayer(player);

        // Search the start date
        int i = 0;
        while(i < roundList.size() && (roundList.get(i).getLocalDate().compareTo(datePickerStart) <= 0)) { i++; }
        // Add in a list just the elements needed
        ArrayList<Round> roundListSelected = new ArrayList<Round>();

        while (i < roundList.size()  && roundList.get(i).getLocalDate().compareTo(datePickerEnd) <= 0) {
            roundListSelected.add(roundList.get(i++));
        }
        ObservableList<Round> observableRoundList = FXCollections.observableArrayList(roundListSelected);

        dayColumn1_11.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getLocalDate().getDayOfMonth() + "/" +
                cellData.getValue().getLocalDate().getMonthValue() + "/" + cellData.getValue().getLocalDate().getYear())
        );
        timeColumn1_11.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getTimeStamp().getHour() + ":" +
                cellData.getValue().getTimeStamp().getMinute() + ":" + cellData.getValue().getTimeStamp().getSecond()
        ));
        winnerColumn1_11.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getWinner().getNickName()));
        loserColumn1_11.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getLoser().getNickName()));

        table1_11.setItems(observableRoundList);
    }
    private void actionsPlayerLoses() {
        // Initialize choice box
        choiceBox(pickerPlayer1_12);
        bSearch1_12.setOnAction((event) -> {
            performPlayerLoses();
        });
        bSearch1_12.setOnKeyPressed((event) -> {
            performPlayerLoses();
        });
        
    }
    private void performPlayerLoses() {
        Player player = api.getPlayer(pickerPlayer1_12.getValue());
        LocalDate datePickerStart = startDate1_12.getValue();
        LocalDate datePickerEnd = endDate1_12.getValue();

        ArrayList<Round> roundList = api.getLostRoundsPlayer(player);

        // Search the start date
        int i = 0;
        while(i < roundList.size() && (roundList.get(i).getLocalDate().compareTo(datePickerStart) <= 0)) { i++; }
        // Add in a list just the elements needed
        ArrayList<Round> roundListSelected = new ArrayList<Round>();

        while (i < roundList.size()  && roundList.get(i).getLocalDate().compareTo(datePickerEnd) <= 0) {
            roundListSelected.add(roundList.get(i++));
        }
        ObservableList<Round> observableRoundList = FXCollections.observableArrayList(roundListSelected);

        dayColumn1_12.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getLocalDate().getDayOfMonth() + "/" +
                cellData.getValue().getLocalDate().getMonthValue() + "/" + cellData.getValue().getLocalDate().getYear())
        );
        timeColumn1_12.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getTimeStamp().getHour() + ":" +
                cellData.getValue().getTimeStamp().getMinute() + ":" + cellData.getValue().getTimeStamp().getSecond()
        ));
        winnerColumn1_12.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getWinner().getNickName()));

        table1_12.setItems(observableRoundList);
    }

}
