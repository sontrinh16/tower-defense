package sample.Tile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.GameConfig;
import sample.Tile.Tower.MachineTower;
import sample.Tile.Tower.NormalTower;
import sample.Tile.Tower.SniperTower;
import sample.Tile.Tower.Tower;
import sample.TileMap;

import java.util.ArrayList;
import java.util.List;

public class ButtonAction extends Button {
    public  Button button ;

    public ButtonAction(String url, double posX,double posY, Group root){
        this.button = new Button();
        this.button.setLayoutX(posX);
        this.button.setLayoutY(posY);
        this.button.setBackground(null);
        this.button.setGraphic(new ImageView(new Image(url)));

        root.getChildren().add(this.button);
    }

    public ButtonAction(String url, double posX, double posY, Pane pane){
        this.button = new Button();
        this.button.setLayoutX(posX);
        this.button.setLayoutY(posY);
        this.button.setBackground(null);
        this.button.setGraphic(new ImageView(new Image(url)));

        pane.getChildren().add(this.button);
    }

    public Button getButton() {
        return this.button;
    }

    public void buy(Scene main_scene, Group root, GraphicsContext gc2, TileMap tileMap, List<Tower> towers, int tick){
        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ImageCursor cursor = null;
                switch (tick){
                    case 1: {
                        cursor = new ImageCursor(new Image(GameConfig.NORMAL_TOWER_URL));
                        break;
                    }
                    case 2:{
                        cursor = new ImageCursor(new Image(GameConfig.MACHINEGUN_TOWER_URL));
                        break;
                    }
                    case 3:{
                        cursor = new ImageCursor(new Image(GameConfig.SNIPER_TOWER_URL));
                        break;
                    }
                }
                root.setCursor(cursor);
                main_scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(checkCoordinate(tileMap,mouseEvent)){
                            Tower tower_ = buyTower(tileMap.getPosXTile(mouseEvent),tileMap.getPosYTile(mouseEvent),tileMap,towers,tick);
                            if(tower_.getCost() <= tileMap.getCoin_value()) {
                                towers.add(tower_);
                                tower_.getTurret_build().setVolume(0.3);
                                tower_.getTurret_build().play();
                                tileMap.setCoin_value(tileMap.getCoin_value() - tower_.getCost());
                                towers.get(towers.size() - 1).draw(gc2, tileMap.getPosXTile(mouseEvent), tileMap.getPosYTile(mouseEvent));
                                towers.get(towers.size() - 1).getBullet().draw();
                            }
                            root.setCursor(Cursor.DEFAULT);
                        }
                    }
                });
            }
        });
    }
    public void sell(Scene main_scene, Group root, GraphicsContext gc2, TileMap tileMap, List<Tower> towers){
        this.button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ImageCursor cursor1= new ImageCursor(new Image(GameConfig.DELETE_BUTTON));
                root.setCursor(cursor1);
                main_scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Tower tower_ = sellTower(tileMap.getPosXTile(mouseEvent),tileMap.getPosYTile(mouseEvent),tileMap,towers,root,gc2);
                        if(tower_ != null){
                            tower_.setSell(true);
                            gc2.clearRect(tower_.getPosX(),tower_.getPosY(),30,30);
                            tileMap.setCoin_value(tileMap.getCoin_value() + tower_.getCost() / 2);
                            root.getChildren().remove(tower_.getCircle());
                            root.getChildren().remove(tower_.getBullet().getBulletV());
                            towers.remove(tower_);
                        }
                        root.setCursor(Cursor.DEFAULT);
                    }
                });
            }
        });
    }
    private Tower sellTower(int posX,int posY,TileMap tileMap,List<Tower> towers,Group root,GraphicsContext gc2){
        Tower tower_ = null;
        if(!towers.isEmpty()){
            for(int i = 0 ; i < towers.size() ; i++){
                if(towers.get(i).getPosX() == posX && towers.get(i).getPosY() == posY){
                    tower_ = towers.get(i);
                }
            }
        }
        return tower_;
    }

    private Tower buyTower(int posX, int posY,TileMap tileMap,List<Tower> towers,int tick) {
        Tower tower = null;
        switch (tick) {
            case 1: {
                tower = new NormalTower(posX, posY, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                break;
            }
            case 2: {
                tower = new MachineTower(posX, posY, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                break;
            }
            case 3: {
                tower = new SniperTower(posX, posY, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                break;
            }
        }
        return tower;
    }
    private boolean checkCoordinate(TileMap tileMap , MouseEvent mouseEvent){
        double mouse_x = mouseEvent.getX();
        double mouse_y = mouseEvent.getY();

        int mouse_col = (int)  mouse_x / GameConfig.TILE_SIZE;
        int mouse_row = (int) mouse_y / GameConfig.TILE_SIZE;

        if(tileMap.getIndexTitle(mouse_row,mouse_col) == 0)
            return true;
        return false;
    }
    public void exit(){
        this.button.setOnAction(event -> {
            System.exit(-1);
        });
    }
    public void start(Stage stage,Scene main_scene,GraphicsContext gc_tmp,GraphicsContext gc2,TileMap tileMap,Spawner spawner){
        this.button.setOnAction(e->{
            stage.setScene(main_scene);
            tileMap.drawMap(gc_tmp,30);
            tileMap.drawMap(gc2,30);

            this.button.setDisable(true);
            this.button.setVisible(false);
            spawner.setSpawning(true);
            spawner.setFirst_wave(1);
        });
    }
}