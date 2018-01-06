package model;

import java.util.ArrayList;

import model.pokemon.Abra;
import model.pokemon.Bulbasaur;
import model.pokemon.Charizard;
import model.pokemon.Cubone;
import model.pokemon.Diglett;
import model.pokemon.Koffing;
import model.pokemon.Lugia;
import model.pokemon.Mew;
import model.pokemon.MissingNo;
import model.pokemon.Pidgeotto;
import model.pokemon.Pikachu;
import model.pokemon.Rapidash;
import model.pokemon.Rayquaza;
import model.pokemon.Scyther;
import model.pokemon.Vulpix;

// Author: Rowan Lochrin
/**
 * Returns a random Pokemon of the level input. Out of all Pokemon there's a 10%
 * chance that the Pokemon will from the highest rarity tier, a 20% chance that
 * it will be from the second highest tier and a 70% chance that it will be
 * common.
 */
public class RandomPokemonGenerator {

	/** ADD ALL POKEMON TO THIS ARRAY (except human) */
	public static Pokemon[] POKEMON = { new Abra(0), new Bulbasaur(0), new Charizard(0), new Cubone(0), new Diglett(0),
			new Koffing(0), new Lugia(0), new Mew(0), new MissingNo(0), new Pidgeotto(0), new Pikachu(0),
			new Rapidash(0), new Rayquaza(0), new Scyther(0), new Vulpix(0) };

	public static final ArrayList<Pokemon> ECONOMY = filterRarity(POKEMON, Rarity.ECONOMY);
	public static final ArrayList<Pokemon> LUXURY = filterRarity(POKEMON, Rarity.LUXURY);
	public static final ArrayList<Pokemon> EXECUTIVEPLATINUM = filterRarity(POKEMON, Rarity.EXECUTIVEPLATINUM);

	public static Pokemon next(int level) {
		ArrayList<Pokemon> tier;
		int rand = (int) (Math.random() * 10);

		if (rand == 0)
			tier = EXECUTIVEPLATINUM;
		else if (rand <= 3)
			tier = LUXURY;
		else
			tier = ECONOMY;

		// takes a clone of a random element from the tier selected
		Pokemon poke = tier.get((int) (Math.random() * tier.size())).clone();
		poke.level = level;
		for (int i = 0; i <= level; i++) {
			poke.levelUp();
		}
		return poke;
	}

	private static final ArrayList<Pokemon> filterRarity(Pokemon[] pokes, Rarity r) {
		ArrayList<Pokemon> filtered = new ArrayList<Pokemon>();
		for (Pokemon poke : pokes)
			if (poke.rarity == r)
				filtered.add(poke);
		return filtered;
	}
}