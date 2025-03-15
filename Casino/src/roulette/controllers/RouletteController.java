package roulette.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import main.Main;
import roulette.BetOption;
import roulette.BetOptionCellFactory;
import roulette.InputBet;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RouletteController {
	private ArrayList<Integer> firstRow = new ArrayList<>(Arrays.asList(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34));
	private ArrayList<Integer> secondRow = new ArrayList<>(Arrays.asList(2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35));
	private ArrayList<Integer> thirdRow = new ArrayList<>(Arrays.asList(3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36));
	private ArrayList<Integer> redNumbers = new ArrayList<>(
			Arrays.asList(1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36));
	private ArrayList<Integer> blackNumbers = new ArrayList<>(
			Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35));
	private ArrayList<Integer> currentRow = new ArrayList<>();
	private ArrayList<Integer> currentTwelve = new ArrayList<>();
	private ArrayList<Integer> currentEighteen = new ArrayList<>();
	private ArrayList<Integer> selectedNums = new ArrayList<>();
	private ArrayList<InputBet> inputBets = new ArrayList<>();
	private Integer num = null;
	private int selectedPrice;
	private Boolean rowGotRight = false;
	private Boolean allInIsClicked = false;
	private Boolean allInIsPositioned = false;
	private Integer allInQuantity;
	private Image selectedImage; // Variabile per salvare l'immagine selezionata

//	public Integer money = 750;
	private Integer inputBetAmount = 0;
