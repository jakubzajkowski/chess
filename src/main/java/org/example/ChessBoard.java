package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard extends JPanel {
    private final int BOARD_SIZE = 8;
    private final int SQUARE_SIZE = 100;

    private List<ChessPawn> pawnList = new ArrayList<>();

    public ChessBoard() {
        this.setPreferredSize(new Dimension(BOARD_SIZE*SQUARE_SIZE,BOARD_SIZE*SQUARE_SIZE));
        ChessPawn pawn = new ChessPawn(1);
        pawnList.add(pawn);
    }
    public void paint(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int x = col * SQUARE_SIZE;
                int y = row * SQUARE_SIZE;

                if ((row + col) % 2 == 0) g.setColor(Color.GRAY);
                else g.setColor(Color.RED);

                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g.drawImage(this.pawnList.get(0).image, x, y, SQUARE_SIZE, SQUARE_SIZE, null);
            }
        }
    }
}
