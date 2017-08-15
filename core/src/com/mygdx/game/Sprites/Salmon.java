package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Caleb on 7/26/2017.
 */

public class Salmon extends EnemyFish {

    private static final int MIN_SIZE = 45;
    private static final int MAX_SIZE = 110;

    public Salmon(int startPos) {
        super(MIN_SIZE, MAX_SIZE, startPos);
        this.enemyFish = new Texture("salmonAnimation.png");
        this.enemyAnimation = new Animation(new TextureRegion(enemyFish),3,0.5f);
        this.enemyFishSprite = new Sprite(enemyFish);
    }
}
