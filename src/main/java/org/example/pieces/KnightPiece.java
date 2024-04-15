package org.example.pieces;

import org.example.ChessPiece;

public class KnightPiece extends ChessPiece {
    public KnightPiece(int pawn, int color, int x, int y) {
        super(pawn, color, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int x, int y, ChessPiece[][] board) {
        if (startX == x && startY == y) {
            return false;
        }

        if (Math.abs(x - startX) > 1 || Math.abs(y - startY) > 1) {
            return false;
        }

        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }

        return true;
    }
}
