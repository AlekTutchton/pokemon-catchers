package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Map {

	private Room[][] map;
	private String board = "";
	
	public Map() {
		map = new Room[100][100];
		makeMap(100,100);
	}
	
	
	private void makeMap(int a, int b) {
		for (int c = 0; c < a; c++) {
			for (int r = 0; r < b; r++) {
				map[c][r] = new Room();
				map[c][r].setPosition(c, r);
			}
		}
	}
	
	public char getRoom(int c, int r) {
		if(map[c][r].isPlayer()) {
			return 'T';
		}
		if(map[c][r].isBall()) {
			return 'B';
		}
		else if(map[c][r].isGrass()) {
			return 'G';
		}
		else if(map[c][r].isPath()) {
			return 'P';
		}
		else if(map[c][r].isWater()) {
			return 'W';
		}
		else if(map[c][r].isPortal()) {
			return 'V';
		}
		else if(map[c][r].isBorder()) {
			return 'X';
		}
		else if(map[c][r].isCave()) {
			return 'C';
		}
		else if(map[c][r].isOther()) {
			return 'O';
		}
		else if(map[c][r].isRock()) {
			return 'R';
		}
		else {
			return ' ';
		}
	}
	
	public void createMap1() {
		setBounds(100);
		createWater1();
		createGrass1();
		createBorders1();
		createPaths1();
		createBalls1();
		createRocks1();
		placeCave(2,0);
		placePortal(99,96);
		setOther();
		spawnItems();

	}
	
	
	private void createGrass1() {
		setGrass(13,21,17,19);
		setGrass(11,13,12,22);
		setGrass(10,11,13,21);
		setGrass(9,10,14,20);
		setGrass(8,9,15,19);
		setGrass(7,8,16,18);
		setGrass(4,7,9,12);
		setGrass(9,33,27,32);
		setGrass(29,33,12,27);
		setGrass(25,28,17,26);
		setGrass(2,5,13,38);
		setGrass(5,23,34,37);
		setGrass(11,13,22,27);
		setGrass(7,9,24,34);
		setGrass(3,30,4,7);
		setGrass(26,30,7,8);
		setGrass(27,30,8,9);
		setGrass(28,30,9,10);
		setGrass(29,30,10,11);
		setGrass(3,6,1,4);
		
		setGrass(43,45,39,41);
		setGrass(45,49,42,45);
		setGrass(43,45,47,52);
		setGrass(44,51,56,57);
		setGrass(54,57,56,57);
		setGrass(56,57,54,56);
		setGrass(55,56,55,56);
		setGrass(55,56,45,49);
		setGrass(56,57,47,49);
		setGrass(59,61,41,59);
		setGrass(35,41,37,59);
		setGrass(25,35,40,57);
		
		setGrass(15,30,65,80);
		setGrass(13,15,67,78);
		setGrass(11,13,69,76);
		setGrass(9,11,71,74);
		setGrass(7,9,72,73);
		
		setGrass(30,32,67,78);
		setGrass(32,34,69,76);
		setGrass(34,36,71,74);
		setGrass(36,38,72,73);
		
		setGrass(17,28,63,65);
		setGrass(19,26,61,63);
		setGrass(21,24,59,61);
		setGrass(22,23,57,59);
		
		setGrass(17,28,80,82);
		setGrass(19,26,82,84);
		setGrass(21,24,84,86);
		setGrass(22,23,86,88);
		
		setGrass(5,7,55,90);
		setGrass(7,40,55,57);
		setGrass(38,40,57,90);
		setGrass(7,40,88,90);
		
		setGrass(4,41,94,95);
		setGrass(4,5,95,99);
		setGrass(40,41,95,99);
		
		setGrass(12,40,50,57);
		setGrass(18,40,45,50);

		setGrass(4,10,43,45);
		setGrass(5,9,42,43);
		setGrass(5,9,45,46);
		setGrass(6,8,46,47);
		setGrass(6,8,41,42);
		
		setGrass(9,12,40,41);
		setGrass(11,12,41,43);
		setGrass(10,11,41,42);
		
		setGrass(2,5,40,41);
		setGrass(2,3,41,43);
		setGrass(3,4,41,42);
		
		setGrass(2,5,47,48);
		setGrass(2,3,45,47);
		setGrass(3,4,46,47);
		
		setGrass(9,12,47,48);
		setGrass(11,12,45,47);
		setGrass(10,11,46,47);
		
		setGrass(63,64,52,55);
		
		setGrass(7,14,57,63);
		setGrass(31,38,57,63);
		setGrass(7,14,83,88);
		setGrass(31,38,83,88);
		setGrass(40,41,50,90);
		setGrass(4,5,55,90);
		setGrass(1,4,94,99);
		
		setGrass(91,98,2,25);
		setGrass(88,91,13,25);
		setGrass(57,88,13,15);
		setGrass(60,88,23,25);
		setGrass(94,98,25,35);
		setGrass(71,94,33,35);
		
		setGrass(47,50,63,95);
		setGrass(53,56,63,95);
		setGrass(59,62,63,95);
		setGrass(65,68,61,95);
		setGrass(71,74,56,95);
		setGrass(77,80,56,95);
		setGrass(83,86,56,95);
		setGrass(89,92,54,95);
		setGrass(95,98,47,95);

	}
	
	
	private void createWater1() {
		setWater(13,19,12,17);
		setWater(19,25,14,17);
		setWater(21,25,17,22);
		setWater(13,21,19,22);
		setWater(17,21,22,26);
		setWater(20,25,8,11);
		
		setWater(45,55,45,55);
		setWater(49,51,41,45);
		setWater(51,59,41,43);
		setWater(57,59,43,59);
		setWater(41,59,57,59);
		setWater(41,43,37,57);
		setWater(41,63,37,39);
		setWater(61,63,39,59);
		setWater(63,66,57,59);
		setWater(64,66,56,57);
		
		setWater(5,40,95,99);
		
		setWater(50,90,5,8);
		setWater(87,90,8,13);
		setWater(53,87,10,13);
		setWater(53,56,13,18);
		setWater(56,87,15,18);
		setWater(84,87,18,23);
		setWater(56,84,20,23);
		setWater(56,59,23,28);
		setWater(59,94,25,28);
		setWater(91,94,28,33);
		setWater(67,91,30,33);
		setWater(67,70,33,38);
		setWater(70,99,35,38);
		setWater(96,99,38,43);
		setWater(77,96,40,43);
		setWater(77,80,43,48);
		setWater(80,90,46,48);
		setWater(88,90,48,53);
		setWater(64,88,51,53);
		setWater(64,66,53,56);
	}
	
	private void createPaths1() {
		setPath(5,10,51,54);
		setPath(13,17,47,49);
		setPath(16,17,45,47);
		setPath(15,16,46,47);
	}
	
	private void createBorders1() {
		setBorder(21,25,25,26);
		setBorder(1,24,38,39);
		setBorder(23,24,32,38);
		setBorder(24,34,32,33);
		setBorder(33,34,12,32);
		setBorder(30,31,1,11);
		setBorder(41,42,61,99);
	}
	
	private void createBalls1() {
		setBall(51,44);
		setBall(63,56);
	}
	
	private void createRocks1() {
		setRock(21,22);
		setRock(21,23);
		setRock(21,24);
	}
	
	public void ballUsed(int x, int y) {
		map[x][y].ballFound();
//		placeGrass(x,y);
	}
	
	public void baitUsed(int x, int y) {
		map[x][y].baitFound();
		placeGrass(x,y);
	}
	
	public void potionUsed(int x, int y) {
		map[x][y].potionFound();
		placeGrass(x,y);
	}
	
	public void rockUsed(int x, int y) {
		map[x][y].rockFound();
//		placeGrass(x,y);
	}
	
	public void zincUsed(int x, int y) {
		map[x][y].zincFound();
		placeGrass(x,y);
	}
	

	
	private void setWater(int startX, int endX, int startY, int endY) {
		for(int c = startX; c < endX; c++) {
			for(int r = startY; r < endY; r++) {
				map[c][r].setWater();
			}
		}
	}
	
	private void setGrass(int startX, int endX, int startY, int endY) {
		for(int c = startX; c < endX; c++) {
			for(int r = startY; r < endY; r++) {
				map[c][r].setGrass();
			}
		}
	}
	
	private void setPath(int startX, int endX, int startY, int endY) {
		for(int c = startX; c < endX; c++) {
			for(int r = startY; r < endY; r++) {
				map[c][r].setPath();
			}
		}
	}
	
	private void setBorder(int startX, int endX, int startY, int endY) {
		for(int c = startX; c < endX; c++) {
			for(int r = startY; r < endY; r++) {
				map[c][r].setBorder();
			}
		}
	}
	
	private void setBall(int x, int y) {
		map[x][y].setGrass();
		map[x][y].setBall();
	}
	
	private void setRock(int x, int y) {
		map[x][y].setOther();
		map[x][y].setRock();
	}
	
	private void setBait(int x, int y) {
		map[x][y].setBait();
	}
	
	private void setZinc(int x, int y) {
		map[x][y].setZinc();
	}
	
	private void setPotion(int x, int y) {
		map[x][y].setPotion();
	}
	
	private void setBounds(int a) {
		for(int i = 0; i < a; i++) {
			map[0][i].setBorder();
			map[i][0].setBorder();
			map[i][a-1].setBorder();
			map[a-1][i].setBorder();
		}
	}
	
	private void setOther() {
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if(!map[c][r].isBorder() && !map[c][r].isGrass() && !map[c][r].isWater() && !map[c][r].isPath()) {
					map[r][c].setOther();
				}
			}
		}
	}
	
	public void setTrainer(int x, int y) {
		map[x][y].setPlayer();
	}
	
	public void unsetTrainer(int x, int y) {
		map[x][y].unsetPlayer();
	}
	
	private void placeGrass(int x, int y) {
		map[x][y].setGrass();
	}
	
	private void placeCave(int x, int y) {
		map[x][y].setCave();
	}
	
	private void placePortal(int x, int y) {
		map[x][y].setPortal();
	}
	
	public String getItem(int x, int y) {
		String i = "";
		if(map[x][y].isBall()) {
			i += "Ball";
		}
		if(map[x][y].isBait()) {
			i += "Bait";
		}
		if(map[x][y].isZinc()) {
			i += "Zinc";
		}
		if(map[x][y].isPotion()) {
			i += "Potion";
		}
		if(map[x][y].isRock()) {
			i += "Rock";
		}
		return i;
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
		
		if(xf > 99) {
			xi -= (xf - 99);
			xf = 99;
		}
		if(yf > 99) {
			yi -= (yf - 99);
			yf = 99;
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
				if(map[c][r].isPlayer()) {
					pv += "T ";
					continue;
				}
				if(map[c][r].isBall()) {
					pv += "B ";
				}
				else if(map[c][r].isGrass()) {
					pv += "G ";
				}
				else if(map[c][r].isPath()) {
					pv += "P ";
				}
				else if(map[c][r].isWater()) {
					pv += "W ";
				}
				else if(map[c][r].isPortal()) {
					pv += "V ";
				}
				else if(map[c][r].isBorder()) {
					pv += "X ";
				}
				else if(map[c][r].isCave()) {
					pv += "C ";
				}
				else if(map[c][r].isOther()) {
					pv += ". ";
				}
				else {
					map[c][r].setOther();
					pv += ". ";
				}
			}
			pv += '\n';
		}
		return pv;
	}
	
	public String printMap() {
		board = "";
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if(map[c][r].isPlayer()) {
					board += "T ";
					continue;
				}
				if(map[c][r].isRock()) {
					board += "R ";
				}
				else if(map[c][r].isBall()) {
					board += "B ";
				}
				else if(map[c][r].isGrass()) {
					board += "G ";
				}
				else if(map[c][r].isPath()) {
					board += "P ";
				}
				else if(map[c][r].isWater()) {
					board += "W ";
				}
				else if(map[c][r].isBorder()) {
					board += "X ";
				}
				else if(map[c][r].isOther()) {
					board += ". ";
				}
				else {
					map[c][r].setOther();
					board += ". ";
				}
			}
			board += '\n';
		}
		return board;
	}
	
	public boolean isGrass(int x, int y) {
		return map[x][y].isGrass();
	}
	
	public boolean isPath(int x, int y) {
		return map[x][y].isPath();
	}
	
	public boolean isWater(int x, int y) {
		return map[x][y].isWater();
	}
	
	public boolean isOther(int x, int y) {
		return map[x][y].isOther();
	}
	
	public boolean isRock(int x, int y) {
		return map[x][y].isRock();
	}
	
	public boolean isCave(int x, int y) {
		return map[x][y].isCave();
	}
	
	public boolean isPortal(int x, int y) {
		return map[x][y].isPortal();
	}
	
	public boolean isTeleport(int x, int y) {
		return map[x][y].isTeleoprt();
	}
	
	public boolean isItem(int x, int y) {
		return map[x][y].isItem();
	}
	public boolean isPotion(int x, int y) {
		return map[x][y].isPotion();
	}
	public boolean isZinc(int x, int y) {
		return map[x][y].isZinc();
	}
	public boolean isBait(int x, int y) {
		return map[x][y].isBait();
	}
	public boolean isBall(int x, int y) {
		return map[x][y].isBall();
	}
	
	public void placePotion(int x, int y) {
		map[x][y].setPotion();
	}
	public void placeZinc(int x, int y) {
		map[x][y].setZinc();
	}	public void placeBait(int x, int y) {
		map[x][y].setBait();
	}
	
	
	private void spawnItems() {
		Random ran = new Random();
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if(map[c][r].isGrass()) {
					int i = ran.nextInt(750);
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
	
	public void spawnTeleport() {
		Random ran = new Random();
		int x;
		int y;
		x = ran.nextInt(100);
		y = ran.nextInt(100);
		
		while(!map[x][y].isOther()) {
			x = ran.nextInt(100);
			y = ran.nextInt(100);
		}
		
		map[x][y].setTeleport();
	}
	
	public int[] getTeleport(){
		int[] coor = new int[2];
		
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				if(map[c][r].isTeleoprt()) {
					coor[0] = c;
					coor[1] = r;
					break;
				}
			}
		}
		
		return coor;
	}
	
	public void unsetTeleport(int x, int y) {
		map[x][y].teleMoved();
	}
	
	public void setTeleport(int x, int y) {
		map[x][y].setTeleport();
		map[x][y].setOther();
	}
}

