package org.example.pieces;

import org.example.ChessPiece;

public class RookPiece extends ChessPiece {
    public RookPiece(int pawn, int color, int x, int y) {
        super(pawn, color, x, y);
    }

    @Override
    public boolean isValidMove(int startX, int startY, int x, int y, ChessPiece[][] board) {
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return false;
        }
        if (startX == x && startY == y){
            return false;
        }

        if(startX == x || startY == y){
            if (startX-x<0 && board[startY][startX+1]!=null){
                if (startX-x==-1 && board[y][x]!=null && board[startY][startX].color!=board[y][x].color){
                    this.kill(board,x,y);
                    return true;
                }
                return false;
            }
            if (startX-x>0 && board[startY][startX-1]!=null){
                if (startX-x==1 && board[y][x]!=null && board[startY][startX].color!=board[y][x].color){
                    this.kill(board,x,y);
                    return true;
                }
                return false;
            }
            if (startY-y<0 && board[startY+1][startX]!=null){
                if (startY-y==-1 && board[y][x]!=null && board[startY][startX].color!=board[y][x].color){
                    this.kill(board,x,y);
                    return true;
                }
                return false;
            }
            if (startY-y>0 && board[startY-1][startX]!=null){
                if (startY-y==1 && board[y][x]!=null && board[startY][startX].color!=board[y][x].color){
                    this.kill(board,x,y);
                    return true;
                }
                return false;
            }
            if (board[y][x]!=null && board[startY][startX].color!=board[y][x].color){
                this.kill(board,x,y);
                return true;
            }
            return board[y][x] == null || board[startY][startX].color != board[y][x].color;
        }
        return false;
    }
}
