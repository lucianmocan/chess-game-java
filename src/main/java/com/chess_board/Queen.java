package com.chess_board;

import com.chess_javafx.ChessBoard;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Queen extends LongMoves{

    public Queen(Color color) throws IOException {
        this.color = color;
        if (this.color == Color.WHITE){
            File f = new File("src/main/resources/images/queen_white.png");
            image = new Image(new FileInputStream(f.getCanonicalPath()));
        }
        else {
            File f = new File("src/main/resources/images/queen_dark.png");
            image = new Image(new FileInputStream(f.getCanonicalPath()));
        }
    }

    protected List<ChessTile> getPossibleMoves(int coord, List<ChessTile> tiles, Color color){
        int col = column(coord);
        int row = row(coord);
        List<ChessTile> result = new ArrayList<>();
        result.addAll(longPossibleMoves(coord, new Couple(1,- 1), tiles, color));
        result.addAll(longPossibleMoves(coord, new Couple(1,1), tiles, color));
        result.addAll(longPossibleMoves(coord, new Couple(- 1,1), tiles, color));
        result.addAll(longPossibleMoves(coord, new Couple(- 1,- 1), tiles, color));
        result.addAll(longPossibleMoves(coord, new Couple(1,0), tiles, color));
        result.addAll(longPossibleMoves(coord, new Couple(0,1), tiles, color));
        result.addAll(longPossibleMoves(coord, new Couple(- 1,0), tiles, color));
        result.addAll(longPossibleMoves(coord, new Couple(0,- 1), tiles, color));
        return result;
    }


    @Override
    public List howToTake() {
        return null;
    }
}
