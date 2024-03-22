package com.chess_board;

import com.chess_javafx.ChessController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    private Boolean hasReachedEnd = false;
    public Boolean initialPosition = true;
    private boolean isMagic;
    public Pawn(Color color, boolean isMagic) throws IOException {
        this.color = color;
        this.isMagic = isMagic;
         if (this.color == Color.WHITE){
             File f = new File("src/main/resources/images/pawn_white60.png");
             image = new Image(new FileInputStream(f.getCanonicalPath()));
         }
         else {
             File f = new File("src/main/resources/images/pawn_dark60.png");
             image = new Image(new FileInputStream(f.getCanonicalPath()));
         }
    }
    @Override
    public List howToMove(int coord, List<ChessTile> tiles, Color color) {
        if(!canCapture(coord, tiles, color) && !canMove(coord, tiles, color)){
            return null;
        }
        List moves = new ArrayList<>();
        if(canMove(coord, tiles, color)){
            moves.addAll(getPossibleMove(coord, tiles, color));
        }
        if (canCapture(coord, tiles, color)){
            moves.addAll(getPossibleCaptures(coord, tiles, color));
        }
        return moves;
    }

    private List<ChessTile> getPossibleCaptures(int coord, List<ChessTile> tiles, Color color) {
        List<ChessTile> captures = new ArrayList<>();
        if (color.equals(Color.WHITE)){
            if (this.isMagic) {
                ChessTile magic = getCaptureTile(coord, tiles, -8);
                if (magic != null) captures.add(magic);
            }
            if (!isOnLeftBorder(coord)){
                ChessTile capture = getCaptureTile(coord, tiles, -9);
                if (capture != null) captures.add(capture);
            }
            if (!isOnRightBorder(coord)) {
                ChessTile capture = getCaptureTile(coord, tiles, -7);
                if (capture != null) captures.add(capture);
            }
        }
        else {
            if (this.isMagic){
                ChessTile magic = getCaptureTile(coord, tiles, 8);
                if (magic != null) captures.add(magic);
            }
            if (!isOnRightBorder(coord)){
                ChessTile capture = getCaptureTile(coord, tiles, 9);
                if (capture != null) captures.add(capture);
            }
            if (!isOnLeftBorder(coord)) {
                ChessTile capture = getCaptureTile(coord, tiles, 7);
                if (capture != null) captures.add(capture);
            }
        }
        for (ChessTile c : captures){
            System.out.println(c.getBgColor());
        }
        return captures;
    }

    private ChessTile getCaptureTile(int coord, List<ChessTile> tiles, int distance){
        ChessTile t2 = tiles.get(coord + distance);
        if (t2.getType() != null && t2.getType().getColor() != tiles.get(coord).getType().getColor()){
            return t2;
        }
        return null;
    }
//    && !(t2.getType() instanceof King)

    private List<ChessTile> getPossibleMove(int coord, List<ChessTile> tiles, Color color) {
        List<ChessTile> result = new ArrayList<>();
        if (initialPosition && canMoveStart2(coord, tiles, color)) {
            if (color.equals(Color.WHITE)) result.add(tiles.get(coord - 16)); else result.add(tiles.get(coord + 16));
        }
        if (color.equals(Color.WHITE)) result.add(tiles.get(coord - 8)); else result.add(tiles.get(coord + 8));
        return result;
    }

    /**
     *
     * @param coord
     * @param tiles
     * @param color if true then upwards, if false then downwards
     * @return
     */
    private boolean canMove(int coord, List<ChessTile> tiles, Color color) {
        if (color.equals(Color.WHITE)){
            return tiles.get(coord - 8).getType() == null;
        }
        else {
            return tiles.get(coord + 8).getType() == null;
        }
    }

    private boolean canMoveStart2(int coord, List<ChessTile> tiles, Color color) {
        if (color.equals(Color.WHITE)){
            return tiles.get(coord - 16).getType() == null;
        }
        else {
            return tiles.get(coord + 16).getType() == null;
        }
    }

    private boolean canCapture(int coord, List<ChessTile> tiles, Color color) {
        if (color.equals(Color.WHITE)){
            return tiles.get(coord - 7).getType() != null || tiles.get(coord - 9) != null;
        }
        else {
            return tiles.get(coord + 7).getType() != null || tiles.get(coord + 9) != null;
        }
    }

    private Boolean isOnLeftBorder(int coord){
        return coord % 8 == 0;
    }
    private Boolean isOnRightBorder(int coord){
        return (coord+1) % 8 == 0;
    }

    @Override
    public List howToTake() {
        return null;
    }
}
