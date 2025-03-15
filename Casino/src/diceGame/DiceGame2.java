package diceGame;

import diceGame.controllers.DiceGameController;
import main.*;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class DiceGame2 extends Game {

    private final DiceGameController controller;
    private final Bot bot;
    private final Player player;
    private Dice3D[] playerDice3D = new Dice3D[4];
    private Dice3D[] botDice3D = new Dice3D[4];
    private MediaPlayer mediaPlayer;

    public DiceGame2(DiceGameController controller) {
        super(10); // Min bet: 10
        this.controller = controller;
        this.bot = Main.getBot();
        this.player = Main.getPlayer();
    }

    
    @Override
    public void startGame() {
        startRoll();
    }

    
    public void setupGame(int mode) {
        // Initialize the dice arrays based on mode (1-4 dice)
        int diceCount = mode; // mode will determine how many dice to use (1 to 4)

        for (int i = 0; i < diceCount; i++) {
            playerDice3D[i] = new Dice3D();
            botDice3D[i] = new Dice3D();
        }

        // Dynamically set up the UI based on the number of dice
        for (int i = 0; i < 4; i++) {
            if (i < diceCount) {
                controller.getPlayerDiceContainer(i).getChildren().setAll(playerDice3D[i].createScene());
                controller.getBotDiceContainer(i).getChildren().setAll(botDice3D[i].createScene());

                // Add image overlays for each dice
                controller.getPlayerDiceContainer(i).getChildren().add(controller.getPlayerDiceImage(i));
                controller.getBotDiceContainer(i).getChildren().add(controller.getBotDiceImage(i));
            } else {
                // Hide the unused dice containers if the mode is 1, 2, or 3 dice
                controller.getPlayerDiceContainer(i).setVisible(false);
                controller.getBotDiceContainer(i).setVisible(false);
            }
        }

        hideDiceImages();
        updateUI();
    }

    
    public void startRoll() {
        int betAmount = validateBet(controller.getBetInput().getText());
        if (betAmount == -1) return;

        controller.getRollButton().setDisable(true); // Disable the button to prevent multiple rolls

        hideDiceImages(); // Hide the previous dice images

        // Roll the player dice
        for (int i = 0; i < playerDice3D.length; i++) {
            if (playerDice3D[i] != null) {
                playerDice3D[i].roll3DDice();
            }
        }

        // Roll the bot dice
        for (int i = 0; i < botDice3D.length; i++) {
            if (botDice3D[i] != null) {
                botDice3D[i].roll3DDice();
            }
        }

        playSound(); // Play sound after dice animation starts
        finalizeRoll(betAmount);
    }

    
    private void finalizeRoll(int betAmount) {
        // Simulate the roll of each dice for player and bot based on mode
        int[] playerDiceResults = new int[playerDice3D.length];
        int[] botDiceResults = new int[botDice3D.length];

        for (int i = 0; i < playerDice3D.length; i++) {
            if (playerDice3D[i] != null) {
                playerDiceResults[i] = (int) (Math.random() * 6) + 1;
            }
        }

        for (int i = 0; i < botDice3D.length; i++) {
            if (botDice3D[i] != null) {
                botDiceResults[i] = bot.rollDice();
            }
        }

        new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Update images based on the results of the dice roll
            for (int i = 0; i < playerDice3D.length; i++) {
                if (playerDice3D[i] != null) {
                    controller.getPlayerDiceImage(i).setImage(getDiceImage(playerDiceResults[i]));
                }
            }

            for (int i = 0; i < botDice3D.length; i++) {
                if (botDice3D[i] != null) {
                    controller.getBotDiceImage(i).setImage(getDiceImage(botDiceResults[i]));
                }
            }

            // Calculate the totals
            int playerTotal = 0;
            int botTotal = 0;
            
            for (int i = 0; i < playerDiceResults.length; i++) {
                playerTotal += playerDiceResults[i];
            }
            
            for (int i = 0; i < botDiceResults.length; i++) {
                botTotal += botDiceResults[i];
            }

            int multiplier = playerTotal - botTotal; // Calculate multiplier
            int result = betAmount * multiplier; // Final result (money)

            player.incrementGamesPlayed();

            String resultText;
            
            if (playerTotal > botTotal) {
            	
                player.addMoney(result);
                player.incrementGamesWon();
                bot.incrementGamesLost();
                resultText = "You Win! +" + result;
                
            } else if (playerTotal < botTotal) {
            	
                player.subtractMoney(Math.abs(result));
                player.incrementGamesLost();
                bot.incrementGamesWon();
                resultText = "Bot Wins! " + result;
                
            } else {
            	
                resultText = "It's a Tie!";
                
            }

            controller.updateResult(resultText); // Update result text
            controller.updateMultiplier("Multiplier: " + multiplier + "x"); // Update multiplier label
            updateUI(); // Update UI (money)

            // Show the images with a fade-in effect
            new Timeline(new KeyFrame(Duration.seconds(1),
                    new KeyValue(controller.getBotDiceImage(0).opacityProperty(), 1, Interpolator.EASE_IN),
                    new KeyValue(controller.getBotDiceImage(1).opacityProperty(), 1, Interpolator.EASE_IN),
                    new KeyValue(controller.getBotDiceImage(2).opacityProperty(), 1, Interpolator.EASE_IN),
                    new KeyValue(controller.getBotDiceImage(3).opacityProperty(), 1, Interpolator.EASE_IN),
                    new KeyValue(controller.getPlayerDiceImage(0).opacityProperty(), 1, Interpolator.EASE_IN),
                    new KeyValue(controller.getPlayerDiceImage(1).opacityProperty(), 1, Interpolator.EASE_IN),
                    new KeyValue(controller.getPlayerDiceImage(2).opacityProperty(), 1, Interpolator.EASE_IN),
                    new KeyValue(controller.getPlayerDiceImage(3).opacityProperty(), 1, Interpolator.EASE_IN)
            )).play();

            controller.getRollButton().setDisable(false); // Re-enable the roll button after animation
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
        controller.updateMoneyLabel(player.getMoney().toString() + "$");
    }

    
    private void hideDiceImages() {
        for (int i = 0; i < 4; i++) {
            controller.getBotDiceImage(i).setOpacity(0);
            controller.getPlayerDiceImage(i).setOpacity(0);
        }
    }

    
    private Image getDiceImage(int diceNumber) {
        return new Image(getClass().getResourceAsStream("/resources/img/dice" + diceNumber + ".png"));
    }

    
    private void playSound() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }

        String soundFile = getClass().getResource("/sounds/dice_sfx.mp3").toExternalForm();
        Media sound = new Media(soundFile);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}
