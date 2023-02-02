package server;

import assets.Game;


public class GameServer {
    private final GameListener gameListenerOne;
    private final GameListener gameListenerTwo;
    private int activeListeners;

    public int[] ports = new int[]{4443, 4444, 4445, 4446};
    public Game game;

    public GameServer() {
        this.game = new Game(20);
        this.gameListenerOne = new GameListener(this, ports[0], true);
        this.gameListenerTwo = new GameListener(this, ports[1], false);
        this.activeListeners = 0;
    }

    public void start() {
        gameListenerOne.start();
        gameListenerTwo.start();
    }

    public synchronized void startUpdater() {
        activeListeners++;

        if (activeListeners >= 2) {
            GameUpdater gameUpdaterOne = new GameUpdater(this, ports[2], gameListenerOne.getUser().getAddress());
            GameUpdater gameUpdaterTwo = new GameUpdater(this, ports[3], gameListenerTwo.getUser().getAddress());
            gameUpdaterOne.start();
            gameUpdaterTwo.start();
        }
    }

}
