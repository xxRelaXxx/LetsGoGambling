package roulette.controllers;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
public class PopUpController {
	@FXML
	private Button siButton;
	@FXML
	private Button noButton;
	@FXML
	public void initialize() {
	    siButton.setStyle("-fx-font-size: 50px; -fx-alignment: center;");
	    noButton.setStyle("-fx-font-size: 50px; -fx-alignment: center;");
	}
	
	@FXML
	void si(ActionEvent event) throws IOException {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("/roulette/fxml/RoulettePazza.fxml"));
	    Parent root = loader.load();

	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    Scene scene = new Scene(root);
	    
	    stage.setScene(scene);
	    stage.show();

	    stage.setFullScreen(true);
	}

   @FXML
   void no(ActionEvent event) {
   }
}



