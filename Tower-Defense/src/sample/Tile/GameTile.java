package sample.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.GameConfig;
import sample.GameEntity;

public abstract class GameTile extends GameEntity {
    public GameTile( double posX , double posY,double width, double height){
        super(posX,posY,width,height);
    }
}
