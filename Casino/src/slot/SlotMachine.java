package slot;
import java.io.File;
import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import slot.controller.SlotMachineController;

public class SlotMachine {

	public Double playRound(Double bet, SlotMachineController controller, ImageView imgSlot1, ImageView imgSlot2, ImageView imgSlot3,
			Label lblSlot1, Label lblSlot2, Label lblSlot3, Label lblWinLose,
			Double moneySpent, Boolean moneyIsZero, Label lblMWCurrency, ActionEvent event,  Label lblMLCurrency ,int endAnim1, int endAnim2,int endAnim3, Integer maxSlot) {

		System.out.println("Inizio playRound con moneySpent: " + moneySpent);

		if (moneyIsZero) {
			System.out.println("Soldi Finiti!");
			return bet = 0.0;
		}

		
		ArrayList<String> NRand = randomNumber();
		String N1 = NRand.get(0), N2 = NRand.get(1), N3 = NRand.get(2);

		// Associa immagini ai numeri casuali
		association(N1, N2, N3, imgSlot1, imgSlot2, imgSlot3,endAnim1,endAnim2,endAnim3);

		// Confronta i numeri
		numberComparison(N1, N2, N3, event, lblWinLose,endAnim3,maxSlot);

		// Calcola i soldi moltiplicati in base alla combinazione di numeri
		MoneyManagerSlot mms = new MoneyManagerSlot();
		Double moneyMultiplied = mms.moneyMultiplied(N1, N2, N3, moneySpent);

		System.out.println("Valore di moneyMultiplied dopo calcolo: " + moneyMultiplied);

		// Mostra il risultato nel label
		mms.moneyMultipliedDisplay(lblMWCurrency, moneyMultiplied,endAnim3);
		
		bet=mms.setMoneyLeft(moneyMultiplied, moneySpent, bet);
		mms.moneyLeftDisplay(lblMLCurrency, bet,endAnim3);
		
		return bet;
	}

	
	
	
	
	
	
	public void association(String N1, String N2, String N3, ImageView imgSlot1,ImageView imgSlot2,ImageView imgSlot3,int endAnim1, int endAnim2,int endAnim3){
		
		
		
		
		try {
			String path= "/src/resource/img/Icon" +N1+".jpg"; //prende il path dell'immagine associata al numero casuale
			Image image1 = new Image(getClass().getResourceAsStream(path));
			applySlotAnimation(imgSlot1 ,image1,endAnim1);
    	
		}catch(Exception e1) {
			}
		
		try {
			String path= "/src/resource/img/Icon" +N2+".jpg";
			Image image2 = new Image(getClass().getResourceAsStream(path));
		
			applySlotAnimation(imgSlot2 ,image2,endAnim2);
		}catch(Exception e1) {
			}
		
		try {
			String path= "/src/resource/img/Icon" +N3+".jpg";
			Image image3 = new Image(getClass().getResourceAsStream(path));
		
			applySlotAnimation(imgSlot3 ,image3,endAnim3);
		}catch(Exception e1) {
			}
		
    	
		
	}
	
	

	public ArrayList<String> randomNumber(){//metodo per creare un numero casuale da 1 a 6
		Integer randomN=0;
		ArrayList<String> numbers = new ArrayList<String>(); //numeri messi in un ArrayList di String
		
		for(int i=0; i< 3;i++) {
			
			randomN =(int) (Math.random()*6)+1;
			
		
			System.out.println(randomN);
			
			numbers.add(randomN.toString());
		
		}
		
		
		return numbers;
	}
	
	
	
	 public void victoryState(String state, ActionEvent e,Label lblWinLose,int endAnim ,Integer maxSlot) {//metodo che decide lo stato di vittoria
		 String input = "";
		 Timeline timeline = new Timeline();
		 
		 switch(state) {
		 
		 case "0" :
			 input = "Hai perso"; 
			 break;
			 
		 case "2" :
			 
			 input = "2/" + maxSlot ;
			 break;
		
		 case "3" :
			 if(maxSlot == 3)
			 input = "Hai vinto";
			 else
				 input= "3/" + maxSlot;
			 break;
		 case "4" :
			 input = "4/" + maxSlot ;
			 break;
		 case "5" :
			 if(maxSlot == 5)
				 input = "Hai vinto";
			 else 
			 input = "2/" + maxSlot ;
			 break;
		default: 
			input = "null";
		 
		 }
		
		 
		  String finalInput = input;
		 
		 timeline.getKeyFrames().add(new KeyFrame(Duration.millis(endAnim), event -> {
		     lblWinLose.setText(finalInput);  // Imposta il testo direttamente, non c'è più bisogno di Platform.runLater()
		 }));

		 timeline.setCycleCount(1);
		 timeline.play();

		 System.out.println(input);
	    	
	    }
		
	 
		
	 private void applySlotAnimation(ImageView imgSlot,Image finalImage,int endAnim) {
		    Timeline slotanimTimeline = new Timeline();
		    

		    // Aggiunge KeyFrames ogni 50ms fino a 700ms
		    for (int i = 0; i <= endAnim; i += 50) {
		        slotanimTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(i), event -> {
		            Image randomImage = getRandomImage(); // Metodo per generare immagini casuali
		            updateAnimationSlot(randomImage, imgSlot);
		        }));
		    }

		    // Aggiunge il KeyFrame finale a 700ms per impostare l'immagine definitiva
		    slotanimTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(endAnim), event -> {
		        updateAnimationSlot(finalImage, imgSlot);
		    }));

		    // L'animazione si esegue solo una volta
		    slotanimTimeline.setCycleCount(1);
		    slotanimTimeline.play();
		}

		
	 
	 
	 
	 
	 
	 
	 // Metodo per aggiornare le immagini degli slot
		private void updateAnimationSlot(Image image, ImageView imgSlot) {
		    imgSlot.setImage(image);
		    
		}

		// Metodo per ottenere un'immagine casuale (puoi personalizzarlo)
		private Image getRandomImage() {
		    String[] images = { "/src/resource/img/Icon1.jpg", "/src/resource/img/Icon2.jpg", "/src/resource/img/Icon3.jpg", "/src/resource/img/Icon4.jpg", "/src/resource/img/Icon5.jpg", "/src/resource/img/Icon6.jpg" }; // Percorsi delle immagini
		    int index = new Random().nextInt(images.length);
		    return new Image(images[index]);
		}
	 
	 
	
		
		
		
		public void numberComparison(String N1, String N2, String N3, ActionEvent event, Label lblWinLose,int endAnim, Integer maxSlot) {
			String state= null;
			
			try {
				//comparazione tra i numeri casuali 
					
					
					if(N2.equals(N3) && N2.equals(N1)) {
						state="3";
						System.out.println("Vittoria");
						 victoryState(state, event,lblWinLose,endAnim, maxSlot); //se i numeri sono uguali allora vittoria	
					}else if(N1.equals(N2) || N2.equals(N3)|| N3.equals(N1) ) {
						state="2";
						System.out.println("2/"+ maxSlot);
						 victoryState(state, event,lblWinLose,endAnim, maxSlot);
						
						
					}else {
						state= "0";
						System.out.println("perdita");
						victoryState(state,event,lblWinLose,endAnim, maxSlot); //se i numeri non sono uguali allora perdita
					}
					
				
				}catch(NullPointerException e ){ //try catch in caso esca un null nella comparazione
					
				}
			
		} 
		

		public void changeSlot3x1() {
			
			
			
		}
		
		
		public void changeSlot3x3() {
			
			
			
		}



		public void changeSlot5x3() {
	
	
	
		}
		
		
		

	 
	 
	}


	
	

