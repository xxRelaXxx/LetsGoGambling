package guessNumber;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController {
    private Stage primaryStage;
    
    @FXML
    private GridPane numeriContainer;

    @FXML
    private TextField InputBet;

    @FXML
    private Button btnPlay;

    @FXML
    private Text lblBet;

    @FXML
    private Label lblMoney;

    @FXML
    private Label lblMultiplier;

    private int min;
    private int max;
    private GuessNumber g=new GuessNumber();
    private String intervallo;
    private int numerosegreto;

    public GameController() {

    }
    public void setIntervallo(String intervallo) {
        this.intervallo = intervallo;
    }
    @FXML
    void start(ActionEvent event) {
    	 

    	 String[] parts = intervallo.split("-");
         min = Integer.parseInt(parts[0]);
         max = Integer.parseInt(parts[1]);
         g = new GuessNumber(max, min);

    	System.out.println("valore in input " + InputBet.getText());
        g.setBet(Integer.parseInt(InputBet.getText()));
        System.out.println(" bet settata a " + g.getBet());
        int newbudget= g.player1.getMoney()-g.getBet();
        System.out.println(" il new budget e' " + newbudget);
        g.player1.setMoney(newbudget);
        System.out.println(" money del player settati a " + g.player1.getMoney());

        lblBet.setVisible(false);
        btnPlay.setVisible(false);
        InputBet.setVisible(false);

       

        numerosegreto = g.generateRandomNumber(max);
       System.out.println(g.getSecretNumber()); 

        aggiungiNumeri(min, max);
        showMoney();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    

    private void aggiungiNumeri(int min, int max) {
        numeriContainer.getChildren().clear();
        numeriContainer.getColumnConstraints().clear();
        numeriContainer.getRowConstraints().clear();

        int colonne = 5;
        int righe = (int) Math.ceil((max - min + 1) / (double) colonne);

        for (int i = 0; i < colonne; i++) {
            numeriContainer.getColumnConstraints().add(new javafx.scene.layout.ColumnConstraints());
        }
        for (int i = 0; i < righe; i++) {
            numeriContainer.getRowConstraints().add(new javafx.scene.layout.RowConstraints());
        }

        for (int i = min; i <= max; i++) {
            Button numeroButton = new Button(String.valueOf(i));
            numeroButton.setPrefSize(80, 80);
            numeroButton.setStyle("-fx-font-size: 18px; -fx-background-color: lightblue; -fx-border-color: black;");
            numeroButton.setOnAction(event -> gestisciTentativo(numeroButton));

            int col = (i - min) % colonne;
            int row = (i - min) / colonne;
            numeriContainer.add(numeroButton, col, row);
        }
    }

    private void gestisciTentativo(Button numeroButton) {
        int numeroScelto = Integer.parseInt(numeroButton.getText());

        // Porta il bottone in primo piano
        numeroButton.toFront();

        // Ingrandimento del bottone
        ScaleTransition enlarge = new ScaleTransition(Duration.millis(500), numeroButton);
        enlarge.setToX(1.5); // Ingrandisce il bottone
        enlarge.setToY(1.5);

        // Rotazione 3D del bottone
        RotateTransition rotate = new RotateTransition(Duration.millis(500), numeroButton);
        rotate.setAxis(Rotate.Y_AXIS); // Ruota attorno all'asse Y
        rotate.setFromAngle(0); // Partenza da 0 gradi
        rotate.setToAngle(90); // Ruota fino a 90 gradi

        // Riduzione del bottone alla dimensione originale
        ScaleTransition shrink = new ScaleTransition(Duration.millis(500), numeroButton);
        shrink.setToX(1); // Torna alla dimensione originale
        shrink.setToY(1);

        if (g.verifyNumber(numeroScelto)) {
            enlarge.setOnFinished(e -> {
                rotate.setOnFinished(ev -> {
                    numeroButton.setText("✅"); // Mostra "V" di successo
                    numeroButton.setStyle("-fx-font-size: 18px; -fx-background-color: lightgreen; -fx-border-color: black;");
                    
                    // Torna alla dimensione originale e ruota indietro
                    RotateTransition rotateBack = new RotateTransition(Duration.millis(500), numeroButton);
                    rotateBack.setAxis(Rotate.Y_AXIS);
                    rotateBack.setFromAngle(90);
                    rotateBack.setToAngle(0);
                    
                    rotateBack.setOnFinished(ev2 -> {
                        shrink.play(); // Torna alla dimensione originale
                        g.calculateFinalBudget();
                        stopGame();
                    });
                    
                    rotateBack.play(); // Avvia la rotazione di ritorno
                });
                rotate.play(); // Avvia la rotazione iniziale
            });
            enlarge.play(); // Avvia l'ingrandimento

          
        } else {
            enlarge.setOnFinished(e -> {
                rotate.setOnFinished(ev -> {
                    numeroButton.setText("❌"); // Mostra "X" di errore
                    numeroButton.setStyle("-fx-font-size: 18px; -fx-background-color: lightcoral; -fx-border-color: black;");
                    
                    // Torna alla dimensione originale e ruota indietro
                    RotateTransition rotateBack = new RotateTransition(Duration.millis(500), numeroButton);
                    rotateBack.setAxis(Rotate.Y_AXIS);
                    rotateBack.setFromAngle(90);
                    rotateBack.setToAngle(0);
                    
                    rotateBack.setOnFinished(ev2 -> {
                        shrink.play(); // Torna alla dimensione originale
                        numeroButton.setDisable(true); // Disabilita il bottone in caso di errore
                    });
                    
                    rotateBack.play(); // Avvia la rotazione di ritorno
                });
                rotate.play(); // Avvia la rotazione iniziale
            });
            enlarge.play(); // Avvia l'ingrandimento
        }

        showMultiplier();
        System.out.println(g.getMultiplier());
    }



    public void showMultiplier() {
        double updated = g.updateMultiplier();
        lblMultiplier.setText(String.valueOf(g.getMultiplier()));
    }

    public void showMoney() {
        System.out.println(" money del player settati a " + g.player1.getMoney());

        lblMoney.setText(String.valueOf(g.player1.getMoney())); 
    }

    public void showResult() {
        numeriContainer.getChildren().clear();
        numeriContainer.getColumnConstraints().clear();
        numeriContainer.getRowConstraints().clear();
        showMoney();
    }
    private void stopGame() {
        numeriContainer.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setDisable(true);
            }
           
        });
        
        System.out.println("Gioco terminato! Budget finale: " + g.player1.getMoney());
        repeat();
    }
	private void repeat() {
		System.out.println(" sono dentro repeat ");
	    lblBet.setVisible(true);
	    btnPlay.setVisible(true);
	    InputBet.setVisible(true);
	    
	    // Pulisci il container
	    numeriContainer.getChildren().clear();
	    numeriContainer.getColumnConstraints().clear();
	    numeriContainer.getRowConstraints().clear();
	    
	    // Genera un nuovo numero segreto
	    numerosegreto = g.generateRandomNumber(max);
	    System.out.println(g.getSecretNumber());
	}
}
