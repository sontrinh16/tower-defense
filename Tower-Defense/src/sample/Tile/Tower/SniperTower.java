package sample.Tile.Tower;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Bullet.Bullet;
import sample.Bullet.BulletAction;
import sample.GameConfig;

public class SniperTower extends Tower {

    public SniperTower(double posX, double posY,double width,double height){
        super(posX,posY,width,height);
        this.setSpeed(GameConfig.SNIPER_TOWER_RATESHOT);
        this.setRange(GameConfig.SNIPER_TOWER_RANGE);
        this.setCost(GameConfig.SNIPER_TOWER_COIN);
        this.setTower(new Image((GameConfig.SNIPER_TOWER_URL)));
        this.setTowerV(new ImageView(new Image(GameConfig.SNIPER_TOWER_URL)));
        this.setCircle(new Circle(this.getPosX() + GameConfig.TILE_SIZE /2 , this.getPosY() + GameConfig.TILE_SIZE / 2 , GameConfig.SNIPER_TOWER_RANGE, Color.TRANSPARENT));
        this.setBullet( new BulletAction(GameConfig.SNIPER_TOWER_BULLET,this.getCenterPosX(),this.getCenterPosY(),GameConfig.BULLET_SIZE,GameConfig.BULLET_SIZE,GameConfig.SNIPER_TOWER_SPEED,GameConfig.SNIPER_TOWER_DAMAGE,GameConfig.SNIPER_TOWER_RANGE));
    }
}
