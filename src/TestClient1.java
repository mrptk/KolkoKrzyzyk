import assets.GameFrame;

import java.awt.*;

public class TestClient1 {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            GameFrame gf = new GameFrame();
            gf.setVisible(true);
        });
    }
}
