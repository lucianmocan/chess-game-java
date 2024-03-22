package com.chess_board;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class LongMoves extends Piece{

    protected abstract List<ChessTile> getPossibleMoves(int coord, List<ChessTile> tiles, Color color);

    @Override
    public List<ChessTile> howToMove(int coord, List<ChessTile> tiles, Color color) {
        return new ArrayList<>(getPossibleMoves(coord, tiles, tiles.get(coord).getType().getColor()));
    }

    protected List<ChessTile> longPossibleMoves(int coord, Couple couple, List<ChessTile> tiles, Color color) {
        List<Integer> moves = coordinates(longPossibleMovesInf(coord, couple));
        List<ChessTile> result = new ArrayList<>();
        int i = 0;
        while (i < moves.size() && tiles.get(moves.get(i)).getType() == null){
            result.add(tiles.get(moves.get(i)));
            i++;
        }
        if (i < moves.size() && tiles.get(moves.get(i)).getType().getColor() != color){
            result.add(tiles.get(moves.get(i)));
        }
        return result;
    }

//    && !(tiles.get(moves.get(i)).getType() instanceof King)

    protected List<Couple> longPossibleMovesInf(int coord, Couple couple) {
        List<Couple> list = new ArrayList<>();
        int i = 0;
        int col = column(coord);
        int row = row(coord);
        Couple c = new Couple(col, row);
        while (i < 8){
            c.y = couple.y + c.y;
            c.x = couple.x + c.x;
            list.add(new Couple(c.x,c.y));
            i++;
        }
        return list;
    }
}
