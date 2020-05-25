package sample.Enemy;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import sample.GameConfig;
import sample.Main;

public class HealthBar {
    public Rectangle outerHealthRect;
    public Rectangle innerHealthRect;

    public HealthBar(double x, double y,double health_point) {

         double height = 2 ;
        double outerWidth =health_point;
        double innerWidth =health_point;

        outerHealthRect = new Rectangle(x, y, outerWidth, height);
        outerHealthRect.setStroke(Color.BLACK);
        outerHealthRect.setStrokeWidth(2);
        outerHealthRect.setStrokeType(StrokeType.OUTSIDE);
        outerHealthRect.setFill(Color.RED);

        innerHealthRect = new Rectangle(x+2, y+2, innerWidth, height);
        innerHealthRect.setStrokeType(StrokeType.OUTSIDE);
        innerHealthRect.setFill(Color.LIMEGREEN);
    }

    public void draw_bar(){
        Main.root.getChildren().add(outerHealthRect);
        Main.root.getChildren().add(innerHealthRect);
    }

    public void setHealth_point(double value, double Health_Max){
        this.innerHealthRect.setWidth(outerHealthRect.getWidth() * value/Health_Max);
    }
}
