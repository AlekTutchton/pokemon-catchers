package model;

import java.io.Serializable;

public class Trainer implements Serializable{
	
	private int steps;
	private int balls;
	private int rocks;
	private int bait;
	private int potion;
	private int zinc;
	private int x;
	private int y;
	private Pokedex pokedex;
	
	private boolean arena;
	private boolean fire;
	private boolean cave;
	private boolean water;
	private boolean snow;
	private boolean map;

	
	
	public Trainer() {
		steps = 0;
		balls = 10;
		bait = 10;
		rocks = 10;
		potion = 1;
		zinc = 0;
		x = 3;
		y = 3;
		pokedex = new Pokedex();
		arena = false;
		fire = false;
		cave = false;
		water = false;
		snow = false;
		map = false;
	}
	
	public void inArena() {
		fire = false;
		cave = false;
		water = false;
		snow = false;
		arena = true;
		map = false;
	}
	
	public void inMap() {
		fire = false;
		cave = false;
		water = false;
		snow = false;
		arena = false;
		map = true;
	}
	public void inFire() {
		arena = false;
		cave = false;
		water = false;
		snow = false;
		fire = true;
		map = false;
	}
	
	public void inCave() {
		arena = false;
		fire = false;
		cave = true;
		water = false;
		snow = false;
		map = false;
	}
	
	public void inWater() {
		arena = false;
		fire = false;
		cave = false;
		water = true;
		snow = false;
		map = false;
	}
	
	public void inSnow() {
		arena = false;
		fire = false;
		cave = false;
		water = false;
		snow = true;
		map = false;
	}
	
	
	public String getRoom() {
		if(arena) {
			return "arena";
		}
		
		if(fire) {
			return "fire";
		}
		
		if(cave) {
			return "cave";
		}
		
		if(water) {
			return "water";
		}
		
		if(snow) {
			return "snow";
		}
		
		return "map";
	}
	
	public Pokedex getPokedex() {
		return pokedex;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getBalls() {
		return balls;
	}
	
	public int getRocks() {
		return rocks;
	}
	
	public int getSteps() {
		return steps;
	}
	
	public int getBait() {
		return bait;
	}
	
	public int getPotion() {
		return potion;
	}
	
	public int getZinc() {
		return zinc;
	}
	
	public void addBall() {
		balls++;
	}
	
	public void useBall() {
		balls--;
	}
	
	public void addRock() {
		rocks++;
	}
	public void useRock() {
		rocks--;
	}

	public void addStep() {
		steps++;
	}
	
	public void addBait() {
		bait++;
	}
	
	public void useBait() {
		bait--;
	}
	
	public void addPotion() {
		potion++;
	}
	
	public void usePotion() {
		potion--;
	}
	
	public void addZinc() {
		zinc++;
	}
	
	public void useZinc() {
		zinc--;
	}
	
	public void moveUp() {
		y--;
	}
	
	public void moveDown() {
		y++;
	}
	
	public void moveRight() {
		x += 1;
	}
	
	public void moveLeft() {
		x--;
	}
	
	public boolean hasMoreSteps() {
		return steps < 300;
	}
	
	public void setPos(int X, int Y) {
		x = X;
		y = Y;
	}

}
