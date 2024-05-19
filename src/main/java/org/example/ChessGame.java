package org.example;

import org.example.pieces.*;

public class ChessGame {
    private final int BOARD_SIZE = 8;
    private final ChessPiece[][] squares = new ChessPiece[BOARD_SIZE][BOARD_SIZE];

    public ChessGame() {
        int INIT_WHITE_ROW = 7;
        int INIT_BLACK_ROW = 0;
        initializePieces(INIT_BLACK_ROW, INIT_WHITE_ROW);
    }

    private void initializePieces(int INIT_BLACK_ROW, int INIT_WHITE_ROW) {
        placeRooks(INIT_BLACK_ROW, INIT_WHITE_ROW);
        placeKings(INIT_BLACK_ROW, INIT_WHITE_ROW);
        placeKnights(INIT_BLACK_ROW, INIT_WHITE_ROW);
        placeQueens(INIT_BLACK_ROW, INIT_WHITE_ROW);
        placePawns();
        placeBishops(INIT_BLACK_ROW, INIT_WHITE_ROW);
    }

    private void placePawns() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            ChessPiece pawn = new PawnPiece(2, 0, 1, i);
            squares[1][i] = pawn;
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            ChessPiece pawn = new PawnPiece(2, 1, 6, i);
            squares[6][i] = pawn;
        }
    }

    private void placeQueens(int INIT_BLACK_ROW, int INIT_WHITE_ROW) {
        ChessPiece queenWhite = new QueenPiece(6, 0, INIT_BLACK_ROW, 3);
        squares[INIT_BLACK_ROW][3] = queenWhite;

        ChessPiece queenBlack = new QueenPiece(6, 1, INIT_WHITE_ROW, 3);
        squares[INIT_WHITE_ROW][3] = queenBlack;
    }

    private void placeBishops(int INIT_BLACK_ROW, int INIT_WHITE_ROW) {
        ChessPiece bishopWhite = new BishopPiece(4, 0, INIT_BLACK_ROW, 2);
        squares[INIT_BLACK_ROW][2] = bishopWhite;

        ChessPiece bishopBlack = new BishopPiece(4, 1, INIT_WHITE_ROW, 2);
        squares[INIT_WHITE_ROW][2] = bishopBlack;

        ChessPiece bishopSecWhite = new BishopPiece(4, 0, INIT_BLACK_ROW, 5);
        squares[INIT_BLACK_ROW][5] = bishopSecWhite;

        ChessPiece bishopSecBlack = new BishopPiece(4, 1, INIT_WHITE_ROW, 5);
        squares[INIT_WHITE_ROW][5] = bishopSecBlack;
    }

    private void placeRooks(int INIT_BLACK_ROW, int INIT_WHITE_ROW) {
        ChessPiece rookWhite = new RookPiece(5, 0, INIT_BLACK_ROW, 0);
        squares[INIT_BLACK_ROW][0] = rookWhite;

        ChessPiece rookBlack = new RookPiece(5, 1, INIT_WHITE_ROW, 0);
        squares[INIT_WHITE_ROW][0] = rookBlack;

        ChessPiece rookSecWhite = new RookPiece(5, 0, INIT_BLACK_ROW, 7);
        squares[INIT_BLACK_ROW][7] = rookSecWhite;

        ChessPiece rookSecBlack = new RookPiece(5, 1, INIT_WHITE_ROW, 7);
        squares[INIT_WHITE_ROW][7] = rookSecBlack;
    }

    private void placeKnights(int INIT_BLACK_ROW, int INIT_WHITE_ROW) {
        ChessPiece knightWhite = new KnightPiece(3, 0, INIT_BLACK_ROW, 1);
        squares[INIT_BLACK_ROW][1] = knightWhite;

        ChessPiece knightBlack = new KnightPiece(3, 1, INIT_WHITE_ROW, 1);
        squares[INIT_WHITE_ROW][1] = knightBlack;

        ChessPiece knightSecWhite = new KnightPiece(3, 0, INIT_BLACK_ROW, 6);
        squares[INIT_BLACK_ROW][6] = knightSecWhite;

        ChessPiece knightSecBlack = new KnightPiece(3, 1, INIT_WHITE_ROW, 6);
        squares[INIT_WHITE_ROW][6] = knightSecBlack;
    }

    private void placeKings(int INIT_BLACK_ROW, int INIT_WHITE_ROW) {
        ChessPiece kingWhite = new KingPiece(1, 1, INIT_WHITE_ROW, 4);
        squares[INIT_WHITE_ROW][4] = kingWhite;

        ChessPiece kingBlack = new KingPiece(1, 0, INIT_BLACK_ROW, 4);
        squares[INIT_BLACK_ROW][4] = kingBlack;
    }

    public ChessPiece[][] getBoard() {
        return squares;
    }

    public boolean isValidMove(int fromX, int fromY, int toX, int toY) {
        ChessPiece piece = squares[fromY][fromX];
        if (piece != null) {
            return piece.isValidMove(fromX, fromY, toX, toY, squares);
        }
        return false;
    }

    public void movePiece(int fromX, int fromY, int toX, int toY) {
        ChessPiece piece = squares[fromY][fromX];
        squares[toY][toX] = piece;
        squares[fromY][fromX] = null;
        if (piece != null) {
            piece.x = toX;
            piece.y = toY;
        }
    }

    public void isCheck(ChessPiece chessPiece) {
        ChessPiece king = null;
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if ((squares[i][j] instanceof KingPiece) && squares[i][j].color==chessPiece.color){
                    king=squares[i][j];
                }
            }
        }
        for (int i=0;i<8;i++){
            for (int j=0;j<8;j++){
                if(squares[i][j]!=null){
                    if (squares[i][j].color!=chessPiece.color){
                        assert king != null;
                        if (squares[i][j].isValidMove(j, i, king.y,king.x,squares)){
                            System.out.println(king.x);
                            System.out.println(king.y);
                            System.out.println("check");
                            System.out.println(j);
                            System.out.println(i);
                            System.out.println(squares[i][j]);
                        }
                    }
                }
            }
        }
    }
}
