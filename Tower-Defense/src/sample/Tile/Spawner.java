package sample.Tile;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sample.Enemy.*;
import sample.GameConfig;
import sample.Main;

import java.io.File;

public class Spawner extends  GameTile {
    private int spawnrate = 500;
    private int waverate = 500;
    private int timer2 = 0;
    private int timer = 0;
    private int wave;
    private boolean isSpawning = false;
    private int enemy_number;
    private int normal_number;
    private int fast_number;
    private int tanker_number;
    private int boss_number;
    private int counter = 0;


    public Spawner(double posX , double posY,double width, double height) {
        super(posX, posY, width, height);
        this.enemy_number = 7;
        this.normal_number = this.enemy_number;
        this.fast_number = 0;
        this.tanker_number = 0;
        this.boss_number = 0;
    }

    public void setFirst_wave(int wave) {
        this.wave = wave;
    }

    public int getWave() {
        return this.wave;
    }

    public void setSpawnrate(int spawnrate) {
        this.spawnrate = spawnrate;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setTimer2(int timer2) {
        this.timer2 = timer2;
    }

    public int getTimer2() {
        return timer2;
    }

    public int getTimer() {
        return this.timer;
    }

    public void setSpawning(boolean spawning) {
        this.isSpawning = spawning;
    }

    public void calculate_wave_power(){
        if (this.wave % 3 == 0){
            this.fast_number += 1;
        }
        if (this.wave % 4 == 0){
            this.tanker_number += 1;
        }
        if (this.wave % 5 == 0){
            this.boss_number = 1;
        }
        if (this.wave % 5 != 0){
            this.boss_number = 0;
        }
        this.normal_number = this.enemy_number - this.fast_number - this.tanker_number - this.boss_number;
        while (this.normal_number < 0){
            this.enemy_number *= 2;
            this.normal_number = this.enemy_number - this.fast_number - this.tanker_number - this.boss_number;
        }
        if (this.wave % 10 == 0){
            GameConfig.NORMAL_ENEMY_HEALTHPOINT *= 1.5;
        }
        if (this.wave % 12 == 0){
            GameConfig.FAST_ENEMY_HEALTHPOINT *=1.5;
        }
        if (this.wave % 15 == 0){
            GameConfig.TANKER_HEALTHPOINT *=1.5;
        }
    }

    public void Spawn() {
        if (this.isSpawning && this.counter < this.enemy_number) {
            for (int i = 0; i < this.normal_number; i++) {
                if (this.getTimer() < this.spawnrate) {
                    this.setTimer(this.getTimer() + 1);
                }
                else {
                    Enemy enemy = new NormalEnemy(GameConfig.SPAWN_POSX, GameConfig.SPAWN_POSY, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                    Main.enemies.add(enemy);
                    if (!Main.root.getChildren().contains(enemy.enemyV)) {
                        enemy.draw();
                        this.setTimer(0);
                        this.counter += 1;
                    }
                }
            }
            for (int i = 0; i < this.fast_number; i++) {
                if (this.getTimer() < this.spawnrate) {
                    this.setTimer(this.getTimer() + 1);
                }
                else {
                    Enemy enemy = new FastEnemy(GameConfig.SPAWN_POSX, GameConfig.SPAWN_POSY, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                    Main.enemies.add(enemy);
                    if (!Main.root.getChildren().contains(enemy.enemyV)) {
                        enemy.draw();
                        this.setTimer(0);
                        this.counter += 1;
                    }

                }
            }
            for (int i = 0; i < this.tanker_number; i++) {
                if (this.getTimer() < this.spawnrate) {
                    this.setTimer(this.getTimer() + 1);
                }
                else {
                    Enemy enemy = new Tanker(GameConfig.SPAWN_POSX, GameConfig.SPAWN_POSY, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                    Main.enemies.add(enemy);
                    if (!Main.root.getChildren().contains(enemy.enemyV)) {
                        enemy.draw();
                        this.setTimer(0);
                        this.counter += 1;
                    }
                }
            }
            for (int i = 0; i < this.boss_number; i++) {
                if (this.getTimer() < this.spawnrate) {
                    this.setTimer(this.getTimer() + 1);
                }
                else {
                    Enemy enemy = new Boss(GameConfig.SPAWN_POSX, GameConfig.SPAWN_POSY, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
                    Main.enemies.add(enemy);
                    if (!Main.root.getChildren().contains(enemy.enemyV)) {
                        enemy.draw();
                        this.setTimer(0);
                        this.counter += 1;
                    }

                }
            }

        }
        if (Main.enemies.size() == this.enemy_number){
            this.setSpawning(false);
        }
        else if (Main.enemies.isEmpty() && this.getWave() != 0){
            if (this.getTimer2() < this.waverate) {
                this.setTimer2(this.getTimer2() + 1);
            }
            else {
                this.setFirst_wave(this.getWave() + 1);
                this.calculate_wave_power();
                this.setSpawning(true);
                this.setTimer2(0);
                this.counter = 0;
            }
        }
    }


}
