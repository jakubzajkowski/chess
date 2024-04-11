package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard extends JPanel {
    private final int BOARD_SIZE = 8;
    private final int SQUARE_SIZE = 100;
    private Rectangle[][] squares = new Rectangle[BOARD_SIZE][BOARD_SIZE];;

    private List<ChessPawn> pawnList = new ArrayList<>();

    public ChessBoard() {
        this.setPreferredSize(new Dimension(BOARD_SIZE*SQUARE_SIZE,BOARD_SIZE*SQUARE_SIZE));
        for (int i=0;i<this.BOARD_SIZE;i++){
            ChessPawn pawn = new ChessPawn(2,0);
            pawnList.add(pawn);
        }
        for (int i=0;i<this.BOARD_SIZE;i++){
            ChessPawn pawn = new ChessPawn(2,1);
            pawnList.add(pawn);
        }
    }
    public void paint(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int x = col * SQUARE_SIZE;
                int y = row * SQUARE_SIZE;

                int padding = (int) (0.1 * SQUARE_SIZE);

                squares[row][col] = new Rectangle(x, y, SQUARE_SIZE, SQUARE_SIZE);

                if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));

                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                if (row==1){
                    g.drawImage(this.pawnList.get(row).image, x + padding, y + padding, SQUARE_SIZE - 2 * padding, SQUARE_SIZE - 2 * padding, null);
                }
                if (row==6){
                    g.drawImage(this.pawnList.get(row+7).image, x + padding, y + padding, SQUARE_SIZE - 2 * padding, SQUARE_SIZE - 2 * padding, null);
                }
            }
        }
    }
}
