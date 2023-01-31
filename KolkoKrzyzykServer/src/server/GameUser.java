package server;

import java.net.InetAddress;

class GameUser {
    private InetAddress address;
    private int port;

    public GameUser(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
