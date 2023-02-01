package assets;

import java.util.Random;

public class Game {
    private final int size;
    private boolean crossTurn;
    private String[] gameData;

    public Game(int size) {
        this.size = size;
        this.gameData = new String[size * size];

        Random rand = new Random();
        this.crossTurn = rand.nextDouble() < 0.5;
        for (int i = 0; i < gameData.length; i++) gameData[i] = "0";
    }

    public synchronized void updateGame(String newVal, boolean isCross) {
        if (isCross == crossTurn) gameData[Integer.parseInt(newVal.trim())] = isCross ? "1" : "2";
        crossTurn = !crossTurn;
    }

    public synchronized String getGameData() {
        String gameData = String.valueOf(this.crossTurn);
            for (String s : this.gameData) gameData += " " + s;

        return gameData;
    }

}
