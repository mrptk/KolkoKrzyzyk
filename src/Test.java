import gui.GameFrame;

import java.awt.*;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameFrame gf = new GameFrame();
                gf.setVisible(true);
            }
        });
    }
}
