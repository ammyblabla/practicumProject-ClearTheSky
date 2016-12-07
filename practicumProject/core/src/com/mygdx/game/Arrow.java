package com.mygdx.game;

import java.util.Timer;
import java.util.TimerTask;

import com.badlogic.gdx.math.Vector2;

public class Arrow {
	
	private Timer arrowTimer;
	private TimerTask arrowTask;
	private Vector2 position;
	private int rotation;
	private float v;
	private float t;
	private float g;
	private float x;
	private float y;
	 
    public Arrow(int rotation, float velocity) {
    	this.rotation = rotation;
    	v = velocity;
    	g = 9.81f;
    	x = (float) (160*Math.cos(Math.toRadians(rotation)));
    	y = (float) (160*Math.sin(Math.toRadians(rotation)));
        position = new Vector2(x, y);
        makeTimer();
    }    
    
    private void makeTimer() {
		arrowTimer = new Timer();
		arrowTask = new TimerTask() {
			@Override
			public void run() {
				t += 0.01;
			}
		};
		arrowTimer.scheduleAtFixedRate(arrowTask, 0, 1);
	}
 
    public void update() {
    	x = (float) (v*Math.cos(Math.toRadians(rotation)))*t;
    	y = (float) ((float) ((v*Math.sin(Math.toRadians(rotation)))*t) - (g/2)*(Math.pow(t, 2))); 
    	position.set(x, y);
    }
    
    public Vector2 getPosition() {
    	return position;
    }
    
    public float getCurrentRotation() {
    	float vx = (float) (v*Math.cos(Math.toRadians(rotation)));
    	float vy = (float) (v*Math.sin(Math.toRadians(rotation)) - (g*t));
    	return (float) Math.toDegrees(Math.atan(vy/vx));
    }
    
    public boolean disappear() {
    	if (position.x >= ClearThisSky.WIDTH | position.y >= ClearThisSky.HEIGHT) {
    		return true;
    	}
    	return false;
    }
}
