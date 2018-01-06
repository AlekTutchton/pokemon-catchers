package model;

import java.util.Random;

public class Arena {
	
	private Room[][] arena;
	private int size = 30;

	public Arena() {
		arena = new Room[size][size];
		makeArena(size,size);
	}
	
	private void makeArena(int a, int b) {
		for (int c = 0; c < a; c++) {
			for (int r = 0; r < b; r++) {
				arena[c][r] = new Room();
				arena[c][r].setPosition(c, r);
			}
		}
	}
	
	public void createArena() {
		setBounds(size);

		placePortal(0,14);
		createGrass();
	}
	
	public void createCave() {
		setBounds(size);
		placeCave(2,0);
		setWater(10,20,10,15);
		setWater(12,18,9,10);
		setWater(14,16,6,9);
		setWater(8,22,5,6);
		setWater(6,8,6,7);
		setWater(22,24,6,7);
		setWater(24,26,7,8);
		setWater(4,6,7,8);
		setWater(4,5,8,16);
		setWater(25,26,8,16);
		setWater(14,16,15,27);
		setWater(12,18,26,27);
		
		setOther();
		spawnItems();
	}
	
	public void createSnow() {
		setBounds(size);
		placeTeleport(14,13);
		
		setWater(7,13,12,15);
		setWater(9,20,11,12);
		setWater(16,22,12,15);
		setWater(9,20,15,16);
		setWater(11,18,16,17);
		setWater(11,18,10,11);
		setWater(13,16,17,18);
		setWater(13,16,9,10);
		
		setWater(13,16,18,28);
		setWater(2,7,12,15);
		setWater(22,28,12,15);
		setWater(13,16,2,9);
		
		setGrass(1,11,1,9);
		setGrass(18,29,1,9);
		setGrass(1,11,21,29);
		setGrass(18,29,21,29);
		
		setOther();
		spawnItemsGrass();
	}
	
	public void createFire() {
		setBounds(size);
		setWater(3,27,3,7);
		setWater(3,7,5,20);
		setWater(23,27,5,20);
		setWater(3,13,20,24);
		setWater(16,27,20,24);
		setWater(12,18,11,15);
		
		placeTeleport(14,28);
		setOther();
		spawnItems();
	}
	
	public void createWater() {
		setBounds(size);
		placeTeleport(4,21);
		
		setWater(18,27,25,29);
		setWater(22,27,22,26);
		setWater(4,10,26,28);
		setWater(3,5,21,28);
		setWater(11,17,12,16);
		setWater(11,14,7,12);
		setWater(14,17,9,13);
		setWater(24,27,7,9);
		setWater(3,5,1,17);
		setWater(5,26,1,3);
		setWater(5,13,3,5);
		setWater(21,28,3,5);
		setWater(26,28,5,10);
		
		setWater(4,14,13,14);
		setWater(8,16,16,17);
		
		setPath(10,18,26,28);
		setPath(24,27,13,22);
		setPath(17,24,13,15);
		setPath(14,24,7,9);
		setPath(5,11,14,16);
		setPath(14,17,3,7);
		setPath(13,14,3,9);
		
		setPath(4,18,16,16);
		
		setGrass(3,5,11,17);
		setGrass(11,13,7,11);
		setGrass(14,17,9,11);
		setGrass(14,17,11,13);
		setGrass(25,27,7,9);
		setGrass(10,26,1,3);
		setGrass(3,10,26,28);
		
		setGrass(8,16,16,17);
		setGrass(26,28,8,10);
		setGrass(10,13,3,5);
		
		placeOther(1,3,1,28);
		spawnItemsGrass();
		setOther();
	}
	
	private void placeCave(int x, int y) {
		arena[x][y].setCave();
	}
	
	
	private void createGrass() {
		placeGrass(14,13);
		placeGrass(13,14);
		placeGrass(14,15);
		placeGrass(16,14);
		placeGrass(15,15);
		placeGrass(3,14);
		placeGrass(26,14);
		
		setGrass(4,14,14,15);
		setGrass(16,26,14,15);
		setGrass(14,15,4,13);
		setGrass(3,27,13,14);
		setGrass(4,26,11,13);
		setGrass(5,25,9,11);
		setGrass(6,24,8,9);
		setGrass(7,23,7,8);
		setGrass(9,21,6,7);
		setGrass(10,20,5,6);
		setGrass(12,18,4,5);
		
		placeGrass(3,15);
		placeGrass(26,15);
		placeGrass(4,16);
		placeGrass(25,16);
		placeGrass(4,17);
		placeGrass(25,17);
		placeGrass(5,18);
		placeGrass(24,18);
		placeGrass(5,19);
		placeGrass(24,19);
		placeGrass(6,20);
		placeGrass(23,20);
		placeGrass(7,21);
		placeGrass(22,21);
		placeGrass(8,22);
		placeGrass(21,22);
		placeGrass(10,23);
		placeGrass(19,23);
		placeGrass(12,24);
		placeGrass(17,24);
		placeGrass(13,24);
		placeGrass(14,24);
		placeGrass(15,24);
		placeGrass(16,24);
		
	}
	
