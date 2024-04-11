package org.example;

import javax.swing.*;

public class ChessFrame extends JFrame {
    ChessBoard chessBoard;

    public ChessFrame(){
        this.chessBoard = new ChessBoard();
        this.add(this.chessBoard);
        this.pack();
        this.setTitle("Chess");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
