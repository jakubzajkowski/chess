package org.example;

import org.example.pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChessBoard extends JPanel {
    private final Color squareColorDark = Color.getHSBColor(0.6F, 1.0F, 0.55F);
    private final Color squareColorLight = Color.getHSBColor(0.6F, 0.10F, 0.85F);
    private final int BOARD_SIZE = 8;
    private final int SQUARE_SIZE = 70;
    private final ChessGame game;
    private int mouseX = -1;
    private int mouseY = -1;
    private boolean isSelectedPawn = false;
    private ChessPiece selectedChessPawn;
    private int selectedChessPawnX;
    private int selectedChessPawnY;
    private boolean move = true;
    private boolean checkmate = false;

    public ChessBoard() {
        this.setPreferredSize(new Dimension(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE));
        this.game = new ChessGame();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        int pawnPadding = (int) (0.1 * SQUARE_SIZE);
        ChessPiece[][] squares = game.getBoard();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                int x = col * SQUARE_SIZE;
                int y = row * SQUARE_SIZE;

                if ((row + col) % 2 == 0) g.setColor(squareColorDark);
                else g.setColor(squareColorLight);

                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);
                g.fillRect(x, y, SQUARE_SIZE, SQUARE_SIZE);

                if (squares[row][col] != null) {
                    g.drawImage(squares[row][col].image, x + pawnPadding, y + pawnPadding, SQUARE_SIZE - 2 * pawnPadding, SQUARE_SIZE - 2 * pawnPadding, null);
                }
            }
        }

        if (mouseX != -1 && mouseY != -1) {
            handleMouseClick(g, mouseY, mouseX, squares, SQUARE_SIZE);
        }
    }

    public void handleMouseClick(Graphics g, int mouseY, int mouseX, ChessPiece[][] squares, int SQUARE_SIZE) {
        int hoverPadding = (int) (0.05 * SQUARE_SIZE);
        int pawnPadding = (int) (0.1 * SQUARE_SIZE);
        int row = (int) Math.floor((double) mouseY / SQUARE_SIZE);
        int col = (int) Math.floor((double) mouseX / SQUARE_SIZE);

        if (!isSelectedPawn) {
            selectPawn(g, squares, row, col, SQUARE_SIZE, hoverPadding);
        } else {
            moveSelectedPawn(g, squares, row, col, SQUARE_SIZE, pawnPadding);
        }
    }

    private void selectPawn(Graphics g, ChessPiece[][] squares, int row, int col, int SQUARE_SIZE, int hoverPadding) {
        if (squares[row][col] != null) {
            if (move && squares[row][col].color == 1 || !move && squares[row][col].color == 0) {
                if ((row + col) % 2 == 0) g.setColor(squareColorDark);
                else g.setColor(squareColorLight);
                g.fillRect((col * SQUARE_SIZE), (row * SQUARE_SIZE), SQUARE_SIZE, SQUARE_SIZE);
                g.drawImage(squares[row][col].image, (col * SQUARE_SIZE) + hoverPadding, (row * SQUARE_SIZE) + hoverPadding, SQUARE_SIZE - 2 * hoverPadding, SQUARE_SIZE - 2 * hoverPadding, null);
                isSelectedPawn = true;
                selectedChessPawn = squares[row][col];
                selectedChessPawnX = col;
                selectedChessPawnY = row;
            }
        }
    }

    private void moveSelectedPawn(Graphics g, ChessPiece[][] squares, int row, int col, int SQUARE_SIZE, int pawnPadding) {
        if ((row + col) % 2 == 0) g.setColor(squareColorDark);
        else g.setColor(squareColorLight);

        if (game.isValidMove(selectedChessPawnX, selectedChessPawnY, col, row)) {
            if (!(squares[row][col] instanceof KingPiece)) {
                g.fillRect((col * SQUARE_SIZE), (row * SQUARE_SIZE), SQUARE_SIZE, SQUARE_SIZE);
                g.drawImage(selectedChessPawn.image, (col * SQUARE_SIZE) + pawnPadding, (row * SQUARE_SIZE) + pawnPadding, SQUARE_SIZE - 2 * pawnPadding, SQUARE_SIZE - 2 * pawnPadding, null);
                game.movePiece(selectedChessPawnX, selectedChessPawnY, col, row);
                this.move = !this.move;
                if ((selectedChessPawnY + selectedChessPawnX) % 2 == 0) g.setColor(squareColorDark);
                else g.setColor(squareColorLight);
                g.fillRect((selectedChessPawnX * SQUARE_SIZE), (selectedChessPawnY * SQUARE_SIZE), SQUARE_SIZE, SQUARE_SIZE);
                game.isCheck(col, row, this.checkmate);
            }
        }
        selectedChessPawn = null;
        isSelectedPawn = false;
    }
}