package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

class GameBoard extends JPanel {
    private GameFrame game;

    GameBoard(GameFrame game) {
        this.game = game;


        setBackground(Color.darkGray);
        GridLayout layout = new GridLayout(0,15);


        game.setSize(15 * 20 + 100, 15 * 20 + 100);


        setLayout(layout);
        for (int i = 0; i < 15 * 15; i++)
            add(new GameUnit(this));

    }
}
