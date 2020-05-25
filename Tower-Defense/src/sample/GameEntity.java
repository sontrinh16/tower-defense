package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.Tile.GameTile;

import java.util.Collection;
import java.util.List;

public abstract class  GameEntity {
    private double posX;
    private double posY;
    private double width;
    private  double height;

    public GameEntity(double posX , double posY, double width,double height){
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
    }

    public double getPosX() {
        return this.posX;
    }
    public double getPosY() {
        return this.posY;
    }
    public  double getWidth(){
        return  this.width;
    }
    public double getHeight(){
        return this.height;
    }
    public double getCenterPosX(){
        return this.posX + this.width / 2;
    }
    public double getCenterPosY(){
        return this.posY + this.height / 2;
    }

    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setPosX(double posX){
        this.posX = posX;
    }
    public void setPosY(double posY) {
        this.posY = posY;
    }






}
