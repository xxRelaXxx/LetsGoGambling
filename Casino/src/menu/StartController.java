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

public class StartController {
	
	private Stage stage;
	private Scene scene;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btLogIn;

    @FXML
    private Button btSignIn;

    @FXML
    void gosignInPage(ActionEvent event) {
    	BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("fxml/SignIn.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
	    	scene = new Scene(root);
	    	stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void gologInPage(ActionEvent event) {
    	BorderPane root;
		try {
			root = (BorderPane)FXMLLoader.load(getClass().getResource("fxml/LogIn.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
	    	scene = new Scene(root);
	    	stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {
        assert btLogIn != null : "fx:id=\"btLogIn\" was not injected: check your FXML file 'StartMenu.fxml'.";
        assert btSignIn != null : "fx:id=\"btSignIn\" was not injected: check your FXML file 'StartMenu.fxml'.";

    }

}
