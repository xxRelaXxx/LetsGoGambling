package guessNumber;

import main.Player;

public class GuessNumber {
	private int secretNumber;
	private int attempts;
	private double multiplier;
	private int totalAttempts; // Numero massimo di tentativi
	private double baseMultiplier; // Moltiplicatore iniziale in base alla difficoltà
	private int minRange;
	private int maxRange;
	private double negativeMultiplierMax; // Valore minimo, es. -10
	private int bet=0;

	Player player1 = new Player("alessandro", 1000, 0, 0, 0, 0);

	public void setBet(int bet) {
		this.bet = bet;
	}
	public int getBet() {
		return bet;
	}

	public String getMoney() {
		String money = player1.getMoney().toString() + " $ ";
		return money;
	}
	
public GuessNumber() {
	// TODO Auto-generated constructor stub
}
	// Costruttore: imposta l'intervallo (da minRange a maxRange)
	public GuessNumber(int maxRange, int minRange) {
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.secretNumber = generateRandomNumber(maxRange);
		this.attempts = 0;
		this.baseMultiplier = calculateBaseMultiplier();
		this.multiplier = baseMultiplier;
		this.totalAttempts = maxRange; // Per esempio, il numero massimo di tentativi è pari all'intervallo
		this.negativeMultiplierMax = 10.0; // Alla fine, se l'utente indovina solo all'ultimo tentativo, il
											// moltiplicatore diventa -10
	}

	// Calcola il moltiplicatore base in base all'intervallo (difficoltà)
	private double calculateBaseMultiplier() {
		int range = maxRange - minRange + 1;
		if (range <= 10)
			return 1.0;
		else if (range <= 15)
			return 2.0;
		else
			return 3.0;
	}

	// Genera un numero casuale compreso tra minRange e maxRange
	public int generateRandomNumber(int maxRange) {
		return (int) (Math.random() * (maxRange - minRange + 1)) + minRange;
	}

	public boolean verifyNumber(int chosenNumber) {
		return secretNumber == chosenNumber;
	}

	// Metodo per aggiornare il moltiplicatore dopo un tentativo fallito.
	// La logica è divisa in due fasi:
	// 1. Fino all'80% dei tentativi totali: riduzione lineare da baseMultiplier a
	// 0.
	// 2. Dall'80% all'ultimo tentativo: riduzione esponenziale da 0 a
	// -negativeMultiplierMax.
	public double updateMultiplier() {
		attempts++;
		double f0 = 0.8; // soglia: i primi 80% dei tentativi
		int threshold = (int) Math.ceil(f0 * totalAttempts);

		if (attempts <= threshold) {
			// Riduzione lineare:
			// - Al tentativo 1, multiplier = baseMultiplier.
			// - Al tentativo 'threshold', multiplier = 0.
			if (threshold > 1)
				multiplier = baseMultiplier * (1 - (double) (attempts - 1) / (threshold - 1));
			else
				multiplier = 0;
		} else {
			// Fase esponenziale: da 0 a -negativeMultiplierMax
			int negativePhaseAttempts = totalAttempts - threshold;
			int currentNegativeAttempt = attempts - threshold;
			double x = (double) currentNegativeAttempt / negativePhaseAttempts; // x va da 0 a 1
			double L = 2.0; // parametro per la decrescita esponenziale
			multiplier =(int)( -negativeMultiplierMax * (Math.exp(L * x) - 1) / (Math.exp(L) - 1));
		}

		// Arrotonda il moltiplicatore a due decimali
		multiplier = Math.round(multiplier * 100.0) / 100.0;
		return multiplier;
	}

	public double calculateFinalBudget() {
		Double finalBudget = bet * (1 + multiplier) + player1.getMoney();
		player1.setMoney(Integer.parseInt(finalBudget.toString()));
		return finalBudget < 0 ? 0 : finalBudget;
	}

	// Restituisce il moltiplicatore attuale (con due decimali)
	public double getMultiplier() {
		return Math.round(multiplier * 100.0) / 100.0;
	}

	// Restituisce il moltiplicatore formattato come intero (cifre tonde)
	/*public double getFormattedMultiplier() {
		return Math.round(multiplier);
	}*/

	public int getSecretNumber() {
		return secretNumber;
	}

	public int getRemainingAttempts() {
		return totalAttempts - attempts;
	}

	public void resetGame() {
		this.attempts = 0;
		this.multiplier = baseMultiplier;
	}
}
