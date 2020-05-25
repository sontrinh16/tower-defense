package sample;

public final class GameConfig {

    // BULLET CONFIG
    public static final int TILE_SIZE = 30;
    public static final int BULLET_SIZE = 15;

    // TOWER CONFIG
    public static final int NORMAL_TOWER_SPEED = 15;
    public static final int NORMAL_TOWER_RATESHOT = 20;
    public static final String NORMAL_TOWER_URL = "file:img//normaltower.png";
    public static final int NORMAL_TOWER_RANGE = 100;
    public static final int NORMAL_TOWER_DAMAGE = 10;
    public static final int NORMAL_TOWER_COIN = 20;
    public static final String NORMAL_TOWER_BULLET = "file:img//normalbullet.png";

    public static final int MACHINEGUN_TOWER_SPEED = 20;
    public static final int MACHINEGUM_TOWER_RATESHOT = 4;
    public static final String MACHINEGUN_TOWER_URL = "file:img//machineguntower.png";
    public static final int MACHINEGUN_TOWER_RANGE = 80;
    public static final int MACHINEGUN_TOWER_DAMAGE = 6;
    public static final int MACHINEGUN_TOWER_COIN = 50;
    public static final String MACHINEGUN_TOWER_BULLET = "file:img//machinebullet.png";


    public static final int SNIPER_TOWER_SPEED = 30;
    public static final int SNIPER_TOWER_RATESHOT = 100;
    public static final String SNIPER_TOWER_URL = "file:img//snipertower.png";
    public static final int SNIPER_TOWER_RANGE = 150;
    public static final int SNIPER_TOWER_DAMAGE = 50;
    public static final int SNIPER_TOWER_COIN = 30;
    public static final String SNIPER_TOWER_BULLET = "file:img//sniperbullet.png";

    // ENEMY CONFIG
    public static final double SPAWN_POSX = 30;
    public static final double SPAWN_POSY = 30;

    public static final String NORMAL_ENEMY_URL = "file:img//normalenemy.png";
    public static final int NORMAL_ENEMY_COST = 10;
    public static final double NORMAL_ENEMY_DAMAGE = 5;
    public static final double NORMAL_ENEMY_SPEED =  0.5;

    public static final String FAST_ENEMY_URL ="file:img//fastenemy.png";
    public static final int FAST_ENEMY_COST = 10;
    public static final double FAST_ENEMY_DAMAGE = 5;
    public static final double FAST_ENEMY_SPEED = 2;

    public static final String TANKER_ENEMY_URL = "file:img//tanker.png";
    public static final int TANKER_ENEMY_COST = 20;
    public static final double TANKER_ENEMY_DAMAGE = 5;
    public static final double TANKER_ENEMY_SPEED = 0.25;

    public static final String BOSS_ENEMY_URL = "file:img//boss.png";
    public static final int BOSS_ENEMY_COST = 50;
    public static final double BOSS_ENEMY_DAMAGE = 100;
    public static final double BOSS_ENEMY_SPEED = 0.25;

    // POINT
    public static double START_HP_POINT = 200;
    public static  int START_COIN = 50;

    public static double HOME_HEALTHPOINT_SIZE = 100;
    public static double HOME_X = 420;
    public static double HOME_Y = 390;

    public static double NORMAL_ENEMY_HEALTHPOINT = 200;
    public static double HEALTH_BAR_SIZE = 30;
    public static double FAST_ENEMY_HEALTHPOINT = 100;
    public static double TANKER_HEALTHPOINT = 700;
    public static double BOSS_HEALTHPOINT = 2500;

    public static final String DELETE_BUTTON = "file:img//deletebutton1.png";
    public static final String START_BUTTON = "file:img//startbutton.png";
    public static final String EXIT_BUTTON = "file:img//exitbutton.png";

    // WINDOW
    public static final String STAGE_BACKGROUND = "file:img//background1.png";
    public static final double STAGE_WIDTH = 600;
    public static final double STAGE_HEIGHT = 450;
    //MUSIC
    public static final String MUSIC_BACKGROUND = "E:\\Game\\TowerDefense\\music\\jojo.mp3";
    public static final String MUSIC_LOSE = "E:\\Game\\TowerDefense\\music\\anta-baka.mp3";
    public static final String MUSIC_EARNCOIN = "E:\\Game\\TowerDefense\\music\\coin.mp3";
    public static final String MUSIC_ATTACK_HOMEBASE = "E:\\Game\\TowerDefense\\music\\yasuo.mp3";
}