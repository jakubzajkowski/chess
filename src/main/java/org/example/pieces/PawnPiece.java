package org.example.pieces;

import org.example.ChessPiece;

public class PawnPiece extends ChessPiece {

    public PawnPiece(int pawn, int color, int x, int y) {
        super(pawn, color, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int x, int y, ChessPiece[][] board) {

        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }

        int direction = board[startY][startX].color==1 ? -1 : 1;

        if(startY + direction == y && (startX + 1 == x || startX - 1 == x) && board[y][x] != null && board[y][x].color!=board[startY][startX].color){
            this.kill(board,x,y);
            return true;
        }
        if (startY + direction == y && startX == x && board[y][x] == null) {
            return true;
        }
        if ((startY==6 || startY==1) && (startY + direction == y || startY + (2*direction) == y) && startX == x && board[y][x] == null) {
            return true;
        }

        return false;
    }
}
