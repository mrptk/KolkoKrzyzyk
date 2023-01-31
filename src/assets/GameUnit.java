package assets;

import javax.swing.*;
import java.awt.*;

class GameUnit extends JButton {
    public final GameBoard board;
    private boolean clicked;
    private boolean cross;

    GameUnit(GameBoard board) {
        this.board = board;
        this.clicked = false;

        setIcon(board.emptyIcon);
        setPreferredSize(new Dimension(20, 20));

        addActionListener(e -> {
            if (!clicked) {
                clicked = true;
                setIcon(board.crossTurn ? board.crossIcon : board.circleIcon);
                cross = board.crossTurn;

                board.changeTurn();
            }
        });
    }

}
