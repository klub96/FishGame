package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.Animation.Animation;
import com.mygdx.game.FishGameDemo;
import com.mygdx.game.States.LevelState;

/**
 * Created by Caleb on 6/11/2017.
 */

public class Fish {
    private Texture fish;
    private Texture collisionBoxImg;
    private Animation fishAnimation;
    private Sprite fishSprite;
    //private Sprite collisionBoxSprt;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle collisionBox;

    private static final int originalFishWidth = 60;
    private static final int originalFishHeight = 55;
    private int fishWidth, fishHeight;

    private final int fishX = 150;

    private static int fishWeight;

    private static int gravity;
    private static int jumpHeight;
   // private static final int MOVEMENT = 100;

    private boolean gravityOn;
    private boolean collisionOn;

    private float health;

  //  private float fishRotation = 0;

    public Fish(){
        gravityOn = false;
        collisionOn = false;
        fishWidth = originalFishWidth;
        fishHeight = originalFishHeight;
        fishWeight = 1;
        fish = new Texture("fishy3_fins.png");
        fishAnimation = new Animation(new TextureRegion(fish),3,1f);
        fishSprite = new Sprite(fishAnimation.getFrame());
        fishSprite.setSize(fishWidth,fishHeight);
        position = new Vector3(fishX, FishGameDemo.HEIGHT + fish.getHeight(),0);
        velocity = new Vector3(0,0,0);
        collisionBox = new Rectangle(fishX + fishWidth/5,position.y + fishHeight/8,fishSprite.getWidth() - fishWidth/5,fishSprite.getHeight() - fishHeight/5);
        collisionBoxImg = new Texture("whiteBackground.png");
        gravity = -30;
        jumpHeight = 700;
        health = 1;
//        collisionBoxSprt = new Sprite(collisionBoxImg);
//        collisionBoxSprt.setSize(collisionBox.getWidth(),collisionBox.getHeight());
    }

    public void updateAnim(float dt){
        fishAnimation.update(dt);
        fishSprite.setRegion(fishAnimation.getFrame());
       // fishRotation += 5;

        if(position.y > 0 && gravityOn){
            velocity.add(0, gravity,0);
        }

        velocity.scl(dt);
        if(gravityOn) {
            position.add(0, velocity.y, 0);
        }

        if(position.y < 10){
            position.y = 10;
        }


        if(position.y >= LevelState.camHeight - (fishSprite.getHeight())){
            position.y = LevelState.camHeight - (fishSprite.getHeight());
        }

        velocity.scl(1/dt);
        collisionBox.setPosition(position.x + fishWidth/5,position.y + fishHeight/8);
       // collisionBoxSprt.setPosition(collisionBox.getX(),collisionBox.getY());
    }

    public void jump(){
        velocity.y = jumpHeight;
       // System.out.println("Jump");
    }

    public int fishSize(){
        return fishHeight * fishWidth;
    }

    public void increaseFishSize(int width, int height, int weight){
        fishWidth += width;
        fishHeight += height;
       // fishSprite.setScale(2);
        fishSprite.setSize(fishWidth,fishHeight);
        collisionBox.setSize(fishWidth - fishWidth/5,fishHeight - fishHeight/5);
       // collisionBoxSprt.setSize(collisionBox.getWidth(),collisionBox.getHeight());
        fishWeight += weight / 5;
    }

    //public Texture getTexture(){return fish;}

    public int getSize(){
        return fishWidth * fishHeight;
    }

    public void resetFishSize(){
        fishWidth = originalFishWidth;
        fishHeight = originalFishHeight;
        fishSprite.setSize(fishWidth,fishHeight);
        collisionBox.setSize(fishWidth - fishWidth/5,fishHeight - fishHeight/5);
    }

    public void throwUp(){
        fishWidth -= fishWidth/5;
        fishHeight -= fishHeight/5;
        fishSprite.setSize(fishWidth,fishHeight);
        collisionBox.setSize(fishWidth - fishWidth/5,fishHeight - fishHeight/5);
        fishWeight -= fishWeight/5;
    }

    public Sprite getSprite(){return fishSprite;}

    public Vector3 getPosition(){return position;}

    public Rectangle getCollisionBox(){return collisionBox;}

    public int getFishWidth(){return fishWidth;}

    public int getFishHeight(){return fishHeight;}

    public boolean canEat(EnemyFish enemy){
        return (fishSize() > enemy.enemySize());
    }

    public static void setGravity(int gravity) {
        Fish.gravity = gravity;
    }

    public static void setJumpHeight(int jumpHeight) {
        Fish.jumpHeight = jumpHeight;
    }

    public TextureRegion getTexture(){
        return fishAnimation.getFrame();
    }

    public void addFishY(float fishY){
        position.add(0,fishY,0);
    }

    public void setFishY(float fishY){
        position.set(fishX,fishY,0);
    }

    public void turnOnGravity(){
        gravityOn = true;
    }

    public void turnOffGravity(){
        gravityOn = false;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollision(boolean collisionStatus){
        collisionOn = collisionStatus;
    }

//    public Sprite getCollisionBoxSprt() {
//        return collisionBoxSprt;
//    }

    public int getFishWeight() {
        return fishWeight;
    }

    public float getHealth() {
        return health;
    }

    public void adjustHealth(float healthAdjusment){
        health += healthAdjusment;
    }

    public void resetHealth(){
        health = 1;
    }

    //    public float getRotation(){
//        return fishRotation;
//    }

    public void dispose(){
        //fish.dispose();
        fishSprite.getTexture().dispose();
    }


}
