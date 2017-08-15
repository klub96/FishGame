package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Sprites.EnemyFish;
import com.mygdx.game.Sprites.Fish;

import java.util.Random;

/**
 * Created by Caleb on 6/22/2017.
 */

public class PondState extends LevelState{
    private final Texture bg = new Texture("ocean.png");
    private static final int fishSizeCap = 150;
    private static final int fishGap = 150;

    protected PondState(StatesManager sm, Fish fish) {
        super(sm, Level.POND, fishGap, fish);
       // playMusic();
        setBackgroundImage(bg);
    }

    @Override
    public void checkFishSize() {
        if(fish.getFishWidth() > fishSizeCap){
            EnemyFish highestFish = findHighestFish();
            inputEnabled = false;
            fish.turnOffGravity();
            for(EnemyFish current : enemyFishes){
                current.turnOffEnemyMovement();
            }
            if(!fishOffScreen){
                levelOutro(highestFish);
            }
            else {
                //fishInPosition = false;
                //fish.setFishY(LakeState.lakeCamHeight + fish.getPosition().y);
                sm.set(new LakeState(sm, this.fish));

            }
        }
    }

//    public void playMusic(){
//        randNum = new Random();
//        int num = randNum.nextInt(6);
//        switch(num){
//            case 0:
//                music = Gdx.audio.newMusic(Gdx.files.internal("feelGood.mp3"));
//                music.setLooping(true);
//                music.setVolume(0.2f);
//                music.play();
//                break;
//            case 1:
//                music = Gdx.audio.newMusic(Gdx.files.internal("americandream.mp3"));
//                music.setLooping(true);
//                music.setVolume(0.2f);
//                music.play();
//                break;
//            case 2:
//                music = Gdx.audio.newMusic(Gdx.files.internal("stay.mp3"));
//                music.setLooping(true);
//                music.setVolume(0.2f);
//                music.play();
//                break;
//            case 3:
//                music = Gdx.audio.newMusic(Gdx.files.internal("parachute.mp3"));
//                music.setLooping(true);
//                music.setVolume(0.2f);
//                music.play();
//                break;
//            case 4:
//                music = Gdx.audio.newMusic(Gdx.files.internal("wastedyouth.mp3"));
//                music.setLooping(true);
//                music.setVolume(0.2f);
//                music.play();
//                break;
//            case 5:
//                music = Gdx.audio.newMusic(Gdx.files.internal("vibe.mp3"));
//                music.setLooping(true);
//                music.setVolume(0.2f);
//                music.play();
//                break;
//        }
//    }

//    @Override
//    public void dispose(){
//        super.dispose();
//        music.stop();
//    }
}
