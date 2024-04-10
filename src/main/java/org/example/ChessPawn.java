package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ChessPawn {
    public final int None = 0;
    public final int King = 1;
    public final int Pawn = 2;
    public final int Knight = 3;
    public final int Bishop = 4;
    public final int Rook = 5;
    public final int Queen = 6;

    public BufferedImage image;

    public ChessPawn(int pawn){
        if (pawn==1){
            this.image=loadImage("/pawn.png");
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
}
