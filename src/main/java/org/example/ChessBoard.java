package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessBoard extends JPanel {
    private final int BOARD_SIZE = 8;
    private final int SQUARE_SIZE = 100;
    private final ChessPawn[][] squares = new ChessPawn[BOARD_SIZE][BOARD_SIZE];;
    private int mouseX = -1;
    private int mouseY = -1;
    private boolean isSelectedPawn=false;
    private ChessPawn selectedChessPawn;
    private int selectedChessPawnX;
    private int selectedChessPawnY;

    public ChessBoard() {
        this.setPreferredSize(new Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                repaint();
            }
        });

        for (int i = 0; i < this.BOARD_SIZE; i++) {
            ChessPawn pawn = new ChessPawn(2, 0,1,i);
            this.squares[1][i] = pawn;
        }
        for (int i = 0; i < this.BOARD_SIZE; i++) {
            ChessPawn pawn = new ChessPawn(2, 1,6,i);
            this.squares[6][i] = pawn;
        }
        int INIT_WHITE_ROW = 7;
        ChessPawn kingWhite = new ChessPawn(1, 1, INIT_WHITE_ROW,4);
        this.squares[INIT_WHITE_ROW][4] = kingWhite;

        int INIT_BLACK_ROW = 0;
        ChessPawn kingBlack = new ChessPawn(1, 0, INIT_BLACK_ROW,4);
        this.squares[INIT_BLACK_ROW][4] = kingBlack;

        ChessPawn knightWhite = new ChessPawn(3, 0, INIT_BLACK_ROW,1);
        this.squares[INIT_BLACK_ROW][1] = knightWhite;

        ChessPawn knightBlack = new ChessPawn(3, 1, INIT_WHITE_ROW,1);
        this.squares[INIT_WHITE_ROW][1] = knightBlack;

        ChessPawn knightSecWhite = new ChessPawn(3, 0, INIT_BLACK_ROW,6);
        this.squares[INIT_BLACK_ROW][6] = knightSecWhite;

        ChessPawn knightSecBlack = new ChessPawn(3, 1, INIT_WHITE_ROW,6);
        this.squares[INIT_WHITE_ROW][6] = knightSecBlack;

        ChessPawn rookWhite = new ChessPawn(5, 0, INIT_BLACK_ROW,0);
        this.squares[INIT_BLACK_ROW][0] = rookWhite;

        ChessPawn rookBlack = new ChessPawn(5, 1, INIT_WHITE_ROW,0);
        this.squares[INIT_WHITE_ROW][0] = rookBlack;

        ChessPawn rookSecWhite = new ChessPawn(5, 0, INIT_BLACK_ROW,7);
        this.squares[INIT_BLACK_ROW][7] = rookSecWhite;

        ChessPawn rookSecBlack = new ChessPawn(5, 1, INIT_WHITE_ROW,7);
        this.squares[INIT_WHITE_ROW][7] = rookSecBlack;

        ChessPawn bishopWhite = new ChessPawn(4, 0, INIT_BLACK_ROW,2);
        this.squares[INIT_BLACK_ROW][2] = bishopWhite;

        ChessPawn bishopBlack = new ChessPawn(4, 1, INIT_WHITE_ROW,2);
        this.squares[INIT_WHITE_ROW][2] = bishopBlack;

        ChessPawn bishopSecWhite = new ChessPawn(4, 0, INIT_BLACK_ROW,5);
        this.squares[INIT_BLACK_ROW][5] = bishopSecWhite;

        ChessPawn bishopSecBlack = new ChessPawn(4, 1, INIT_WHITE_ROW,5);
        this.squares[INIT_WHITE_ROW][5] = bishopSecBlack;

        ChessPawn queenWhite = new ChessPawn(6, 0, INIT_BLACK_ROW,3);
        this.squares[INIT_BLACK_ROW][3] = queenWhite;

        ChessPawn queenBlack = new ChessPawn(6, 1, INIT_WHITE_ROW,3);
        this.squares[INIT_WHITE_ROW][3] = queenBlack;

    }
    @Override
    public void paint(Graphics g) {
        int pawnPadding = (int) (0.1 * SQUARE_SIZE);

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int x = col * SQUARE_SIZE;
                int y = row * SQUARE_SIZE;


                if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));

                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);

                if (squares[row][col]!=null){
                    g.drawImage(squares[row][col].image, x + pawnPadding, y + pawnPadding, SQUARE_SIZE - 2 * pawnPadding, SQUARE_SIZE - 2 * pawnPadding, null);
                }
            }
        }


        if (mouseX != -1 && mouseY != -1) {
            movePawn(g,mouseY,mouseX,squares,SQUARE_SIZE);
        }
    }
    public void movePawn(Graphics g, int mouseY, int mouseX, ChessPawn[][] squares, int SQUARE_SIZE){
        int hoverPadding = (int) (0.05 * SQUARE_SIZE);
        int pawnPadding = (int) (0.1 * SQUARE_SIZE);

        int row = (int) Math.floor((double) mouseY /100);
        int col = (int) Math.floor((double) mouseX /100);


        if (squares[row][col]!=null){
            if (!isSelectedPawn){
                if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));
                g.fillRect((col*100), (row*100), SQUARE_SIZE, SQUARE_SIZE);
                g.drawImage(squares[row][col].image, (col*100) + hoverPadding, (row*100) + hoverPadding, SQUARE_SIZE - 2 * hoverPadding, SQUARE_SIZE - 2 * hoverPadding, null);
                isSelectedPawn=true;
                selectedChessPawn = squares[row][col];
                selectedChessPawnX = col;
                selectedChessPawnY = row;
            }
        }else {
            if (isSelectedPawn) {

                if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));
                g.fillRect((col * 100), (row * 100), SQUARE_SIZE, SQUARE_SIZE);
                g.drawImage(selectedChessPawn.image, (col * 100) + pawnPadding, (row * 100) + pawnPadding, SQUARE_SIZE - 2 * pawnPadding, SQUARE_SIZE - 2 * pawnPadding, null);

                squares[row][col]= selectedChessPawn;
                squares[selectedChessPawnY][selectedChessPawnX]=null;
                selectedChessPawn.x = col;
                selectedChessPawn.y = row;

                if ((selectedChessPawnY + selectedChessPawnX) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));
                g.fillRect((selectedChessPawnX * 100), (selectedChessPawnY * 100), SQUARE_SIZE, SQUARE_SIZE);

                selectedChessPawn = null;
                isSelectedPawn = false;
            }
        }
    }
}
