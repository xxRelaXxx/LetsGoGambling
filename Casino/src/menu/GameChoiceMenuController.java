package menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameChoiceMenuController {

	private Stage stage;
	private Scene scene;	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btCraps;

    @FXML
    private Button btGame5;

    @FXML
    private Button btGoBack;

    @FXML
    private Button btGuessNumber;

    @FXML
    private Button btRoulette;

    @FXML
    private Button btSlotMachines;

    @FXML
    private Button btViewStats;

    @FXML
    void goBack(ActionEvent event) {
    	
    }

    @FXML
    void startCraps(ActionEvent event) {
    	/*BorderPane root;
		try {
			root = FXMLLoader.load(getClass().getResource("/craps/DiceGame.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
	    	scene = new Scene(root);
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    	
    	BorderPane root;
    	try {
			root = FXMLLoader.load(getClass().getResource("/diceGame/fxmls/modeSwitch.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
	    	scene = new Scene(root);
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void startGame5(ActionEvent event) {

    }

    @FXML
    void startGuessNumber(ActionEvent event) {

    }

    @FXML
    void startRoulette(ActionEvent event) {

    }

    @FXML
    void startSlotMachines(ActionEvent event) {

    }

    @FXML
    void viewStats(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btCraps != null : "fx:id=\"btCraps\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert btGame5 != null : "fx:id=\"btGame5\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert btGoBack != null : "fx:id=\"btGoBack\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert btGuessNumber != null : "fx:id=\"btGuessNumber\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert btRoulette != null : "fx:id=\"btRoulette\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert btSlotMachines != null : "fx:id=\"btSlotMachines\" was not injected: check your FXML file 'GameMenu.fxml'.";
        assert btViewStats != null : "fx:id=\"btViewStats\" was not injected: check your FXML file 'GameMenu.fxml'.";

    }

}
