package sample.Enemy;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.GameConfig;



public class Boss extends Enemy  {
    private String BOSS = GameConfig.BOSS_ENEMY_URL;
    private Image boss = new Image(BOSS);

    public Boss(double posX,double posY, double width, double height){
        super(posX,posY,width,height,GameConfig.BOSS_ENEMY_SPEED, GameConfig.BOSS_HEALTHPOINT,GameConfig.BOSS_ENEMY_DAMAGE,GameConfig.BOSS_ENEMY_COST);
        this.healthBar = new HealthBar(this.getPosX()-5,this.getPosY(),GameConfig.HEALTH_BAR_SIZE);
        this.enemyV = new ImageView(boss);

    }

    @Override
    public void getHit(double damage) {
        this.setHealth(this.getHealth() - damage);
        this.healthBar.setHealth_point(this.getHealth(),GameConfig.BOSS_HEALTHPOINT);
    }
}

