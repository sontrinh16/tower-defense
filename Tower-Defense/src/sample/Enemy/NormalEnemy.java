package sample.Enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.GameConfig;
import sample.Main;

public class NormalEnemy extends Enemy {
    private String NORMAL_ENEMY = GameConfig.NORMAL_ENEMY_URL;
    private Image normal_enemy = new Image(NORMAL_ENEMY);


    public NormalEnemy(double posX,double posY, double width, double height){
        super(posX,posY,width,height,GameConfig.NORMAL_ENEMY_SPEED, GameConfig.NORMAL_ENEMY_HEALTHPOINT,GameConfig.NORMAL_ENEMY_DAMAGE,GameConfig.NORMAL_ENEMY_COST);
        this.healthBar = new HealthBar(this.getPosX()-5,this.getPosY(),GameConfig.HEALTH_BAR_SIZE);
        this.enemyV = new ImageView(normal_enemy);
    }
    public void getHit(double damage){
        this.setHealth(this.getHealth() - damage);
        this.healthBar.setHealth_point(this.getHealth(),GameConfig.NORMAL_ENEMY_HEALTHPOINT);
    }


}
