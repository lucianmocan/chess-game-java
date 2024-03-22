package com.chess_board;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MagicBishop extends LongMoves{

    public MagicBishop(Color color) throws IOException {
        this.color = color;
        if (this.color == Color.WHITE){
            File f = new File("src/main/resources/images/bishop_white.png");
            image = new Image(new FileInputStream(f.getCanonicalPath()));
        }
        else {
            File f = new File("src/main/resources/images/bishop_dark.png");
            image = new Image(new FileInputStream(f.getCanonicalPath()));
        }
    }
    protected List<ChessTile> getPossibleMoves(int coord, List<ChessTile> tiles, Color color){
        int col = column(coord);
        int row = row(coord);
        List<ChessTile> result = new ArrayList<>();
        List<ChessTile> tmp = new ArrayList<>(longPossibleMoves(coord, new Couple(1, -1), tiles, color));
        if (tmp.size() > 3) result.addAll(tmp.subList(0,3));
        else result.addAll(tmp);
        tmp = new ArrayList<>(longPossibleMoves(coord, new Couple(1,1), tiles, color));
        if (tmp.size() > 3) result.addAll(tmp.subList(0,3));
        else result.addAll(tmp);
        tmp = new ArrayList<>(longPossibleMoves(coord, new Couple(- 1,1), tiles, color));
        if (tmp.size() > 3) result.addAll(tmp.subList(0,3));
        else result.addAll(tmp);
        tmp = new ArrayList<>(longPossibleMoves(coord, new Couple(- 1,- 1), tiles, color));
        if (tmp.size() > 3) result.addAll(tmp.subList(0,3));
        else result.addAll(tmp);
        return result;
    }


    @Override
    public List<ChessTile> howToTake() {
        return null;
    }
}
