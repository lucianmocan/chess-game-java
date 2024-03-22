package com.chess_javafx;

import com.chess_board.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private GraphicsContext gc;
    static final int SIZE_LINE_GRID = 8;
    static final int SIZE_ROW_GRID = 8;
    static final int SIZECASE = 80;

    static final Color BLACK = Color.BLACK;
    static final Color WHITE = Color.WHITE;

    static final Color HIGHLIGHTED = Color.CORAL;

    List<ChessTile> tiles = new ArrayList<>();

    public ChessBoard(GraphicsContext gc) {
        this.gc = gc;
        initChessBoard();
    }

    private void initChessBoard() {
        for (int i = 0; i < 64; i++) {
            int row = (i / SIZE_ROW_GRID) % 2;
            if (row == 0) {
                if (i % 2 == 0) {
                    tiles.add(new ChessTile(WHITE));
                } else {
                    tiles.add(new ChessTile(BLACK));
                }
            } else {
                if (i % 2 == 0) {
                    tiles.add(new ChessTile(BLACK));
                } else {
                    tiles.add(new ChessTile(WHITE));
                }
            }
        }
    }

    public void drawChessBoard() {
        for (int i = 0; i < 64; i++) {
            int c = i % SIZE_ROW_GRID;
            int l = i / SIZE_LINE_GRID;
            ChessTile tmp = tiles.get(i);
            tmp.setX(c);
            tmp.setY(l);
            gc.setFill(tmp.getBgColor());
            gc.fillRect(c * SIZECASE, l * SIZECASE, SIZECASE, SIZECASE);
        }
    }

    public void classicChess() throws IOException {
        setPieces(true, WHITE);
        setPieces(true, BLACK);
        updateChessBoard();
    }

    public void magicChess() throws IOException {
        setPieces(false, WHITE);
        setPieces(false, BLACK);
        updateChessBoard();
    }

    private void setPieces(Boolean classic, Color color) throws IOException {
        if (classic) {
            setPawns(color);
            setQueen(color);
            setKing(color);
            setBishops(color);
            setRooks(color);
            setHorses(color);
        }
        else {
            setMagicPawns(color);
            setMagicBishops(color);
            setMagicHorseKing(color);
            setRooks(color);
            setQueen(color);
            setMagicSquares(color);
        }
    }

    private void setMagicSquares(Color color) throws IOException {
        if (color.equals(WHITE)) {
            tiles.get(60).setType(new MagicSquare(color));
        } else {
            tiles.get(4).setType(new MagicSquare(color));
        }
    }

    private void setHorses(Color color) throws IOException {
        if (color.equals(WHITE)) {
            tiles.get(57).setType(new Horse(color));
            tiles.get(62).setType(new Horse(color));
        } else {
            tiles.get(1).setType(new Horse(color));
            tiles.get(6).setType(new Horse(color));
        }
    }

    private void setRooks(Color color) throws IOException {
        if (color.equals(WHITE)) {
            tiles.get(56).setType(new Rook(color));
            tiles.get(63).setType(new Rook(color));
        } else {
            tiles.get(0).setType(new Rook(color));
            tiles.get(7).setType(new Rook(color));
        }
    }

    private void setBishops(Color color) throws IOException {
        if (color.equals(WHITE)) {
            tiles.get(58).setType(new Bishop(color));
            tiles.get(61).setType(new Bishop(color));
        } else {
            tiles.get(2).setType(new Bishop(color));
            tiles.get(5).setType(new Bishop(color));
        }
    }

    private void setKing(Color color) throws IOException {
        if (color.equals(WHITE)) {
            tiles.get(60).setType(new King(color));
        } else {
            tiles.get(4).setType(new King(color));
        }
    }

    private void setQueen(Color color) throws IOException {
        if (color.equals(WHITE)) {
            tiles.get(59).setType(new Queen(WHITE));
        } else {
            tiles.get(3).setType(new Queen(BLACK));
        }
    }

    private void setPawns(Color color) throws IOException {
        if (color.equals(WHITE)) {
            for (int i = 48; i < 56; i++) {
                tiles.get(i).setType(new Pawn(WHITE, false));
            }
        } else {
            for (int i = 8; i < 16; i++) {
                tiles.get(i).setType(new Pawn(BLACK, false));
            }
        }
    }
    private void setMagicPawns(Color color) throws IOException {
        if (color.equals(WHITE)) {
            for (int i = 48; i < 56; i++) {
                tiles.get(i).setType(new MagicPawn(WHITE));
            }
        } else {
            for (int i = 8; i < 16; i++) {
                tiles.get(i).setType(new MagicPawn(BLACK));
            }
        }
    }
    private void setMagicHorseKing(Color color) throws IOException {
        if (color.equals(WHITE)) {
            tiles.get(57).setType(new MagicHorseKing(color));
            tiles.get(62).setType(new MagicHorseKing(color));
        } else {
            tiles.get(1).setType(new MagicHorseKing(color));
            tiles.get(6).setType(new MagicHorseKing(color));
        }
    }

    private void setMagicBishops(Color color) throws IOException {
        if (color.equals(WHITE)) {
            tiles.get(58).setType(new MagicBishop(color));
            tiles.get(61).setType(new MagicBishop(color));
        } else {
            tiles.get(2).setType(new MagicBishop(color));
            tiles.get(5).setType(new MagicBishop(color));
        }
    }

    public void updateChessBoard() {
        for (int i = 0; i < 64; i++) {
            int c = i % SIZE_ROW_GRID;
            int l = i / SIZE_LINE_GRID;
            if (tiles.get(i).getType() != null) {
                gc.setFill(tiles.get(i).getBgColor());
                if (tiles.get(i).isHighlighted()){
                    gc.setFill(HIGHLIGHTED);
                }
                gc.fillRect(c * SIZECASE, l * SIZECASE, SIZECASE, SIZECASE);
                gc.drawImage(tiles.get(i).getType().getImage(), c * SIZECASE + 2, l * SIZECASE + 2);
            }
            if (tiles.get(i).getType() == null && !tiles.get(i).isHighlighted()) {
                gc.setFill(tiles.get(i).getBgColor());
                gc.fillRect(c * SIZECASE, l * SIZECASE, SIZECASE, SIZECASE);
            }
        }
    }

    public void displayMoves(List<ChessTile> moves){
        for (int i = 0; i < moves.size(); i++){
            moves.get(i).setHighlighted(true);
            gc.setFill(HIGHLIGHTED);
            gc.fillRect(moves.get(i).getX() * SIZECASE, moves.get(i).getY() * SIZECASE, SIZECASE, SIZECASE);
        }
    updateChessBoard();
    }
    public void hidePreviousTrigger(List<ChessTile> moves){
        for (int i = 0; i < moves.size(); i++){
            moves.get(i).setHighlighted(false);
            gc.setFill(moves.get(i).getBgColor());
            gc.fillRect(moves.get(i).getX() * SIZECASE, moves.get(i).getY() * SIZECASE, SIZECASE, SIZECASE);
        }

        updateChessBoard();
    }
}