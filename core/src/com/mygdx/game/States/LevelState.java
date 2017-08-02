package com.mygdx.game.States;

        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Input;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.utils.Array;
        import com.mygdx.game.Sprites.EnemyFish;
        import com.mygdx.game.Sprites.Fish;
        import com.mygdx.game.Sprites.Salmon;
        import com.mygdx.game.Sprites.Sturgeon;
        import com.mygdx.game.Sprites.Trout;
        import com.mygdx.game.Sprites.Catfish;
        import com.mygdx.game.Sprites.Tadpole;
        import com.mygdx.game.Sprites.Minot;

        import java.util.Random;

/**
 * Created by Caleb on 6/22/2017.
 */

public abstract class LevelState extends State{
    protected Fish fish;
    protected Array<EnemyFish> enemyFishes;
    protected Random randNum;
    protected Level currentLevel;
    public static int camWidth, camHeight;
    private static int fishEatenCount = 0;
    protected static int enemyFishGap;

    protected boolean fishDead;

    protected boolean fishInPosition,fishOffScreen;
    protected boolean inputEnabled;
    protected boolean enemyOffScreen;

    protected BitmapFont font;
    protected int fontScale;
    protected Texture bg;
    //protected Music music;

    protected boolean paused;

    protected LevelState(StatesManager sm, int cam_Width, int cam_Height,Level level, int enemyGap, Fish fish) {
        super(sm);
        fishInPosition = false;
        fishOffScreen = false;
        inputEnabled = false;
        enemyOffScreen = false;
        camWidth = cam_Width;
        camHeight = cam_Height;
        this.fish = fish;
        randNum = new Random();
        enemyFishes = new Array<EnemyFish>();
        currentLevel = level;
        enemyFishGap = enemyGap;
        fishDead = false;
        font = new BitmapFont();
        this.bg = bg;
        addFishes();
        setEnemySpeed();
        paused = false;
    }

    protected void addFishes(){
        if(currentLevel.equals(Level.POND)) {
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyFishes.add(new Tadpole(camWidth + (i * enemyFishGap)));

                } else {
                    enemyFishes.add(new Minot(camWidth + (i * enemyFishGap)));
                }
            }
            fontScale = 1;
        }
        else if(currentLevel.equals(Level.LAKE)){
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyFishes.add(new Trout(camWidth + (i * enemyFishGap)));
                } else {
                    enemyFishes.add(new Catfish(camWidth + (i * enemyFishGap)));
                }
            }
            fontScale = 2;
        }
        else if(currentLevel.equals(Level.RIVER)){
            for (int i = 0; i < 20; i++) {
                int num = randNum.nextInt(10) + 1;
                if (num % 2 == 0) {
                    enemyFishes.add(new Salmon(camWidth + (i * enemyFishGap)));
                } else {
                    enemyFishes.add(new Sturgeon(camWidth + (i * enemyFishGap)));
                }
            }
            fontScale = 3;
        }
    }

    public void setEnemySpeed(){
        if(currentLevel.equals(Level.POND)){
            for(EnemyFish current: enemyFishes){
                current.setEnemySpeed(-3);
            }
        }
        else if(currentLevel.equals(Level.LAKE)){
            for(EnemyFish current: enemyFishes){
                current.setEnemySpeed(-6);
            }
        }
        else if(currentLevel.equals(Level.RIVER)){
            for(EnemyFish current: enemyFishes){
                current.setEnemySpeed(-12);
            }
        }
    }

    protected void setCamSize(int camWidth, int camHeight){
        this.camWidth = camWidth;
        this.camHeight = camHeight;
    }

    protected void setBackgroundImage(Texture bg){
        this.bg = bg;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if(inputEnabled) {
                fish.jump();
            }
            else{
                fish.setFishY(gameCam.position.y);
            }
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            paused = true;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            paused = false;
        }

    }

    @Override
    protected void updateAnim(float dt) {
        handleInput();
        if(!paused) {
            fish.updateAnim(dt);
            checkFishSize();
            if (!fishInPosition) {
                levelIntro();
            } else {
                for (EnemyFish current : enemyFishes) {
                    if (current.getPosition().x < -75) {
                        current.resetFish();
                        // checkForEnemyCollisions(current);
                    }
                    current.updateAnim(dt);
                    if (current.collides(fish.getCollisionBox())) {
                        if (fish.canEat(current)) {
                            fish.increaseFishSize(current.getEnemyFishWidth() / 10, current.getEnemyFishHeight() / 10);
                            current.resetFish();
                            fishEatenCount++;
                            System.out.println("Fish eaten: " + fishEatenCount);
                            //checkForEnemyCollisions(current);
                        } else {
                            fishDead = true;
                            int score = fishEatenCount;
                            fishEatenCount = 0;
//                    music.stop();
                            sm.set(new GameOverState(sm, score, gameCam));
                        }
                    }
                }
            }
        }
    }

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
        font.setColor(Color.WHITE);
        font.getData().setScale(fontScale);
        font.draw(sb,"Fish Eaten: " + fishEatenCount,50,camHeight - 50);
        font.draw(sb,"Fish Size: " + fish.getFishWidth() + " lbs.",50, camHeight - 90);

        sb.end();
    }

    public abstract void checkFishSize();

    public void levelIntro(){
        if(!fishInPosition){
            fish.addFishY(-(camHeight/250));
        }
        if(fish.getPosition().y <= gameCam.position.y){
            fishInPosition = true;
            fish.turnOnGravity();
            inputEnabled = true;
            for(EnemyFish current : enemyFishes){
               current.turnOnEnemyMovement();
            }
        }
    }

    public void levelOutro(EnemyFish highestFish){
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

    public EnemyFish findHighestFish(){
        EnemyFish highestFish = enemyFishes.get(0);
        for(EnemyFish current : enemyFishes){
            if(current.getPosition().y > highestFish.getPosition().y){
                highestFish = current;
            }
        }
        return highestFish;
    }

    @Override
    protected void dispose() {
        if(fishDead){
            fish.dispose();
        }
        font.dispose();
       // bg.dispose();
//        for(EnemyFish current : enemyFishes){
//            current.getSprite().getTexture().dispose();
//        }
        System.out.println("PondState2 disposed");
    }
}
