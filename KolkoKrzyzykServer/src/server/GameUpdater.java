package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class GameUpdater extends Thread {
    private final GameServer server;
    private final InetAddress playerAddress;
    private DatagramSocket socket;
    private final int port;

    public GameUpdater(GameServer server, int port, InetAddress playerAddresses){
        this.server = server;
        this.playerAddress = playerAddresses;
        this.port = port;
        try {
            this.socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Socket Updater");
        byte[] buf = new byte[4096];

        while (true) {
            try {
                buf = server.game.updateAndGetGame("none", false).getBytes();

                DatagramPacket packetOne = new DatagramPacket(buf, buf.length, playerAddress, port);

                socket.send(packetOne);
                System.out.println("Updating " + port);
                sleep(100);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
