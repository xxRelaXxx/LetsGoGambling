package slot.controller;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import slot.MoneyManagerSlot;
import slot.SlotMachine;


public class SlotMachineController{
	
	MoneyManagerSlot moneyManagerSlot = new MoneyManagerSlot();
	SlotMachine slot = new SlotMachine();
	 private Double moneySpent = 0.0;
	 private Integer restartCount=0;
	 Double money=500.00;
	 Boolean moneyIsZero= false;
	 int endAnim1=700, endAnim2=endAnim1+700,endAnim3=endAnim2+700,maxslot = 3;
	 Popup popup = new Popup(); 
     Label label = new Label("Istruzioni");
     AnchorPane instructionPane = new AnchorPane();
	
	 
	 @FXML
	 private void startGame(ActionEvent e) {
	     // Assicurati che moneySpent sia maggiore di 0 prima di avviare il gioco
	     if (moneySpent > 0) {
	    	 btnBegin.setDisable(false);
	         this.money=slot.playRound(money, this, imgSlot1, imgSlot2, imgSlot3, lblSlot1, lblSlot2, lblSlot3, lblWinLose, moneySpent,moneyIsZero, lblMWCurrency, e,lblMLCurrency,endAnim1,endAnim2,endAnim3,maxslot);
	         btnBegin.setDisable(true);  // Disabilita il pulsante per evitare ulteriori clic
	     } else {
	         // Messaggio di errore se moneySpent è 0
	    	 
	         System.out.println("Errore: Non puoi giocare con 0 soldi");
	         
	         
	         
	     }
	 }
	 
	 @FXML
	 private void instruction() throws IOException{

		 
		 Parent root = FXMLLoader.load(getClass().getResource("/slot/fxml/Instruction.fxml"));
		 Stage stage = new Stage ();
	       
	       label.setStyle(" -fx-background-color: white;"); 
	       popup.getContent().add(label); 
	       label.setMinWidth(80); 
	        label.setMinHeight(50); 
	        
	        EventHandler<ActionEvent> event =  
	                new EventHandler<ActionEvent>() { 
	           
	                    public void handle(ActionEvent e) 
	                    { 
	                        if (!popup.isShowing()) 
	                            popup.show(stage); 
	                        else
	                            popup.hide(); 
	                    } 
	                };
	        
	        
	        
	         Scene sceneInstruction = new Scene(root);
	         stage.setScene(sceneInstruction);       
	         stage.show();
	           
			
		}
	 
	 

	
   
    @FXML
    private void restartGame(ActionEvent e) {
    	
    	restartCount++;
        initialize(); // Riavvia l'inizializzazione della partita
        lblCounterGames.setText("N. Partita: " + restartCount);
        btnBegin.setDisable(false);
        
    }
    
    @FXML
    private void changeSlots(ActionEvent e) {
    	
    	
    	
    }
    
    
    @FXML
    private void slotsListSetup() {
    	
    	ArrayList<String> buttonList =new ArrayList<String>();
    	buttonList.add("3X1");
    	buttonList.add("3X3");
    	buttonList.add("5X3");
    	
    	
    	cbofButtons.getItems().addAll(buttonList);
    	String slotChoice=cbofButtons.getValue();
    	
    	switch(slotChoice) {
    	
    		case "3X1":
    			
    			slot.changeSlot3x1();
    			
    			break;
    			
    		case "3X3":
    			
    			slot.changeSlot3x3();
    			
    			
    			break;
    			
    		case "5X3":
	
    			slot.changeSlot5x3();
    			
    			break;
    	
    	}
    		
    	
    	
    }
    
    
    
    
	 
	 private void changeMoneySpentValue() {
		    // Imposta il valore dello spinner e aggiorna moneySpent
		    moneySpent = moneyManagerSlot.moneySelection(money, spnrMoneySpent, lblMIUCurrency);
		    System.out.println("Valore di moneySpent : " + moneySpent);
		}
   


	
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Button backButton;
    
    @FXML
    private Button btnBegin;

    @FXML
    private Button btnInstruction;
    
    @FXML
    private Button btnRestart;
    
    @FXML
    private Button btnChangeSlots;

    @FXML
    private ImageView imgSlot1;

    @FXML
    private ImageView imgSlot2;

    @FXML
    private ImageView imgSlot3;
    
    @FXML
    private ImageView botAvatar;
    
    @FXML
    private Label moneyLabel;
    
    @FXML
    private Label botDialogueLabel;
    
    @FXML
    private Label lblHowMuchSpend;

    @FXML
    private Label lblMIUCurrency;

    @FXML
    private Label lblMLCurrency;

    @FXML
    private Label lblMWCurrency;
    
    @FXML
    private Label lblMoneyInUse;

    @FXML
    private Label lblMoneyLeft;

    @FXML
    private Label lblMoneyWon;

    @FXML
    private Label lblSlot1;

    @FXML
    private Label lblSlot2;

    @FXML
    private Label lblSlot3;

    @FXML
    private Label lblSlotMachine;

    @FXML
    private Label lblWinLose;
    
    @FXML
    private Label lblCounterGames;

    @FXML
    private Spinner<Double> spnrMoneySpent;
    
    @FXML
    private ListView<Button> lvmodeSelection;
    
    @FXML
    private ChoiceBox<String> cbofButtons;
    
    
 
    
    @FXML
    void initialize() {
        // Imposta il valore iniziale dello spinner
        changeMoneySpentValue();// Imposta il valore iniziale dei soldi spesi
        
        
        //slotsListSetup();
       
        
        // Ascolta il cambiamento del valore dello spinner e aggiorna il valore di moneySpent
        lblMLCurrency.setText(money.toString()+"0" + " euro");;
        spnrMoneySpent.valueProperty().addListener((observable, oldValue, newValue) -> {
            moneySpent = newValue;  // Aggiorna moneySpent ogni volta che lo spinner cambia
            System.out.println("Valore aggiornato di moneySpent: " + moneySpent);
        });
    

    	
    	
      

    }



}
