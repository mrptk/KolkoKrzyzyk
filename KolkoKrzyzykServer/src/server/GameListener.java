package server;

import assets.Game;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

class GameListener extends Thread {
    private final GameServer gameServer;
    private final Game game;
    private GameUser player;
    private DatagramSocket socket;
    private byte[] buf = new byte[4096];
    private int port;
    private boolean running;
    private boolean isCross;

    public GameListener(GameServer gameServer, int port, boolean isCross) {
        this.gameServer = gameServer;
        this.game = gameServer.game;
        this.port = port;
        this.isCross = isCross;
        try {
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        running = true;
        System.out.printf("Socket %d%n", port);

        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());

                if (received.equals("handshake")) {
                    player = new GameUser(packet.getAddress(), packet.getPort());
                    respond("" + isCross);
                    gameServer.startUpdater();
                } else game.updateGame(received, isCross);

                buf = new byte[4096];

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }

    public GameUser getUser() {
        return player;
    }

    private void respond(String response) throws IOException {
        buf = response.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, player.getAddress(), player.getPort());
        socket.send(packet);
    }
}
