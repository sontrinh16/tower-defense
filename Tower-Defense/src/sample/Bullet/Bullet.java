package sample.Bullet;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import sample.Enemy.Enemy;
import sample.GameConfig;
import sample.GameEntity;
import sample.Main;
import sample.Tile.Tower.Tower;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public  abstract class Bullet extends GameEntity {

    private String bulletURL;
    private int damageB;
    private double rangeB;
    private int speedB;
    private double directionB;

    private  Image bulletIMG ;
    private  ImageView bulletV ;
    public Bullet(String bulletURL,double posX , double posY, double width, double height, int speedB, int damageB, int rangeB){
        super(posX, posY,width,height);
        this.damageB = damageB;
        this.rangeB = rangeB;
        this.speedB = speedB;
        this.bulletURL = bulletURL;


        this.directionB = directionB;
    }
    public int getDamageB(){
        return this.damageB;
    }
    public double getRangeB(){
        return this.rangeB;
    }
    public Image getBulletIMG(){return this.bulletIMG;}
    public ImageView getBulletV(){return  this.bulletV;}

    public double getDirectionB() { return this.directionB;}
    public double getSpeedB(){return this.speedB;}

    public double getStartPosX(Tower tower){
        return tower.getCenterPosX();
    }
    public double getStartPosY(Tower tower){
        return tower.getCenterPosY();
    }

    public void setBulletIMG(Image bulletIMG) { this.bulletIMG = bulletIMG; }

    public void setBulletV(ImageView bulletV) { this.bulletV = bulletV; }

    public void setDamageB(int damageB) {
        this.damageB = damageB;
    }

    public void setRangeB(int rangeB) {
        this.rangeB = rangeB;
    }

    public void setSpeedB(int speedB) { this.speedB = speedB; }

    public void setDirectionB(double directionB) { this.directionB = directionB; }

    public double distanceTo(Enemy enemy)
    {
        return Math.sqrt(Math.pow(this.getCenterPosX() - enemy.getCenterPosX(), 2) + Math.pow(this.getCenterPosY() - enemy.getCenterPosY(), 2));
    }

    public void update(Enemy enemy){
        this.directionB = Math.acos((enemy.getCenterPosX() - this.getCenterPosX()) / this.distanceTo(enemy)) ;
         if(enemy.getPosY() > this.getPosY())
            this.directionB = - this.directionB;

        this.getBulletV().setX(this.getBulletV().getX() + this.speedB *Math.cos(directionB  ));
        this.getBulletV().setY(this.getBulletV().getY() - this.speedB * Math.sin(directionB ));

    }
    public void draw(){
        this.bulletV.setX(this.getPosX());
        this.bulletV.setY(this.getPosY());
        Main.root.getChildren().add(bulletV);
    }
    public void delete(Tower tower){
        this.getBulletV().setX(getStartPosX(tower));
        this.getBulletV().setY(getStartPosY(tower));
    }
    public boolean checkCollision(Enemy enemy){
        return (this.getBulletV().getX() + GameConfig.BULLET_SIZE /2 > enemy.getPosX() && this.getBulletV().getX() + GameConfig.BULLET_SIZE /2 <= enemy.getPosX() + enemy.getWidth() &&
                this.getBulletV().getY() + GameConfig.BULLET_SIZE /2 > enemy.getPosY() && this.getBulletV().getY()+ GameConfig.BULLET_SIZE /2  <= enemy.getPosY() + enemy.getHeight())    ;
    }

}