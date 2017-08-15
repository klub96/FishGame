package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Class: StatesManager
 * Purpose: Creates an stack that handles all game states
 * Created by Caleb on 6/11/2017.
 */

public class StatesManager{

    //Contains current game states
    private Stack<State> gameStates;

    /**
     * Initializes gameState stack
     */
    public StatesManager(){
        this.gameStates = new Stack<State>();
    }

    /**
     * Pushes new state onto the stack
     * @param state
     */
    public void push(State state){
        gameStates.push(state);
    }

    /**
     * Removes top state on stack
     * @param state
     */
    public void pop(State state){
        gameStates.pop().dispose();
    }

    /**
     * Pops off current state and pushes a new one onto the stack
     * @param state
     */
    public void set(State state){
        gameStates.pop().dispose();
        gameStates.push(state);
    }

    /**
     * Calls the current states updateAnim function
     * @param dt
     */
    public void update(float dt){
        gameStates.peek().updateAnim(dt);
    }

    /**
     * Calls the current states render function
     * @param sb
     */
    public void render(SpriteBatch sb){
        gameStates.peek().render(sb);
    }
}
