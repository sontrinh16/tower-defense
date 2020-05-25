package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;

public class GameStage {
    private final long width;
    private final long height;

    private final List<GameEntity> gameEntities;

    public GameStage(long width, long height, List<GameEntity> gameEntities) {
        this.width = width;
        this.height = height;
        this.gameEntities = List.copyOf(gameEntities);
    }

    public long getWidth() {
        return this.width;
    }

    public long getHeight() {
        return this.height;
    }

    public List<GameEntity> getGameEntities() {
        return gameEntities;
    }

}
