package com.chess_board;


import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ChessTile {
    private boolean highlighted = false;
    private Piece type = null;
    private Color bgColor;
    private Color originalColor;
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    public ChessTile(Color bgColor){
        this.bgColor = bgColor; this.originalColor = bgColor;
    }
    public Color getOriginalColor(){ return originalColor;}
    public Color getBgColor() {
        return bgColor;
    }
    public void setBgColor(Color color){
        this.bgColor = color;
    }
    public void setType(Piece type) {
        this.type = type;
    }

    public Piece getType() {
        return type;
    }

    public static void switchTiles(ChessTile t1, ChessTile t2){
        Piece type = t1.getType();
        t1.setType(t2.getType());
        t2.setType(type);
    }

    public static void captureTile(ChessTile t1, ChessTile t2){
        switchTiles(t1, t2);
        t2.setType(null);
    }

    public boolean endTile(){
        return y == 0 || y == 7;
    }

    public static Boolean emptyTile(int coord, List<ChessTile> tiles){
        return (tiles.get(coord).getType() == null);
    }
}
