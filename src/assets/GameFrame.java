package assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame{
    private JPanel header, controlPanel;
    private JLabel msg;
    private JLabel turn;

    public GameFrame(int[] ports, int size) {
        init(ports, size);
    }

    public void changeMsg(String newMsg) {
        msg.setText(newMsg);
    }
    public void changeTurn(String newTurn) {
        turn.setText(newTurn);
    }

    private void init(int[] ports, int size) {
        setTitle("Kółko i krzyżyk");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        add(header);

        msg = new JLabel();
        msg.setHorizontalAlignment(SwingConstants.LEFT);
        msg.setVerticalAlignment(SwingConstants.CENTER);
        header.add(msg);

        turn = new JLabel();
        turn.setHorizontalAlignment(SwingConstants.LEFT);
        turn.setVerticalAlignment(SwingConstants.CENTER);
        header.add(turn);

        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        add(controlPanel);

        final GameBoard gb = new GameBoard(this, ports, size);
        controlPanel.add(gb);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
    }
}
