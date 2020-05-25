package sample.Enemy;

import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.GameConfig;
import sample.GameEntity;
import sample.Main;

import java.io.File;

public abstract class Enemy extends GameEntity {
    private double health;
    private double damage;
    protected HealthBar healthBar;
    private double speed;
    public ImageView enemyV;
    private int coin;
    private MediaPlayer dead;
    private MediaPlayer attack_homebase;
    private MediaPlayer coin_gain;


    public Enemy(double x,double y, double width,double height,double speed, double health, double damage,int coin){
        super(x,y,width,height);
        this.health = health;
        this.damage = damage;
        this.speed = speed;
        this.coin = coin;
        String s1 = new File(GameConfig.MUSIC_ATTACK_HOMEBASE).toURI().toString();
        this.attack_homebase = new MediaPlayer(new Media(s1));
        String s2 = new File(GameConfig.MUSIC_EARNCOIN).toURI().toString();
        this.coin_gain = new MediaPlayer(new Media(s2));
    }

    public MediaPlayer getAttack() {
        return this.attack_homebase;
    }

    public MediaPlayer getCoin_gain() {
        return coin_gain;
    }

    public MediaPlayer getDead() {
        return dead;
    }
    public ImageView getEnemyV() {
        return this.enemyV;
    }
    public double getSpeed() {
        return this.speed;
    }
    public double getHealth() {
        return this.health;
    }
    public HealthBar getHealthBar() {
        return this.healthBar;
    }
    public double getDamage() { return this.damage; }
    public int getCoin() {
        return this.coin;
    }

    public void setHealth(double health){
        this.health = health;
    }

    public boolean isAlive(){
        return (this.getHealth() > 0);
    }
    public void draw() {
        Main.root.getChildren().add(enemyV);
        this.healthBar.draw_bar();
    }

    public void update(int[][] ID,double tile_size) {

        double speed_x = 0;
        double speed_y = 0;
        int tile_number_y = (int) (this.getPosX()/tile_size);
        int tile_number_x = (int) (this.getPosY()/tile_size);
        if (ID[tile_number_x][tile_number_y] == 2 || ID[tile_number_x][tile_number_y] == 9){
            speed_x =  this.getSpeed();
            speed_y = 0;
            this.enemyV.setRotate(0);
            this.enemyV.setScaleY(1);
        }
        else if (ID[tile_number_x][tile_number_y] == 3){
            if(this.getPosX()>tile_number_y*tile_size)
            {
                speed_x = - this.getSpeed();
                speed_y = 0;
            }
            else {
                speed_x = 0;
                speed_y = this.getSpeed();
            }
        }
        else if (ID[tile_number_x][tile_number_y] == 4){
            speed_x = - this.getSpeed();
            speed_y = 0;
            this.enemyV.setRotate(180);
            this.enemyV.setScaleY(-1);
        }
        this.setPosX(this.getPosX() + speed_x);
        this.setPosY(this.getPosY() + speed_y);
        enemyV.relocate(this.getPosX(),this.getPosY());
        this.healthBar.outerHealthRect.relocate(this.getPosX()-5,this.getPosY());
        this.healthBar.innerHealthRect.relocate(this.getPosX()-3,this.getPosY()+2);

    }


    public  void delete(){
        Main.root.getChildren().remove(enemyV);
        Main.root.getChildren().remove(this.healthBar.innerHealthRect);
        Main.root.getChildren().remove(this.healthBar.outerHealthRect);
    }

    public boolean reach_target(){
        return (this.getPosX() == GameConfig.HOME_X  && this.getPosY() == GameConfig.HOME_Y );
    }
    public void remove_from_game(){
            this.delete();
    }
    public abstract void getHit(double damage);

}