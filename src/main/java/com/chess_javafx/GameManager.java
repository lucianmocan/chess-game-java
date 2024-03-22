package com.chess_javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

class GameData {
    private String player1Name;
    private String player2Name;
    private boolean gameMode;

    public GameData(String player1Name, String player2Name, boolean gameMode) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.gameMode = gameMode;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public boolean getGameMode() {
        return gameMode;
    }

    public void setGameMode(boolean gameMode) {
        this.gameMode = gameMode;
    }
}

public class GameManager implements StartListener {
    private FXMLLoader startLoader;
    private FXMLLoader chessLoader;

    public GameData gameData;
    private Stage stage;
    public ScreenSize screenSize;

    GameManager(Stage stage) throws IOException {
        startLoader = new FXMLLoader(ChessApplication.class.getResource("startup_menu.fxml"));
        chessLoader = new FXMLLoader(ChessApplication.class.getResource("chess.fxml"));
        screenSize = new ScreenSize();
        this.stage = stage;
        this.startMenu();
    }

    public void startMenu() throws IOException {
        Scene scene = new Scene(startLoader.load(), screenSize.getStartHeight(), screenSize.getStartWidth());
        StartMenuController start = startLoader.getController();
        start.setGameManager(this);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void startChess() throws IOException {
        Scene scene = new Scene(chessLoader.load(), screenSize.getWidth()/2, screenSize.getHeight()/1.2);
        ChessController chess = chessLoader.getController();
        chess.gameMode = gameData.getGameMode();
        chess.initData(gameData);
        chess.startChess();
        stage.setScene(scene);
        stage.show();
    }

    public void playGame(GameData gameData) throws IOException {
        this.gameData = gameData;
        startChess();
    }
}
