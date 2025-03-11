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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class LogInController {

	private Stage stage;
	private Scene scene;
	private int shift = 3; //shift for encrypting and decrypting psw
	private GameAccess gameAccess = new GameAccess();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btBack;

    @FXML
    private Button btLogIn;

    @FXML
    private Label lbUserNotFound;
    
    @FXML
    private Label lbWrongPsw;
    
    @FXML
    private Label lbEmptyFields;
    
    @FXML
    private TextField inpPsw;

    @FXML
    private TextField inpUsername;

    @FXML
    void goBack(ActionEvent event) {
    	//go to StartMenu
    	BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("fxml/StartMenu.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
	    	scene = new Scene(root);
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void logIn(ActionEvent event) {
    	if (gameAccess.logIn(this)) {
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
    }
    
    
    //getter methods
    public TextField getInpPsw() {return inpPsw;}
    public TextField getInpUsername() {return inpUsername;}
    public Label getLbEmptyFields() {return lbEmptyFields;}
    public Label getLbUserNotFound() {return lbUserNotFound;}
    public Label getLbWrongPsw() {return lbWrongPsw;}
    public int getShift() {return shift;}

    @FXML
    void initialize() {
        assert btBack != null : "fx:id=\"btBack\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert btLogIn != null : "fx:id=\"btLogIn\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert inpPsw != null : "fx:id=\"inpPsw\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert inpUsername != null : "fx:id=\"inpUsername\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert lbUserNotFound != null : "fx:id=\"lbUserNotFound\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert lbWrongPsw != null : "fx:id=\"lbWrongPsw\" was not injected: check your FXML file 'LogIn.fxml'.";
        assert lbEmptyFields != null : "fx:id=\"lbEmptyFields\" was not injected: check your FXML file 'LogIn.fxml'.";

    }

}
