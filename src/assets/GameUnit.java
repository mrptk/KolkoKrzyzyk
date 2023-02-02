package assets;

import javax.swing.*;
import java.awt.*;

class GameUnit extends JButton {
    public final GameBoard board;
    private boolean clicked;
    private boolean cross;
    private final int index;

    GameUnit(GameBoard board, int index) {
        this.board = board;
        this.clicked = false;
        this.index = index;

        setIcon(board.emptyIcon);
        setPreferredSize(new Dimension(20, 20));

        addActionListener(e -> {
            if (!clicked && (board.crossTurn == board.isCross)) {
                board.lastChoice = index;
                board.update();
            }
        });
    }

    public void update(String val, String where) {
        switch (val) {
            case "0":
                if (clicked) {
                    board.setMsg("Desynchronizacja");
                    System.out.println("Desynchronizacja 0 " + index + " " + where);
                }
                break;
            case "1":
                if (!clicked) {
                    clicked = true;
                    cross = true;
                    setIcon(board.crossIcon);
                }
                else if (!cross) {
                    board.setMsg("Desynchronizacja");
                    System.out.println("Desynchronizacja 0 " + index + " " + where);
                }
                break;
            case "2":
                if (!clicked) {
                    clicked = true;
                    cross = false;
                    setIcon(board.circleIcon);
                }
                else if (cross) {
                    board.setMsg("Desynchronizacja");
                    System.out.println("Desynchronizacja 0 " + index + " " + where);
                }
                break;
        }
    }

}
