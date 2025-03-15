package roulette.controllers;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
public class RoulettePazzaController {
	private Boolean PlayerTurn = true;
	private Boolean isTransitionOver = false;
	public static Boolean isRicaricaAnimationOver = true;
	@FXML
	private ImageView RevolverBot;
	@FXML
	private ImageView RevolverIMG;
	@FXML
	private ImageView RevolverPlayer;
	@FXML
	private ImageView GunShotPlayer;
	@FXML
	private ImageView GunShotBot;
	@FXML
	private Button sparaBtn;
   @FXML
   private Label lblBot;
   @FXML
   private Label lblPlayer;
	@FXML
	private ImageView bloodDrip; // ImageView per l'effetto di gocciolamento
	public void initialize() {
		// Imposta l'ImageView come invisibile all'inizio
		bloodDrip.setVisible(false);
		// Gestione dell'evento hover
		sparaBtn.setOnMouseEntered(event -> {
			isTransitionOver = false;
			if (!isTransitionOver) {
				bloodDrip.setVisible(true); // Mostra le gocce di sangue
				// Crea l'animazione
				TranslateTransition transition = new TranslateTransition(Duration.seconds(1), bloodDrip);
				transition.setFromY(40); // Parti dalla posizione iniziale
				transition.setToY(70); // Sposta le gocce verso il basso
				transition.setCycleCount(1); // Esegui l'animazione solo una volta
				transition.play(); // Avvia l'animazione
				transition.setOnFinished(event1 -> {
					isTransitionOver = true;
				});
			}
		});
		sparaBtn.setOnMouseExited(event -> {
			if (isTransitionOver) {
				// Riporta le gocce alla posizione iniziale e nascondile
				TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), bloodDrip);
				transition.setFromY(70); // Parti dalla posizione finale
				transition.setToY(40); // Riporta le gocce alla posizione iniziale
				transition.setOnFinished(e -> bloodDrip.setVisible(false)); // Nasconde le gocce alla fine //
																			// dell'animazione
				transition.play(); // Avvia l'animazione
			}
		});
	}
	@FXML
	private void giraRevolver(ActionEvent e) {
		lblPlayer.setStyle("-fx-text-fill: black;");
		lblBot.setStyle("-fx-text-fill: black;");
		RevolverIMG.setVisible(true);
		if (PlayerTurn) {
			RevolverBot.setVisible(false);
			// Reset dell'angolo di rotazione prima di applicare la nuova animazione
			RevolverIMG.setRotate(90);
			// Crea la transizione di rotazione
			RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), RevolverIMG);
			rotateTransition.setByAngle(-90 * 10);
			rotateTransition.setCycleCount(1);
			rotateTransition.setAutoReverse(false);
			rotateTransition.play();
			rotateTransition.setOnFinished(event -> {
				RevolverPlayer.setVisible(true);
				RevolverIMG.setVisible(false);
				sparaBtn.setVisible(true);
				lblPlayer.setStyle("-fx-text-fill: green;");
				lblBot.setStyle("-fx-text-fill: black;");
			});
			PlayerTurn = false;
		} else {
			RevolverPlayer.setVisible(false);
			sparaBtn.setVisible(false);
			bloodDrip.setVisible(false);
			// Reset dell'angolo di rotazione prima di applicare la nuova animazione
			RevolverIMG.setRotate(-90);
			// Crea la transizione di rotazione
			RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), RevolverIMG);
			rotateTransition.setByAngle(90 * 10);
			rotateTransition.setCycleCount(1);
			rotateTransition.setAutoReverse(false);
			rotateTransition.play();
			rotateTransition.setOnFinished(event -> {
				RevolverBot.setVisible(true);
				RevolverIMG.setVisible(false);
				lblPlayer.setStyle("-fx-text-fill: black;");
				lblBot.setStyle("-fx-text-fill: green;");
			});
			PlayerTurn = true;
		}
	}
	
	@FXML
	private void spara(ActionEvent event) {
		   try {
	            // Carica il file FXML della nuova scena
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ricarica.fxml"));
	            Parent root = loader.load();
	            // Crea una nuova finestra (Stage)
	            Stage stage = new Stage();
	            stage.setScene(new Scene(root));
	            stage.setTitle("Ricarica");
	            stage.show();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}


