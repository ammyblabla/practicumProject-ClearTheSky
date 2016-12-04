package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WorldRenderer {

	private ClearThisSky clearThisSky;
	private World world;
	private SpriteBatch batch;
	
	private Texture birdImg;
	private Texture planeImg;
	private Texture arrowImg;
    private Texture BG;
    
	private BitmapFont fontScore;
	private BitmapFont fontTime;
	
	public WorldRenderer(ClearThisSky clearThisSky, World world) {
		this.clearThisSky = clearThisSky;
		this.world = world;
		this.batch = clearThisSky.batch;
		
		birdImg = new Texture("Bird.png");
		planeImg = new Texture("Plane.png");
		arrowImg = new Texture("Arrow_cut.png");
//        bowImg = new Texture("Bow.png");
        BG = new Texture("BG.jpg");
        
		fontScore = new BitmapFont();
		fontTime = new BitmapFont();
	}
	
	public void render(float delta) {
		batch.begin();
		batch.draw(BG,-230,0);
		world.getBows().bowsRenderer(batch);
		targetRender(world.getPlane(), planeImg, 7);
        targetRender(world.getBird(), birdImg, 3);
        arrowRender(world.getArrow());
		fontScore.draw(batch, "Score: " + world.getScore(), clearThisSky.WIDTH * 2/3, clearThisSky.HEIGHT - 60);
		fontTime.draw(batch, "Time: " + world.getTime(), clearThisSky.WIDTH * 1/3, clearThisSky.HEIGHT - 60);
		batch.end();
	}
	
	private void targetRender(ArrayList<Target> arrayOfTarget, Texture img, int scale) {
		for (Target target : arrayOfTarget) {
			if (target.getX() == 1) {
				batch.draw(img, target.getPosition().x - img.getWidth()/(2*scale), target.getPosition().y - img.getHeight()/(2*scale), img.getWidth()/scale, img.getHeight()/scale, 1, 1, img.getWidth(), img.getHeight(), true, false);
			}
			else if(target.getX() == -1) {
				batch.draw(img, target.getPosition().x - img.getWidth()/(2*scale), target.getPosition().y - img.getHeight()/(2*scale), img.getWidth()/scale, img.getHeight()/scale, 1, 1, img.getWidth(), img.getHeight(), false, false);
			}
		}
	}
	
	private void arrowRender(ArrayList<Arrow> arrayOfArrow) {
		for (Arrow arrow : arrayOfArrow) {
			batch.draw(arrowImg, arrow.getPosition().x, arrow.getPosition().y, arrowImg.getWidth()/20, arrowImg.getHeight()/20, arrowImg.getWidth()/10, arrowImg.getHeight()/10, 1, 1, arrow.getCurrentRotation(), 1, 1, arrowImg.getWidth(), arrowImg.getHeight(), false, false);
		}
	}
}