//	private Boolean inputBetSelected = false;
	private Boolean inputBetIsPositioned = true;
	private Boolean isGameWon = false;

	private Timeline allInTimeline;
	private boolean isAnimationRunning = false; // Variabile per controllare lo stato dell'animazione
	private Stage stage;
	private Scene scene;

	/*ImageView image1 = new ImageView(new Image("/img/singleChip.png"));
	ImageView image2 = new ImageView(new Image("/img/greenChip25.png"));
	ImageView image3 = new ImageView(new Image("/img/blueChip.png"));
	ImageView image4 = new ImageView(new Image("/img/blackChip.png"));
	ImageView image5 = new ImageView(new Image("/img/whiteChip.png"));
	ImageView image6 = new ImageView(new Image("/img/InputChip.png"));
	ImageView image7 = new ImageView(new Image("/img/allIn.png"));
	Image allIn = new Image("/img/allIn.png");
	Image inputChip = new Image("/img/InputChip.png");*/
	
	
	
	ImageView image1 = new ImageView(new Image(getClass().getResource("/resources/img/singleChip.png").toExternalForm()));
	ImageView image2 = new ImageView(new Image(getClass().getResource("/resources/img/greenChip25.png").toExternalForm()));
	ImageView image3 = new ImageView(new Image(getClass().getResource("/resources/img/blueChip.png").toExternalForm()));
	ImageView image4 = new ImageView(new Image(getClass().getResource("/resources/img/blackChip.png").toExternalForm()));
	ImageView image5 = new ImageView(new Image(getClass().getResource("/resources/img/whiteChip.png").toExternalForm()));
	ImageView image6 = new ImageView(new Image(getClass().getResource("/resources/img/InputChip.png").toExternalForm()));
	ImageView image7 = new ImageView(new Image(getClass().getResource("/resources/img/allIn.png").toExternalForm()));
	Image allIn = new Image(getClass().getResource("/resources/img/allIn.png").toExternalForm());
	Image inputChip = new Image(getClass().getResource("/resources/img/InputChip.png").toExternalForm());
	
	
	
	
    @FXML
    private Button btGoBack;
	
	@FXML
	private Group sezioniGroup; // Group che contiene le sezioni e i numeri

	@FXML
	private GridPane gridPaneTable;

	@FXML
	private StackPane roulettePane;

	@FXML
	private ComboBox<BetOption> betComboBox;

	@FXML
	private Label lblMoney;

	@FXML
	private Label lblNumeroUscito;

	@FXML
	private Label lblPremi1;

	@FXML
	private HBox ruotaBox;

	@FXML
	private VBox inputBox;

	@FXML
	private VBox tableBox;

	@FXML
	private Label lblNU;

	@FXML
	private Button allInButton;

	@FXML
	private Button inviaButton;

	@FXML
	private TextField inputBet;

	@FXML
	private Group pallinaGroup;

	@FXML
	private Circle pallina;
	

    @FXML
    void goBack(ActionEvent event) {
    	//go back to mode menu
    	BorderPane root;
    	try {
			root = FXMLLoader.load(getClass().getResource("/menu/fxml/gameChoiceMenu.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
	    	scene = new Scene(root);
	    	stage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	

	public void initialize() {
	    refreshMoney();

	    // Imposta il testo predefinito
	    betComboBox.setPromptText("Scegli la puntata");

	    // Configura la cell factory personalizzata
	    BetOptionCellFactory cellFactory = new BetOptionCellFactory();
	    betComboBox.setCellFactory(cellFactory);

	    // Imposta la ButtonCell personalizzata
	    betComboBox.setButtonCell(cellFactory.call(null));

	    // Ridimensiona le immagini
	    image1.setFitWidth(20);
	    image1.setFitHeight(20);
	    image2.setFitWidth(20);
	    image2.setFitHeight(20);
	    image3.setFitWidth(20);
	    image3.setFitHeight(20);
	    image4.setFitWidth(20);
	    image4.setFitHeight(20);
	    image5.setFitWidth(20);
	    image5.setFitHeight(20);
	    image6.setFitWidth(20);
	    image6.setFitHeight(20);
	    image7.setFitWidth(25);
	    image7.setFitHeight(25);
	    allInButton.setGraphic(image7);
	    allInButton.getStyleClass().add(".ui");
	    allInButton.setOnMouseEntered(event -> applyGlitchEffect());
	    allInButton.setOnMouseExited(event -> resetGlitchEffect());
	    allInButton.setStyle("-fx-text-fill: #b98f10;");

	    // Listener per la selezione
	    betComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            selectedPrice = (int) newValue.getPrice();
	            selectedImage = newValue.getImage().getImage(); // Salva l'immagine selezionata

	            // Aggiorna la ButtonCell per visualizzare l'immagine selezionata
	            betComboBox.setButtonCell(cellFactory.call(null));
	            betComboBox.setValue(newValue); // Imposta il valore selezionato
	        }
	    });

	    betComboBox.getItems().addAll(new BetOption(image1, 10), new BetOption(image2, 25), new BetOption(image3, 50),
	            new BetOption(image4, 100), new BetOption(image5, 200));

	    // Aggiungi un listener per attendere che ruotaBox sia aggiunto alla scena
	    ruotaBox.sceneProperty().addListener((observable, oldScene, newScene) -> {
	        if (newScene != null) {
	            newScene.setOnKeyPressed(event -> {
	                if (event.getCode() == KeyCode.DIGIT1 && !isAnimationRunning) { // Verifica se è stato premuto Enter
	                    giraLaRuota(null); // Chiama il metodo giraLaRuota
	                    lblPremi1.setVisible(false);
	                }
	            });
	        }
	    });
	    
	}
	
	@FXML
	public void allInButton(ActionEvent event) {
		allInIsClicked = true;
		allInQuantity = Main.player.getMoney();
		allInButton.setVisible(false);
		betComboBox.setVisible(false);
		inputBox.setVisible(false);
	}

	@FXML
	public void inviaButton(ActionEvent event) {
		try {
			inputBetAmount = Integer.parseInt(inputBet.getText());
			if (inputBetAmount > Main.player.getMoney() || inputBetAmount <= 0) {
				inputBetAmount = 0;
				inputBet.setText("Input non valido");
				return;
			}
			inputBetIsPositioned = false;
			allInButton.setVisible(false);
			betComboBox.setVisible(false);
			inputBox.setVisible(false);

		} catch (Exception e) {
			inputBet.setText("Inserisci un numero");
			inputBetAmount = 0;
		}
	}

	@FXML
	private void applyGlitchEffect() {
		allInButton.setGraphic(null);
		allInTimeline = new Timeline(new KeyFrame(Duration.millis(0), event -> updateButtonText("$$$$$")),
				new KeyFrame(Duration.millis(50), event -> updateButtonText(".#]")),
				new KeyFrame(Duration.millis(100), event -> updateButtonText("^{")),
				new KeyFrame(Duration.millis(150), event -> updateButtonText(Main.player.getMoney().toString())),
				new KeyFrame(Duration.millis(200), event -> updateButtonText("#$_")),
				new KeyFrame(Duration.millis(250), event -> updateButtonText("№:0")),
				new KeyFrame(Duration.millis(300), event -> updateButtonText("#{+.")),
				new KeyFrame(Duration.millis(350), event -> updateButtonText("@}-?")),
				new KeyFrame(Duration.millis(400), event -> updateButtonText("?{4@%")),
				new KeyFrame(Duration.millis(450), event -> updateButtonText("=.,^!")),
				new KeyFrame(Duration.millis(500), event -> updateButtonText(Main.player.getMoney().toString())),
				new KeyFrame(Duration.millis(550), event -> updateButtonText("/;1}]")),
				new KeyFrame(Duration.millis(600), event -> updateButtonText("?{%:%")),
				new KeyFrame(Duration.millis(650), event -> updateButtonText("$$$$$$")),
				new KeyFrame(Duration.millis(700), event -> updateButtonText("#§?!$")));
		allInTimeline.setCycleCount(Timeline.INDEFINITE);
		allInTimeline.play();
	}

	private void updateButtonText(String text) {
		allInButton.setText(text);
		allInButton.setStyle("-fx-text-fill: #b98f10;");
	}

	private void resetGlitchEffect() {
		if (allInTimeline != null) {
			allInTimeline.stop();
		}
		allInButton.setText("All IN");
		allInButton.setGraphic(image7);
	}

	@FXML
	void giraLaRuota(ActionEvent e) {
		
	    if (isAnimationRunning) {
	        return; // Ignora la chiamata se l'animazione è già in corso
	    }

	    isAnimationRunning = true; // Imposta il flag prima di avviare l'animazione

	    lblNU.setVisible(false);
	    lblNumeroUscito.setVisible(false);

	    // Ruota parte subito
	    RotateTransition rotateTransition = new RotateTransition(Duration.seconds(4.5), sezioniGroup);
	    rotateTransition.setByAngle((int) (Math.random() * 360) + (100 * 5)); // Ruota 5 volte
	    rotateTransition.setCycleCount(1);
	    rotateTransition.setAutoReverse(false);
	    rotateTransition.play(); // La ruota inizia immediatamente

	    // Animazione di ingrandimento della pallina
	    ScaleTransition scaleUp = new ScaleTransition(Duration.millis(250), pallina);
	    scaleUp.setToX(1.4);
	    scaleUp.setToY(1.4);

	    ScaleTransition scaleDown = new ScaleTransition(Duration.millis(250), pallina);
	    scaleDown.setToX(1.0);
	    scaleDown.setToY(1.0);

	    ScaleTransition scaleUp2 = new ScaleTransition(Duration.millis(250), pallina);
	    scaleUp2.setToX(1.4);
	    scaleUp2.setToY(1.4);

	    ScaleTransition scaleDown2 = new ScaleTransition(Duration.millis(250), pallina);
	    scaleDown2.setToX(1.0);
	    scaleDown2.setToY(1.0);

	    ScaleTransition scaleUp3 = new ScaleTransition(Duration.millis(250), pallina);
	    scaleUp3.setToX(1.4);
	    scaleUp3.setToY(1.4);

	    ScaleTransition scaleDown3 = new ScaleTransition(Duration.millis(250), pallina);
	    scaleDown3.setToX(1.0);
	    scaleDown3.setToY(1.0);

	    // Dopo la scala, la pallina inizia a girare
	    RotateTransition rotateTransition2 = new RotateTransition(Duration.seconds(2.5), pallinaGroup);
	    rotateTransition2.setByAngle(-((int) (Math.random() * 360) + (90 * 5))); // Ruota 5 volte in senso opposto
	    rotateTransition2.setCycleCount(1);
	    rotateTransition2.setAutoReverse(false);

	    ruotaBox.setVisible(true);

	    // Prima fa la scala, poi parte la rotazione della pallina
	    SequentialTransition sequence = new SequentialTransition(scaleUp, scaleDown, scaleUp2, scaleDown2, scaleUp3,
	            scaleDown3, rotateTransition2);
	    sequence.play();

	    // Nasconde la ruotaBox poco prima della fine dell'animazione
	    Timeline hideBoxTimeline = new Timeline(
	        new KeyFrame(Duration.seconds(3.8), event -> { // Nasconde la box dopo 3.8 secondi
	            ruotaBox.setVisible(false);
	        })
	    );
	    hideBoxTimeline.play();

	    // Quando tutto è finito, mostra il numero uscito
	    sequence.setOnFinished(event -> {
	    	Main.player.incrementGamesPlayed();
	        isAnimationRunning = false; // Resetta il flag dopo la fine dell'animazione

	        Integer nUscito = (int) (Math.random() * 38);
	        lblNU.setVisible(true);

	        if (nUscito == 37) {
	            lblNumeroUscito.setText("00");
	            lblNumeroUscito.setStyle("-fx-text-fill: green;");
	        } else {
	            if (nUscito == 0) {
	                lblNumeroUscito.setText(nUscito.toString());
	                lblNumeroUscito.setStyle("-fx-text-fill: green;");
	            }
	            if (redNumbers.contains(nUscito)) {
	                lblNumeroUscito.setText(nUscito.toString());
	                lblNumeroUscito.setStyle("-fx-text-fill: red;");
	            } else {
	                lblNumeroUscito.setText(nUscito.toString());
	                lblNumeroUscito.setStyle("-fx-text-fill: darkgrey;");
	            }
	        }

	        lblNumeroUscito.setVisible(true);

	        // Controlla le vincite
	        for (Node node : gridPaneTable.getChildren()) {
	            checkNodeForBets(node, nUscito);
	        }
	        if(isGameWon) {
	        	Main.player.incrementGamesWon();
	        }else {
	        	Main.player.incrementGamesLost();
	        }
	        isGameWon = false;
	        resetTable();
	        resetWheelAndBall();
	    });
	}
	
	@FXML
	private void resetWheelAndBall() {
	    // Riporta la ruota alla posizione iniziale
	    sezioniGroup.setRotate(0); // Azzera l'angolo di rotazione della ruota

	    // Riporta la pallina alla sua posizione e scala iniziali
	    pallina.setScaleX(1.0); // Reimposta la scala X
	    pallina.setScaleY(1.0); // Reimposta la scala Y
	    pallinaGroup.setRotate(0); // Azzera l'angolo di rotazione del gruppo della pallina

	}
	private void checkNodeForBets(Node node, Integer nUscito) {
	    if (node instanceof Button) {
	        Button button = (Button) node;
	        if (button.getGraphic() != null) {
	            ImageView imageView = (ImageView) button.getGraphic();
	            int chipValue;

	            // Controlla se è una scommessa "All In"
	            if (allInIsClicked && imageView.getImage() == allIn) {
	                chipValue = allInQuantity; // Usa il valore di "All In"
	            }
	            // Controlla se è una scommessa "Input Bet"
	            else if (imageView.getImage() == inputChip) {
	                // Trova la scommessa corrispondente nel list inputBets
	                int buttonId = getButtonIdFromButton(button); // Funzione per ottenere l'ID del bottone
	                InputBet inputBet = findInputBetByButtonId(buttonId);
	                if (inputBet != null) {
	                    chipValue = inputBet.getBetAmount(); // Usa il valore di "Input Bet"
	                } else {
	                    chipValue = 0; // Se non trova la scommessa, non applica moltiplicatori
	                }
	            }
	            // Altrimenti, usa il valore normale della chip
	            else {
	                chipValue = getPriceForImage(imageView.getImage());
	            }

	            String buttonText = button.getText();

	            // Numeri
	            if (buttonText.equals(nUscito.toString())) {
	                Main.player.addMoney(chipValue * 35); // Moltiplicatore x35 per i numeri
	                animateButton(button); // Animazione per il bottone vincente
	            }

	            // Colori
	            if (buttonText.equalsIgnoreCase("RED") && redNumbers.contains(nUscito)) {
	                Main.player.addMoney(chipValue * 2); // Moltiplicatore x2 per il rosso
	                animateButton(button); // Animazione per il bottone vincente
	            } else if (buttonText.equalsIgnoreCase("BLACK") && blackNumbers.contains(nUscito)) {
	                Main.player.addMoney(chipValue * 2); // Moltiplicatore x2 per il nero
	                animateButton(button); // Animazione per il bottone vincente
	            }

	            // Pari/Dispari
	            if (buttonText.equalsIgnoreCase("ODD") && nUscito % 2 == 1) {
	                Main.player.addMoney(chipValue * 2); // Moltiplicatore x2 per il dispari
	                animateButton(button); // Animazione per il bottone vincente
	            } else if (buttonText.equalsIgnoreCase("EVEN") && nUscito % 2 == 0) {
	                Main.player.addMoney(chipValue * 2); // Moltiplicatore x2 per il pari
	                animateButton(button); // Animazione per il bottone vincente
	            }

	            // Dozzine
	            if (buttonText.equalsIgnoreCase("1st 12") && nUscito >= 1 && nUscito <= 12) {
	                Main.player.addMoney(chipValue * 3); // Moltiplicatore x3 per la prima dozzina
	                animateButton(button); // Animazione per il bottone vincente
	            } else if (buttonText.equalsIgnoreCase("2nd 12") && nUscito >= 13 && nUscito <= 24) {
	                Main.player.addMoney(chipValue * 3); // Moltiplicatore x3 per la seconda dozzina
	                animateButton(button); // Animazione per il bottone vincente
	            } else if (buttonText.equalsIgnoreCase("3rd 12") && nUscito >= 25 && nUscito <= 36) {
	                Main.player.addMoney(chipValue * 3); // Moltiplicatore x3 per la terza dozzina
	                animateButton(button); // Animazione per il bottone vincente
	            }

	            // Colonne (righe)
	            if (currentRow.contains(nUscito) && !rowGotRight) {
	                System.out.println("Row got Right");
	                rowGotRight = true;
	                Main.player.addMoney(chipValue * 3); // Moltiplicatore x3 per la riga
	                animateButton(button); // Animazione per il bottone vincente
	            }

	            // 1-18 / 19-36
	            if (buttonText.equalsIgnoreCase("1 to 18") && nUscito >= 1 && nUscito <= 18) {
	                Main.player.addMoney(chipValue * 2); // Moltiplicatore x2 per 1-18
	                animateButton(button); // Animazione per il bottone vincente
	            } else if (buttonText.equalsIgnoreCase("19 to 36") && nUscito >= 19 && nUscito <= 36) {
	                Main.player.addMoney(chipValue * 2); // Moltiplicatore x2 per 19-36
	                animateButton(button); // Animazione per il bottone vincente
	            }
	        }
	    } else if (node instanceof Parent) {
	        for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
	            checkNodeForBets(child, nUscito);
	        }
	    }
	}

	private void animateButton(Button button) {
	    // Salva lo stile originale del bottone
	    String originalStyle = button.getStyle();

	    // Crea una Timeline per far lampeggiare il bottone
	    Timeline timeline = new Timeline(
	        new KeyFrame(Duration.seconds(0), event -> button.setStyle("-fx-background-color: yellow;")), // Inizia con giallo
	        new KeyFrame(Duration.seconds(0.5), event -> button.setStyle(originalStyle)), // Torna allo stile originale dopo 0.5 secondi
	        new KeyFrame(Duration.seconds(1), event -> button.setStyle("-fx-background-color: yellow;")), // Giallo dopo 1 secondo
	        new KeyFrame(Duration.seconds(1.5), event -> button.setStyle(originalStyle)), // Torna allo stile originale dopo 1.5 secondi
	        new KeyFrame(Duration.seconds(2), event -> button.setStyle("-fx-background-color: yellow;")), // Giallo dopo 2 secondi
	        new KeyFrame(Duration.seconds(2.5), event -> button.setStyle(originalStyle)), // Torna allo stile originale dopo 2.5 secondi
	        new KeyFrame(Duration.seconds(3), event -> button.setStyle(originalStyle)) // Alla fine, assicurati che sia ripristinato
	    );

	    timeline.setCycleCount(1); // Esegui l'animazione una sola volta
	    timeline.play();
	}

	private int getButtonIdFromButton(Button button) {
		String buttonText = button.getText();
		switch (buttonText) {
		case "1st 12":
			return 42;
		case "2nd 12":
			return 43;
		case "3rd 12":
			return 44;
		case "ODD":
			return 45;
		case "EVEN":
			return 46;
		case "RED":
			return 47;
		case "BLACK":
			return 48;
		case "1 to 18":
			return 49;
		case "19 to 36":
			return 50;
		default:
			try {
				return Integer.parseInt(buttonText); // Numeri da 1 a 36
			} catch (NumberFormatException e) {
				if (buttonText.equals("0"))
					return 37;
				if (buttonText.equals("00"))
					return 38;
				return -1; // Caso non gestito
			}
		}
	}

	private InputBet findInputBetByButtonId(int buttonId) {
		for (InputBet inputBet : inputBets) {
			if (inputBet.getButtonId() == buttonId) {
				return inputBet;
			}
		}
		return null; // Se non trova la scommessa
	}

	private int getPriceForImage(Image image) {
		// Ritorna il prezzo in base all'immagine
		if (image == image1.getImage()) {
			return 10;
		} else if (image == image2.getImage()) {
			return 25;
		} else if (image == image3.getImage()) {
			return 50;
		} else if (image == image4.getImage()) {
			return 100;
		} else if (image == image5.getImage()) {
			return 200;
		}
		return 0; // Nel caso in cui l'immagine non corrisponda a nessuna chip
	}

	public void resetTable() {
		refreshMoney();
		// Reset delle variabili di stato
		selectedNums.clear(); // Pulisce l'elenco dei numeri selezionati
		currentRow.clear(); // Pulisce la selezione della riga
		currentTwelve.clear(); // Pulisce la selezione della dozzina
		currentEighteen.clear(); // Pulisce la selezione dei numeri da 1 a 18 / 19 a 36
		num = null; // Reset del numero selezionato

		// Resetta lo stato di "All In"
		allInIsClicked = false;
		allInIsPositioned = false;
		allInQuantity = 0;

		// inputbet reset
		inputBetAmount = 0;
		inputBetIsPositioned = true;
		inputBets.clear();

		if (Main.player.getMoney() != 0) {
			allInButton.setVisible(true);
			betComboBox.setVisible(true);
			inputBox.setVisible(true);
		}else {
			allInButton.setVisible(false);
			betComboBox.setVisible(false);
			inputBox.setVisible(false);
			mostraPopup();
		}

		for (Node node : gridPaneTable.getChildren()) {
			// Se il nodo è un HBox o VBox
			if (node instanceof HBox || node instanceof VBox) {
				// Ottieni i figli di HBox o VBox
				for (Node childNode : ((Parent) node).getChildrenUnmodifiable()) {
					// Se il figlio è un bottone, rimuovi l'immagine
					if (childNode instanceof Button) {
						Button btn = (Button) childNode;
						btn.setGraphic(null); // Rimuovi l'immagine dal bottone
					}
				}
			}
			// Se il nodo è direttamente un bottone (nel caso sia direttamente nella
			// GridPane)
			else if (node instanceof Button) {
				Button btn = (Button) node;
				btn.setGraphic(null); // Rimuovi l'immagine dal bottone
			}
		}
	}
	
	@FXML
	private void mostraPopup() {
	    try {
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/roulette/fxml/popUp.fxml"));
	        Parent root = loader.load();

	        // Ottieni la finestra principale
	        Stage stage = (Stage) ruotaBox.getScene().getWindow(); 
	        
	        Scene scene = new Scene(root);
	        stage.setScene(scene);
	        stage.show();
	        stage.setFullScreen(true);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	
	@FXML
	public void handleNum(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();
		int num = Integer.parseInt(clickedButton.getText());

		// Verifica se c'è già un'immagine nel bottone
		if (clickedButton.getGraphic() != null) {
			// Se c'è già un'immagine, dobbiamo determinare quale tipo di scommessa è stata
			// piazzata
			ImageView currentImageView = (ImageView) clickedButton.getGraphic();
			Image currentImage = currentImageView.getImage();

			// Controlla se è una scommessa "All In"
			if (currentImage == allIn) {
				// Ripristina il saldo con il valore di "All In"
				Main.player.addMoney(allInQuantity);
				refreshMoney();

				// Resetta le variabili di "All In"
				allInIsClicked = false;
				allInIsPositioned = false;
				allInQuantity = 0;

				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);

				// Rimuovi l'immagine dal bottone
				clickedButton.setGraphic(null);
			}
			// Controlla se è una scommessa "Input Bet"
			else if (currentImage == inputChip) {
				// Trova la scommessa corrispondente nel list inputBets
				int buttonId = getButtonIdFromButton(clickedButton); // Funzione per ottenere l'ID del bottone
				InputBet inputBet = findInputBetByButtonId(buttonId);

				if (inputBet != null) {
					// Ripristina il saldo con il valore della scommessa
					Main.player.addMoney(inputBet.getBetAmount());
					refreshMoney();

					// Rimuovi la scommessa dalla lista inputBets
					inputBets.remove(inputBet);

					// Rimuovi l'immagine dal bottone
					clickedButton.setGraphic(null);

					// Resetta le variabili di "Input Bet"
					inputBetIsPositioned = false;
					inputBetAmount = 0;
				}
			}
			// Altrimenti, è una scommessa normale (chip standard)
			else {
				// Recupera il prezzo corrispondente all'immagine
				int priceToAdd = getPriceForImage(currentImage);

				// Rimuovi l'immagine
				clickedButton.setGraphic(null);

				// Aggiungi il prezzo del numero al denaro (corrispondente alla rimozione)
				Main.player.addMoney(priceToAdd);
				refreshMoney(); // Ricalcola e ggiorna il saldo
				selectedNums.remove(Integer.valueOf(num)); // Rimuovi il numero dalla lista
			}
		} else {
			// Se non c'è un'immagine, gestisci la nuova scommessa
			if (allInIsClicked && !allInIsPositioned) {
				// Aggiungi l'immagine solo se il saldo è sufficiente
				ImageView imageView = new ImageView(allIn);
				imageView.setFitWidth(25);
				imageView.setFitHeight(25);
				clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
				selectedNums.add(num); // Aggiungi il numero alla lista
				allInIsPositioned = true;
				Main.player.setMoney(0);
				refreshMoney();
			} else {
				if (!inputBetIsPositioned) {
					ImageView imageView = new ImageView(inputChip);
					imageView.setFitWidth(25);
					imageView.setFitHeight(25);
					clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
					selectedNums.add(num); // Aggiungi il numero alla lista (obsoleto)
					inputBetIsPositioned = true;
					inputBets.add(new InputBet(inputBetAmount, num)); // Aggiungi la scommessa alla lista
					Main.player.subtractMoney(inputBetAmount);
					if (Main.player.getMoney() != 0) {
						allInButton.setVisible(true);
						betComboBox.setVisible(true);
						inputBox.setVisible(true);
					}
					refreshMoney();
				} else {
					// Se l'utente ha abbastanza denaro e c'è una chip selezionata
					if (selectedImage != null && Main.player.getMoney() >= selectedPrice) {
						// Aggiungi l'immagine solo se il saldo è sufficiente
						ImageView imageView = new ImageView(selectedImage);
						imageView.setFitWidth(20);
						imageView.setFitHeight(20);
						clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone

						// Sottrai il prezzo del numero
						Main.player.subtractMoney(selectedPrice);
						refreshMoney(); // Ricalcola e aggiorna il saldo
						selectedNums.add(num); // Aggiungi il numero alla lista
					}
				}
			}
		}
	}

	@FXML
	public void handleColor(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();

		// Verifica se c'è già un'immagine nel bottone
		if (clickedButton.getGraphic() != null) {
			// Se c'è già un'immagine, dobbiamo determinare quale tipo di scommessa è stata
			// piazzata
			ImageView currentImageView = (ImageView) clickedButton.getGraphic();
			Image currentImage = currentImageView.getImage();

			// Controlla se è una scommessa "All In"
			if (currentImage == allIn) {
				// Ripristina il saldo con il valore di "All In"
				Main.player.addMoney(allInQuantity);
				refreshMoney();

				// Resetta le variabili di "All In"
				allInIsClicked = false;
				allInIsPositioned = false;
				allInQuantity = 0;

				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);

				// Rimuovi l'immagine dal bottone
				clickedButton.setGraphic(null);
			}
			// Controlla se è una scommessa "Input Bet"
			else if (currentImage == inputChip) {
				// Trova la scommessa corrispondente nel list inputBets
				int buttonId = getButtonIdFromButton(clickedButton); // Funzione per ottenere l'ID del bottone
				InputBet inputBet = findInputBetByButtonId(buttonId);

				if (inputBet != null) {
					// Ripristina il saldo con il valore della scommessa
					Main.player.addMoney(inputBet.getBetAmount());
					refreshMoney();

					// Rimuovi la scommessa dalla lista inputBets
					inputBets.remove(inputBet);

					// Rimuovi l'immagine dal bottone
					clickedButton.setGraphic(null);

					// Resetta le variabili di "Input Bet"
					inputBetIsPositioned = false;
					inputBetAmount = 0;
				}
			}
			// Altrimenti, è una scommessa normale (chip standard)
			else {
				// Recupera il prezzo corrispondente all'immagine
				int priceToAdd = getPriceForImage(currentImage);

				// Rimuovi l'immagine
				clickedButton.setGraphic(null);

				// Aggiungi il prezzo del numero al denaro (corrispondente alla rimozione)
				Main.player.addMoney(priceToAdd);
				refreshMoney(); // Ricalcola e aggiorna il saldo
			}
		} else {
			if (allInIsClicked && !allInIsPositioned) {
				// Aggiungi l'immagine solo se il saldo è sufficiente
				ImageView imageView = new ImageView(allIn);
				imageView.setFitWidth(20);
				imageView.setFitHeight(20);
				clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
				allInIsPositioned = true;
				Main.player.setMoney(0);
				refreshMoney();
			} else {
				if (!inputBetIsPositioned) {
					ImageView imageView = new ImageView(inputChip);
					imageView.setFitWidth(25);
					imageView.setFitHeight(25);
					clickedButton.setGraphic(imageView);
					inputBetIsPositioned = true;
					Main.player.subtractMoney(inputBetAmount);
					if (clickedButton.getText().equalsIgnoreCase("RED")) {
						inputBets.add(new InputBet(inputBetAmount, 47)); // 47 è id per rosso
					} else {
						inputBets.add(new InputBet(inputBetAmount, 48)); // 48 è id per nero
					}
					if (Main.player.getMoney() != 0) {
						allInButton.setVisible(true);
						betComboBox.setVisible(true);
						inputBox.setVisible(true);
					}
					refreshMoney();
				} else {
					// Aggiungi l'immagine selezionata solo se il saldo è sufficiente
					if (selectedImage != null && Main.player.getMoney() >= selectedPrice) {

						ImageView imageView = new ImageView(selectedImage);
						imageView.setFitWidth(20);
						imageView.setFitHeight(20);
						clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone

						// Sottrai il prezzo della chip
						Main.player.subtractMoney(selectedPrice);
						refreshMoney();
					}
				}
			}
		}
	}

	@FXML
	public void handleOddEven(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();

		// Verifica se c'è già un'immagine nel bottone
		if (clickedButton.getGraphic() != null) {
			// Se c'è già un'immagine, dobbiamo determinare quale tipo di scommessa è stata
			// piazzata
			ImageView currentImageView = (ImageView) clickedButton.getGraphic();
			Image currentImage = currentImageView.getImage();

			// Controlla se è una scommessa "All In"
			if (currentImage == allIn) {
				// Ripristina il saldo con il valore di "All In"
				Main.player.addMoney(allInQuantity);
				refreshMoney();

				// Resetta le variabili di "All In"
				allInIsClicked = false;
				allInIsPositioned = false;
				allInQuantity = 0;

				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);

				// Rimuovi l'immagine dal bottone
				clickedButton.setGraphic(null);
			}
			// Controlla se è una scommessa "Input Bet"
			else if (currentImage == inputChip) {
				// Trova la scommessa corrispondente nel list inputBets
				int buttonId = getButtonIdFromButton(clickedButton); // Funzione per ottenere l'ID del bottone
				InputBet inputBet = findInputBetByButtonId(buttonId);

				if (inputBet != null) {
					// Ripristina il saldo con il valore della scommessa
					Main.player.addMoney(inputBet.getBetAmount());
					refreshMoney();

					// Rimuovi la scommessa dalla lista inputBets
					inputBets.remove(inputBet);

					// Rimuovi l'immagine dal bottone
					clickedButton.setGraphic(null);

					// Resetta le variabili di "Input Bet"
					inputBetIsPositioned = false;
					inputBetAmount = 0;
				}
			}
			// Altrimenti, è una scommessa normale (chip standard)
			else {
				// Recupera il prezzo corrispondente all'immagine
				int priceToAdd = getPriceForImage(currentImage);

				// Rimuovi l'immagine
				clickedButton.setGraphic(null);

				// Aggiungi il prezzo del numero al denaro (corrispondente alla rimozione)
				Main.player.addMoney(priceToAdd);
				refreshMoney(); // Ricalcola e aggiorna il saldo
			}

		} else {
			if (allInIsClicked && !allInIsPositioned) {
				// Aggiungi l'immagine solo se il saldo è sufficiente
				ImageView imageView = new ImageView(allIn);
				imageView.setFitWidth(20);
				imageView.setFitHeight(20);
				clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
				allInIsPositioned = true;
				Main.player.setMoney(0);
				refreshMoney();
			} else {
				if (!inputBetIsPositioned) {
					ImageView imageView = new ImageView(inputChip);
					imageView.setFitWidth(25);
					imageView.setFitHeight(25);
					clickedButton.setGraphic(imageView);
					inputBetIsPositioned = true;
					if (clickedButton.getText().equalsIgnoreCase("ODD")) {
						inputBets.add(new InputBet(inputBetAmount, 45)); // 45 è id per odd
					} else {
						inputBets.add(new InputBet(inputBetAmount, 46)); // 46 è id per even
					}
					Main.player.subtractMoney(inputBetAmount);
					if (Main.player.getMoney() != 0) {
						allInButton.setVisible(true);
						betComboBox.setVisible(true);
						inputBox.setVisible(true);
					}
					refreshMoney();
				} else {
					// Se l'utente ha abbastanza denaro e c'è una chip selezionata
					if (selectedImage != null && Main.player.getMoney() >= selectedPrice) {
						// Aggiungi l'immagine solo se il saldo è sufficiente
						ImageView imageView = new ImageView(selectedImage);
						imageView.setFitWidth(20); // Imposta la larghezza dell'immagine
						imageView.setFitHeight(20); // Imposta l'altezza dell'immagine
						clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone

						// Sottrai il prezzo
						Main.player.subtractMoney(selectedPrice);
						refreshMoney(); // Ricalcola e aggiorna il saldo
					}
				}
			}
		}
	}

	@FXML
	public void handleTwelves(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();

		// Verifica se c'è già un'immagine nel bottone
		if (clickedButton.getGraphic() != null) {
			// Se c'è già un'immagine, dobbiamo determinare quale tipo di scommessa è stata
			// piazzata
			ImageView currentImageView = (ImageView) clickedButton.getGraphic();
			Image currentImage = currentImageView.getImage();

			// Controlla se è una scommessa "All In"
			if (currentImage == allIn) {
				// Ripristina il saldo con il valore di "All In"
				Main.player.addMoney(allInQuantity);
				refreshMoney();

				// Resetta le variabili di "All In"
				allInIsClicked = false;
				allInIsPositioned = false;
				allInQuantity = 0;

				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);

				// Rimuovi l'immagine dal bottone
				clickedButton.setGraphic(null);
				return;
			}
			// Controlla se è una scommessa "Input Bet"
			else if (currentImage == inputChip) {
				// Trova la scommessa corrispondente nel list inputBets
				int buttonId = getButtonIdFromButton(clickedButton); // Funzione per ottenere l'ID del bottone
				InputBet inputBet = findInputBetByButtonId(buttonId);

				if (inputBet != null) {
					// Ripristina il saldo con il valore della scommessa
					Main.player.addMoney(inputBet.getBetAmount());
					refreshMoney();

					// Rimuovi la scommessa dalla lista inputBets
					inputBets.remove(inputBet);

					// Rimuovi l'immagine dal bottone
					clickedButton.setGraphic(null);

					// Resetta le variabili di "Input Bet"
					inputBetIsPositioned = false;
					inputBetAmount = 0;
					return;
				}
			}

			// Altrimenti, è una scommessa normale (chip standard)
			else {
				// Recupera il prezzo corrispondente all'immagine
				int priceToAdd = getPriceForImage(currentImage);

				// Rimuovi l'immagine
				clickedButton.setGraphic(null);

				// Aggiungi il prezzo del numero al denaro (corrispondente alla rimozione)
				Main.player.addMoney(priceToAdd);
				refreshMoney(); // Ricalcola e aggiorna il saldo
				currentTwelve.clear();
				return;
			}
		}
		if (allInIsClicked && !allInIsPositioned) {
			// Aggiungi l'immagine solo se il saldo è sufficiente
			ImageView imageView = new ImageView(allIn);
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
			selectedNums.add(num); // Aggiungi il numero alla lista
			allInIsPositioned = true;
			Main.player.setMoney(0);
			refreshMoney();
			return;
		}
		if (!inputBetIsPositioned) {
			ImageView imageView = new ImageView(inputChip);
			imageView.setFitWidth(25);
			imageView.setFitHeight(25);
			clickedButton.setGraphic(imageView);
			inputBetIsPositioned = true;
			if (clickedButton.getText().equalsIgnoreCase("1st 12")) {
				inputBets.add(new InputBet(inputBetAmount, 42)); // 42 è id per prima dozzina
			} else if (clickedButton.getText().equalsIgnoreCase("2nd 12")) {
				inputBets.add(new InputBet(inputBetAmount, 43)); // 43 è id per seconda dozzina
			} else {
				inputBets.add(new InputBet(inputBetAmount, 44)); // 44 è id per terza dozzina
			}
			Main.player.subtractMoney(inputBetAmount);
			if (Main.player.getMoney() != 0) {
				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);
			}
			refreshMoney();
			return;
		}

		// Se l'utente ha abbastanza denaro e c'è una chip selezionata
		if (selectedImage != null && Main.player.getMoney() >= selectedPrice) {
			// Aggiungi la selezione dei numeri in base al bottone premuto
			switch (clickedButton.getText()) {
			case "1st 12":
				for (int i = 1; i <= 12; i++) {
					currentTwelve.add(i);
				}
				break;
			case "2nd 12":
				for (int i = 13; i <= 24; i++) {
					currentTwelve.add(i);
				}
				break;
			case "3rd 12":
				for (int i = 25; i <= 36; i++) {
					currentTwelve.add(i);
				}
				break;
			}

			// Aggiungi l'immagine al bottone
			ImageView imageView = new ImageView(selectedImage);
			imageView.setFitWidth(20); // Imposta la larghezza dell'immagine
			imageView.setFitHeight(20); // Imposta l'altezza dell'immagine
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone

			// Sottrai il prezzo dal saldo
			Main.player.subtractMoney(selectedPrice);
			refreshMoney(); // Ricalcola e aggiorna il saldo
		}
	}

	@FXML
	public void handleEighteen(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();

		// Verifica se c'è già un'immagine nel bottone
		if (clickedButton.getGraphic() != null) {
			// Se c'è già un'immagine, dobbiamo determinare quale tipo di scommessa è stata
			// piazzata
			ImageView currentImageView = (ImageView) clickedButton.getGraphic();
			Image currentImage = currentImageView.getImage();

			// Controlla se è una scommessa "All In"
			if (currentImage == allIn) {
				// Ripristina il saldo con il valore di "All In"
				Main.player.addMoney(allInQuantity);
				refreshMoney();

				// Resetta le variabili di "All In"
				allInIsClicked = false;
				allInIsPositioned = false;
				allInQuantity = 0;

				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);

				// Rimuovi l'immagine dal bottone
				clickedButton.setGraphic(null);
				return;
			}
			// Controlla se è una scommessa "Input Bet"
			else if (currentImage == inputChip) {
				// Trova la scommessa corrispondente nel list inputBets
				int buttonId = getButtonIdFromButton(clickedButton); // Funzione per ottenere l'ID del bottone
				InputBet inputBet = findInputBetByButtonId(buttonId);

				if (inputBet != null) {
					// Ripristina il saldo con il valore della scommessa
					Main.player.addMoney(inputBet.getBetAmount());
					refreshMoney();

					// Rimuovi la scommessa dalla lista inputBets
					inputBets.remove(inputBet);

					// Rimuovi l'immagine dal bottone
					clickedButton.setGraphic(null);

					// Resetta le variabili di "Input Bet"
					inputBetIsPositioned = false;
					inputBetAmount = 0;
					return;
				}
			}

			// Altrimenti, è una scommessa normale (chip standard)
			else {
				// Recupera il prezzo corrispondente all'immagine
				int priceToAdd = getPriceForImage(currentImage);

				// Rimuovi l'immagine
				clickedButton.setGraphic(null);

				// Aggiungi il prezzo del numero al denaro (corrispondente alla rimozione)
				Main.player.addMoney(priceToAdd);
				refreshMoney(); // Ricalcola e aggiorna il saldo
				currentEighteen.clear();
				return;
			}
		}
		if (allInIsClicked && !allInIsPositioned) {
			// Aggiungi l'immagine solo se il saldo è sufficiente
			ImageView imageView = new ImageView(allIn);
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
			selectedNums.add(num); // Aggiungi il numero alla lista
			allInIsPositioned = true;
			Main.player.setMoney(0);
			refreshMoney();
			return;
		}
		if (!inputBetIsPositioned) {
			ImageView imageView = new ImageView(inputChip);
			imageView.setFitWidth(25);
			imageView.setFitHeight(25);
			clickedButton.setGraphic(imageView);
			inputBetIsPositioned = true;
			if (clickedButton.getText().equalsIgnoreCase("1 to 18")) {
				inputBets.add(new InputBet(inputBetAmount, 49)); // 49 è id per i primi 18
			} else {
				inputBets.add(new InputBet(inputBetAmount, 50)); // 46 è id per gli ultimi 18
			}
			Main.player.subtractMoney(inputBetAmount);
			if (Main.player.getMoney() != 0) {
				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);
			}
			refreshMoney();
			return;
		}
		// Se l'utente ha abbastanza denaro e c'è una chip selezionata
		if (selectedImage != null && Main.player.getMoney() >= selectedPrice) {
			// Aggiungi la selezione dei numeri in base al bottone premuto
			if (clickedButton.getText().equalsIgnoreCase("1 to 18")) {
				for (int i = 1; i <= 18; i++) {
					currentEighteen.add(i);
				}
			} else {
				for (int i = 19; i <= 36; i++) {
					currentEighteen.add(i);
				}
			}

			// Aggiungi l'immagine al bottone
			ImageView imageView = new ImageView(selectedImage);
			imageView.setFitWidth(20); // Imposta la larghezza dell'immagine
			imageView.setFitHeight(20); // Imposta l'altezza dell'immagine
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone

			// Sottrai il prezzo dal saldo
			Main.player.subtractMoney(selectedPrice);
			refreshMoney(); // Ricalcola e aggiorna il saldo
		}
	}

	@FXML
	public void handleRow1(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();
		currentRow.addAll(firstRow);

		// Verifica se c'è già un'immagine nel bottone
				if (clickedButton.getGraphic() != null) {
					// Se c'è già un'immagine, dobbiamo determinare quale tipo di scommessa è stata
					// piazzata
					ImageView currentImageView = (ImageView) clickedButton.getGraphic();
					Image currentImage = currentImageView.getImage();

					// Controlla se è una scommessa "All In"
					if (currentImage == allIn) {
						// Ripristina il saldo con il valore di "All In"
						Main.player.addMoney(allInQuantity);
						refreshMoney();

						// Resetta le variabili di "All In"
						allInIsClicked = false;
						allInIsPositioned = false;
						allInQuantity = 0;

						allInButton.setVisible(true);
						betComboBox.setVisible(true);
						inputBox.setVisible(true);

						// Rimuovi l'immagine dal bottone
						clickedButton.setGraphic(null);
						return;
					}
					// Controlla se è una scommessa "Input Bet"
					else if (currentImage == inputChip) {
						// Trova la scommessa corrispondente nel list inputBets
						int buttonId = getButtonIdFromButton(clickedButton); // Funzione per ottenere l'ID del bottone
						InputBet inputBet = findInputBetByButtonId(buttonId);

						if (inputBet != null) {
							// Ripristina il saldo con il valore della scommessa
							Main.player.addMoney(inputBet.getBetAmount());
							refreshMoney();

							// Rimuovi la scommessa dalla lista inputBets
							inputBets.remove(inputBet);

							// Rimuovi l'immagine dal bottone
							clickedButton.setGraphic(null);

							// Resetta le variabili di "Input Bet"
							inputBetIsPositioned = false;
							inputBetAmount = 0;
							return;
						}
					}

					// Altrimenti, è una scommessa normale (chip standard)
					else {
						// Recupera il prezzo corrispondente all'immagine
						int priceToAdd = getPriceForImage(currentImage);

						// Rimuovi l'immagine
						clickedButton.setGraphic(null);

						// Aggiungi il prezzo del numero al denaro (corrispondente alla rimozione)
						Main.player.addMoney(priceToAdd);
						refreshMoney(); // Ricalcola e aggiorna il saldo
						currentRow.clear();
						return;
					}
				}
		if (allInIsClicked && !allInIsPositioned) {
			// Aggiungi l'immagine solo se il saldo è sufficiente
			ImageView imageView = new ImageView(allIn);
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
			selectedNums.add(num); // Aggiungi il numero alla lista
			allInIsPositioned = true;
			Main.player.setMoney(0);
			refreshMoney();
			return;
		}
		if (!inputBetIsPositioned) {
			ImageView imageView = new ImageView(inputChip);
			imageView.setFitWidth(25);
			imageView.setFitHeight(25);
			clickedButton.setGraphic(imageView);
			inputBetIsPositioned = true;
			inputBets.add(new InputBet(inputBetAmount, 39)); // 39 è id per prima riga
			Main.player.subtractMoney(inputBetAmount);
			if (Main.player.getMoney() != 0) {
				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);
			}
			refreshMoney();
			return;
		}
		// Se l'utente ha abbastanza denaro e c'è una chip selezionata
		if (selectedImage != null && Main.player.getMoney() >= selectedPrice) {
			// Aggiungi l'immagine al bottone
			ImageView imageView = new ImageView(selectedImage);
			imageView.setFitWidth(20); // Imposta la larghezza dell'immagine
			imageView.setFitHeight(20); // Imposta l'altezza dell'immagine
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone

			// Sottrai il prezzo dal saldo
			Main.player.subtractMoney(selectedPrice);
			refreshMoney(); // Ricalcola e aggiorna il saldo
		}
	}

	@FXML
	public void handleRow2(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();
		currentRow.addAll(secondRow);

		// Verifica se c'è già un'immagine nel bottone
		if (clickedButton.getGraphic() != null) {
			// Se c'è già un'immagine, dobbiamo determinare quale tipo di scommessa è stata
			// piazzata
			ImageView currentImageView = (ImageView) clickedButton.getGraphic();
			Image currentImage = currentImageView.getImage();

			// Controlla se è una scommessa "All In"
			if (currentImage == allIn) {
				// Ripristina il saldo con il valore di "All In"
				Main.player.addMoney(allInQuantity);
				refreshMoney();

				// Resetta le variabili di "All In"
				allInIsClicked = false;
				allInIsPositioned = false;
				allInQuantity = 0;

				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);

				// Rimuovi l'immagine dal bottone
				clickedButton.setGraphic(null);
				return;
			}
			// Controlla se è una scommessa "Input Bet"
			else if (currentImage == inputChip) {
				// Trova la scommessa corrispondente nel list inputBets
				int buttonId = getButtonIdFromButton(clickedButton); // Funzione per ottenere l'ID del bottone
				InputBet inputBet = findInputBetByButtonId(buttonId);

				if (inputBet != null) {
					// Ripristina il saldo con il valore della scommessa
					Main.player.addMoney(inputBet.getBetAmount());
					refreshMoney();

					// Rimuovi la scommessa dalla lista inputBets
					inputBets.remove(inputBet);

					// Rimuovi l'immagine dal bottone
					clickedButton.setGraphic(null);

					// Resetta le variabili di "Input Bet"
					inputBetIsPositioned = false;
					inputBetAmount = 0;
					return;
				}
			}

			// Altrimenti, è una scommessa normale (chip standard)
			else {
				// Recupera il prezzo corrispondente all'immagine
				int priceToAdd = getPriceForImage(currentImage);

				// Rimuovi l'immagine
				clickedButton.setGraphic(null);

				// Aggiungi il prezzo del numero al denaro (corrispondente alla rimozione)
				Main.player.addMoney(priceToAdd);
				refreshMoney(); // Ricalcola e aggiorna il saldo
				currentRow.clear();
				return;
			}
		}
		if (allInIsClicked && !allInIsPositioned) {
			// Aggiungi l'immagine solo se il saldo è sufficiente
			ImageView imageView = new ImageView(allIn);
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
			selectedNums.add(num); // Aggiungi il numero alla lista
			allInIsPositioned = true;
			Main.player.setMoney(0);
			refreshMoney();
			return;
		}
		if (!inputBetIsPositioned) {
			ImageView imageView = new ImageView(inputChip);
			imageView.setFitWidth(25);
			imageView.setFitHeight(25);
			clickedButton.setGraphic(imageView);
			inputBetIsPositioned = true;
			inputBets.add(new InputBet(inputBetAmount, 40)); // 40 è id per seconda riga
			Main.player.subtractMoney(inputBetAmount);
			if (Main.player.getMoney() != 0) {
				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);
			}
			refreshMoney();
			return;
		}
		// Se l'utente ha abbastanza denaro e c'è una chip selezionata
		if (selectedImage != null && Main.player.getMoney() >= selectedPrice) {
			// Aggiungi l'immagine al bottone
			ImageView imageView = new ImageView(selectedImage);
			imageView.setFitWidth(20); // Imposta la larghezza dell'immagine
			imageView.setFitHeight(20); // Imposta l'altezza dell'immagine
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone

			// Sottrai il prezzo dal saldo
			Main.player.subtractMoney(selectedPrice);
			refreshMoney(); // Ricalcola e aggiorna il saldo
		}
	}

	@FXML
	public void handleRow3(ActionEvent event) {
		Button clickedButton = (Button) event.getSource();
		currentRow.addAll(thirdRow);

		// Verifica se c'è già un'immagine nel bottone
		if (clickedButton.getGraphic() != null) {
			// Se c'è già un'immagine, dobbiamo determinare quale tipo di scommessa è stata
			// piazzata
			ImageView currentImageView = (ImageView) clickedButton.getGraphic();
			Image currentImage = currentImageView.getImage();

			// Controlla se è una scommessa "All In"
			if (currentImage == allIn) {
				// Ripristina il saldo con il valore di "All In"
				Main.player.addMoney(allInQuantity);
				refreshMoney();

				// Resetta le variabili di "All In"
				allInIsClicked = false;
				allInIsPositioned = false;
				allInQuantity = 0;

				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);

				// Rimuovi l'immagine dal bottone
				clickedButton.setGraphic(null);
				return;
			}
			// Controlla se è una scommessa "Input Bet"
			else if (currentImage == inputChip) {
				// Trova la scommessa corrispondente nel list inputBets
				int buttonId = getButtonIdFromButton(clickedButton); // Funzione per ottenere l'ID del bottone
				InputBet inputBet = findInputBetByButtonId(buttonId);

				if (inputBet != null) {
					// Ripristina il saldo con il valore della scommessa
					Main.player.addMoney(inputBet.getBetAmount());
					refreshMoney();

					// Rimuovi la scommessa dalla lista inputBets
					inputBets.remove(inputBet);

					// Rimuovi l'immagine dal bottone
					clickedButton.setGraphic(null);

					// Resetta le variabili di "Input Bet"
					inputBetIsPositioned = false;
					inputBetAmount = 0;
					return;
				}
			}

			// Altrimenti, è una scommessa normale (chip standard)
			else {
				// Recupera il prezzo corrispondente all'immagine
				int priceToAdd = getPriceForImage(currentImage);

				// Rimuovi l'immagine
				clickedButton.setGraphic(null);

				// Aggiungi il prezzo del numero al denaro (corrispondente alla rimozione)
				Main.player.addMoney(priceToAdd);
				refreshMoney(); // Ricalcola e aggiorna il saldo
				currentRow.clear();
				return;
			}
		}
		if (allInIsClicked && !allInIsPositioned) {
			// Aggiungi l'immagine solo se il saldo è sufficiente
			ImageView imageView = new ImageView(allIn);
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone
			selectedNums.add(num); // Aggiungi il numero alla lista
			allInIsPositioned = true;
			Main.player.setMoney(0);
			refreshMoney();
			return;
		}
		if (!inputBetIsPositioned) {
			ImageView imageView = new ImageView(inputChip);
			imageView.setFitWidth(25);
			imageView.setFitHeight(25);
			clickedButton.setGraphic(imageView);
			inputBetIsPositioned = true;
			inputBets.add(new InputBet(inputBetAmount, 41)); // 41 è id per terza riga
			Main.player.subtractMoney(inputBetAmount);
			if (Main.player.getMoney() != 0) {
				allInButton.setVisible(true);
				betComboBox.setVisible(true);
				inputBox.setVisible(true);
			}
			refreshMoney();
			return;
		}
		// Se l'utente ha abbastanza denaro e c'è una chip selezionata
		if (selectedImage != null && Main.player.getMoney() >= selectedPrice) {
			// Aggiungi l'immagine al bottone
			ImageView imageView = new ImageView(selectedImage);
			imageView.setFitWidth(20); // Imposta la larghezza dell'immagine
			imageView.setFitHeight(20); // Imposta l'altezza dell'immagine
			clickedButton.setGraphic(imageView); // Aggiungi l'immagine al bottone

			// Sottrai il prezzo dal saldo
			Main.player.subtractMoney(selectedPrice);
			refreshMoney(); // Ricalcola e aggiorna il saldo
		}
	}

	public void refreshMoney() {
		lblMoney.setText(Main.player.getMoney().toString());
		if (Main.player.getMoney() == 0) {
			lblMoney.setStyle("-fx-text-fill: red;");
		} else {
			lblMoney.setStyle("-fx-text-fill: white;");
		}
	}
	public void debug() {
		System.out.println("Games Played: " + Main.player.getGamesPlayed());
		System.out.println("Games won: " + Main.player.getGamesWon());
		System.out.println("Games lost: " + Main.player.getGamesLost());
		System.out.println("Money " + Main.player.getMoney());
		//IMPLEMENTARE GLI ALTRI METODI
	}
	
	
	
}



