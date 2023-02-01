package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class GameUpdater extends Thread {
    private GameServer server;
    private InetAddress playerAddress;
    private DatagramSocket socket;
    private int port;
    private byte[] buf = new byte[4096];
    private boolean running;

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
        running = true;
        System.out.printf("Socket Updater");

        while (running) {
            try {
                buf = server.game.getGameData().getBytes();

                DatagramPacket packetOne = new DatagramPacket(buf, buf.length, playerAddress, port);

                socket.send(packetOne);
                sleep(5000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        socket.close();
    }
}
