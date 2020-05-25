package sample.Tile.Tower;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import sample.Bullet.Bullet;
import sample.Bullet.BulletAction;
import sample.Enemy.Enemy;
import sample.Enemy.NormalEnemy;
import sample.GameConfig;
import sample.Tile.GameTile;

import java.util.*;


public  class NormalTower extends Tower {


    public NormalTower( double posX, double posY,double width,double height){
        super(posX,posY,width,height);
        this.setSpeed(GameConfig.NORMAL_TOWER_RATESHOT);
        this.setRange(GameConfig.NORMAL_TOWER_RANGE);
        this.setCost(GameConfig.NORMAL_TOWER_COIN);
        this.setTower(new Image((GameConfig.NORMAL_TOWER_URL)));
        this.setTowerV(new ImageView(new Image(GameConfig.NORMAL_TOWER_URL)));
        this.setCircle(new Circle(this.getPosX() + GameConfig.TILE_SIZE /2 , this.getPosY() + GameConfig.TILE_SIZE / 2 , GameConfig.NORMAL_TOWER_RANGE,Color.TRANSPARENT));
        this.setBullet( new BulletAction(GameConfig.NORMAL_TOWER_BULLET,this.getCenterPosX(),this.getCenterPosY(),GameConfig.BULLET_SIZE,GameConfig.BULLET_SIZE,GameConfig.NORMAL_TOWER_SPEED,GameConfig.NORMAL_TOWER_DAMAGE,GameConfig.NORMAL_TOWER_RANGE));

    }







}
