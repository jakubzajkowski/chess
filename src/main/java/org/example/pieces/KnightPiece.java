package org.example.pieces;

import org.example.ChessPiece;

public class KnightPiece extends ChessPiece {
    public KnightPiece(int pawn, int color, int x, int y) {
        super(pawn, color, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int x, int y, ChessPiece[][] board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8 || (startX == x && startY == y)) {
            return false;
        }

        int deltaX = Math.abs(x - startX);
        int deltaY = Math.abs(y - startY);

        if ((deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2)) {
            if (board[y][x] != null && board[y][x].color == board[startY][startX].color) {
                return false;
            }

            if (board[y][x] != null && board[y][x].color != board[startY][startX].color) {
                kill(board, x, y);
            }

            return true;
        }

        return false;
    }
}
