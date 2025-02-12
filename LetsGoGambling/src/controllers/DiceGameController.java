package controllers;

import DiceGame.DiceGame;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class DiceGameController {

    @FXML private Label resultLabel, multiplierLabel, moneyLabel;
    @FXML private TextField betInput;
    @FXML private Button rollButton;
    @FXML private StackPane PlayerDiceContainer1, PlayerDiceContainer2, BotDiceContainer1, BotDiceContainer2;
    @FXML private ImageView botDiceImage1, botDiceImage2, playerDiceImage1, playerDiceImage2;

    private DiceGame diceGame;

    @FXML
    public void initialize() {
        diceGame = new DiceGame(this);
        diceGame.setupGame();
    }

    @FXML
    private void rollDice() {
        diceGame.startRoll();
    }

    // 🔹 **Getter Methods for `DiceGame` to access UI elements**
    public TextField getBetInput() { return betInput; }
    public Button getRollButton() { return rollButton; }
    public Label getResultLabel() { return resultLabel; }
    public Label getMultiplierLabel() { return multiplierLabel; }
    public Label getMoneyLabel() { return moneyLabel; }
    public StackPane getPlayerDiceContainer1() { return PlayerDiceContainer1; }
    public StackPane getPlayerDiceContainer2() { return PlayerDiceContainer2; }
    public StackPane getBotDiceContainer1() { return BotDiceContainer1; }
    public StackPane getBotDiceContainer2() { return BotDiceContainer2; }
    public ImageView getBotDiceImage1() { return botDiceImage1; }
    public ImageView getBotDiceImage2() { return botDiceImage2; }
    public ImageView getPlayerDiceImage1() { return playerDiceImage1; }
    public ImageView getPlayerDiceImage2() { return playerDiceImage2; }

    // 🔹 UI Update Methods
    public void updateMoneyLabel(String moneyText) { moneyLabel.setText(moneyText); }
    public void updateResult(String resultText) { resultLabel.setText(resultText); }
    public void updateMultiplier(String multiplierText) { multiplierLabel.setText(multiplierText); }
}
