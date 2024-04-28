package org.example.pieces;

import org.example.ChessPiece;

public class QueenPiece extends ChessPiece {
    public QueenPiece(int pawn, int color, int x, int y) {
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
        if (startX == x) {
            int deltaY = Integer.compare(y, startY);
            for (int i = startY+deltaY; i != y; i +=deltaY) {
                if (board[i][startX] != null) {
                    return false;
                }
            }
            if (board[y][x]!=null && (startY + deltaY ==y) && board[startY][startX].color!=board[y][x].color){
                this.kill(board,x,y);
                return true;
            }
            if (board[y][x]!=null && board[startY][startX].color==board[y][x].color) return false;
            return true;
        }
        if (startY == y) {
            int deltaX = Integer.compare(x, startX);
            for (int i = startX+deltaX; i != x; i +=deltaX) {
                if (board[startY][i] != null) {
                    return false;
                }
            }
            if (board[y][x]!=null && (startX + deltaX == x) && board[startY][startX].color!=board[y][x].color){
                this.kill(board,x,y);
                return true;
            }
            if (board[y][x]!=null && board[startY][startX].color==board[y][x].color) return false;
            return true;
        }
        if (Math.abs(startX - x) == Math.abs(startY - y)) {
            int deltaX = Integer.compare(x, startX);
            int deltaY = Integer.compare(y, startY);

            for (int i = startX + deltaX, j = startY + deltaY; i != x && j != y; i += deltaX, j += deltaY) {
                System.out.println(i);
                System.out.println(j);
                if (board[j][i] != null) {
                    System.out.println("skos");
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
