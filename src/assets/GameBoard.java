package assets;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

class GameBoard extends JPanel {
    public ImageIcon emptyIcon;
    public ImageIcon crossIcon;
    public ImageIcon circleIcon;

    private final GameFrame game;
    public boolean crossTurn;

    public int unitSideDiameter = 20;

    GameBoard(GameFrame game) {
        this.game = game;
        Random rand = new Random();
        this.crossTurn = rand.nextDouble() < 0.5;
        changeTurn();

        initIcons();
        init();
    }

    public void changeTurn() {
        crossTurn = !crossTurn;
        game.changeMsg(crossTurn ? "Kolej X" : "Kolej O");
    }

    private void init() {
        setBackground(Color.darkGray);
        GridLayout layout = new GridLayout(0, unitSideDiameter);

        game.setSize(unitSideDiameter * 20 + 100, unitSideDiameter * 20 + 100);

        setLayout(layout);
        for (int i = 0; i < unitSideDiameter * unitSideDiameter; i++)
            add(new GameUnit(this));

    }

    private void initIcons() {
        emptyIcon = new ImageIcon(Objects.requireNonNull(GameUnit.class.getClassLoader().getResource("empty.jpg")));
        crossIcon = new ImageIcon(Objects.requireNonNull(GameUnit.class.getClassLoader().getResource("cross.jpg")));
        circleIcon = new ImageIcon(Objects.requireNonNull(GameUnit.class.getClassLoader().getResource("circle.jpg")));
    }
}
