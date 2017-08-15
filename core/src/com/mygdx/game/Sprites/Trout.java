package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Caleb on 6/23/2017.
 */

public class Trout extends EnemyFish{
    private static final int MIN_SIZE = 40;
    private static final int MAX_SIZE = 110;

    public Trout(int startPos) {
        super(MIN_SIZE, MAX_SIZE, startPos);
        this.enemyFish = new Texture("troutAnimation.png");
        this.enemyAnimation = new Animation(new TextureRegion(enemyFish),3,0.5f);
        this.enemyFishSprite = new Sprite(enemyFish);
    }
}
