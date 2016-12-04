package com.mygdx.game;

import java.util.TimerTask;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class World {

	public static final int TARET_CLOUD = 0;
	public static final int TARGET_BIRD = 1;
	public static final int TARGET_PLANE = 2;
	
	private ClearThisSky clearThisSky;
	private Bows bows;
	private ArrayList<Arrow> arrow;
	private ArrayList<Target> bird;
	private ArrayList<Target> plane;
	
	private Timer mainTimer;
	private TimerTask mainTask;
	private TimerTask arrowTask;
	
	private int velocity; //for vibration sensor
	private int score;
	private int time;
    private int arrowTime;
    private int arrowRelease;
    private float LastTarget;
    private int Delay = 2;
	
	World(ClearThisSky clearThisSky) {
		velocity = 125; //change for vibration sensor
		arrow = new ArrayList<Arrow>();
		bird = new ArrayList<Target>();
		plane = new ArrayList<Target>();
		bows = new Bows(this);
		makeTimer();
		score = 0;
		time = 30;
		mainTimer.scheduleAtFixedRate(mainTask, 1000, 1000);
		mainTimer.scheduleAtFixedRate(arrowTask, 0, 250);
		genTarget(5, 3);
	}
	
	private void genTarget(int numBird, int numPlane) {
		for (int i = 0; i < numBird; i++) {
			bird.add(new Target(this));
		}
		for (int j = 0; j < numPlane; j++) {
			plane.add(new Target(this));
		}
	}
	
	private void makeTimer() {
		mainTimer = new Timer();
		mainTask = new TimerTask() {
			@Override
			public void run() {
				time--;
			}
		};
		arrowTask = new TimerTask() {
			@Override
			public void run() {
				arrowTime++;
			}
		};
	}
	
	public void update(float delta) { 
		bows.setRotation();
		updateArrow();
		updateTarget();
		updateAttacked();
		addTarget(delta);
		if(Gdx.input.isKeyPressed(Keys.SPACE) & arrowRelease < arrowTime-1) {
			arrowRelease = arrowTime;
    		arrow.add(new Arrow(bows.getRotation(), velocity));
        }
	}
	
	public void addTarget(float delta) {
		LastTarget = LastTarget + delta;
		if(LastTarget >= Delay) {
//			System.out.println("eiei");
			Random rand = new Random();
			int item = rand.nextInt(2);
			if(item == 0) {
				plane.add(new Target(this));
			} else {
				bird.add(new Target(this));
			}
			LastTarget = 0;
		}
		System.out.println(LastTarget);
		System.out.println(delta);
	}
	
	public void updateArrow() {
		for (Arrow a : arrow) {
			a.update();
		}
	}
	
	public void updateTarget() {
		for (Target b : bird) {
			b.update();
		}
		for (Target p : plane) {
			p.update();
		}
	}
	
	public void updateAttacked() {
		boolean active = true;
		for (int indexArrow = 0; indexArrow < arrow.size(); indexArrow++) {
			active = loopForUpdateAttack(bird, TARGET_BIRD, indexArrow, active);
			active = loopForUpdateAttack(plane, TARGET_PLANE, indexArrow, active);
		}
	}
	
	private boolean loopForUpdateAttack(ArrayList<Target> target, int TARGET_SCORE, int indexArrow, boolean active) {
		if(active) {
			for (int indexTarget = 0; indexTarget < target.size(); indexTarget++) {
				if(isAttacked(arrow.get(indexArrow), target.get(indexTarget))) {
					arrow.remove(indexArrow);
					target.remove(indexTarget);
					score += TARGET_SCORE;
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean isAttacked(Arrow arrow, Target target) {
		int range = 40;
		if ((arrow.getPosition().x <= target.getPosition().x+range) & (arrow.getPosition().x >= target.getPosition().x-range) & (arrow.getPosition().y <= target.getPosition().y+range) & (arrow.getPosition().y >= target.getPosition().y-range)) {
			return true;
		}
		return false;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getTime() {
		return time;
	}
	
	public boolean clearAllTargets() {
		if(bird.isEmpty() & plane.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Target> getBird() {
		return bird;
	}
	
	public ArrayList<Target> getPlane() {
		return plane;
	}
	
	public ArrayList<Arrow> getArrow() {
		return arrow;
	}
	
	public ClearThisSky getClearThisSky() {
		return clearThisSky;
	}
	
	public Bows getBows() {
		return bows;
	}
}
