import assets.GameFrame;

import java.awt.*;

public class TestClient2 {

    public static void main(String[] args) {
        int[] ports = new int[]{4444, 4446};
        int size = 20;

        EventQueue.invokeLater(() -> {
            GameFrame gf = new GameFrame(ports, size);
            gf.setVisible(true);
        });
    }
}
