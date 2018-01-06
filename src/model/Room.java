package model;

public class Room {
	
	private boolean isGrass;
	private boolean isPath;
	private boolean isBall;
	private boolean isBait;
	private boolean isZinc;
	private boolean isPotion;
	private boolean isRock;
	private boolean isOther;
	private boolean isWater;
	private boolean isBorder;
	private boolean isPlayer;
	private boolean isCave;
	private boolean isPortal;
	private boolean isItem;
	private boolean isTeleport;
	private int x;
	private int y;
	
	public Room() {
		isGrass = false;
		isPath = false;
		isBall = false;
		isRock = false;
		isOther = false;
		isWater = false;
		isBorder = false;
		isPlayer = false;
		isCave = false;
		isPortal = false;
		isItem = false;
		
	}
	
	public void setGrass() {
		isGrass = true;
	}
	
	public boolean isGrass() {
		return isGrass;
	}
	
	public void setPath() {
		isPath = true;
	}
	
	public boolean isPath() {
		return isPath;
	}
	
	public void setBall() {
		isBall = true;
	}
	
	public boolean isBall() {
		return isBall;
	}
	
	public void setBait() {
		isBait = true;
	}
	
	public boolean isBait() {
		return isBait;
	}
	
	public void setPotion() {
		isPotion = true;
	}
	
	public boolean isPotion() {
		return isPotion;
	}
	
	public void setZinc() {
		isZinc = true;
	}
	
	public boolean isZinc() {
		return isZinc;
	}
	
	public void setRock() {
		isRock = true;
	}
	
	public boolean isRock() {
		return isRock;
	}
	
	public void setWater() {
		isWater = true;
	}
	
	public boolean isWater() {
		return isWater;
	}
	
	public void setOther() {
		isOther = true;
	}
	
	public boolean isOther() {
		return isOther;
	}
	
	public void setBorder() {
		isBorder = true;
	}
	
	public boolean isBorder() {
		return isBorder;
	}
	
	public void setPlayer() {
		isPlayer = true;
	}
	
	public void unsetPlayer() {
		isPlayer = false;
	}
	
	public boolean isPlayer() {
		return isPlayer;
	}
	
	public void setCave() {
		isCave = true;
	}
	
	public boolean isCave() {
		return isCave;
	}
	
	public void setPortal() {
		isPortal = true;
	}
	
	public boolean isPortal() {
		return isPortal;
	}
	
	public void setTeleport() {
		isTeleport = true;
	}
	
	public boolean isTeleoprt() {
		return isTeleport;
	}
	
	public void teleMoved() {
		isTeleport = false;
	}
	
	public void ballFound() {
		isBall = false;
	}
	
	public void baitFound() {
		isBait = false;
	}
	
	public void potionFound() {
		isPotion = false;
	}
	
	public void rockFound() {
		isRock = false;
	}
	
	public void zincFound() {
		isZinc = false;
	}
		
	public void setPosition(int X, int Y) {
		x = X;
		y = Y;
	}
	
	
	public boolean isItem() {
		return isBait || isBall || isPotion || isZinc || isRock;
	}
}

