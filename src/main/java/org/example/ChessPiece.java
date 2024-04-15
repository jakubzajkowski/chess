package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class ChessPiece {
    public final int King = 1;
    public final int Pawn = 2;
    public final int Knight = 3;
    public final int Bishop = 4;
    public final int Rook = 5;
    public final int Queen = 6;
    public final int White = 1;
    public final int Black = 0;
    public BufferedImage image;
    public int color;
    public int x;
    public int y;

    public ChessPiece(int pawn, int color, int x, int y){
        this.x = x;
        this.y = y;
        this.color=color;
        if (pawn==2 && color==0){
            this.image=loadImage("/pawn_black.png");
        }
        if (pawn==2 && color==1){
            this.image=loadImage("/pawn_white.png");
        }
        if (pawn==1 && color==1){
            this.image=loadImage("/king_white.png");
        }
        if (pawn==1 && color==0){
            this.image=loadImage("/king_black.png");
        }
        if (pawn==3 && color==1){
            this.image=loadImage("/horse_white.png");
        }
        if (pawn==3 && color==0){
            this.image=loadImage("/horse_black.png");
        }
        if (pawn==5 && color==1){
            this.image=loadImage("/rook_white.png");
        }
        if (pawn==5 && color==0){
            this.image=loadImage("/rook_black.png");
        }
        if (pawn==4 && color==1){
            this.image=loadImage("/bishop_white.png");
        }
        if (pawn==4 && color==0){
            this.image=loadImage("/bishop_black.png");
        }
        if (pawn==6 && color==1){
            this.image=loadImage("/queen_white.png");
        }
        if (pawn==6 && color==0){
            this.image=loadImage("/queen_black.png");
        }
    }
    private BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(getClass().getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    abstract public boolean isValidMove(int startX, int startY, int x, int y, ChessPiece[][] board);

    public void kill(ChessPiece[][] board,int x,int y){
        board[y][x] = null;
    }
}
