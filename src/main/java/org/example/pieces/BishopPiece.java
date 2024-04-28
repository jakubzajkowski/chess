package org.example.pieces;

import org.example.ChessPiece;

public class BishopPiece extends ChessPiece {
    public BishopPiece(int pawn, int color, int x, int y) {
        super(pawn, color, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int x, int y, ChessPiece[][] board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }
        if (startX == x && startY == y) {
            return false;
        }
        if (Math.abs(startX - x) == Math.abs(startY - y)) {
            int deltaX = Integer.compare(x, startX);
            int deltaY = Integer.compare(y, startY);

            for (int i = startX + deltaX, j = startY + deltaY; i != x && j != y; i += deltaX, j += deltaY) {
                if (board[j][i] != null) {
                    return false;
                }
            }
            if (board[y][x]!=null && (startX + deltaX == x && startY + deltaY ==y) && board[startY][startX].color!=board[y][x].color){
                this.kill(board,x,y);
                return true;
            }
            if (board[y][x]!=null && board[startY][startX].color==board[y][x].color) return false;

            return true;
        }
        return false;
    }
}
