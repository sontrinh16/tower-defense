package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class TileMap {
    private double tile_size;
    private Image grass = new Image("file:grass1.png");
    private Image road = new Image("file:road.png");
    private Image spawn = new Image("file:spawn.png");
    private Image end = new Image("file:end.png");
    private int coin_value;
    private Text coin;
    private int[][] ID = {
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,9,2,2,2,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,2,2,2,2,2,2,2,2,2,2,3,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0},
            {0,0,3,4,4,4,4,4,4,4,4,4,4,4,4,4,0,0,0,0},
            {0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0},
            {0,3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,0,0,0},
            {0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,2,2,2,2,2,2,2,2,2,2,2,2,2,10,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    public TileMap(){
        this.coin_value = GameConfig.START_COIN;
    }
    public int[][] getID(){
        return this.ID;
    }
    public int getIndexTitle (int row , int column){
        return ID[row][column];
    }
    public int getPosXTile(MouseEvent mouseEvent){
        int index_x = (int)mouseEvent.getX() / GameConfig.TILE_SIZE;
        return index_x * GameConfig.TILE_SIZE;
    }
    public int getPosYTile(MouseEvent mouseEvent){
        int index_y = (int)mouseEvent.getY() / GameConfig.TILE_SIZE;
        return index_y * GameConfig.TILE_SIZE;
    }
    public void setCoin_value(int coin_value) {
        this.coin_value = coin_value;
    }

    public int getCoin_value() {
        return coin_value;
    }

    public Text getCoin() {
        return coin;
    }

    public void drawMap(GraphicsContext gc, double tile_size){
        for(int i = 0 ; i < 15 ; i++){
            for (int j = 0 ; j < 20 ; j++){
                String str ="file:img/" + Integer.toString(ID[i][j]) + ".png";
                gc.drawImage(new Image(str),j*GameConfig.TILE_SIZE,i*GameConfig.TILE_SIZE);
                gc.setStroke(Color.LIGHTGRAY);
                gc.setLineWidth(0.5);
                //gc.strokeRect(i*tile_size,j*tile_size,tile_size,tile_size);
            }
        }
    }
    public void set_coin(){
        String s = "COIN: " + this.coin_value;
        this.coin = new Text(500,30,s);
        this.coin.setFont(new Font("Consolas", 12));
        this.coin.setStroke(Color.BLACK);
        this.coin.setStrokeWidth(0.25);
        this.coin.setFill(Color.YELLOW);
        this.coin.setScaleX(2);
        this.coin.setScaleY(2);
        Main.root.getChildren().add(this.coin);
    }

    public void update_coin(){
        String s = "COIN: " + this.coin_value;
        this.coin.setText(s);

    }


}