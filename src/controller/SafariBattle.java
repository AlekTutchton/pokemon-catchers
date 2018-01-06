package controller;

import java.util.Observable;
import java.util.Observer;

import model.Pokemon;
import model.RandomPokemonGenerator;
import model.Rarity;
import model.Trainer;
import model.pokemon.Human;

public class SafariBattle extends Battle {
	private Human human;
	private Pokemon pokemon;
	private Trainer trainer;
	int friendliness;

	/** true when the encounter has ended in a capture */
	public Boolean captured = false;
	/** true when the encounter has ended in an escape */
	public Boolean escaped = false;

	int turn = 0;

	public SafariBattle(Observer gui, Trainer trainer) {
		super(gui);
		this.trainer= trainer;
		human = new Human();
		int level = 10 + (int) Math.abs(Math.random() * 10 - Math.random() * 10);
		pokemon = RandomPokemonGenerator.next(level);
		friendliness = Pokemon.binomialRand(20,70) - level;
		
		startMessage();
	}

	public Boolean action(int choice) {
		if (choice == 0 && trainer.getRocks() == 0)
			return false;
		if (choice == 1 && trainer.getBait() == 0)
			return false;
		if (choice == 2 && trainer.getBalls() == 0)
			return false;
		
		if (choice == 0) {
			rock();
			trainer.useRock();
		}
		else if (choice == 1) {
			bait();
			trainer.useBait();
		}
		else {
			pokeball();
			trainer.useBall();
		}
		
		escaped = ranAway();

		if (isOver())
			endMessage();
		else
			message(pokemon.getName() + getFeeling());
		
		return true;
	}

	/** Throw a Pokeball */
	private void pokeball() {
		String output;
		output = "You threw a Pokeball at " + pokemon.getName() + ".";
		if (caughtByPokeball()) {
			captured = true;
			output += " Succses!";
		} else
			output += " " + pokemon.getName() + " broke free.";
		message(output);
	}

	/** Throw a rock */
	private void rock() {
		String output;
		int damage = (int) (Math.random() * Math.random() * pokemon.maxHP);

		pokemon.takeAttack(damage);
		friendliness -= (int) (Math.random() * 15);

		output = "You threw a rock at " + pokemon.getName() + ".";
		output += " Doing " + damage + " damage.";

		message(output);
	}

	/** Throw bait */
	private void bait() {
		friendliness += (int) (Math.random() * 15);
		message(pokemon.getName() + " took the bait.");
	}

	/**
	 * Returns a short sentence indicating the Pokemons feelings about the trainer.
	 * This should be used to give the trainer some feedback on the Pokemons
	 * attitude towards them when there throwing bait and rocks at it.
	 */
	public String getFeeling() {
		String feeling;
		if (friendliness > 85)
			feeling = " looks adoringly at you.";
		else if (friendliness > 60)
			feeling = " smiles politely.";
		else if (friendliness > 50)
			feeling = " appears inquisitive.";
		else if (friendliness > 40)
			feeling = " looks impatient.";
		else {
			feeling = " is irate.";
		}
		return feeling;
	}

	public Boolean isOver() {
		return escaped || captured || pokemon.isFainted();
	}

	/**
	 * Returns true if the Pokeball successfully caught the wild Pokemon, this is
	 * based on the Pokemons friendliness and its health.
	 */
	public Boolean caughtByPokeball() {
		double healthRation = (double) (pokemon.hp / pokemon.maxHP);
		double friendlinessRation = (double) (friendliness / 100);

		return Math.random() + Math.random() < healthRation + friendlinessRation;
	}

	/**
	 * If the player would not have caught the Pokemon in three tries the Pokemon
	 * runs away. In addition Pokemon have a flat 5% chance of running away each
	 * round to keep there behavior from getting too predictable.
	 */
	public Boolean ranAway() {
		Boolean flatChance = Math.random() < 0.05;
		Boolean threeFails = !(caughtByPokeball() || caughtByPokeball() || caughtByPokeball());
		return flatChance || threeFails;
	}

	/** Should return the 4 available options corresponding to move(0) - move(3). */
	public String[] avaliableOptions() {
		String[] options = human.getAttackNames();
		options[0] = options[0] + " (" + trainer.getRocks() + ")";
		options[1] = options[1] + " (" + trainer.getBait() + ")";
		options[2] = options[2] + " (" + trainer.getBalls() + ")";
		return options;
	}

	/**
	 * Adds punctuation to give the player an idea of the rarity of the encounter
	 */
	public void startMessage() {
		String punctuation;
		if (pokemon.rarity == Rarity.EXECUTIVEPLATINUM)
			punctuation = "!?";
		else if (pokemon.rarity == Rarity.LUXURY)
			punctuation = "!";
		else
			punctuation = ".";

		String output = "A wild " + pokemon.title() + " appeard" + punctuation;
		output += " It" + getFeeling();

		message(output);
	}

	public void endMessage() {
		if (escaped)
			message(pokemon.getName() + " got away.");
		else if (captured)
			message(pokemon.getName() + " captured!");
		else
			message(pokemon.getName() + " fainted.");
	}

	/**
	 * Return the Pokemon that you encountered, used add the Pokemon to inventory.
	 * after a successful capture
	 */

	@Override
	Pokemon getRight() {
		return pokemon;
	}

	@Override
	Pokemon getLeft() {
		return human;
	}

}
