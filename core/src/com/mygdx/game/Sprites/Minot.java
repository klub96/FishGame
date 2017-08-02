package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Animation.Animation;

/**
 * Created by Caleb on 6/16/2017.
 */

public class Minot extends EnemyFish{

    private static final int MIN_SIZE = 45;
    private static final int MAX_SIZE = 75;

    public Minot(int startPos) {
        super(MIN_SIZE, MAX_SIZE, startPos);
        this.enemyFish = new Texture("minotAnimation.png");
        this.enemyAnimation = new Animation(new TextureRegion(enemyFish),3,0.8f);
        this.enemyFishSprite = new Sprite(enemyFish);
    }

}
