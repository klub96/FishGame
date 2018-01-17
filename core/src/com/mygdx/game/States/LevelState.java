package com.mygdx.game.States;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.math.Rectangle;
        import com.badlogic.gdx.utils.Array;
        import com.mygdx.game.FishGameDemo;
        import com.mygdx.game.Sprites.EnemyFish;
        import com.mygdx.game.Sprites.Fish;
        import com.mygdx.game.Sprites.Ipecac;
        import com.mygdx.game.Sprites.Salmon;
        import com.mygdx.game.Sprites.Sturgeon;
        import com.mygdx.game.Sprites.Trout;
        import com.mygdx.game.Sprites.Catfish;
        import com.mygdx.game.Sprites.Tadpole;
        import com.mygdx.game.Sprites.Minot;

        import java.util.Random;

/**
 * Class: LevelState
 * Purpose: Abstract state that handles all levels in the game
 * Created by Caleb on 6/22/2017.
 */

public abstract class LevelState extends State{

    //Player fish object
    protected Fish fish;

    //Array of all enemyFish that are spawned
    protected Array<EnemyFish> enemyFishes;

    //This random number determines what type of random fish are spawned in the current level
    protected Random randNum;

    //Enum that helps determines what enemy fish to spawn depending on the current level
    protected Level currentLevel;

    //Camera width and height depending on what level the player is on
    public static final int camWidth = 1920;
    public static final int camHeight = 1080;

    //Current amount of fish eaten by the player fish
    private int fishEatenCount = 0;

    //Horizontal space between each enemy fish
    protected int enemyFishGap;

    //Set to true if player fish collides with a larger fish
    protected boolean fishDead;

    //These booleans are used in the intro and outro methods that move the fish into position
    protected boolean fishInPosition,fishOffScreen, inIntro;

    //Input is enabled once the levelIntro and levelOutro methods are complete and the fish is in position
    protected boolean inputEnabled;

    //Set to true when the enemies have all moved off of the screen in the levelOutro method
    protected boolean enemyOffScreen;

    //This font is used to display fishEatenCount and fishSize(TEMPERORY)
    protected BitmapFont font;
    protected int fontScale;

    //Background image
    protected Texture bg;

    protected Texture pauseBtn;
    protected Rectangle pauseBtnBox;

    protected Texture startBtn;
    private float sbWidth = FishGameDemo.WIDTH/10;
    private float sbHeight = FishGameDemo.HEIGHT/10;
    protected Rectangle startBtnBox;

    //protected Music music;

    //Health bar
    protected Texture healthBar;
    protected boolean liveHealth;

    private String currentLevelName;

    private Ipecac ipecac;
    private int enemyFishWidthMin;

    /**
     * Initializes Level variables including textures and camera size
     * @param sm - StateManager
     * @param level - Level enum
     * @param enemyGap
     * @param fish
     */
    protected LevelState(StatesManager sm, Level level, int enemyGap, Fish fish) {
        super(sm);
        gameCam.setToOrtho(false,camWidth,camHeight);
        fishInPosition = false;
        fishOffScreen = false;
        inputEnabled = false;
        enemyOffScreen = false;
        inIntro = true;
        liveHealth = false;
        fishDead = false;
        this.fish = fish;
        randNum = new Random();
        enemyFishes = new Array<EnemyFish>();
        currentLevel = level;
        enemyFishGap = enemyGap;
        font = new BitmapFont();
        pauseBtn = new Texture("pausebtn.png");
        pauseBtnBox = new Rectangle(0,0,140,140);
        startBtn = new Texture("playbtn.png");
        startBtnBox = new Rectangle(gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        addFishes();
        setEnemySpeed();
        fish.resetFishSize();
        healthBar = new Texture("whiteBackground.png");
        ipecac = new Ipecac(FishGameDemo.WIDTH*2);
        enemyFishWidthMin = 0;
    }

    /**
     * Spawns enemy fish depending on what Level enum was passed into the constructor
     *  - Fish types are spawned at random
     */
    protected void addFishes(){
        if(currentLevel.equals(Level.POND)) {
            currentLevelName = "Pond";
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyFishes.add(new Tadpole(camWidth + (i * enemyFishGap)));

                } else {
                    enemyFishes.add(new Minot(camWidth + (i * enemyFishGap)));
                }
            }
        }
        else if(currentLevel.equals(Level.LAKE)){
            currentLevelName = "Lake";
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyFishes.add(new Trout(camWidth + (i * enemyFishGap)));
                } else {
                    enemyFishes.add(new Catfish(camWidth + (i * enemyFishGap)));
                }
            }
        }
        else if(currentLevel.equals(Level.RIVER)){
            currentLevelName = " River";
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyFishes.add(new Salmon(camWidth + (i * enemyFishGap)));
                } else {
                    enemyFishes.add(new Sturgeon(camWidth + (i * enemyFishGap)));
                }
            }
        }
    }

    /**
     * Speed varies depending on size of level
     */
    public void setEnemySpeed(){
        if(currentLevel.equals(Level.POND)){
            for(EnemyFish current: enemyFishes){
                current.setEnemySpeed(-6);
            }
            enemyFishWidthMin = 40;
        }
        else if(currentLevel.equals(Level.LAKE)){
            for(EnemyFish current: enemyFishes){
                current.setEnemySpeed(-7);
            }
            enemyFishWidthMin = 40;
        }
        else if(currentLevel.equals(Level.RIVER)){
            for(EnemyFish current: enemyFishes){
                current.setEnemySpeed(-9);
            }
            enemyFishWidthMin = 40;
        }
    }

    /**
     * Camera size increases with each letter
     * @param camWidth
     * @param camHeight
     */
