package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;

public class Target {

	private World world;
	private Vector2 position;
	private ClearThisSky clearThisSky;
	private Random rand;
	private int x;
	private int y;
	private int speed;
	private int edge;
	private boolean changeDirection;
	
	public Target(World world) {
		this.world = world;
		this.clearThisSky = world.getClearThisSky();
		speed = 5;
		edge = 20;
		changeDirection = false;
		rand = new Random();
        position = new Vector2(rand.nextInt(ClearThisSky.WIDTH-(2*edge))+edge, rand.nextInt((ClearThisSky.HEIGHT-(2*edge))+edge));
    }    
 
    public void update() {
    	if(world.getTime()%2 == 0 & !changeDirection) {
    		x = randomPlusOrMinus();
    		y = randomPlusOrMinus();
    		changeDirection = true;
    	}
    	else if (world.getTime()%2 != 0) {
    		changeDirection = false;
    	}
    	inScreen();
    	position.add(speed*x,speed*y);
    }
    
    private int randomPlusOrMinus() {
    	boolean rnd = rand.nextBoolean();
    	if(rnd) {
    		return 1;
    	}
    	return -1;
    }
    
    private void inScreen() {
    	if (position.x >= clearThisSky.WIDTH-edge | position.x <= edge) {
    		x *= -1;
    	}
    	else if (position.y >= clearThisSky.HEIGHT-edge | position.y <= edge) {
    		y *= -1;
    	}
    }
    
    public Vector2 getPosition() {
    	return position;
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }
}