	public String playerView(int x, int y) {
		String pv = "";
		int xi;
		int yi;
		int xf;
		int yf;
		
		xf = x + 15;
		yf = y + 15;
		
		xi = x - 15;
		yi = y - 15;
		
		if(xf > 29) {
			xi -= (xf - 29);
			xf = 29;
		}
		if(yf > 29) {
			yi -= (yf - 29);
			yf = 29;
		}
		if(xi < 0) {
			xf += (xi*-1);
			xi = 0;
		}
		if(yi < 0) {
			yf += (yi*-1);
			yi = 0;
		}
		

		for(int r = yi; r < yf; r++) {
			for(int c = xi; c < xf; c++) {
				if(arena[c][r].isPlayer()) {
					pv += "T ";
					continue;
				}
				if(arena[c][r].isBall()) {
					pv += "B ";
				}
				else if(arena[c][r].isGrass()) {
					pv += "G ";
				}
				else if(arena[c][r].isPath()) {
					pv += "P ";
				}
				else if(arena[c][r].isWater()) {
					pv += "W ";
				}
				else if(arena[c][r].isBorder()) {
					pv += "X ";
				}
				else if(arena[c][r].isOther()) {
					pv += ". ";
				}
				else {
					arena[c][r].setOther();
					pv += ". ";
				}
			}
			pv += '\n';
		}
		return pv;
	}
	
	public char getRoom(int c, int r) {
		if(arena[c][r].isPlayer()) {
			return 'T';
		}
		if(arena[c][r].isBall()) {
			return 'B';
		}
		else if(arena[c][r].isPortal()) {
			return 'V';
		}
		else if(arena[c][r].isGrass()) {
			return 'G';
		}
		else if(arena[c][r].isPath()) {
			return 'P';
		}
		else if(arena[c][r].isWater()) {
			return 'W';
		}
		else if(arena[c][r].isBorder()) {
			return 'X';
		}
		else if(arena[c][r].isOther()) {
			return 'O';
		}
		else if(arena[c][r].isRock()) {
			return 'R';
		}
		else {
			return ' ';
		}
	}
	
	public void ballUsed(int x, int y) {
		arena[x][y].ballFound();
	}
	
	public void baitUsed(int x, int y) {
		arena[x][y].baitFound();
	}
	
	public void potionUsed(int x, int y) {
		arena[x][y].potionFound();
	}
	
	public void rockUsed(int x, int y) {
		arena[x][y].rockFound();
	}
	
	public void zincUsed(int x, int y) {
		arena[x][y].zincFound();
	}
	
	private void setWater(int startX, int endX, int startY, int endY) {
		for(int c = startX; c < endX; c++) {
			for(int r = startY; r < endY; r++) {
				arena[c][r].setWater();
			}
		}
	}
	
	private void setGrass(int startX, int endX, int startY, int endY) {
		for(int c = startX; c < endX; c++) {
			for(int r = startY; r < endY; r++) {
				arena[c][r].setGrass();
			}
		}
	}
	
	private void setPath(int startX, int endX, int startY, int endY) {
		for(int c = startX; c < endX; c++) {
			for(int r = startY; r < endY; r++) {
				arena[c][r].setPath();
			}
		}
	}
		
	private void setBounds(int a) {
		for(int i = 0; i < a; i++) {
			arena[0][i].setBorder();
			arena[i][0].setBorder();
			arena[i][a-1].setBorder();
			arena[a-1][i].setBorder();
		}
	}
	
	public void setTrainer(int x, int y) {
		arena[x][y].setPlayer();
	}
	
	public void unsetTrainer(int x, int y) {
		arena[x][y].unsetPlayer();
	}
	
	private void placeGrass(int x, int y) {
		arena[x][y].setGrass();
	}
	
	private void placePortal(int x, int y) {
		arena[x][y].setPortal();
	}
	
