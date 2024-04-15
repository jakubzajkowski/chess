package org.example.pieces;

import org.example.ChessPiece;

public class KingPiece extends ChessPiece {
    public KingPiece(int pawn, int color, int x, int y) {
        super(pawn, color, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int x, int y, ChessPiece[][] board) {
        if (startX == x && startY == y) {
            return false;
        }

        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }

        if (startX + 1 == x || startY + 1 == y && board[x][y] == null) {
            return true;
        }

        if (Math.abs(startX - x) == 1 && startY + 1 == y && board[x][y] != null) {
            return true;
        }

        return false;
    }
}
