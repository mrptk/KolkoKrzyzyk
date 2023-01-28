package gui;

import javax.swing.*;
import java.awt.*;

public class GameUnit extends JButton {
    private boolean clicked = false;
    private final GameBoard board;


    GameUnit(GameBoard board) {
        super(new ImageIcon(GameUnit.class.getClassLoader().getResource("empty.jpg")));

        this.board = board;

        setPreferredSize(new Dimension(20, 20));

    }
}