	private void placeTeleport(int x, int y) {
		arena[x][y].setTeleport();
	}
	
	private void placeOther(int startX, int endX, int startY, int endY) {
		for(int c = startX; c < endX; c++) {
			for(int r = startY; r < endY; r++) {
				arena[c][r].setPath();
			}
		}
	}
	
	public boolean isGrass(int x, int y) {
		return arena[x][y].isGrass();
	}
	
	public boolean isPath(int x, int y) {
		return arena[x][y].isPath();
	}
	
	public boolean isWater(int x, int y) {
		return arena[x][y].isWater();
	}
	
	public boolean isOther(int x, int y) {
		return arena[x][y].isOther();
	}
	
	public boolean isPortal(int x, int y) {
		return arena[x][y].isPortal();
	}
	
	public boolean isTeleport(int x, int y) {
		return arena[x][y].isTeleoprt();
	}
	
	private void setBall(int x, int y) {
//		arena[x][y].setGrass();
		arena[x][y].setBall();
	}
	
	private void setRock(int x, int y) {
		arena[x][y].setOther();
		arena[x][y].setRock();
	}
	
	private void setBait(int x, int y) {
		arena[x][y].setBait();
	}
	
	private void setZinc(int x, int y) {
		arena[x][y].setZinc();
	}
	
	private void setPotion(int x, int y) {
		arena[x][y].setPotion();
	}
	
	private void setOther() {
		for (int r = 0; r < 30; r++) {
			for (int c = 0; c < 30; c++) {
				if(!arena[c][r].isBorder() && !arena[c][r].isGrass() && !arena[c][r].isWater() && !arena[c][r].isTeleoprt()) {
					arena[r][c].setOther();
				}
			}
		}
	}
	
	public boolean isItem(int x, int y) {
		return arena[x][y].isItem();
	}
	public boolean isRock(int x, int y) {
		return arena[x][y].isRock();
	}
	public boolean isPotion(int x, int y) {
		return arena[x][y].isPotion();
	}
	public boolean isZinc(int x, int y) {
		return arena[x][y].isZinc();
	}
	public boolean isBait(int x, int y) {
		return arena[x][y].isBait();
	}
	public boolean isBall(int x, int y) {
		return arena[x][y].isBall();
	}
	
	public void placeRock(int x, int y) {
		arena[x][y].setRock();
	}
	public void placePotion(int x, int y) {
		arena[x][y].setPotion();
	}
	public void placeZinc(int x, int y) {
		arena[x][y].setZinc();
	}
	public void placeBait(int x, int y) {
		arena[x][y].setBait();
	}
	public void placeBall(int x, int y) {
		arena[x][y].setBall();
	}
	
	public void spawnItems() {
		Random ran = new Random();
		for (int r = 0; r < 30; r++) {
			for (int c = 0; c < 30; c++) {
				if(arena[c][r].isOther() && !arena[c][r].isWater()) {
					int i = ran.nextInt(100);
					if(i < 2) {
						setBall(c,r);
					}
					if(i > 10 && i < 12) {
						setRock(c,r);
					}
					if(i > 19 && i < 21) {
						setZinc(c,r);
					}
					if(i > 24 && i < 26) {
						setPotion(c,r);
					}
					if(i > 29 && i < 32) {
						setBait(c,r);
					}
				}
			}
			
		}

	}
	
	private void spawnItemsGrass() {
		Random ran = new Random();
		for (int r = 0; r < 30; r++) {
			for (int c = 0; c < 30; c++) {
				if(arena[c][r].isGrass()) {
					int i = ran.nextInt(100);
					if(i < 2) {
						setBall(c,r);
					}
					if(i > 10 && i < 12) {
						setRock(c,r);
					}
					if(i > 19 && i < 21) {
						setZinc(c,r);
					}
					if(i > 24 && i < 26) {
						setPotion(c,r);
					}
					if(i > 29 && i < 32) {
						setBait(c,r);
					}
				}
			}
			
		}

	}
	
	public String getItem(int x, int y) {
		String i = "";
		if(arena[x][y].isBall()) {
			i += "Ball";
		}
		if(arena[x][y].isBait()) {
			i += "Bait";
		}
		if(arena[x][y].isZinc()) {
			i += "Zinc";
		}
		if(arena[x][y].isPotion()) {
			i += "Potion";
		}
		if(arena[x][y].isRock()) {
			i += "Rock";
		}
		return i;
	}

	public boolean isCave(int x, int y) {
		return arena[x][y].isCave();
	}

}