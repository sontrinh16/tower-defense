package sample;

import java.util.ArrayList;
import java.util.List;

public class GameField {

    private double width;
    private double height;

    public GameField(GameStage gameStage)
    {
        this.width = gameStage.getWidth();
        this.height = gameStage.getHeight();

    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }


}
