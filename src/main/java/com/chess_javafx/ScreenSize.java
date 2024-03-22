package com.chess_javafx;
import java.awt.*;

public class ScreenSize {

    private Dimension screenSize;
    private boolean done = false;
    public ScreenSize(){
        getScreenSize();
    }

    private void getScreenSize(){
        if (!done) {
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            done = true;
        }
    }
    public double getWidth(){
        return screenSize.getWidth();
    }
    public double getHeight(){
        return screenSize.getHeight();
    }

    public double getStartWidth(){
        return getWidth()/3;
    }
    public double getStartHeight(){
        return getHeight()/1.5;
    }

}
