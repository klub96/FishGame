package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Caleb on 6/11/2017.
 */

public class Tadpole extends EnemyFish{

    private static final int MIN_SIZE = 40;
    private static final int MAX_SIZE = 100;

    public Tadpole(int startPos){
        super(MIN_SIZE,MAX_SIZE, startPos);
        enemyFish = new Texture("tadpoleAnimation.png");
        this.enemyAnimation = new Animation(new TextureRegion(enemyFish),3,0.5f);
        enemyFishSprite = new Sprite(enemyFish);
    }

//    public void updateAnim(float dt){
//        position.add(-3,0,0);
//        enemyCollisionBox.setPosition(position.x,position.y);
//    }

//    public void resetFish(){
//        maxHeight = PondState2.currentCamHeight - maxSize;
//        xSpawn = PondState2.currentCamWidth + maxSize;
//        resizeFish();
//        enemyFishY = getRandomNum(75,maxHeight);
//        position.set(xSpawn,enemyFishY,0);
//
////        System.out.println("Reset");
////        System.out.println("y: " + enemyFishY);
//
//    }

//    public void resizeFish(){
//        enemyFishHeight = getRandomNum(minSize,maxSize);
//        enemyFishWidth = enemyFishHeight + 5;
//    }

//    public void resetY(){
//        enemyFishY = getRandomNum(75,maxHeight);
//        position.set(xSpawn,enemyFishY,0);
//    }

//    public int enemySize(){
//        return enemyFishWidth * enemyFishHeight;
//    }

//    public boolean collides(Rectangle other){
//        return other.overlaps(enemyCollisionBox);
//    }

//    public int getRandomNum(int min, int max){
//        return rand.nextInt(max - min) + min;
//    }

//    public Texture getTexture(){return enemyFish;}
//
//    //public Sprite getSprite(){return enemyFishSprite;}
//
//    public Vector3 getPosition() {return position;}
//
//    public int getEnemyFishWidth() {
//        return enemyFishWidth;
//    }
//
//    public int getEnemyFishHeight() {
//        return enemyFishHeight;
//    }



//    public Rectangle getEnemyCollisionBox(){return enemyCollisionBox;}
//
//    public void setMaxSize(int maxSize) {
//        this.maxSize = maxSize;
//    }
//
//    public int getMaxSize() {
//        return maxSize;
//    }
}
