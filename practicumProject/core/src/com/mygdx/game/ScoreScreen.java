package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreScreen extends ScreenAdapter {
	
	private SpriteBatch batch;
	private ClearThisSky clearThisSky;
	private BitmapFont scoreText;
	private int score;
	private Texture BG;
	private boolean restart;
	
	public ScoreScreen(ClearThisSky clearThisSky, World world) {
		this.clearThisSky = clearThisSky;
		this.batch = clearThisSky.batch;
		restart = false;
		BG = new Texture("BG.jpg");
		
		scoreText = new BitmapFont(Gdx.files.internal("EndingFont.fnt"));
		score = world.getScore();
		scoreText.setColor(128, 128, 128, (float) 0.87);
	}
	
	@Override
    public void render(float delta) {
		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f); // Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Fills the screen with the selected color
        
		batch.begin();
		batch.draw(BG,-230,0);
		scoreText.draw(batch, "Your score: " + score, clearThisSky.WIDTH/2, clearThisSky.HEIGHT/2, 1, 1, false);
		batch.end();
		
		checkSpace();
	}
	
	public void checkSpace() {
		if(Gdx.input.isKeyPressed(Keys.ENTER)) {
			restart = true;
		}
	}
	
	public boolean isRestart() {
		return restart;
	}

}
