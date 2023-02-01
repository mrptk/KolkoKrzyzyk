package assets;

import javax.swing.*;
import java.awt.*;

class GameUnit extends JButton {
    public final GameBoard board;
    private boolean clicked;
    private boolean cross;
    private int index;

    GameUnit(GameBoard board, int index) {
        this.board = board;
        this.clicked = false;
        this.index = index;

        setIcon(board.emptyIcon);
        setPreferredSize(new Dimension(20, 20));

        addActionListener(e -> {
            if (!clicked && (board.crossTurn == board.isCross)) {
                update(board.isCross ? "1" : "2");
                board.lastChoice = index;
                board.crossTurn = !board.crossTurn;
                board.update();
                board.listen();
            }
        });
    }

    public void update(String val) {
        switch (val) {
            case "0":
                if (clicked) board.setMsg("Desynchronizacja");
                break;
            case "1":
                if (!clicked) {
                    clicked = true;
                    cross = true;
                    setIcon(board.crossIcon);
                }
                else if (!cross) board.setMsg("Desynchronizacja");
            case "2":
                if (!clicked) {
                    clicked = true;
                    cross = false;
                    setIcon(board.circleIcon);
                }
                else if (cross) board.setMsg("Desynchronizacja");
        }
    }

}
