module Casino {
	requires javafx.controls;
	requires javafx.fxml;
    requires javafx.graphics;
	requires javafx.media;
	
    exports menu to javafx.fxml;  // Exports 'menu' package to javafx.fxml
	exports main to javafx.graphics;  // Exports 'main' package to javafx.graphics
    exports craps;  // Exports 'craps' craps
    exports diceGame.controllers;

    opens craps to javafx.fxml;  // Open 'craps' package to javafx.fxml
    opens menu to javafx.fxml;
    opens diceGame.controllers to javafx.fxml;
}
