package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Pokemon;
import model.pokemon.Abra;
import model.pokemon.Charizard;
import model.pokemon.Diglett;
import model.pokemon.MissingNo;

public class PokemonTest {
	//TODO Add a multiplayer controller
	//TODO Make an interface for this an the multiplayer controller so that both can
	// be referenced in the same way
	@Before
	public void setUp() throws Exception {
		Pokemon.setNoRand();
	}

	@After
	public void tearDown() throws Exception {
		Pokemon.setRand();
	}

	@Test
	public void test() {
		Abra abra = new Abra(1);
		Diglett diglett = new Diglett(3);
		Charizard charizard = new Charizard(2);
		MissingNo missingNo = new MissingNo(20);

		assertTrue(abra.title().equals("Abra(1)"));
		
		assertTrue(abra.getDefense() < missingNo.getDefense());
		assertTrue(diglett.getAttack() < missingNo.getAttack());
		assertTrue(diglett.getHp() < missingNo.getHp());

		String ret;

		ret = abra.attack(missingNo, 1).toLowerCase();

		assertTrue(ret.contains("abra"));
		assertTrue(ret.contains("attack"));

		ret = diglett.attack(missingNo, 0).toLowerCase();

		assertTrue(ret.contains("diglet"));
		assertTrue(ret.contains("damage"));

		ret = charizard.attack(missingNo, 1).toLowerCase();

		assertTrue(ret.contains("charizard"));
		assertTrue(ret.contains("defense"));

		assertFalse(missingNo.isFainted());

		missingNo.attack(diglett, 1);
		missingNo.attack(abra, 1);
		missingNo.attack(charizard, 1);

		assertTrue(diglett.isFainted());
		assertTrue(charizard.isFainted());
		assertTrue(abra.isFainted());
	}

}
