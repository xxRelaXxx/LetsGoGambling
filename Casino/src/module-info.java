module Casino {
	requires javafx.controls;
	requires javafx.fxml;
    requires javafx.graphics;
	requires javafx.media;
	
    exports menu to javafx.fxml;  // Exports 'menu' package to javafx.fxml
	exports main to javafx.graphics;  // Exports 'main' package to javafx.graphics
	exports guessNumber to javafx.fxml;
	
	//Export controllers of each game
    exports diceGame.controllers;
    exports roulette.controllers; 
    exports slot.controller;
    //exports guessNumber;
    
    
    //opens packages for reflection access (necessary for FXML)
    opens menu to javafx.fxml;
    opens diceGame.controllers to javafx.fxml;
    opens roulette.controllers to javafx.fxml;
    opens slot.controller to javafx.fxml;
    opens guessNumber to javafx.fxml;
    
    
}
