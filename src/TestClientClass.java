import java.io.IOException;
import java.net.*;

public class TestClientClass {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    private byte[] buf;

    public TestClientClass(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            address = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public String sendEcho(String msg) {
        buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, port);
        String received = "";
        try {
            socket.send(packet);
            if (msg.equals("handshake ok")){
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                received = new String(
                        packet.getData(), 0, packet.getLength());
                System.out.println(received);
                TestClientListenerClass testClientListener = new TestClientListenerClass(4445);
                testClientListener.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return received;
    }

    public void close() {
        socket.close();
    }
}