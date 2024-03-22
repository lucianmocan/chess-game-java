package com.chess_javafx;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;


public class ChessApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        GameManager gm = new GameManager(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}