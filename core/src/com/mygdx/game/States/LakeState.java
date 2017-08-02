package com.mygdx.game.States;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Sprites.EnemyFish;
import com.mygdx.game.Sprites.Fish;

/**
 * Created by Caleb on 6/22/2017.
 */

public class LakeState extends LevelState{
    private final Texture bg = new Texture("lake.png");
    public static final int lakeCamWidth = 1600;
    public static final int lakeCamHeight = 1000;
    public static final int fishSizeCap = 200;
    public static final int fishGap = 150;

    protected LakeState(StatesManager sm, Fish fish) {
        super(sm, lakeCamWidth, lakeCamHeight,Level.LAKE, fishGap, fish);
        setCamSize(lakeCamWidth,lakeCamHeight);
        setBackgroundImage(bg);
        this.fish = fish;
        fish.setFishY(lakeCamHeight + fish.getFishHeight());
        fish.setGravity(-30);
        fish.setJumpHeight(700);
        gameCam.setToOrtho(false,lakeCamWidth,lakeCamHeight);
    }

    @Override
    public void checkFishSize() {
        if(fish.getFishWidth() > fishSizeCap){
            inputEnabled = false;
            fish.turnOffGravity();
            for(EnemyFish current : enemyFishes){
                current.turnOffEnemyMovement();
            }
            if(!fishOffScreen){
                levelOutro(findHighestFish());
            }
            else {
                //fishInPosition = false;
                //fish.setFishY(LakeState.lakeCamHeight + fish.getPosition().y);
                sm.set(new RiverState(sm,fish));
            }
        }
    }
}
