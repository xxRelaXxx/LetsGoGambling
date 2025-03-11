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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.*;

public class ModeMenuController {
	
	private Stage stage;
	private Scene scene;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btAutomaticMode;

    @FXML
    private Button btConfirmDeposit;

    @FXML
    private Button btManualMode;

    @FXML
    private TextField inpCardNumber;

    @FXML
    private TextField inpCardOwner;

    @FXML
    private TextField inpCvv;

    @FXML
    private TextField inpDepositBalance;

    @FXML
    private Label lbCurrentBalance;

    @FXML
    private Label lbEmptyFields;

    @FXML
    private Label lbNonValidData;

    @FXML
    private Label lbOperationConfirmed;

    @FXML
    void ConfirmDeposit(ActionEvent event) {
    	
    	
    	Main.player.setMoney((Main.player.getMoney() + Integer.parseInt(inpDepositBalance.getText())));
    }

    @FXML
    void playAutomaticMode(ActionEvent event) {
    	//automatic mode to be defined_______________________
    }

    @FXML
    void playManualMode(ActionEvent event) {
    	//go to game choice menu
    	BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("fxml/GameChoiceMenu.fxml")); // change into game mode choice
			stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
	    	scene = new Scene(root);
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    //getter methods
    public TextField getInpCardNumber() {return inpCardNumber;}
    public TextField getInpCardOwner() {return inpCardOwner;}
    public TextField getInpCvv() {return inpCvv;}
    public TextField getInpDepositBalance() {return inpDepositBalance;}
    public Label getLbCurrentBalance() {return lbCurrentBalance;}
    public Label getLbEmptyFields() {return lbEmptyFields;}
    public Label getLbNonValidData() {return lbNonValidData;}
    public Label getLbOperationConfirmed() {return lbOperationConfirmed;}
    

    @FXML
    void initialize() {
        assert btAutomaticMode != null : "fx:id=\"btAutomaticMode\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert btConfirmDeposit != null : "fx:id=\"btConfirmDeposit\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert btManualMode != null : "fx:id=\"btManualMode\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert inpCardNumber != null : "fx:id=\"inpCardNumber\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert inpCardOwner != null : "fx:id=\"inpCardOwner\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert inpCvv != null : "fx:id=\"inpCvv\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert inpDepositBalance != null : "fx:id=\"inpDepositBalance\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert lbCurrentBalance != null : "fx:id=\"lbCurrentBalance\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert lbEmptyFields != null : "fx:id=\"lbEmptyFields\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert lbNonValidData != null : "fx:id=\"lbNonValidData\" was not injected: check your FXML file 'ModeMenu.fxml'.";
        assert lbOperationConfirmed != null : "fx:id=\"lbOperationConfirmed\" was not injected: check your FXML file 'ModeMenu.fxml'.";

    }

}
