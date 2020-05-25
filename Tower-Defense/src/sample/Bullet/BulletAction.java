package sample.Bullet;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BulletAction extends  Bullet{
    public BulletAction(String bulletURL,double posX , double posY, double width, double height, int speedB, int damageB, int rangeB){
        super(bulletURL,posX,posY,width,height,speedB,damageB,rangeB);
        this.setBulletIMG(new Image((bulletURL)));
        this.setBulletV(new ImageView(this.getBulletIMG()));
    }
}
