package assets;

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

    public void changeMsg(String newMsg) {
        header.setText(newMsg);
    }

    private void init() {
        setTitle("Kółko i krzyżyk");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        header = new JLabel();
        add(header);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel);

        GameBoard gb = new GameBoard(this);
        controlPanel.add(gb);
    }
}
