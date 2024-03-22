package com.chess_board;

import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.List;

public class MagicPawn extends Pawn{
    public MagicPawn(Color color) throws IOException {
        super(color, true);
    }

    private boolean canCapture(int coord, List<ChessTile> tiles, Color color) {
        if (color.equals(Color.WHITE)){
            return tiles.get(coord - 7).getType() != null || tiles.get(coord - 9) != null || tiles.get(coord - 8) != null;
        }
        else {
            return tiles.get(coord + 7).getType() != null || tiles.get(coord + 9) != null || tiles.get(coord + 8) != null;
        }
    }

}
