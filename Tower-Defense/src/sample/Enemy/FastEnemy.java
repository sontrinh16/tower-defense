package sample.Enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.GameConfig;

public class FastEnemy extends Enemy {
    private String FAST_ENEMY = GameConfig.FAST_ENEMY_URL;
    private Image fast_enemy = new Image(FAST_ENEMY);

    public FastEnemy(double posX,double posY, double width, double height){
        super(posX,posY,width,height,GameConfig.FAST_ENEMY_SPEED, GameConfig.FAST_ENEMY_HEALTHPOINT,GameConfig.FAST_ENEMY_DAMAGE,GameConfig.FAST_ENEMY_COST);
        this.healthBar = new HealthBar(this.getPosX()-5,this.getPosY(),GameConfig.HEALTH_BAR_SIZE);
        this.enemyV = new ImageView(fast_enemy);
    }

    @Override
    public void getHit(double damage) {
        this.setHealth(this.getHealth() - damage);
        this.healthBar.setHealth_point(this.getHealth(),GameConfig.FAST_ENEMY_HEALTHPOINT);
    }
}
