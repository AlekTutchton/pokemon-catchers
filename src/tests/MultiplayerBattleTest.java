package tests;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.MultiplayerBattle;
import controller.Server;
import javafx.concurrent.Task;
import model.pokemon.Charizard;
import model.pokemon.MissingNo;

public class MultiplayerBattleTest implements Observer {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {

	}

	/** Start 2 instances! */
	@Test
	public void test() {
		MultiplayerBattle mc = new MultiplayerBattle(new Charizard(10), this);
		while (!mc.isOver()) {
			mc.action((int)(Math.random() * 3));
		}
		System.out.println("Done");
		
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println((String) arg);
	}

}
