package com.chess_javafx;

import com.chess_board.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ChessController implements Initializable {
    @FXML private Canvas content;
    @FXML private Label player1Name;
    @FXML private Label player2Name;
    @FXML private Button quitButton;
    @FXML private VBox promoteBox;
    @FXML private Button petiteRoque;
    @FXML private Button grandeRoque;
    @FXML private VBox roqueBox;
    private GraphicsContext gc;
    private Boolean clickedTrigger = false;
    private int previousX;
    private int previousY;
    private List<ChessTile> previousOptions;
    private ChessBoard chessBoard;
    private ChessTile currentTile;
    protected boolean gameMode;
    public void initData(GameData data){
        player1Name.setText(data.getPlayer1Name());
        player2Name.setText(data.getPlayer2Name());
        gameMode = data.getGameMode();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gc = content.getGraphicsContext2D();
        chessBoard = new ChessBoard(gc);
    }

    public void startChess(){
        content.setOnMousePressed(rightClickHandler);
        chessBoard.drawChessBoard();
        if (gameMode) {
            try {
                chessBoard.classicChess();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                chessBoard.magicChess();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    EventHandler<MouseEvent> rightClickHandler =
            event -> {
                int c = (int) event.getX() / ChessBoard.SIZECASE;
                int l = (int) event.getY() / ChessBoard.SIZECASE;
                System.out.println(c + " " + l);
                clickedTileAction(c, l);
            };

    private void clickedTileAction(int c, int l) {
        var currentX = c;
        var currentY = l*8;
        if (currentY == previousY && currentX == previousX && clickedTrigger){
            clickedTrigger = false;
            chessBoard.hidePreviousTrigger(previousOptions);
            return;
        }
        else {
            ifPreviousWasRed();
            ChessTile tile = chessBoard.tiles.get(currentY + currentX);
            if (tile.isHighlighted()) {
                highlightedTile(tile);
            }
            else if (tile.getType() != null){
                selectTile(tile, currentX, currentY);
            }
        }
    }

    private void ifPreviousWasRed() {
        ChessTile previousTile = chessBoard.tiles.get(previousX + previousY);
        if (previousTile.getBgColor().equals(Color.RED)){
            previousTile.setBgColor(previousTile.getOriginalColor());
        }
    }

    public void highlightedTile(ChessTile tile){
        pawnPromoteOrStart(tile);
        if (tile.getType() == null)
            ChessTile.switchTiles(tile, chessBoard.tiles.get(previousY + previousX));
        else
            ChessTile.captureTile(tile, chessBoard.tiles.get(previousY + previousX));
        if (tile.getType() instanceof Pawn){
            ((Pawn) tile.getType()).initialPosition = false;
        }
        lookForCheck(Color.WHITE);
        lookForCheck(Color.BLACK);
        chessBoard.hidePreviousTrigger(previousOptions);
        clickedTrigger = false;
        chessBoard.updateChessBoard();
    }

    private void lookForCheck(Color color) {
        if (checkForCheck(color)){
            ChessTile king = searchForKing(color);
            king.setBgColor(Color.RED);
            chessBoard.updateChessBoard();
            if (isCheckMate(king, color)){
                content.setDisable(true);
            }
        }
    }

    private void pawnPromoteOrStart(ChessTile tile) {
        Piece p = chessBoard.tiles.get(previousY + previousX).getType();
        if (p instanceof Pawn){
            if (((Pawn) p).initialPosition)
                ((Pawn) p).initialPosition = false;
            if (tile.endTile()){
                promoteBox.setVisible(true);
                this.currentTile = tile;
            }
        }
    }

    public void selectTile(ChessTile tile, int currentX, int currentY){
        List currentOptions;
        clickedTrigger = true;
        this.currentTile = tile;
        if (tile.getType() instanceof King) isKing(tile);
        if (previousOptions != null) chessBoard.hidePreviousTrigger(previousOptions);
        if (tile.getType().getColor().equals(Color.WHITE)) {
            currentOptions = tile.getType().howToMove(currentY + currentX, chessBoard.tiles, Color.WHITE);
        } else {
            currentOptions = tile.getType().howToMove(currentY + currentX, chessBoard.tiles, Color.BLACK);
        }
        this.previousOptions = currentOptions;
        this.previousX = currentX;
        this.previousY = currentY;
        chessBoard.displayMoves(currentOptions);
    }

    private void isKing(ChessTile tile){
        roqueBox.setVisible(true);
        if (!((King)tile.getType()).getDidRoque()) {
            if (grandeRoquePossible(tile)) {
                grandeRoque.setDisable(false);
            }
            if (petiteRoquePossible(tile)) {
                petiteRoque.setDisable(false);
            }
        }
    }

    private boolean grandeRoquePossible(ChessTile tile){
        int index = chessBoard.tiles.indexOf(tile);
        System.out.println(index);
        int i = 1;
        boolean check = true;
        while (i <= 4 && check){
            if (chessBoard.tiles.get(index - i).getType() != null){
                check = false;
            }
            i++;
        }
        System.out.println(i);
        return (i == 5 && chessBoard.tiles.get(index - (i-1)).getType() instanceof Rook);
    }
    private boolean petiteRoquePossible(ChessTile tile){
        int index = chessBoard.tiles.indexOf(tile);
        System.out.println(index);
        int i = 1;
        boolean check = true;
        while (i <= 3 && check){
            if (chessBoard.tiles.get(index + i).getType() != null){
                check = false;
            }
            i++;
        }
        System.out.println(i);
        return (i == 4 && chessBoard.tiles.get(index + i - 1).getType() instanceof Rook);
    }

    public void quitGame(ActionEvent actionEvent) {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void promoteToQueen(ActionEvent actionEvent) throws IOException {
        promoteBox.setVisible(false);
        this.currentTile.setType(new Queen(this.currentTile.getType().getColor()));
        chessBoard.hidePreviousTrigger(previousOptions);
    }

    public void promoteToBishop(ActionEvent actionEvent) throws IOException {
        promoteBox.setVisible(false);
        this.currentTile.setType(new Bishop(this.currentTile.getType().getColor()));
        chessBoard.hidePreviousTrigger(previousOptions);
    }

    public void promoteToHorse(ActionEvent actionEvent) throws IOException {
        promoteBox.setVisible(false);
        this.currentTile.setType(new Horse(this.currentTile.getType().getColor()));
        chessBoard.hidePreviousTrigger(previousOptions);
    }

    public void promoteToRook(ActionEvent actionEvent) throws IOException {
        promoteBox.setVisible(false);
        this.currentTile.setType(new Rook(this.currentTile.getType().getColor()));
        chessBoard.hidePreviousTrigger(previousOptions);
    }

    public void doGrandRoque(ActionEvent actionEvent) {
        int index = chessBoard.tiles.indexOf(currentTile);
        ((King)currentTile.getType()).setDidRoque(true);
        ChessTile rook = chessBoard.tiles.get(index - 4);
        ChessTile.switchTiles(rook, chessBoard.tiles.get(index - 1));
        ChessTile.switchTiles(currentTile, chessBoard.tiles.get(index - 2));
        chessBoard.hidePreviousTrigger(previousOptions);
        grandeRoque.setDisable(true);
        petiteRoque.setDisable(true);

    }

    public void doPetiteRoque(ActionEvent actionEvent) {
        int index = chessBoard.tiles.indexOf(currentTile);
        ((King)currentTile.getType()).setDidRoque(true);
        ChessTile rook = chessBoard.tiles.get(index + 3);
        ChessTile.switchTiles(rook, chessBoard.tiles.get(index + 1));
        ChessTile.switchTiles(currentTile, chessBoard.tiles.get(index + 2));
        chessBoard.hidePreviousTrigger(previousOptions);
        grandeRoque.setDisable(true);
        petiteRoque.setDisable(true);
    }

    private boolean checkForCheck(Color color){
        ChessTile king = searchForKing(color);
        return (isChecked(king, color));
    }

    private boolean checkForMate(Color color){
        ChessTile king = searchForKing(color);
        return (isCheckMate(king, color));
    }

    private ChessTile searchForKing(Color color) throws IllegalStateException{
        for (ChessTile c : chessBoard.tiles)
            if ((c.getType() instanceof King || c.getType() instanceof MagicSquare) && c.getType().getColor() == color)
                return c;
        throw new IllegalStateException("If the King's not found, then there's a big problem.");
    }

    private boolean isChecked(ChessTile king, Color color){
        for (ChessTile c : chessBoard.tiles){
            if (c.getType() != null && c.getType().howToMove(chessBoard.tiles.indexOf(c), chessBoard.tiles, color).contains(king)){
                System.out.println("yes");
                return true;
            }
        }
        System.out.println("no");
        return false;
    }

    private boolean isCheckMate(ChessTile king, Color color){
        List<ChessTile> list = king.getType().howToMove(chessBoard.tiles.indexOf(king), chessBoard.tiles, color);
        return (staysInCheck(king, list, color));
    }

    private boolean staysInCheck(ChessTile king, List<ChessTile> moves, Color color){
        for (ChessTile c: moves){
            ChessTile.switchTiles(king, c);
            boolean isCheck = isChecked(c, color);
            ChessTile.switchTiles(c, king);
            if (!isCheck) {
                return false;
            }
        }
        return true;
    }

}
