package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.FishGameDemo;
import com.mygdx.game.Sprites.Fish;

/**
 * Created by Caleb on 6/30/2017.
 */

public class GameOverState extends State{

    protected Texture gameOver;
  //  protected Texture bg;
    protected BitmapFont text;
    protected int finalScore;

    protected GameOverState(StatesManager sm, int score) {
        super(sm);
        gameCam.setToOrtho(false, FishGameDemo.WIDTH/2,FishGameDemo.HEIGHT/2);
        gameOver = new Texture("gameover.png");
       // bg = new Texture("kysen.jpg");
        text = new BitmapFont();
        finalScore = score;
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            sm.set(new PondState(sm,new Fish()));
        }
    }

    @Override
    protected void updateAnim(float dt) {
        handleInput();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(gameOver,gameCam.position.x - gameOver.getWidth(), gameCam.position.y - gameOver.getHeight());
        text.setColor(Color.CYAN);
        text.draw(sb,"Final Score: " + finalScore,gameCam.position.x - 150, gameCam.position.y - (gameOver.getHeight() + 50));
        sb.end();
    }

    @Override
    protected void dispose() {
        gameOver.dispose();
    }
}
