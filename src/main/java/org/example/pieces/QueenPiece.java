package org.example.pieces;

import org.example.ChessPiece;

public class QueenPiece extends ChessPiece {
    public QueenPiece(int pawn, int color, int x, int y) {
        super(pawn, color, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int x, int y, ChessPiece[][] board) {
        return false;
    }
}
