package main;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    
    public static Player player = new Player("TestUsername", 200, 0, 0, 0, 0);  //Shared player instance for all games
    public static Bot bot = new Bot("Roberto");

    @Override
    public void start(Stage primaryStage) {
        try {

            BorderPane root = FXMLLoader.load(getClass().getResource("DiceGame.fxml"));
            Scene scene = new Scene(root, 1920, 1080);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            
            primaryStage.setTitle("Casino Game Hub");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Player getPlayer() {return player;}
    public static Bot getBot() {return bot;}
}
