package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.States.StartScreenState;
import com.mygdx.game.States.StatesManager;

/**
 * Class: FishGameDemo
 * Purpose: Sets size of window, and declares StatesManager and SpriteBatch
 */
public class FishGameDemo extends ApplicationAdapter {

	//Window Width and Height
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;


	public static final String TITLE = "Fish Game";

	//Manages different game states like LevelState, GameOverState, etc.
	private StatesManager sm;

	//Draws all textures
	private SpriteBatch batch;

	/**
	 * Initializes States Manager and SpriteBatch, and pushes StartScreenState onto the StatesManager stack
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		sm = new StatesManager();
		sm.push(new StartScreenState(sm));
	}

	/**
	 * Calls the render function in the StatesManager class and passes in delta time which is how the game progresses
	 */
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(batch);
//		batch.begin();
//		batch.draw(img, 0, 0);
//		batch.end();
	}

	/**
	 * Disposes of textures
	 */
	@Override
	public void dispose(){
		super.dispose();
	}

//	@Override
//	public void dispose () {
//		batch.dispose();
//		img.dispose();
//	}
}
