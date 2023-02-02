package assets;

import java.util.Arrays;
import java.util.Random;

public class Game {
    private final int size;
    private volatile boolean crossTurn;
    private final String[] gameData;
    private boolean finished;
    private boolean crossWon;

    public Game(int size) {
        this.size = size;
        this.gameData = new String[size * size];

        Random rand = new Random();
        this.crossTurn = rand.nextDouble() < 0.5;
        Arrays.fill(gameData, "0");
    }

    public synchronized String updateAndGetGame(String newVal, boolean isCross) {

        if (isCross == crossTurn && !newVal.equals("none")) {
            int index = Integer.parseInt(newVal.trim());
            gameData[index] = isCross ? "1" : "2";
            checkIfFinished(index);
            if (finished) crossWon = isCross;
            crossTurn = !crossTurn;
        }
        if (newVal.equals("none")) {
            StringBuilder gameDataStr = new StringBuilder();
            if (finished) {
                gameDataStr.append("finished ").append(crossWon).append(" ").append(this.crossTurn);
            } else {
                gameDataStr.append(this.crossTurn);
            }
            for (String s : gameData) gameDataStr.append(" ").append(s);
            return gameDataStr.toString();
        }
        return null;
    }

    public boolean isFinished() {
        return finished;
    }

    private void checkIfFinished(int index) {
        String checkVal = gameData[index];
        int sqrSize = size * size;

        finished = checkVertically(index, checkVal, sqrSize);
        if (!finished) finished = checkHorizontally(index, checkVal);
        if (!finished) finished = checkBackwardDiagonally(index, checkVal, sqrSize);
        if (!finished) finished = checkForwardDiagonally(index, checkVal, sqrSize);
    }

    private boolean checkVertically(int index, String checkVal, int sqrSize) {
        int checkpoint = index;
        int counter = 1;

        do {
            checkpoint = checkpoint - size;
            if (checkpoint >= 0) {
                if (gameData[checkpoint].equals(checkVal)) counter++;
                else checkpoint = index;
            } else checkpoint = index;

        } while (checkpoint != index);

        if (counter < 5) {
            do {
                checkpoint = checkpoint + size;
                if (checkpoint < sqrSize) {
                    if (gameData[checkpoint].equals(checkVal)) counter++;
                    else checkpoint = index;
                } else checkpoint = index;

            } while (checkpoint != index);
        }

        return counter > 4;
    }

    private boolean checkHorizontally(int index, String checkVal) {
        int checkpoint = index;
        int counter = 1;
        int boundary = index - index % size;

        do {
            checkpoint = checkpoint - 1;
            if (checkpoint >= boundary) {
                if (gameData[checkpoint].equals(checkVal)) counter++;
                else checkpoint = index;
            } else checkpoint = index;

        } while (checkpoint != index);

        boundary += size;
        if (counter < 5) {
            do {
                checkpoint = checkpoint + 1;
                if (checkpoint < boundary) {
                    if (gameData[checkpoint].equals(checkVal)) counter++;
                    else checkpoint = index;
                } else checkpoint = index;

            } while (checkpoint != index);
        }

        return counter > 4;
    }

    private boolean checkBackwardDiagonally(int index, String checkVal, int sqrSize) {
        int checkpoint = index;
        int boundary;
        int counter = 1;

        do {
            checkpoint = checkpoint - size - 1;
            boundary = checkpoint - checkpoint % size;
            if (checkpoint >= 0 && checkpoint >= boundary) {
                if (gameData[checkpoint].equals(checkVal)) counter++;
                else checkpoint = index;
            } else checkpoint = index;

        } while (checkpoint != index);

        if (counter < 5) {
            do {
                checkpoint = checkpoint + size + 1;
                boundary = checkpoint - checkpoint % size + size;
                if (checkpoint < sqrSize && checkpoint < boundary) {
                    if (gameData[checkpoint].equals(checkVal)) counter++;
                    else checkpoint = index;
                } else checkpoint = index;

            } while (checkpoint != index);
        }

        return counter > 4;
    }

    private boolean checkForwardDiagonally(int index, String checkVal, int sqrSize) {
        int checkpoint = index;
        int boundary;
        int counter = 1;

        do {
            checkpoint = checkpoint - size + 1;
            boundary = checkpoint - checkpoint % size + size;
            if (checkpoint >= 0 && checkpoint < boundary) {
                if (gameData[checkpoint].equals(checkVal)) counter++;
                else checkpoint = index;
            } else checkpoint = index;

        } while (checkpoint != index);

        if (counter < 5) {
            do {
                checkpoint = checkpoint + size - 1;
                boundary = checkpoint - checkpoint % size;
                if (checkpoint < sqrSize && checkpoint >= boundary) {
                    if (gameData[checkpoint].equals(checkVal)) counter++;
                    else checkpoint = index;
                } else checkpoint = index;

            } while (checkpoint != index);
        }

        return counter > 4;
    }

}
