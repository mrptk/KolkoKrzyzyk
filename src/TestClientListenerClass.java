import java.io.IOException;
import java.net.*;

public class TestClientListenerClass extends Thread {
    private DatagramSocket socket;
    private int port;
    private boolean running;

    private byte[] buf = new byte[4096];

    public TestClientListenerClass(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        running = true;
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        String received = "";
        while (running) {
            try {
                socket.receive(packet);
                received = new String(
                        packet.getData(), 0, packet.getLength());
                System.out.println(received);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        socket.close();
    }

}