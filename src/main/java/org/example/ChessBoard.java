package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class ChessBoard extends JPanel {
    private final int BOARD_SIZE = 8;
    private final int SQUARE_SIZE = 100;
    private ChessPawnPosition[][] squares = new ChessPawnPosition[BOARD_SIZE][BOARD_SIZE];;
    private final int INIT_BLACK_ROW=0;
    private final int INIT_WHITE_ROW=7;
    private int mouseX = -1;
    private int mouseY = -1;
    private static boolean isSelectedPawn=false;
    private static ChessPawnPosition SelectedChessPawnPosition;

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
            ChessPawn pawn = new ChessPawn(2, 0);
            this.squares[1][i] = new ChessPawnPosition(pawn,1,i);
        }
        for (int i = 0; i < this.BOARD_SIZE; i++) {
            ChessPawn pawn = new ChessPawn(2, 1);
            this.squares[6][i] = new ChessPawnPosition(pawn,6,i);
        }
        ChessPawn kingWhite = new ChessPawn(1, 1);
        this.squares[INIT_WHITE_ROW][4] = new ChessPawnPosition(kingWhite,INIT_WHITE_ROW,4);

        ChessPawn kingBlack = new ChessPawn(1, 0);
        this.squares[INIT_BLACK_ROW][4] = new ChessPawnPosition(kingBlack,INIT_BLACK_ROW,4);

        ChessPawn knightWhite = new ChessPawn(3, 0);
        this.squares[INIT_BLACK_ROW][1] = new ChessPawnPosition(knightWhite,INIT_BLACK_ROW,1);

        ChessPawn knightBlack = new ChessPawn(3, 1);
        this.squares[INIT_WHITE_ROW][1] = new ChessPawnPosition(knightBlack,INIT_WHITE_ROW,1);

        ChessPawn knightSecWhite = new ChessPawn(3, 0);
        this.squares[INIT_BLACK_ROW][6] = new ChessPawnPosition(knightSecWhite,INIT_BLACK_ROW,6);

        ChessPawn knightSecBlack = new ChessPawn(3, 1);
        this.squares[INIT_WHITE_ROW][6] = new ChessPawnPosition(knightSecBlack,INIT_WHITE_ROW,6);

        ChessPawn rookWhite = new ChessPawn(5, 0);
        this.squares[INIT_BLACK_ROW][0] = new ChessPawnPosition(rookWhite,INIT_BLACK_ROW,0);

        ChessPawn rookBlack = new ChessPawn(5, 1);
        this.squares[INIT_WHITE_ROW][0] = new ChessPawnPosition(rookBlack,INIT_WHITE_ROW,0);

        ChessPawn rookSecWhite = new ChessPawn(5, 0);
        this.squares[INIT_BLACK_ROW][7] = new ChessPawnPosition(rookSecWhite,INIT_BLACK_ROW,7);

        ChessPawn rookSecBlack = new ChessPawn(5, 1);
        this.squares[INIT_WHITE_ROW][7] = new ChessPawnPosition(rookSecBlack,INIT_WHITE_ROW,7);

        ChessPawn bishopWhite = new ChessPawn(4, 0);
        this.squares[INIT_BLACK_ROW][2] = new ChessPawnPosition(bishopWhite,INIT_BLACK_ROW,2);

        ChessPawn bishopBlack = new ChessPawn(4, 1);
        this.squares[INIT_WHITE_ROW][2] = new ChessPawnPosition(bishopBlack,INIT_WHITE_ROW,2);

        ChessPawn bishopSecWhite = new ChessPawn(4, 0);
        this.squares[INIT_BLACK_ROW][5] = new ChessPawnPosition(bishopSecWhite,INIT_BLACK_ROW,5);

        ChessPawn bishopSecBlack = new ChessPawn(4, 1);
        this.squares[INIT_WHITE_ROW][5] = new ChessPawnPosition(bishopSecBlack,INIT_WHITE_ROW,5);

        ChessPawn queenWhite = new ChessPawn(6, 0);
        this.squares[INIT_BLACK_ROW][3] = new ChessPawnPosition(queenWhite,INIT_BLACK_ROW,3);

        ChessPawn queenBlack = new ChessPawn(6, 1);
        this.squares[INIT_WHITE_ROW][3] = new ChessPawnPosition(queenBlack,INIT_WHITE_ROW,3);

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
                    g.drawImage(squares[row][col].pawn.image, x + pawnPadding, y + pawnPadding, SQUARE_SIZE - 2 * pawnPadding, SQUARE_SIZE - 2 * pawnPadding, null);
                }
            }
        }


        if (mouseX != -1 && mouseY != -1) {
            movePawn(g,mouseY,mouseX,squares,SQUARE_SIZE);
        }
    }
    public void movePawn(Graphics g, int mouseY, int mouseX, ChessPawnPosition[][] squares, int SQUARE_SIZE){
        int hoverPadding = (int) (0.05 * SQUARE_SIZE);
        int pawnPadding = (int) (0.1 * SQUARE_SIZE);

        int row = (int) Math.floor((double) mouseY /100);
        int col = (int) Math.floor((double) mouseX /100);


        if (squares[row][col]!=null){
            if (!isSelectedPawn){
                if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));
                g.fillRect((col*100), (row*100), SQUARE_SIZE, SQUARE_SIZE);
                g.drawImage(squares[row][col].pawn.image, (col*100) + hoverPadding, (row*100) + hoverPadding, SQUARE_SIZE - 2 * hoverPadding, SQUARE_SIZE - 2 * hoverPadding, null);
                isSelectedPawn=true;
                SelectedChessPawnPosition=squares[row][col];
            }
        }else {
            if (isSelectedPawn) {

                if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));
                g.fillRect((col * 100), (row * 100), SQUARE_SIZE, SQUARE_SIZE);
                g.drawImage(SelectedChessPawnPosition.pawn.image, (col * 100) + pawnPadding, (row * 100) + pawnPadding, SQUARE_SIZE - 2 * pawnPadding, SQUARE_SIZE - 2 * pawnPadding, null);


                squares[row][col]=SelectedChessPawnPosition;

                squares[SelectedChessPawnPosition.y][SelectedChessPawnPosition.x]=null;
                SelectedChessPawnPosition.x=col;
                SelectedChessPawnPosition.y=row;
                isSelectedPawn = false;
            }
        }
    }
}
