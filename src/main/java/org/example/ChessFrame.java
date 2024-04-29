package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class ChessFrame extends JFrame {
    ChessBoard chessBoard;

    public ChessFrame(){
        this.chessBoard = new ChessBoard();
        this.add(this.chessBoard);
        this.pack();
        this.setTitle("Chess");
        Image image = loadImageFromResource();
        this.setIconImage(image);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
    }

    private Image loadImageFromResource() {
        Image image = null;
        String imageURL = "/rook_black.png";
        try {
            image = ImageIO.read(getClass().getResource(imageURL));
            System.out.println(image);
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania obrazu: " + e.getMessage());
        }
        return image;
    }
}
