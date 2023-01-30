package assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{
    private JPanel header, controlPanel;
    private JLabel msg;

    public GameFrame() {
        init();
    }

    public void changeMsg(String newMsg) {
        msg.setText(newMsg);
    }

    private void init() {
        setTitle("Kółko i krzyżyk");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        header = new JPanel();
        header.setLayout(new FlowLayout());
        add(header);

        msg = new JLabel();
        msg.setHorizontalAlignment(SwingConstants.LEFT);
        msg.setVerticalAlignment(SwingConstants.CENTER);
        header.add(msg);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel);

        GameBoard gb = new GameBoard(this);
        controlPanel.add(gb);
    }
}
