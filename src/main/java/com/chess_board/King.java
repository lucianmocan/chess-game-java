package com.chess_board;

import com.chess_javafx.ChessBoard;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class King extends ShortSpecificMoves{

    private boolean didRoque = false;


    public King(Color color) throws IOException {
        this.color = color;
        if (this.color == Color.WHITE){
            File f = new File("src/main/resources/images/king_white.png");
            image = new Image(new FileInputStream(f.getCanonicalPath()));
        }
        else {
            File f = new File("src/main/resources/images/king_dark.png");
            image = new Image(new FileInputStream(f.getCanonicalPath()));
        }
    }

    public boolean getDidRoque() {
        return didRoque;
    }

    public void setDidRoque(boolean didRoque) {
        this.didRoque = didRoque;
    }

    protected List<Couple> coordinatesInf(int coord){
        int col = column(coord);
        int row = row(coord);
        List<Couple> result = new ArrayList<>();
        result.add(new Couple(col,row + 1));
        result.add(new Couple(col,row - 1));
        result.add(new Couple(col + 1,row));
        result.add(new Couple(col - 1,row));
        result.add(new Couple(col + 1, row - 1));
        result.add(new Couple(col + 1,row + 1));
        result.add(new Couple(col - 1,row + 1));
        result.add(new Couple(col - 1,row - 1));
        return result;
    }

    @Override
    public List howToTake() {
        return null;
    }
}
