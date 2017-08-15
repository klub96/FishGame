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
    public static final int fishSizeCap = 200;
    public static final int fishGap = 150;

    protected LakeState(StatesManager sm, Fish fish) {
        super(sm, Level.LAKE, fishGap, fish);
        setBackgroundImage(bg);
        this.fish = fish;
        fish.setFishY(camHeight + fish.getFishHeight());
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
