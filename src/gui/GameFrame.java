package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{
    private JPanel controlPanel;
    private JLabel header;

    public GameFrame() {
        init();
    }
    public void changeSize(int m, int n) {
        setSize(m, n);
    }

    private void init() {
        setTitle("Kółko i krzyżyk");
        setSize(400, 400);
        setLayout(new FlowLayout());

        header = new JLabel("Hello");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        add(header);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel);

        GameBoard gb = new GameBoard(this);
        controlPanel.add(gb);

    }
}
