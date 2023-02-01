package assets;

import javax.swing.*;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.List;

class GameBoard extends JPanel {
    public ImageIcon emptyIcon;
    public ImageIcon crossIcon;
    public ImageIcon circleIcon;
    private int unitsSideDiameter;

    private final GameFrame game;
    private GameUnit[] fields;
    public boolean crossTurn;
    public boolean isCross;
    public int lastChoice;

    private int[] ports;
    private SwingWorker handshaker, updater, listener;


    GameBoard(GameFrame game, int[] ports, int unitsSideDiameter) {
        this.game = game;
        this.ports = ports;
        this.unitsSideDiameter = unitsSideDiameter;
        this.fields = new GameUnit[unitsSideDiameter * unitsSideDiameter];

        initIcons();
        init();
    }

    public void setMsg(String text) {
        game.changeMsg(text);
    }

    private void handshake() {
        buildHandshaker();
        handshaker.execute();
    }

    public void update() {
        buildUpdater();
        updater.execute();
    }
    public void listen() {
        buildListener();
        listener.execute();
    }

    public void synchronize(String[] gameData) {
        crossTurn = Boolean.parseBoolean(gameData[0]);
        game.changeTurn(crossTurn == isCross ? "Teraz ty" : "Teraz przeciwnik");

        int gameDataLen = gameData.length;
        for (int i = 1; i < gameDataLen; i++) fields[i - 1].update(gameData[i]);
    }

    private void init() {
        setBackground(Color.darkGray);
        GridLayout layout = new GridLayout(0, unitsSideDiameter);

        game.setSize(unitsSideDiameter * 20 + 200, unitsSideDiameter * 20 + 200);

        setLayout(layout);
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new GameUnit(this, i);
            add(fields[i]);
        }

        handshake();
        listen();
    }

    private void initIcons() {
        emptyIcon = new ImageIcon(Objects.requireNonNull(GameUnit.class.getClassLoader().getResource("empty.jpg")));
        crossIcon = new ImageIcon(Objects.requireNonNull(GameUnit.class.getClassLoader().getResource("cross.jpg")));
        circleIcon = new ImageIcon(Objects.requireNonNull(GameUnit.class.getClassLoader().getResource("circle.jpg")));
    }

    private void buildHandshaker() {
        handshaker = new SwingWorker<String, String>() {
            @Override
            protected String doInBackground() throws Exception {
                String received = "";
                DatagramSocket socket = new DatagramSocket();
                byte[] buf = new byte[4096];
                buf = ("handshake").getBytes();
                DatagramPacket packet
                        = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), ports[0]);
                try {
                    socket.send(packet);
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    received = new String(
                            packet.getData(), 0, packet.getLength());
                    System.out.println(received);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return received;
            }

            @Override
            protected void done() {
                try {
                    isCross = Boolean.parseBoolean(get());
                    game.changeMsg(isCross ? "Grasz X" : "Grasz O");
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void buildUpdater() {
        updater = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                DatagramSocket socket = new DatagramSocket();
                byte[] buf = new byte[4096];
                buf = ("" + lastChoice + " ").getBytes();
                DatagramPacket packet
                        = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), ports[0]);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }

    private void buildListener() {
        listener = new SwingWorker<String[], String[]>() {
            @Override
            protected String[] doInBackground() throws Exception {
                boolean stop = false;
                byte[] buf = new byte[4096];
                DatagramSocket socket = new DatagramSocket(ports[1]);
                String[] data = null;

                while (!stop) {
                    String received;
                    DatagramPacket packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    received = new String(
                            packet.getData(), 0, packet.getLength());
                    data = received.split(" ");
                    publish(data);
                    if (Boolean.parseBoolean(data[0]) == isCross) stop = true;
                }

                return null;
            }

            @Override
            protected void process(List<String[]> chunks) {
                String[] val = chunks.get(chunks.size() - 1);
                synchronize(val);
            }
        };
    }
}
