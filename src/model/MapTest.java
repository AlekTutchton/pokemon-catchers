package model;

import static org.junit.Assert.*;
//import model.Map;

import org.junit.Test;

public class MapTest {
	Map map = new Map();

	@Test
	public void testMap() {
		map.createMap1();
		map.setTrainer(2, 3);
		
		assertTrue(map.getRoom(2, 3) == 'T');
		map.unsetTrainer(2, 3);
		assertFalse(map.getRoom(2, 3) == 'T');
		
		assertTrue(map.isGrass(3,2));
		assertTrue(map.getRoom(3, 2) == 'G');
		
		assertTrue(map.isBall(51, 44));
		assertTrue(map.getRoom(51, 44) == 'B');
		assertTrue(map.getItem(51, 44).equals("Ball"));
		assertTrue(map.isItem(51, 44));
		map.ballUsed(51, 44);
		assertTrue(!map.isBall(51, 44));
		assertFalse(map.isBall(51, 44));
		
		assertTrue(map.isRock(21,22));
		assertTrue(map.getItem(21, 22).equals("Rock"));
		map.rockUsed(21, 22);
		assertTrue(map.getRoom(21,22) == 'O');
		
		assertTrue(map.isCave(2, 0));
//		assertTrue(map.getRoom(2, 0) == 'O');
		
		assertTrue(map.isWater(46, 46));
		assertTrue(map.getRoom(46, 46) == 'W');
		
		assertTrue(map.isPath(6, 51));
		assertTrue(map.getRoom(6, 51) == 'P');
		
		assertTrue(map.isPortal(99, 96));
		assertTrue(map.getRoom(99, 96) == 'V');
		
		assertTrue(map.isOther(2, 2));
		assertTrue(map.getRoom(2, 2) == 'O');
		
		assertTrue(map.getRoom(0, 1) == 'X');
		
		map.placeBait(2, 5);
		assertTrue(map.isBait(2, 5));
		map.getItem(2, 5).equals("Bait");
		map.baitUsed(2, 5);
		
		map.placeZinc(2, 6);
		assertTrue(map.isZinc(2, 6));
		map.getItem(2, 6).equals("Zinc");
		map.zincUsed(2, 6);
		
		map.placePotion(2, 7);
		assertTrue(map.isPotion(2, 7));
		map.getItem(2, 7).equals("Potion");
		map.potionUsed(2, 7);
		
		map.setTrainer(2, 3);
		String pv = map.playerView(30, 30);
		String pv2 = map.playerView(0, 0);
		String pv3 = map.playerView(2, 40);
		String pv4 = map.playerView(90, 90);
		
		String mapS = map.printMap();
		
		map.spawnTeleport();
		map.setTeleport(46, 46);
		map.spawnTeleport();
		map.spawnTeleport();
		
		int[] coor = new int[2];
		coor = map.getTeleport();
		
		assertTrue(map.isTeleport(coor[0], coor[1]));
		map.unsetTeleport(coor[0], coor[1]);

		
		
	}
	
	@Test
	public void ArenaTest() {
		Arena a = new Arena();
		Arena s = new Arena();
		Arena c = new Arena();
		Arena f = new Arena();
		Arena w = new Arena();
		
		Trainer trainer = new Trainer();
		a.setTrainer(1, 1);
		assertTrue(a.getRoom(1, 1) == 'T');
		trainer.inArena();
		assertTrue(trainer.getRoom() == "arena");
		
		trainer.inCave();
		assertTrue(trainer.getRoom() == "cave");

		trainer.inFire();
		assertTrue(trainer.getRoom() == "fire");

		trainer.inMap();
		assertTrue(trainer.getRoom() == "map");

		trainer.inSnow();
		assertTrue(trainer.getRoom() == "snow");

		trainer.inWater();
		assertTrue(trainer.getRoom() == "water");
		
		Pokedex p = trainer.getPokedex();
		trainer.getX();
		trainer.getY();
		trainer.getBait();
		trainer.addBait();
		trainer.useBait();
		trainer.getBalls();
		trainer.addBall();
		trainer.useBall();
		trainer.getPotion();
		trainer.addPotion();
		trainer.usePotion();
		trainer.getRocks();
		trainer.addRock();
		trainer.useRock();
		trainer.getZinc();
		trainer.addZinc();
		trainer.useZinc();
		trainer.getSteps();
		trainer.addStep();
		
		trainer.moveDown();
		trainer.moveLeft();
		trainer.moveRight();
		trainer.moveUp();
		assertTrue(trainer.hasMoreSteps());
		
		trainer.setPos(0, 0);

		
		a.createArena();
		s.createSnow();
		c.createCave();
		f.createFire();
		w.createWater();
		
		String pv = a.playerView(0, 0);
		String pv2 = s.playerView(18, 18);
		String pv3 = c.playerView(10, 10);
		String pv4 = f.playerView(0, 0);
		String pv5 = w.playerView(0, 0);
		
		a.unsetTrainer(1, 1);

		
		a.placeBait(2, 2);
		s.placeBall(2, 2);
		c.placePotion(2, 2);
		f.placeRock(2, 2);
		w.placeZinc(2, 2);
		
		assertTrue(a.isBait(2, 2));
		assertTrue(a.getItem(2, 2).equals("Bait"));
		assertTrue(a.isItem(2, 2));
		a.baitUsed(2, 2);
		
		assertTrue(s.isBall(2, 2));
		assertTrue(s.getItem(2, 2).equals("Ball"));
		assertTrue(s.getRoom(2, 2) == 'B');
		s.ballUsed(2, 2);
		
		assertTrue(c.isPotion(2, 2));
		assertTrue(c.getItem(2, 2).equals("Potion"));
		c.potionUsed(2, 2);
		
		assertTrue(f.isRock(2, 2));
		assertTrue(f.getItem(2, 2).equals("Rock"));
		f.rockUsed(2, 2);
		
		assertTrue(w.isZinc(2, 2));
		assertTrue(w.getItem(2, 2).equals("Zinc"));
		w.zincUsed(2, 2);
		
		assertTrue(a.isGrass(3, 15));
		assertTrue(a.getRoom(3, 15) == 'G');
		
		assertTrue(a.getRoom(0, 14) == 'V');
		assertTrue(a.isPortal(0, 14));
		
		assertTrue(w.getRoom(10, 26) == 'P');
		assertTrue(w.isPath(10, 26));
		
		assertTrue(w.getRoom(19, 25) == 'W');
		assertTrue(w.isWater(19, 25));
		
		assertTrue(w.getRoom(0, 0) == 'X');
		
		assertTrue(f.isTeleport(14, 28));
		
		
	}
	

}
