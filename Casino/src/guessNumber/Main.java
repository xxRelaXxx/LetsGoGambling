package guessNumber;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InitialPage.fxml"));
            Parent root = loader.load();
            InitialController controller = loader.getController();
            controller.setPrimaryStage(primaryStage); 
            Scene scene = new Scene(root);
            primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
