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

1) Menu iniziali (Rajiv): sono menu con inserimento username e la schelta della modalità del gioco (facile/difficile (si può inventare anche i nomi più assurdi)) e naturalmente il buttone start/continua/lancia/gioca, a seguire con un menu con la scelta di 4(in futuro il numero può aumentarsi) giochi, dove l'utente sceglie in cosa vuole giocare.


2) Classi iniziali (Player, Bot, Game...) (Shaaek): creare i classi di riferimento per poter conettere tutti i parti del programma in futuro.
*Player con attributi e metodi come String username, Integer money/chips, bool bancrot, getMoney(), setMonet(), metodi che servono per giocare tipo launchDice() etc...
*Bot che sara simile al Player
*Game con metodi come startGame(), attributi come "round" etc...
*Altri classi di riferimento


3) Gioco Dadi (Roman aka.RelaX): gico con 2/3 dadi (dipende dalla difficoltà), stai lanciando i tuoi dadi contro i dadi del Bot, chi avra la somma maggiore - vince.(In base della differenza delle somme finali si calcola il moltiplicatore per il Bet messo).


4) Gioco Indovina numero (Tondo): potrai al inizio di ogni round di sceglire un intervallo di numeri (1-10, 1-20, 1-30, 1-40, 1-50), alla base del intervallo scelto il sistema ti assegna il moltiplicatore per il tuo Bet (intervallo più ampio - moltiplicatore più grande) e Bot sceglie un numero da questo intervallo, dopo di che, con ogni prova di indovinare il numero scelto dal Bot il moltiplicatore diminuisce. Facendo tante scelte sbagliate (senza indovinare il numero del Bot) il moltiplicatore diminuirà, dopo di che potrà diventare negativo e scattare i chip.


5) Gioco Roulette (Pisy): un gioco classico di roulette americana(con piccoli cambiamenti per raffacilitare la programmazione), sono sicuro che Pisy sa gia cosa deve fare.(In realtà non conosco le regole)


6) Gioco Slot (Jeremy): anche questo è un gico classico da casino, alla base della difficolta deve cambiarsi la quantità dei slot (facile 1x3, difficile 1x5), Jeremias farà le combinazioni degli slot desiderati da lui, e alla base di questi combinazioni sarà calcolato il moltiplicatore per il Bet messo. **In futuro per complicare la vita si può di rielaborare la struttura degli slot nel grid 3x3 o 5x5, con più combinazioni e più moltiplicatori.(In realtà questa versione si puo fare anche subito)**.


7) Grafico (parte finale): un grafico in tempo reale, dove asse-x rappresenta i round giocati dopo aver entrato nel programma(round generali in tutti i giochi), invece l'asse-y rappresenta i soldi del Player in quel momento(round) del gioco. Il grafico deve essere collegato a ogni gioco, ed essere aggiornato dopo aver finiro il round in gioco qualsiasi. Dopo l'aggiornamento, si aggiunhe un nuoco punto sul grafico con coordinati [round, money/chips] dopo di che si collegamo due punti (ultimo sul grafico e nuovo) con una linea.


8) FileManager (Rajiv): una classe, che potrà avere anche un'interfaccia(o fuzionare solo una volta alla fine del gioco (quando finiscono i soldi di Player)). La funzione è di salvare(non so se abbiamo bisogno di leggere) dentro un file.txt sul desktop le informazioni(i record raggiunti durante il gioco), come il denaro/chip massimi raggiunti, ound finale raggiunto prima di perdere, la data, la ora, il username del Player, la modalità del gioco, quanti round abbiamo giocato e in che gioco, etc...(ovviamente ai può aggiungere di più)


9) Modalità del gioco automatizzata (parte finale): idea iniziale è di lanciare un'altra finestra attiva(con l'interfaccia diversa?) in cui si svolgeranno tanti giochi velocemente. In realtà, scegliendo questa modalità del gioco si creano due Bot, senza Player, che eseguono i metodi dei giochi dentro un loop infinito, che finisce quando i soldi/chip del Bot (che rappresenta il Player) arrivano a ZERO. Tutte le scelte del Player(Bot principale) si fanno a caso con il Random creato da noi. Il gioco in cui giocare, il Bet che facciamo, il modo di giocare, tutto sarà casuale.


10) Sistema del random "sincero": un random che deve essere programmato in modo di non fare le scelte solamente secondo il random della CPU(seguendo la "clock") ma cambiarlo, quindi livellarlo in modo che tutte le possibilità siano approssimativamente uguali. O in un momento cambiarlo all'improviso per perdere subito?


11) Il ViSuAlE (da pensare e sicuramente da fare): deve essere in tema del casino con i colori come verde/rosso/nero(secondo me i colori più usati in casino) e immagini diversi della stessa tematica. Tutti i menu (non parlando neanche dei giochi) dovrebbero avere un design intuitivo, in modo che anche una persona con problemi come la sindrome di down, il cancro al cervello, l'instabilità o la disabilità mentale possa capire cosa c'è e come usarlo.

12) Coin-flip game: un gioco aggiuntivo fatto da Shaeek, dove lanciando la moneta puoi vincere con la probabilita 50/50.

13) Sistema dei account: realizzata da Subramaniam, permette di salvare dati di ogni giocatore registrato e permette di tracciare tutti i suoi dati principali.
