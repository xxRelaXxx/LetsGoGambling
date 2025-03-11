package main;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
    //public static Player player = new Player("TestUsername", 200, 0, 0, 0, 0);  //Shared player instance for all games
    //public static Bot bot = new Bot("Roberto");
	private static Stage stage; // Store a reference to the main stage
	
	public static Player player;  //Shared player instance for all games
    public static Bot bot = new Bot("botname");
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/menu/fxml/StartMenu.fxml"));
			Scene scene = new Scene(root,1080,720);
			//scene.getStylesheets().add(getClass().getResource("menu.css").toExternalForm());
			primaryStage.setTitle("Casino name");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setFullScreen(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getPrimaryStage() {return stage;}
    public static Player getPlayer() {return player;}
    public static Bot getBot() {return bot;}
}
