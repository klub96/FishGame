package com.mygdx.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector3;

/**
 * Abstract class that creates needed functions for all states
 * Created by Caleb on 6/11/2017.
 */

public abstract class State {
    //Game camera
    protected OrthographicCamera gameCam;

    //Determines where the mouse is and where it is clicking
    protected Vector3 mouse;

    //States stack
    protected StatesManager sm;

    protected State(StatesManager sm){
        this.sm = sm;
        gameCam = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    protected abstract void updateAnim(float dt);
    protected abstract void render(SpriteBatch sb);
    protected abstract void dispose();

}
