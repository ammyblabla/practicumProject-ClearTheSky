package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleShader.Inputs;

public class GameScreen extends ScreenAdapter {

	private World world;
	private WorldRenderer worldRenderer;
	private ClearThisSky clearThisSky;
    	 
    public GameScreen(ClearThisSky clearThisSky) {
        this.clearThisSky = clearThisSky;
        
        world = new World(clearThisSky);
        worldRenderer = new WorldRenderer(clearThisSky, world);
        
    }
    
    @Override
    public void render(float delta) {
    	world.update(delta);
    	
    	Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f); // Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Fills the screen with the selected color
      
        worldRenderer.render(delta);
        try {
			SerialExample.setup();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean gameEnd() {
    	if(world.getTime() <= 0 | world.clearAllTargets()) {
    		return true;
    	}
    	return false;
    }
    
    public World getWorld() {
    	return world;
    }

}
