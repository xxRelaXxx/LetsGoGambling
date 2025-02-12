package DiceGame;

import controllers.DiceGameController;
import main.*;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class DiceGame extends Game {

    private final DiceGameController controller;
    private final Bot bot;
    private final Player player;
    private Dice3D PlayerDice3D1, PlayerDice3D2, BotDice3D1, BotDice3D2;

    public DiceGame(DiceGameController controller) {
        super(10); // Min bet: 10
        this.controller = controller;
        this.bot = Main.getBot();
        this.player = Main.getPlayer();
    }

    public void setupGame() {
        PlayerDice3D1 = new Dice3D();
        PlayerDice3D2 = new Dice3D();
        BotDice3D1 = new Dice3D();
        BotDice3D2 = new Dice3D();

        controller.getPlayerDiceContainer1().getChildren().setAll(PlayerDice3D1.createScene());
        controller.getPlayerDiceContainer2().getChildren().setAll(PlayerDice3D2.createScene());
        controller.getBotDiceContainer1().getChildren().setAll(BotDice3D1.createScene());
        controller.getBotDiceContainer2().getChildren().setAll(BotDice3D2.createScene());

        controller.getPlayerDiceContainer1().getChildren().add(controller.getPlayerDiceImage1());
        controller.getPlayerDiceContainer2().getChildren().add(controller.getPlayerDiceImage2());
        controller.getBotDiceContainer1().getChildren().add(controller.getBotDiceImage1());
        controller.getBotDiceContainer2().getChildren().add(controller.getBotDiceImage2());

        hideDiceImages();
        updateUI();
    }

    @Override
    public void startGame() {
        startRoll();
    }

    public void startRoll() {
        int betAmount = validateBet(controller.getBetInput().getText());
        if (betAmount == -1) return;

        controller.getRollButton().setDisable(true);
        hideDiceImages();

        PlayerDice3D1.roll3DDice();
        PlayerDice3D2.roll3DDice();
        BotDice3D1.roll3DDice();
        BotDice3D2.roll3DDice();

        finalizeRoll(betAmount);
    }

    private void finalizeRoll(int betAmount) {
        int playerDice1 = (int) (Math.random() * 6) + 1;
        int playerDice2 = (int) (Math.random() * 6) + 1;
        int botDice1 = bot.rollDice();
        int botDice2 = bot.rollDice();

        new Timeline(new KeyFrame(Duration.seconds(1.75), event -> {
            controller.getBotDiceImage1().setImage(getDiceImage(botDice1));
            controller.getBotDiceImage2().setImage(getDiceImage(botDice2));
            controller.getPlayerDiceImage1().setImage(getDiceImage(playerDice1));
            controller.getPlayerDiceImage2().setImage(getDiceImage(playerDice2));

            int playerTotal = playerDice1 + playerDice2;
            int botTotal = botDice1 + botDice2;
            int multiplier = playerTotal - botTotal;
            int result = Math.abs(betAmount * multiplier);

            Main.player.incrementGamesPlayed();

            String resultText;
            if (playerTotal > botTotal) {
                player.addMoney(result);
                player.incrementGamesWon();
                bot.incrementGamesLost();
                resultText = "You Win! +" + result;
            } else if (playerTotal < botTotal) {
                player.subtractMoney(betAmount);
                player.incrementGamesLost();
                bot.incrementGamesWon();
                resultText = "Bot Wins! -" + betAmount;
            } else {
                resultText = "It's a Tie!";
            }

            controller.updateResult(resultText);
            controller.updateMultiplier("Multiplier: " + multiplier + "x");
            updateUI();

            new Timeline(new KeyFrame(Duration.seconds(1),
                new KeyValue(controller.getBotDiceImage1().opacityProperty(), 1, Interpolator.EASE_IN),
                new KeyValue(controller.getBotDiceImage2().opacityProperty(), 1, Interpolator.EASE_IN),
                new KeyValue(controller.getPlayerDiceImage1().opacityProperty(), 1, Interpolator.EASE_IN),
                new KeyValue(controller.getPlayerDiceImage2().opacityProperty(), 1, Interpolator.EASE_IN)
            )).play();

            controller.getRollButton().setDisable(false);
        })).play();
    }

    private int validateBet(String betText) {
        try {
            int bet = Integer.parseInt(betText);
            if (bet < minBet || bet > player.getMoney()) {
                controller.updateResult("Invalid bet amount! Min: " + minBet);
                return -1;
            }
            return bet;
        } catch (NumberFormatException e) {
            controller.updateResult("Enter a valid number!");
            return -1;
        }
    }

    private void updateUI() {
        controller.updateMoneyLabel("Money: $" + player.getMoney());
    }

    private void hideDiceImages() {
        controller.getBotDiceImage1().setOpacity(0);
        controller.getBotDiceImage2().setOpacity(0);
        controller.getPlayerDiceImage1().setOpacity(0);
        controller.getPlayerDiceImage2().setOpacity(0);
    }

    private Image getDiceImage(int diceNumber) {
        return new Image(getClass().getResourceAsStream("/textures/dice" + diceNumber + ".png"));
    }
}
