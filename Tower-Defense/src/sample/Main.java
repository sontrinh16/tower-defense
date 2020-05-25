package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import sample.Bullet.Bullet;
import sample.Enemy.Enemy;
import sample.Enemy.NormalEnemy;
import sample.Tile.ButtonAction;
import sample.Tile.HomeBase;
import sample.Tile.Spawner;
import sample.Tile.Tower.MachineTower;
import sample.Tile.Tower.NormalTower;
import sample.Tile.Tower.SniperTower;
import sample.Tile.Tower.Tower;


import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Main extends Application {

    private Image back_ground = new Image(GameConfig.STAGE_BACKGROUND);
    public static List<Enemy> enemies = new ArrayList<>();
    public static List<Tower> normalTowers = new ArrayList<>();
    public static Group root = new Group();
    public static TileMap tileMap = new TileMap();

    Spawner spawner = new Spawner(30,30,30,30);
    HomeBase homeBase = new HomeBase(GameConfig.HOME_X,GameConfig.HOME_Y,30,30);
    @Override
    public void start(Stage stage) throws Exception{

        Scene main_scene = new Scene(root);
        stage.setTitle("Tower Defense");
        Pane pane = new Pane();

        Canvas canvas_tmp = new Canvas(GameConfig.STAGE_WIDTH,GameConfig.STAGE_HEIGHT);
        root.getChildren().add(canvas_tmp);

        Canvas canvas2 = new Canvas(GameConfig.STAGE_WIDTH,GameConfig.STAGE_HEIGHT);
        root.getChildren().add(canvas2);

        Canvas canvas = new Canvas(GameConfig.STAGE_WIDTH,GameConfig.STAGE_HEIGHT);
        pane.getChildren().add(canvas);

        GraphicsContext gc_tmp = canvas_tmp.getGraphicsContext2D();
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Creating start/exit button
        ButtonAction start_game = new ButtonAction(GameConfig.START_BUTTON,250,225,pane);
        ButtonAction exit_game = new ButtonAction(GameConfig.EXIT_BUTTON,250,270,pane);


        gc.drawImage(back_ground,0,0,GameConfig.STAGE_WIDTH,GameConfig.STAGE_HEIGHT);

        Scene scene = new Scene(pane);
        stage.setScene(scene);

        exit_game.exit();
        start_game.start(stage,main_scene,gc_tmp,gc2,tileMap,spawner);

        ButtonAction NORbutton = new ButtonAction(GameConfig.NORMAL_TOWER_URL,550,50,root);
        ButtonAction MACbutton = new ButtonAction(GameConfig.MACHINEGUN_TOWER_URL,550,80,root);
        ButtonAction SNIbutton = new ButtonAction(GameConfig.SNIPER_TOWER_URL,550,110,root);
        ButtonAction SELLbutton = new ButtonAction(GameConfig.DELETE_BUTTON,550, 150,root);

        NORbutton.buy(main_scene,root,gc2,tileMap,normalTowers,1);
        MACbutton.buy(main_scene,root,gc2,tileMap,normalTowers,2);
        SNIbutton.buy(main_scene,root,gc2,tileMap,normalTowers,3);
        SELLbutton.sell(main_scene,root,gc2,tileMap,normalTowers);

        tileMap.set_coin();
        homeBase.getHP().draw_bar();

        String urlstring = new File(GameConfig.MUSIC_BACKGROUND).toURI().toString();
        MediaPlayer player = new MediaPlayer(new Media(urlstring));
        String url = new File(GameConfig.MUSIC_LOSE).toURI().toString();
        MediaPlayer endgame = new MediaPlayer(new Media(url));
        new  AnimationTimer(){
            @Override
            public void handle(long currentNanoTime) {

                player.setOnEndOfMedia(new Runnable() {
                    @Override
                    public void run() {
                        player.seek(Duration.ZERO);
                    }
                });
                player.setVolume(0.1);
                player.play();
                spawner.Spawn();
               // SELLbutton.sell(main_scene,root,gc2,tileMap,normalTowers);
               for ( int i = 0; i < enemies.size(); i++ ){
                   enemies.get(i).update(tileMap.getID(),30);
                   // may be get risk
                   if (enemies.get(i).reach_target()){
                       homeBase.getDamage(enemies.get(i));
                       enemies.get(i).delete();
                       enemies.get(i).getAttack().setVolume(0.3);
                       enemies.get(i).getAttack().play();
                       enemies.remove(i);
                   }

               }
                if (homeBase.getHP_point() <= 0 ){
                    this.stop();
                    root.getChildren().clear();
                    player.stop();
                    stage.setScene(scene);
                    gc.drawImage(new Image("file:img//pathetic.png"),0,0,600,450);
                    endgame.setVolume(0.3);
                    endgame.play();
//                    ButtonAction exit_game = new ButtonAction(GameConfig.EXIT_BUTTON,250,260,pane);
//                    pane.getChildren().add(exit_game.getButton());
                }
                tileMap.update_coin();
                if(!enemies.isEmpty()){
                    for (Tower tower : normalTowers){
                        tower.shotClosetEnemy(enemies);
                        tower.TowerEvent();

                    }
                }
                for (int i = 0; i<enemies.size(); i++){
                    if (!enemies.get(i).isAlive()){
                        tileMap.setCoin_value(tileMap.getCoin_value() + enemies.get(i).getCoin());
                        enemies.get(i).delete();
                        enemies.get(i).getCoin_gain().setVolume(0.4);
                        enemies.get(i).getCoin_gain().play();
                        enemies.remove(i);
                    }
                }
            }
        }.start();
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
