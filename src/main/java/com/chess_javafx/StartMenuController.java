package com.chess_javafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartMenuController implements Initializable {
    @FXML private Parent root;
    @FXML private Button exitGameBtn;
    @FXML private ToggleButton classicButton;
    @FXML private ToggleButton magicButton;
    @FXML private TextField player1Field;
    @FXML private TextField player2Field;
    private GameManager gameManager;

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public GameData gameData;

    public void startGame(ActionEvent actionEvent) throws IOException {
        String player1Name = player1Field.getText();
        String player2Name = player2Field.getText();
        boolean gameMode = (classicButton.isSelected() ? true : false);
        gameData = new GameData(player1Name, player2Name, gameMode);
        gameManager.playGame(gameData);
    }

    public void showSettings(ActionEvent actionEvent) {
    }

    @FXML
    public void exitGame(ActionEvent actionEvent) {
        Stage stage = (Stage) exitGameBtn.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

    public void selectClassic(ActionEvent actionEvent) {
        classicButton.setSelected(true);
        magicButton.setSelected(false);
    }

    public void selectMagic(ActionEvent actionEvent) {
        magicButton.setSelected(true);
        classicButton.setSelected(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
