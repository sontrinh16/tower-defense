package sample.Tile.Tower;

import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Bullet.Bullet;
import sample.Enemy.Enemy;
import sample.GameConfig;
import sample.Main;
import sample.Tile.GameTile;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public abstract  class  Tower extends GameTile {
    private double damage;
    private double range;
    private int speed;
    private Image tower;
    private ImageView towerV;
    private Circle circle ;
    private double direction;
    private Bullet bullet;
    private MediaPlayer turret_build;
    private int cost;

    int tick = 0;
    int flag = 1;
    int indexShot = 0;
    private boolean sell = false;


    public Tower(double posX, double posY,double width,double height) {
        super(posX, posY,width,height);
        this.direction = 0;
        String s = new File("E:\\Game\\TowerDefense\\music\\tuturu.mp3").toURI().toString();
        this.turret_build = new MediaPlayer(new Media(s));
        this.turret_build.setVolume(0.3);
    }

    public MediaPlayer getTurret_build() {
        return turret_build;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Bullet getBullet() { return this.bullet; }

    public double getDamage() { return this.damage; }

    public double getRange() { return this.range; }

    public double getSpeed() { return this.speed; }

    public Image getTower() { return this.tower; }

    public ImageView getTowerV() { return this.towerV; }

    public boolean isSell() {
        return this.sell;
    }

    public void setSell(boolean sell) {
        this.sell = sell;
    }

    public double getDirection() { return direction;}

    public Circle getCircle() { return this.circle; }


    public void setBullet(Bullet bullet) { this.bullet = bullet; }

    public void setDirection(double direction) { this.direction = direction; }

    public void setDamage(double damage) { this.damage = damage; }

    public void setRange(double range) { this.range = range; }

    public void setSpeed(int speed) { this.speed = speed; }

    public void setCircle(Circle circle) { this.circle = circle; }

    public void setTower(Image tower) { this.tower = tower; }

    public void setTowerV(ImageView towerV) { this.towerV = towerV; }


    public Circle drawCircle(){
        this.circle = new Circle(this.getPosX() + GameConfig.TILE_SIZE /2 , this.getPosY() + GameConfig.TILE_SIZE / 2 , this.getRange(),Color.TRANSPARENT);
        this.circle.setStroke(Color.LIGHTGRAY);
        return this.circle;
    }
    public void draw(GraphicsContext gc, double posX, double posY){
        gc.drawImage(this.tower,posX,posY); // thay doi i va j
        gc.setStroke(Color.LIGHTGRAY);
        gc.setLineWidth(0.0);
    }

    public double distanceTo(Enemy enemy)
    {
        return Math.sqrt(Math.pow(this.getCenterPosX() - enemy.getCenterPosX(), 2) + Math.pow(this.getCenterPosY() - enemy.getCenterPosY(), 2));
    }
    public boolean checkRange( Enemy enemy){

        return this.distanceTo(enemy) <= this.getRange();
    }
    public boolean checkBulletRange(){
        return  (Math.sqrt(Math.pow(this.getPosX() - bullet.getBulletV().getX(), 2) + Math.pow(this.getPosY() - bullet.getBulletV().getY(), 2)) <= this.getRange());

    }
    public void shotClosetEnemy(List<Enemy> enemies){
        tick++;
        double closetEnemy = distanceTo(enemies.get(0));
        int index = indexShot ;
        for(int i = 0 ; i < enemies.size() ; i++){
            if(distanceTo(enemies.get(i)) < closetEnemy){
                closetEnemy = distanceTo(enemies.get(i));
                index = i;
            }
        }

        if(checkRange(enemies.get(index))){
            shootEnemy(enemies.get(index));
        }
        else if(!checkRange(enemies.get(index) )){
            this.bullet.delete(this);
        }
        if(!enemies.get(index).isAlive()){
            this.bullet.delete(this);
        }

    }
    public void shootEnemy(Enemy enemy){
        if(tick % this.getSpeed() == 0){
            flag = 1;
        }
        if(flag == 1)
        {
            if(!checkBulletRange()){
                this.bullet.delete(this);
            }
            if (bullet.checkCollision(enemy)) {
                bullet.delete(this);
                enemy.getHit(bullet.getDamageB());
                tick = 0;
                flag = 0;
            }
            else {
                bullet.update(enemy);
            }
        }
    }
    public void TowerEvent(){
        if(this.sell = true)
        {
            this.getTowerV().setX(this.getPosX());
            this.getTowerV().setY(this.getPosY());
            if(!Main.root.getChildren().contains(this.getTowerV()))
                Main.root.getChildren().add(this.getTowerV());

            this.getTowerV().setOnMouseEntered(e ->{
                Main.root.getChildren().add(this.drawCircle());
            });

            this.getTowerV().setOnMouseExited(e -> {
                Main.root.getChildren().remove(this.getCircle());
                Main.root.getChildren().remove(this.getTowerV());
            });
        }
    }



}
