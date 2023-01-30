import assets.GameFrame;

import java.awt.*;

public class Test {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            GameFrame gf = new GameFrame();
            gf.setVisible(true);
        });
    }
}
