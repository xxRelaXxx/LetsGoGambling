# MI RACCOMANDO, NOMINARE TUTTI ELEMENTI IN INGLESE, INVECE I COMMENTI POSSONO ESSERE SCRITTI IN LINGUA PIÚ COMODA PER VOI

# LetsGoGambling

Giochi d’Azzardo descrizione

L’applicazione presenterà quattro giochi tradizionali basati esclusivamente sulla casualità:

1. Craps – Un gioco da casinò con i dadi.


2. Indovina il Numero – Un gioco in cui l’utente sceglie un numero e vince se corrisponde all’esito casuale.


3. Roulette – Una roulette in stile europeo con scommesse su rosso/nero, pari/dispari e numeri.


4. Slot Machine – Una slot machine virtuale con probabilità variabili.




---

Modalità di Gioco

Gli utenti avranno due opzioni di gioco:

1. Modalità Manuale (Gioco Tradizionale)

L’utente seleziona un gioco e piazza scommesse manualmente, scegliendo la propria strategia (anche se spericolata).

Il sistema non interferisce con le decisioni dell’utente, permettendo di sperimentare liberamente le conseguenze reali del gioco d’azzardo.


2. Modalità Automatica (Simulazione a Lungo Termine)

L’utente può automatizzare il processo di gioco, permettendo al sistema di giocare per suo conto con condizioni predefinite.

L’utente deve specificare quali giochi includere nell’automazione e quale percentuale di scommesse assegnare a ciascun gioco.


Strategie di Scommessa

Strategia Prudente: Le scommesse vengono effettuate in modo conservativo, concentrandosi su puntate a basso rischio.

Strategia Spericolata: Il sistema effettua scommesse ad alto rischio con il potenziale di guadagni a breve termine, ma con perdite inevitabili nel lungo periodo.



---

Monitoraggio e Visualizzazione in Tempo Reale

Durante il gioco, il sistema deve mostrare:

Capitale Attuale: L’ammontare di denaro rimanente.

Numero di Partite Giocate: Tiene traccia del numero totale di scommesse piazzate.

Indicatore di Stato con Colori:

Verde quando il capitale aumenta.

Rosso quando il capitale diminuisce.



Rappresentazione Grafica

Un grafico aggiornato in tempo reale che mostra il numero di partite (asse x) rispetto al capitale (asse y), illustrando le tendenze finanziarie.



---

Archiviazione Dati e Registri di Gioco

L’applicazione deve memorizzare dati storici per tracciare casi estremi e sessioni significative. I seguenti dati devono essere salvati in file esterni per analisi future:

Striscia di Perdite più Lunga: Il massimo numero di partite perse consecutivamente da un giocatore.

Capitale più Alto Raggiunto: La quantità massima di denaro accumulata in una singola sessione.

Detentori dei Record: Nomi degli utenti che hanno stabilito i record sopra citati.

Date e Orari dei Record: Il momento in cui ogni record è stato stabilito.

Durata delle Partite: La durata totale di ogni sessione (dal principio alla fine).



---

Considerazioni Aggiuntive

L’interfaccia grafica deve essere coinvolgente ma educativa, evidenziando come la probabilità porti inevitabilmente a perdite nel tempo.

Il sistema deve consentire l’esportazione dei dati per analisi approfondite.

Implementare statistiche di base per mostrare il valore atteso (EV) e il vantaggio della casa.

Considerare l’aggiunta di una modalità educativa con spiegazioni sulle probabilità e sul perché i casinò vincono sempre a lungo termine.



# Suddivisione del lavoro

1) Menu iniziali (Rajiv)
2) Classi iniziali (Player, Bot, Game...) (Shaaek)
3) Gioco Dadi (Roman aka.RelaX)
4) Gioco Indovina numero (Tondo)
5) Gioco Roulette (Pisy)
6) Gioco Slot (Jeremy)
7) Grafico (parte finale)
8) FileManager (parte finale)






# Diagramma delle Classi (Generata al momento dal chatGPT, quindi è solamente un riferimento)

Il progetto è strutturato con un'architettura ad ereditarietà e composizione, utilizzando il principio della programmazione ad oggetti per garantire modularità e scalabilità.


