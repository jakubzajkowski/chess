package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import java.awt.datatransfer.*;

public class ChessBoard extends JPanel {
    private final int BOARD_SIZE = 8;
    private final int SQUARE_SIZE = 100;
    private Rectangle[][] squares = new Rectangle[BOARD_SIZE][BOARD_SIZE];;
    private List<ChessPawn> pawnList = new ArrayList<>();

    public ChessBoard() {
        this.setPreferredSize(new Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println("Mouse clicked at: (" + x + ", " + y + ")");
            }
        });

        for (int i = 0; i < this.BOARD_SIZE; i++) {
            ChessPawn pawn = new ChessPawn(2, 0);
            pawnList.add(pawn);
        }
        for (int i = 0; i < this.BOARD_SIZE; i++) {
            ChessPawn pawn = new ChessPawn(2, 1);
            pawnList.add(pawn);
        }
        ChessPawn kingWhite = new ChessPawn(1, 0);
        pawnList.add(kingWhite);
        ChessPawn kingBlack = new ChessPawn(1, 1);
        pawnList.add(kingBlack);
        ChessPawn knightWhite = new ChessPawn(3, 0);
        pawnList.add(knightWhite);
        ChessPawn knightBlack = new ChessPawn(3, 1);
        pawnList.add(knightBlack);
        ChessPawn rookWhite = new ChessPawn(5, 0);
        pawnList.add(rookWhite);
        ChessPawn rookBlack = new ChessPawn(5, 1);
        pawnList.add(rookBlack);
        ChessPawn bishopWhite = new ChessPawn(4, 0);
        pawnList.add(bishopWhite);
        ChessPawn bishopBlack = new ChessPawn(4, 1);
        pawnList.add(bishopBlack);
        ChessPawn queenWhite = new ChessPawn(6, 0);
        pawnList.add(queenWhite);
        ChessPawn queenBlack = new ChessPawn(6, 1);
        pawnList.add(queenBlack);
    }
    public void paint(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int x = col * SQUARE_SIZE;
                int y = row * SQUARE_SIZE;

                int pawnPadding = (int) (0.15 * SQUARE_SIZE);
                int figurePadding = (int) (0.1 * SQUARE_SIZE);

                squares[row][col] = new Rectangle(x, y, SQUARE_SIZE, SQUARE_SIZE);

                if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));

                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);

                initialPawnsPosition(pawnList, x, y,col,row,g,pawnPadding,figurePadding,SQUARE_SIZE);
            }
        }
    }
    public static void initialPawnsPosition(List<ChessPawn> pawnList,int x,int y,int col,int row,Graphics g,int pawnPadding,int figurePadding,int SQUARE_SIZE){
        for(ChessPawn pawn : pawnList){
            if (pawn.initPosition[0]==8 && pawn.initPosition[1]==row){
                g.drawImage(pawn.image, x + pawnPadding, y + pawnPadding, SQUARE_SIZE - 2 * pawnPadding, SQUARE_SIZE - 2 * pawnPadding, null);
            }
            if (pawn.initPosition[0]==col && pawn.initPosition[1]==row){
                g.drawImage(pawn.image, x + figurePadding, y + figurePadding, SQUARE_SIZE - 2 * figurePadding, SQUARE_SIZE - 2 * figurePadding, null);
            }
            if (pawn.initPosition[0]==9 && pawn.initPosition[1]==row && (col==6 || col==1)){
                g.drawImage(pawn.image, x + figurePadding, y + figurePadding, SQUARE_SIZE - 2 * figurePadding, SQUARE_SIZE - 2 * figurePadding, null);
            }
            if (pawn.initPosition[0]==10 && pawn.initPosition[1]==row && (col==7 || col==0)){
                g.drawImage(pawn.image, x + figurePadding, y + figurePadding, SQUARE_SIZE - 2 * figurePadding, SQUARE_SIZE - 2 * figurePadding, null);
            }
            if (pawn.initPosition[0]==11 && pawn.initPosition[1]==row && (col==2 || col==5)){
                g.drawImage(pawn.image, x + figurePadding, y + figurePadding, SQUARE_SIZE - 2 * figurePadding, SQUARE_SIZE - 2 * figurePadding, null);
            }
        }
    }
}