//    protected void setCamSize(int camWidth, int camHeight){
//        this.camWidth = camWidth;
//        this.camHeight = camHeight;
//    }

    /**
     * Level background image
     * @param bg
     */
    protected void setBackgroundImage(Texture bg){
        this.bg = bg;
    }

    /**
     * Allows player to jump, pause and unpause game
     */
    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            if(inputEnabled) {
                if(pauseBtnBox.contains(Gdx.input.getX(),Gdx.input.getY())){
                    sm.push(new PauseState(sm,bg));
                }
                else{
                    fish.jump();
                }
            }
            else {
                if (inIntro) {
                    fish.setFishY(gameCam.position.y);
                }
            }
        }
    }

    /**
     * Handles player and enemy movement, and checks for collisions. If there is a collision the enemy fish is eather eaten,
     * or the player dies and the GameOverState is pushed
     * @param dt
     */
    @Override
    protected void updateAnim(float dt) {
        handleInput();
        if(liveHealth) {
            fish.adjustHealth(-0.0006f);
        }
        if(fish.getHealth() > 1){
            fish.resetHealth();
        }
            fish.updateAnim(dt);
            checkFishSize();
            checkFishHealth();
            ipecac.move();
            if (!fishInPosition) {
                levelIntro();
            }
            else {
                for (EnemyFish current : enemyFishes) {
                    if (current.getPosition().x < -75) {
                        current.resetFish();
                        // checkForEnemyCollisions(current);
                    }
                    current.updateAnim(dt);
                    if (current.collides(fish.getCollisionBox()) && fish.isCollisionOn()) {
                        if (fish.canEat(current)) {
                            fish.increaseFishSize(current.getEnemyFishWidth() / 20, current.getEnemyFishHeight() / 20, current.getEnemyFishWeight());

                            float adjustment = 0.1f * (fish.getFishWidth()/current.getEnemyFishWidth());
                            fish.adjustHealth(adjustment);
                            current.resetFish();

                            fishEatenCount++;
                            System.out.println("Fish eaten: " + fishEatenCount);
                            //checkForEnemyCollisions(current);
                        } else {
                           gameOver();
                        }
                    }
                }
                if(ipecac.getCollisionBox().overlaps(fish.getCollisionBox()) && !ipecac.isEmpty()){
                    fish.throwUp();
                    if(fish.getFishWidth()< enemyFishWidthMin){
                        gameOver();
                    }
                    ipecac.setEmpty(true);
                }
            }
        }

    /**
     * Draws all textures and sprites on the screen, as well as the fonts
     * @param sb
     */
    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(gameCam.combined);
        sb.begin();
        sb.draw(bg,0,0,camWidth,camHeight);
      //  sb.draw(fish.getCollisionBoxSprt(),fish.getCollisionBox().getX(),fish.getCollisionBox().getY(),fish.getCollisionBox().getWidth(),fish.getCollisionBox().getHeight());
        sb.draw(fish.getSprite(),fish.getPosition().x, fish.getPosition().y,fish.getSprite().getWidth(),fish.getSprite().getHeight());

        for(EnemyFish current : enemyFishes){
            //sb.draw(current.getCollisionBoxSprt(),current.getEnemyCollisionBox().getX(),current.getEnemyCollisionBox().getY(),current.getEnemyCollisionBox().getWidth(),current.getEnemyCollisionBox().getHeight());
            sb.draw(current.getSprite(),current.getPosition().x,current.getPosition().y,current.getEnemyFishWidth(),current.getEnemyFishHeight());
        }

        sb.draw(ipecac.getIpecacImg(),ipecac.getX(),ipecac.getY(),75,75);

        //Health Bar
        if(fish.getHealth() > 0.5f) {
            sb.setColor(Color.GREEN);
        }
        else if(fish.getHealth() > 0.25f){
            sb.setColor(Color.YELLOW);
        }
        else if(fish.getHealth() > 0.15f){
            sb.setColor(Color.ORANGE);
        }
        else if(fish.getHealth() > 0){
            sb.setColor(Color.RED);
        }
        sb.draw(healthBar,0,0,Gdx.graphics.getWidth() * fish.getHealth(), 10);
        sb.setColor(Color.WHITE);


        font.setColor(Color.WHITE);
        font.getData().setScale(2);
        font.draw(sb,currentLevelName,150,camHeight - 10);
        font.draw(sb,"Fish Eaten: " + fishEatenCount,150,camHeight - 50);
        font.draw(sb,"Fish Size: " + fish.getFishWeight() + " lbs.",150, camHeight - 90);
        sb.draw(pauseBtn,35,camHeight - 110,75,75);
        sb.end();
    }

    /**
     * Abstract class that is defined in each of this classes subclasses
     */
    public abstract void checkFishSize();

    public void checkFishHealth(){
        if(fish.getHealth() <= 0){
            gameOver();
        }
    }

    public void gameOver(){
        fishDead = true;
        int score = fishEatenCount;
        fishEatenCount = 0;
        sm.set(new GameOverState(sm, score));
    }

    /**
     * Moves the player fish to the center of the screen(Vertically)
     */
    public void levelIntro(){
        if(!fishInPosition){
            fish.addFishY(-(camHeight/250));
        }
        if(fish.getPosition().y <= gameCam.position.y){
            fishInPosition = true;
            fish.turnOnGravity();
            inputEnabled = true;
            inIntro = false;
            fish.resetHealth();
            liveHealth = true;
            fish.setCollision(true);
            for(EnemyFish current : enemyFishes){
               current.turnOnEnemyMovement();
            }
        }
    }

    /**
     * Moves the enemy fish off the screen and the player fish to the top of the screen
     * @param highestFish
     */
    public void levelOutro(EnemyFish highestFish){
        fish.setCollision(false);
        liveHealth = false;
        if(!enemyOffScreen){
            for(EnemyFish current : enemyFishes){
                current.setEnemyY(-(camHeight / 250));
            }
            if(highestFish.getPosition().y + highestFish.getEnemyFishHeight() < -10){
                enemyOffScreen = true;
            }
        }
        else{
            if (!fishOffScreen) {
                fish.addFishY(camHeight / 250);
                fish.addFishY(camHeight / 250);

            }
            if (fish.getPosition().y + fish.getFishHeight() >= camHeight) {
                fishOffScreen = true;
            }
        }
    }

    /**
     * Determines what enemyFish is the highest on the screen so that the levelOutro knows what fish to watch until it is off of the screen
     * @return
     */
    public EnemyFish findHighestFish(){
        EnemyFish highestFish = enemyFishes.get(0);
        for(EnemyFish current : enemyFishes){
            if(current.getPosition().y > highestFish.getPosition().y){
                highestFish = current;
            }
        }
        return highestFish;
    }

    /**
     * Disposes of textures
     */
    @Override
    protected void dispose() {
        if(fishDead){
            fish.dispose();
        }
        font.dispose();
        bg.dispose();
        for(int i = 0; i < 20; i++){
            enemyFishes.get(i).getSprite().getTexture().dispose();
        }
//        for(EnemyFish current : enemyFishes){
//            current.getSprite().getTexture().dispose();
//        }
        System.out.println("PondState2 disposed");
    }
}
