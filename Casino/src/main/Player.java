package main;


public class Player implements Entity {
    
    private Integer money;
    private String username;
    private Integer gamesPlayed;
    private Integer gamesWon;
    private Integer gamesLost;
    private Integer highestEarning;
    private Boolean moneyIsZero;

    public Player(String username, Integer startingMoney, Integer gamesPlayed, Integer gamesWon, Integer gamesLost, Integer highestEarning) {
        this.username = username;
        this.money = startingMoney;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.highestEarning = highestEarning;
        this.moneyIsZero = (startingMoney <= 0);
    }

    // 🔹 **Implementing Entity Methods**
    @Override
    public String getName() { return username; }

    @Override
    public Integer getGamesPlayed() { return gamesPlayed; }

    @Override
    public Integer getGamesWon() { return gamesWon; }

    @Override
    public Integer getGamesLost() { return gamesLost; }

    @Override
    public void incrementGamesPlayed() { this.gamesPlayed++; }

    @Override
    public void incrementGamesWon() { this.gamesWon++; }

    @Override
    public void incrementGamesLost() { this.gamesLost++; }

    // 🔹 **Player-Specific Methods**
    public Integer getMoney() { return money; }

    public Integer getHighestEarning() { return highestEarning; }

    public Boolean isMoneyZero() { return moneyIsZero; }

    public void setMoney(Integer money) {
        this.money = money;
        this.moneyIsZero = (money <= 0);
    }

    public void addMoney(Integer amount) {
        this.money += amount;
        if (money > highestEarning) {
            highestEarning = money;
        }
        this.moneyIsZero = (money <= 0);
    }

    public void subtractMoney(Integer amount) {
        this.money -= amount;
        this.moneyIsZero = (money <= 0);
    }

    @Override
    public String toString() {
        return username + " | Money: $" + money + " | Wins: " + gamesWon + " | Losses: " + gamesLost;
    }
}
