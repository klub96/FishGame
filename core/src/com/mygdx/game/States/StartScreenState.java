package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.FishGameDemo;
import com.mygdx.game.Sprites.Fish;



/**
 * Created by Caleb on 6/11/2017.
 * background, start button, title edits - Jeron 7/4/17
 */

public class StartScreenState extends State{
    //Background texture
    private Texture bg;

    //Start button texture
    private Texture startBtn;

    //Text on screen
    private BitmapFont titleFont;

    //Width and height of start button
    private float sbWidth = FishGameDemo.WIDTH/10;
    private float sbHeight = FishGameDemo.HEIGHT/10;

    /**
     * Sets camera width and height, and initializes textures
     */
    public StartScreenState(StatesManager sm){
        super(sm);
        gameCam.setToOrtho(false, FishGameDemo.WIDTH,FishGameDemo.HEIGHT);
        bg = new Texture("nemoBG.jpg");
        startBtn = new Texture("playbtn.png");
        titleFont = new BitmapFont();
        System.out.println("In StartScreenState");
    }

    /**
     * Checks to see if player is clicking on start button
     */
    @Override
    protected void handleInput() {
        Rectangle sbBound = new Rectangle(gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        if(Gdx.input.justTouched() && sbBound.contains(Gdx.input.getX(), Gdx.input.getY())){
            sm.set(new PondState(sm, new Fish()));
        }
    }

    /**
     * Calls handleInput
     * @param dt
     */
    @Override
    protected void updateAnim(float dt) {
        handleInput();
    }

    /**
     * Draws start button, text and background
     * @param sb
     */
    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(gameCam.combined);
        sb.begin();
        sb.draw(bg,0,0);
        titleFont.setColor(Color.CYAN);
        titleFont.getData().setScale(3);
        titleFont.draw(sb, "Flying with the fishes,\nSwimming with the Pigeons", gameCam.position.x - 200, gameCam.position.y + 300);
        sb.draw(startBtn, gameCam.position.x - sbWidth/2, gameCam.position.y - sbHeight/2, sbWidth, sbHeight);
        sb.end();
    }

    /**
     * Disposes textures
     */
    @Override
    protected void dispose() {
        startBtn.dispose();
        bg.dispose();
        titleFont.dispose();
        System.out.println("StartScreenState disposed");
    }
}