---

1. Classe Astratta Game (Superclasse per tutti i giochi)

Descrizione:
Rappresenta un gioco d'azzardo generico. Ogni gioco specifico (Craps, Roulette, etc.) eredita da questa classe e implementa le proprie regole.

Attributi:

String name → Nome del gioco.

double houseEdge → Vantaggio della casa espresso in percentuale.

Random random → Generatore casuale per le scommesse.


Metodi Astratti:

public abstract double playRound(double bet); → Simula una singola giocata e restituisce il guadagno o la perdita.

public abstract String getRules(); → Restituisce una descrizione delle regole del gioco.



---

2. Sottoclassi di Game (Singoli Giochi)

Tutte le classi seguenti estendono Game, implementando il metodo playRound().

2.1. Classe Craps

Attributi Specifici:

int dice1, dice2 → Valori dei dadi lanciati.


Metodi:

public double playRound(double bet);

public String getRules();



---

2.2. Classe GuessNumber

Attributi Specifici:

int chosenNumber → Numero scelto dall'utente.

int drawnNumber → Numero casuale generato.


Metodi:

public double playRound(double bet);

public String getRules();



---

2.3. Classe Roulette

Attributi Specifici:

String betType → Tipo di scommessa (rosso/nero, pari/dispari, numero esatto).

int winningNumber → Numero estratto dalla roulette.


Metodi:

public double playRound(double bet);

public String getRules();



---

2.4. Classe SlotMachine

Attributi Specifici:

int[] reels → Array di numeri casuali rappresentanti i rulli della slot.


Metodi:

public double playRound(double bet);

public String getRules();



---

3. Classe Player (Giocatore)

Attributi:

String name → Nome del giocatore.

double capital → Capitale attuale del giocatore.


Metodi:

public void placeBet(double amount, Game game); → Effettua una scommessa su un gioco specifico.

public boolean isBankrupt(); → Restituisce true se il capitale è finito.



---

4. Classe ComputerOpponent

Descrizione:
Simula l'avversario virtuale che gestisce il banco.

Attributi:

double totalEarnings → Guadagni complessivi del banco.


Metodi:

public void collectBet(double amount); → Registra una scommessa persa dal giocatore.

public void payOut(double amount); → Paga una vincita al giocatore.



---

5. Classe GameSession

Descrizione:
Gestisce una sessione di gioco, tenendo traccia delle statistiche e della storia delle scommesse.

Attributi:

Player player → Giocatore della sessione.

List<Game> games → Lista dei giochi disponibili.

int totalRounds → Numero di giocate effettuate.


Metodi:

public void start(); → Avvia la sessione e permette all’utente di giocare.

public void printStatistics(); → Mostra i dati di sessione (capitale rimanente, numero di scommesse).



---

6. Classe AutomatedGameSession (Sottoclasse di GameSession)

Descrizione:
Gestisce il gioco automatico con strategie predefinite.

Attributi Specifici:

Map<Game, Double> gameAllocation → Percentuale di scommesse assegnata a ciascun gioco.

boolean recklessMode → Se attivo, effettua puntate ad alto rischio.


Metodi:

public void runSimulation(); → Simula il gioco automatico e aggiorna il capitale.



---

7. Classe DataLogger (Gestione File e Dati Storici)

Descrizione:
Si occupa di salvare le statistiche del gioco per analisi future.

Metodi:

public static void saveRecord(String playerName, double maxCapital, int maxLosingStreak); → Salva un record nel file di dati.

public static List<String> loadRecords(); → Carica e restituisce la lista dei record salvati.



---

8. Classe GraphicalUI (Interfaccia Utente con JavaFX)

Descrizione:
Gestisce l'interfaccia grafica, consentendo all’utente di selezionare giochi, visualizzare statistiche e impostare la modalità automatica.

Metodi:

public void start(Stage primaryStage); → Avvia l’interfaccia JavaFX.

private void displayMainMenu(); → Mostra il menu principale.

private void startManualGame(); → Avvia una sessione manuale.

private void startAutomatedGame(); → Avvia una sessione automatizzata.

private void showStatistics(); → Mostra le statistiche su schermo.
