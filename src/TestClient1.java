import assets.GameFrame;

import java.awt.*;

public class TestClient1 {

    public static void main(String[] args) {
        int[] ports = new int[]{4443, 4445};
        int size = 20;

        EventQueue.invokeLater(() -> {
            GameFrame gf = new GameFrame(ports, size);
            gf.setVisible(true);
        });
    }
}
