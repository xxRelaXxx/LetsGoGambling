package diceGame.controllers;

import diceGame.DiceGame2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import main.SceneController;

public class DiceGameController extends SceneController {

    @FXML private Label resultLabel, multiplierLabel, moneyLabel;
    @FXML private TextField betInput;
    @FXML private Button rollButton, backButton;
    @FXML private StackPane PlayerDiceContainer1, PlayerDiceContainer2, BotDiceContainer1, BotDiceContainer2, PlayerDiceContainer3, PlayerDiceContainer4, BotDiceContainer3, BotDiceContainer4;
    @FXML private ImageView botDiceImage1, botDiceImage2, playerDiceImage1, playerDiceImage2, botDiceImage3, botDiceImage4, playerDiceImage3, playerDiceImage4;

    private DiceGame2 diceGame;

    @FXML
    public void initialize() {
        diceGame = new DiceGame2(this);
        // Default mode, can be updated dynamically based on user input or settings
        diceGame.setupGame(4);  // Default to 4 dice mode SUKKKKKKKKAAAAAAAAAAA NEEEEEEEEED TOOOOOOOOO REEEEEEEESOOOOOOOOOOOOOLLLLLVVVVVVEEEEEEEEEEEEEEE!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

    @FXML
    private void rollDice() {
        diceGame.startGame();
    }

    // Getter Methods for `DiceGame` to access UI elements
    public TextField getBetInput() { return betInput; }
    public Button getRollButton() { return rollButton; }
    public Label getResultLabel() { return resultLabel; }
    public Label getMultiplierLabel() { return multiplierLabel; }
    public Label getMoneyLabel() { return moneyLabel; }

    
 // New method to get the dice container dynamically based on index
    public StackPane getPlayerDiceContainer(int index) {
        switch (index) {
            case 0: return PlayerDiceContainer1;
            case 1: return PlayerDiceContainer2;
            case 2: return PlayerDiceContainer3;
            case 3: return PlayerDiceContainer4;
            default: return null;
        }
    }

    public StackPane getBotDiceContainer(int index) {
        switch (index) {
            case 0: return BotDiceContainer1;
            case 1: return BotDiceContainer2;
            case 2: return BotDiceContainer3;
            case 3: return BotDiceContainer4;
            default: return null;
        }
    }
    
    public ImageView getPlayerDiceImage(int index) {
        switch (index) {
            case 0: return playerDiceImage1;
            case 1: return playerDiceImage2;
            case 2: return playerDiceImage3;
            case 3: return playerDiceImage4;
            default: return null;
        }
    }

    public ImageView getBotDiceImage(int index) {
        switch (index) {
            case 0: return botDiceImage1;
            case 1: return botDiceImage2;
            case 2: return botDiceImage3;
            case 3: return botDiceImage4;
            default: return null;
        }
    }

    // UI Update Methods
    public void updateMoneyLabel(String moneyText) { moneyLabel.setText(moneyText); }
    public void updateResult(String resultText) { resultLabel.setText(resultText); }
    public void updateMultiplier(String multiplierText) { multiplierLabel.setText(multiplierText); }

    public void turnToSwitch(ActionEvent event) {
        // Reset the game UI and game state
        clearGameUI(); // Clear all game-related UI and states
        // Switch to the mode selection scene
        switchScene(event, "/diceGame/fxmls/modeSwitch.fxml");
        // Reset game mode in the DiceGameModeController if applicable
        DiceGameModeController.resetGameMode(); 
        diceGame.setupGame(0);
    }

    @FXML
    public void showStats(ActionEvent event) {
        // Placeholder for stats view
    }

    public void updateDiceVisibility(int diceCount) {
        // Set visibility of the dice containers based on the selected mode
        PlayerDiceContainer1.setVisible(diceCount >= 1);
        PlayerDiceContainer2.setVisible(diceCount >= 2);
        PlayerDiceContainer3.setVisible(diceCount >= 3);
        PlayerDiceContainer4.setVisible(diceCount >= 4);

        BotDiceContainer1.setVisible(diceCount >= 1);
        BotDiceContainer2.setVisible(diceCount >= 2);
        BotDiceContainer3.setVisible(diceCount >= 3);
        BotDiceContainer4.setVisible(diceCount >= 4);
    }

    public void hideDiceImages() {
        for (int i = 0; i < 4; i++) {
            if (getBotDiceImage(i) != null) {
                getBotDiceImage(i).setOpacity(0);
            }
            if (getPlayerDiceImage(i) != null) {
                getPlayerDiceImage(i).setOpacity(0);
            }
        }
    }

    
    private void clearGameUI() {
        // Hide or reset all dice containers and dice images
        for (int i = 0; i < 4; i++) {
            getPlayerDiceContainer(i).getChildren().clear();
            getBotDiceContainer(i).getChildren().clear();
            getPlayerDiceImage(i).setImage(null);
            getBotDiceImage(i).setImage(null);
            getPlayerDiceContainer(i).setVisible(true);
            getBotDiceContainer(i).setVisible(true);
        }

        // Reset any other game-related state if needed, like bet input, etc.
        getBetInput().clear(); // Reset the bet input
    }

    // Call this method when the number of dice changes, so the UI is updated accordingly
    public void setGameMode(int mode) {
        // Update dice visibility based on the selected mode
        updateDiceVisibility(mode);
        diceGame.setupGame(mode);  // Pass the mode to setupGame
    }
}
