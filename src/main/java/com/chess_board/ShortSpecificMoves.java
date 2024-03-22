package com.chess_board;

import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ShortSpecificMoves extends Piece{
    @Override
    public List howToMove(int coord, List<ChessTile> tiles, Color color) {
        List moves = new ArrayList<>();
        List<Couple> inf = coordinatesInf(coord);
        List<Integer> coords = coordinates(inf);
        moves.addAll(getPossibleMove(coords, tiles));
        moves.addAll(getPossibleCaptures(coords, tiles, tiles.get(coord).getType().getColor()));
        return moves;
    }

    private List<ChessTile> getPossibleCaptures(List<Integer> coords, List<ChessTile> tiles, Color color) {
        List<ChessTile> result = new ArrayList<>();
        for (Integer i: coords){
            if (tiles.get(i).getType() != null && !tiles.get(i).getType().getColor().equals(color)
                    ){
                result.add(tiles.get(i));
            }
        }
        return result;
    }
//&& !(tiles.get(i).getType() instanceof King)

    private List<ChessTile> getPossibleMove(List<Integer> coords, List<ChessTile> tiles) {
        List<ChessTile> result = new ArrayList<>();
        for (Integer i : coords){
            if (tiles.get(i).getType() == null){
                result.add(tiles.get(i));
            }
        }
        return result;
    }

    protected abstract List<Couple> coordinatesInf(int coord);
}
