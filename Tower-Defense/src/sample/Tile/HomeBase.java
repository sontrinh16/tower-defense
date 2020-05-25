package sample.Tile;

import sample.Enemy.Enemy;
import sample.Enemy.HealthBar;
import sample.GameConfig;
import sample.Main;

public class HomeBase extends GameTile {
    private HealthBar HP;
    private double HP_point;
    public HomeBase(double posX , double posY,double width, double height) {
        super(posX, posY, width, height);
        this.HP_point = GameConfig.START_HP_POINT;
        this.HP = new HealthBar(this.getPosX(),this.getPosY()-5, GameConfig.HOME_HEALTHPOINT_SIZE);
        this.HP.innerHealthRect.setY(this.getPosY()-5);
        this.HP.innerHealthRect.setX(this.getPosX());
    }

    public HealthBar getHP() {
        return this.HP;
    }

    public double getHP_point() {
        return this.HP_point;
    }

    public void setHP_point(double HP_point) {
        this.HP_point = HP_point;
    }

    public void getDamage (Enemy enemy){
        if (enemy.reach_target()){
             this.setHP_point(this.getHP_point() - enemy.getDamage());
             this.HP.setHealth_point(this.getHP_point(),GameConfig.START_HP_POINT);
        }
    }
    public void draw_health_bar(){
        this.HP.draw_bar();
    }
}
