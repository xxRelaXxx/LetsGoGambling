package guessNumber;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InitialController {
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	void handle1_10(ActionEvent event) {
		apriSchermataGioco("1-10");
	}

	@FXML
	void handle1_15(ActionEvent event) {
		apriSchermataGioco("1-15");

	}

	@FXML
	void handle1_20(ActionEvent event) {
		apriSchermataGioco("1-20");

	}

	@FXML
	void initialize() {

	}

	private void apriSchermataGioco(String intervallo) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
			Parent root = loader.load();
			GameController gameController = loader.getController();
			gameController.setIntervallo(intervallo);
			gameController.setPrimaryStage(primaryStage);
			
			Scene scene = new Scene(root);
			primaryStage.setFullScreen(true);
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
