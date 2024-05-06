package org.example;

import org.example.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessBoard extends JPanel {
    private final int BOARD_SIZE = 8;
    private final int SQUARE_SIZE = 100;
    private final ChessPiece[][] squares = new ChessPiece[BOARD_SIZE][BOARD_SIZE];;
    private int mouseX = -1;
    private int mouseY = -1;
    private boolean isSelectedPawn=false;
    private ChessPiece selectedChessPawn;
    private int selectedChessPawnX;
    private int selectedChessPawnY;
    private boolean move=true;

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
        int INIT_WHITE_ROW = 7;
        int INIT_BLACK_ROW = 0;
        initializePieces(INIT_BLACK_ROW,INIT_WHITE_ROW);
    }
    private void initializePieces(int INIT_BLACK_ROW,int INIT_WHITE_ROW){
        placeRooks(INIT_BLACK_ROW,INIT_WHITE_ROW);
        placeKings(INIT_BLACK_ROW,INIT_WHITE_ROW);
        placeKnight(INIT_BLACK_ROW,INIT_WHITE_ROW);
        placeQueens(INIT_BLACK_ROW,INIT_WHITE_ROW);
        placePawns();
        placeBishops(INIT_BLACK_ROW,INIT_WHITE_ROW);
    }
    private void placePawns(){
        for (int i = 0; i < this.BOARD_SIZE; i++) {
            ChessPiece pawn = new PawnPiece(2, 0,1,i);
            this.squares[1][i] = pawn;
        }
        for (int i = 0; i < this.BOARD_SIZE; i++) {
            ChessPiece pawn = new PawnPiece(2, 1,6,i);
            this.squares[6][i] = pawn;
        }
    }
    private void placeQueens(int INIT_BLACK_ROW,int INIT_WHITE_ROW){
        ChessPiece queenWhite = new QueenPiece(6, 0, INIT_BLACK_ROW,3);
        this.squares[INIT_BLACK_ROW][3] = queenWhite;

        ChessPiece queenBlack = new QueenPiece(6, 1, INIT_WHITE_ROW,3);
        this.squares[INIT_WHITE_ROW][3] = queenBlack;
    }
    private void placeBishops(int INIT_BLACK_ROW,int INIT_WHITE_ROW){
        ChessPiece bishopWhite = new BishopPiece(4, 0, INIT_BLACK_ROW,2);
        this.squares[INIT_BLACK_ROW][2] = bishopWhite;

        ChessPiece bishopBlack = new BishopPiece(4, 1, INIT_WHITE_ROW,2);
        this.squares[INIT_WHITE_ROW][2] = bishopBlack;

        ChessPiece bishopSecWhite = new BishopPiece(4, 0, INIT_BLACK_ROW,5);
        this.squares[INIT_BLACK_ROW][5] = bishopSecWhite;

        ChessPiece bishopSecBlack = new BishopPiece(4, 1, INIT_WHITE_ROW,5);
        this.squares[INIT_WHITE_ROW][5] = bishopSecBlack;
    }
    private void placeRooks(int INIT_BLACK_ROW,int INIT_WHITE_ROW){
        ChessPiece rookWhite = new RookPiece(5, 0, INIT_BLACK_ROW,0);
        this.squares[INIT_BLACK_ROW][0] = rookWhite;

        ChessPiece rookBlack = new RookPiece(5, 1, INIT_WHITE_ROW,0);
        this.squares[INIT_WHITE_ROW][0] = rookBlack;

        ChessPiece rookSecWhite = new RookPiece(5, 0, INIT_BLACK_ROW,7);
        this.squares[INIT_BLACK_ROW][7] = rookSecWhite;

        ChessPiece rookSecBlack = new RookPiece(5, 1, INIT_WHITE_ROW,7);
        this.squares[INIT_WHITE_ROW][7] = rookSecBlack;
    }
    private void placeKnight(int INIT_BLACK_ROW,int INIT_WHITE_ROW){
        ChessPiece knightWhite = new KnightPiece(3, 0, INIT_BLACK_ROW,1);
        this.squares[INIT_BLACK_ROW][1] = knightWhite;

        ChessPiece knightBlack = new KingPiece(3, 1, INIT_WHITE_ROW,1);
        this.squares[INIT_WHITE_ROW][1] = knightBlack;

        ChessPiece knightSecWhite = new KnightPiece(3, 0, INIT_BLACK_ROW,6);
        this.squares[INIT_BLACK_ROW][6] = knightSecWhite;

        ChessPiece knightSecBlack = new KnightPiece(3, 1, INIT_WHITE_ROW,6);
        this.squares[INIT_WHITE_ROW][6] = knightSecBlack;
    }
    private void placeKings(int INIT_BLACK_ROW,int INIT_WHITE_ROW){
        ChessPiece kingWhite = new KingPiece(1, 1, INIT_WHITE_ROW,4);
        this.squares[INIT_WHITE_ROW][4] = kingWhite;

        ChessPiece kingBlack = new KingPiece(1, 0, INIT_BLACK_ROW,4);
        this.squares[INIT_BLACK_ROW][4] = kingBlack;
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
    public void movePawn(Graphics g, int mouseY, int mouseX, ChessPiece[][] squares, int SQUARE_SIZE){
        int hoverPadding = (int) (0.05 * SQUARE_SIZE);
        int pawnPadding = (int) (0.1 * SQUARE_SIZE);

        int row = (int) Math.floor((double) mouseY /100);
        int col = (int) Math.floor((double) mouseX /100);


        if (!isSelectedPawn){
            selectPawn(g,squares,row,col,SQUARE_SIZE,hoverPadding);
        }
        else {
            moveSelectedPawn(g,squares, row,col,SQUARE_SIZE,pawnPadding);
        }
    }
    private void selectPawn(Graphics g, ChessPiece[][] squares, int row, int col, int SQUARE_SIZE,int hoverPadding) {
        if (squares[row][col]!=null) {
            if (move && squares[row][col].color == 1 || !move && squares[row][col].color == 0){
                if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
                else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));
                g.fillRect((col * 100), (row * 100), SQUARE_SIZE, SQUARE_SIZE);
                g.drawImage(squares[row][col].image, (col * 100) + hoverPadding, (row * 100) + hoverPadding, SQUARE_SIZE - 2 * hoverPadding, SQUARE_SIZE - 2 * hoverPadding, null);
                isSelectedPawn = true;
                selectedChessPawn = squares[row][col];
                selectedChessPawnX = col;
                selectedChessPawnY = row;
            }
        }
    }
    private void moveSelectedPawn(Graphics g, ChessPiece[][] squares, int row, int col, int SQUARE_SIZE,int pawnPadding) {
        if ((row + col) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
        else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));


        if (selectedChessPawn.isValidMove(selectedChessPawnX,selectedChessPawnY,col,row,squares)){
            g.fillRect((col * 100), (row * 100), SQUARE_SIZE, SQUARE_SIZE);
            g.drawImage(selectedChessPawn.image, (col * 100) + pawnPadding, (row * 100) + pawnPadding, SQUARE_SIZE - 2 * pawnPadding, SQUARE_SIZE - 2 * pawnPadding, null);

            squares[row][col]= selectedChessPawn;
            squares[selectedChessPawnY][selectedChessPawnX]=null;
            selectedChessPawn.x = col;
            selectedChessPawn.y = row;
            this.move=!this.move;
            if ((selectedChessPawnY + selectedChessPawnX) % 2 == 0) g.setColor(Color.getHSBColor(0.25F, 1.0F, 0.55F));
            else g.setColor(Color.getHSBColor(0.25F, 0.10F, 0.85F));
            g.fillRect((selectedChessPawnX * 100), (selectedChessPawnY * 100), SQUARE_SIZE, SQUARE_SIZE);
        }
        selectedChessPawn = null;
        isSelectedPawn = false;
    }
}
