package roulette.controllers;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
public class RicaricaController {
   @FXML
   private Group sezioniGroup; // Group che contiene le sezioni e i numeri
   @FXML
   private Circle proiettile;
   @FXML
   private Button spinCounterBtn;
   private Integer spinCounter = 0;
   @FXML
   private Label limiteLbl;
   @FXML
   private Button ricaricaBtn;
   private double angoloInizialeProiettile = 0; // Angolo iniziale del proiettile
   // Angoli di ogni slot (6 slot, ogni slot � a 60 gradi di distanza)
   private final double[] angoliSlot = {
       0.0,   // Slot 1 (Proiettile)
       60.0,  // Slot 2
       120.0, // Slot 3
       180.0, // Slot 4
       240.0, // Slot 5
       300.0  // Slot 6
   };
   private int posizioneCorrente = 0; // Posizione corrente dello slot (0 = Slot 1)
   @FXML
   private void initialize() {
       // Memorizza l'angolo iniziale del proiettile rispetto alla ruota
       angoloInizialeProiettile = proiettile.getRotate();
   }
   @FXML
   private void increaseCounter(ActionEvent event) {
       if (spinCounter < 9) {
           ricaricaBtn.setDisable(false);
           spinCounter++;
           spinCounterBtn.setText("x" + spinCounter.toString());
       } else {
           limiteLbl.setVisible(true);
       }
   }
   @FXML
   private void ricarica() {
       // Calcola la posizione finale in base allo spinCounter
       int posizioneFinale = (posizioneCorrente + spinCounter) % 6;
       // Calcola l'angolo di rotazione in base alla posizione finale
       double angoloRotazione = calcolaAngoloRotazione(posizioneFinale);
       // Animazione di ROTAZIONE della roulette
       RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), sezioniGroup); // Durata ridotta a 1 secondo
       rotateTransition.setByAngle(360 * 2 + angoloRotazione); // Ruota 2 volte + l'angolo desiderato
       rotateTransition.setCycleCount(1);
       rotateTransition.setAutoReverse(false);
       // Movimento CIRCOLARE in alto a destra
       TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(1), sezioniGroup); // Durata ridotta a 1 secondo
       moveTransition.setByX(199.5); // Sposta a destra
       moveTransition.setByY(-119); // Sposta in alto
       moveTransition.setCycleCount(1);
       // ParallelTransition per eseguire entrambe le animazioni contemporaneamente
       ParallelTransition parallelTransition = new ParallelTransition(rotateTransition, moveTransition);
       parallelTransition.setOnFinished(event -> {
           // Aggiorna la posizione corrente
           posizioneCorrente = posizioneFinale;
           // Calcola l'angolo finale della rotazione
           double angoloFinale = sezioniGroup.getRotate() % 360; // Normalizza l'angolo tra 0 e 360
           // Determina se il proiettile � in canna (solo se la posizione finale � 3, cio� Slot 4)
           boolean proiettileInCanna = (posizioneFinale == 0);
           // Stampa il risultato nella console
           System.out.println("Spin Counter: " + spinCounter);
           System.out.println("Posizione finale: " + posizioneFinale);
           System.out.println("Angolo finale: " + angoloFinale + " gradi");
           System.out.println("Proiettile in canna: " + (proiettileInCanna ? "S�" : "No"));
          
           // Chiudi la finestra (opzionale)
           // Stage stage = (Stage) sezioniGroup.getScene().getWindow();
           // stage.close();
       });
       parallelTransition.play(); // Avvia la transizione parallela
   }
   private double calcolaAngoloRotazione(int posizioneFinale) {
       // Angolo dello slot corrispondente alla posizione finale
       double angoloSlot = angoliSlot[posizioneFinale];
       // Calcola l'angolo di rotazione necessario per raggiungere lo slot desiderato
       return angoloSlot - angoloInizialeProiettile;
   }
}


