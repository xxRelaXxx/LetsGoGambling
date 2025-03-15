package slot;
import java.net.URL;
import java.util.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MoneyManagerSlot  {

	Double currentMoney;

	
	  

	  
	    	
	public Double moneySelection(Double maxMoney, Spinner<Double> spinner, Label lblMoneySelect) {
	  try {  // Imposta lo Spinner da 0 fino al massimo, con incrementi di 0.5
	    SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, maxMoney, 0.0, 0.50);
	    spinner.setValueFactory(valueFactory);

	    // Arrotonda ai centesimi e aggiorna il label
	    currentMoney = Math.round(spinner.getValue() * 100.0) / 100.0;
	    lblMoneySelect.setText(String.format("%.2f euro", currentMoney));

	    // Ascolta i cambiamenti del valore nello spinner
	    spinner.valueProperty().addListener(new ChangeListener<Double>() {
	        @Override
	        public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
	            currentMoney = Math.round(newValue * 100.0) / 100.0;
	            lblMoneySelect.setText(String.format("%.2f euro", currentMoney));
	            System.out.println("Valore aggiornato nello Spinner: " + currentMoney);
	        }
	    });
	    
	    // Modifica l'editor dello spinner per visualizzare un incremento visivo di 0.50
	    spinner.getEditor().setText(String.format("%.2f", spinner.getValue()));
	    spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue.matches("^\\d*\\.\\d{0,2}$")) {
	            spinner.getEditor().setText(String.format("%.2f", Double.parseDouble(newValue)));
	        }
	    });

	    
	  }catch(Exception e){
		  
		 System.out.println("Metti un numero!");
		 lblMoneySelect.setText("Metti un numero!");
		  
	  }
	  
	  return currentMoney;
	}



	public double moneyMultiplied(String N1, String N2, String N3, Double moneyUsed) {
	    if (moneyUsed <= 0) {
	        System.out.println("Errore: moneyUsed e' 0 o inferiore!");
	        return 0.0;
	    }

	    Double moneyMultiplied = 0.0;
	    Double multiplier2x = Math.round((moneyUsed * 1.5) * 100.0) / 100.0;
	    Double multiplier3x = Math.round((moneyUsed * 3) * 100.0) / 100.0;

	    if (N1.equals(N2) && N1.equals(N3)) {
	        moneyMultiplied = multiplier3x;
	    } else if (N1.equals(N2) || N2.equals(N3) || N1.equals(N3)) {
	        moneyMultiplied = multiplier2x;
	    }

	    System.out.println("Risultato finale di moneyMultiplied: " + moneyMultiplied);
	    return moneyMultiplied;
	}
	
	
	
	
	public void moneyMultipliedDisplay(Label lblMWCurrency, Double moneyMultiplied,int endAnim) {
		// Aggiunge il KeyFrame finale a 700ms per impostare l'immagine definitiva
		Timeline timeline= new Timeline();
	    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(endAnim), event -> {
	    	
	    	if (moneyMultiplied > 0) {
		        lblMWCurrency.setText(moneyMultiplied + " euro");
		    } else {
		        lblMWCurrency.setText("Niente");
		    }
	    	
	    }));

	    // L'animazione si esegue solo una volta
	    timeline.setCycleCount(1);
	    timeline.play();
		
		
	}


	
	public Double setMoneyLeft(Double moneyMultiplied, Double moneyUsed, Double maxValue) {
		
		if(moneyMultiplied>0) {
			
			maxValue =(maxValue-moneyUsed)+moneyMultiplied;
			return maxValue;
			
		}else {
			
			maxValue=maxValue-moneyUsed;
			return maxValue;
		}
		
	}
	
	public void moneyLeftDisplay(Label lblMLCurrency,Double moneyLeft,int endAnim) {
		Timeline timeline= new Timeline();
	    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(endAnim), event -> {
	    	if(moneyLeft > 0) {
	    		lblMLCurrency.setText(moneyLeft.toString() + " euro");
	    		}else {
	    			lblMLCurrency.setText("Soldi Finiti");
	    		}
	    }));

	    // L'animazione si esegue solo una volta
	   	timeline.setCycleCount(1);
	    timeline.play();
		
		
	}
	
	
	
}
