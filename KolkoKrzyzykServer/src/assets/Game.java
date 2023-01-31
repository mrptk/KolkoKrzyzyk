package assets;

import java.util.Random;

public class Game {
    private int size;
    private boolean crossTurn;
    private String[] gameData;

    public Game(int size) {
        this.size = size;
        this.gameData = new String[size * size];

        Random rand = new Random();
        this.crossTurn = rand.nextDouble() < 0.5;
        for (int i = 0; i < gameData.length; i++) gameData[i] = "0";
    }

    public boolean isCrossTurn() {
        return crossTurn;
    }

    public synchronized void updateGame(String[] gameData) {
        this.gameData = gameData;
        crossTurn = !crossTurn;
    }

    public String getGameData() {
        String gameData = String.valueOf(this.crossTurn);

        synchronized (this.gameData) {
            for (String s : this.gameData) gameData += " " + s;
        }

        return gameData;
    }

}
