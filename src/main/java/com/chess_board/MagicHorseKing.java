package com.chess_board;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MagicHorseKing extends ShortSpecificMoves{

    public MagicHorseKing(Color color) throws IOException {
        this.color = color;
        if (this.color == Color.WHITE){
            File f = new File("src/main/resources/images/horse_white.png");
            image = new Image(new FileInputStream(f.getCanonicalPath()));
        }
        else {
            File f = new File("src/main/resources/images/horse_dark.png");
            image = new Image(new FileInputStream(f.getCanonicalPath()));
        }
    }
    @Override
    public List<ChessTile> howToTake() {
        return null;
    }

    @Override
    protected List<Couple> coordinatesInf(int coord) {
        int col = column(coord);
        int row = row(coord);
        List<Couple> result = new ArrayList<>();
        result.add(new Couple(col - 2,row - 1));
        result.add(new Couple(col - 2,row + 1));
        result.add(new Couple(col - 1,row - 2));
        result.add(new Couple(col - 1,row + 2));
        result.add(new Couple(col + 1, row - 2));
        result.add(new Couple(col + 1,row + 2));
        result.add(new Couple(col + 2,row - 1));
        result.add(new Couple(col + 2,row + 1));
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
}
