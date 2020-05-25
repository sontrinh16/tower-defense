package sample.Enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.GameConfig;

public class Tanker extends Enemy {
    private String TANKER = GameConfig.TANKER_ENEMY_URL;
    private Image tanker = new Image(TANKER);

    public Tanker(double posX,double posY, double width, double height){
        super(posX,posY,width,height,GameConfig.TANKER_ENEMY_SPEED, GameConfig.TANKER_HEALTHPOINT,GameConfig.TANKER_ENEMY_DAMAGE,GameConfig.TANKER_ENEMY_COST);
        this.healthBar = new HealthBar(this.getPosX()-5,this.getPosY(),GameConfig.HEALTH_BAR_SIZE);
        this.enemyV = new ImageView(tanker);
    }

    @Override
    public void getHit(double damage) {
        this.setHealth(this.getHealth() - damage);
        this.healthBar.setHealth_point(this.getHealth(),GameConfig.TANKER_HEALTHPOINT);
    }
}
