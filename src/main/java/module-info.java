module com.chess_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.chess_javafx to javafx.fxml;
    exports com.chess_javafx;
}