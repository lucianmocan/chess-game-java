package com.chess_board;

import com.chess_javafx.ChessBoard;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    Image image;
    Color color;

    public abstract List<ChessTile> howToMove(int coord, List<ChessTile> tiles, Color color);

    public abstract List<ChessTile> howToTake();

    public Image getImage() {
        return image;
    }

    public Color getColor() {
        return color;
    }

    protected Integer column(int coord){
        return coord % 8;
    }
    protected Integer row(int coord){
        return coord / 8;
    }
    class Couple {
        public int x;
        public int y;
        Couple(int x, int y){
            this.x = x;
            this.y = y;
        }
        Couple (int coord){
            this.x = column(coord);
            this.y = row(coord);
        }
        protected Integer coord(){
            return x + y * 8;
        }

    }

    protected List<Integer> coordinates(List<Couple> coords){
        List<Integer> result = new ArrayList<>();
        for (Couple i: coords){
            if (!(i.x < 0 || i.x > 7 || i.y < 0 || i.y > 7)){
                result.add(i.coord());
            }
        }
        return result;
    }
}
