package model;

import java.io.Serializable;

// Author: Rowan Lochrin

public abstract class Pokemon implements Cloneable, Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * The displayed name for the Pokemon in the wild this should be the type of
	 * Pokemon it is. In the games you get the option to rename your Pokemon, not
	 * sure if we have do do this in the project though.
	 */
	private String name;
	protected String imgPath;

	/** Current stats of the Pokemon */
	public int hp = 0;
	public int attack = 0;
	public int defense = 0;
	public int maxHP = 0;
	public int level;

	/** The stats that will be awarded to each Pokemon upon level up */
	protected int baseAttack = 0;
	protected int baseDefense = 0;
	protected int baseHP = 0;
	/** The rarity of the pokemon changed in the set base stats */
	public Rarity rarity = Rarity.ECONOMY;

	/**
	 * The stats that will be modified during the battle e.g. if a Pokemon is hit
	 * with a move that decreases there attack there temp attack will be set to a
	 * negative value. These should reset after a battle.
	 */
	private int tempAttack = 0;
	private int tempDefense = 0;

	/** The attacks the Pokemon has, should be set with the setMovesMethod */
	protected Attack attack1 = null;
	protected Attack attack2 = null;
	protected Attack attack3 = null;
	protected Attack attack4 = null;

	/**
	 * Used in the safari zone: friendliness should be a value between 1-100 this
	 * and health determine if a pokeball captures a
	 */

	/** Give Pokemon ability to be generated unrandomly used for testing */
	private static boolean NORANDOM = false;

	public Pokemon(String _name, int _level) {
		level = _level;
		name = _name;
		setBaseStats();
		setAttacks();
		for (int i = 0; i < level; i++) {
			levelUp();
		}
	}

	/** Sets the Pokemons baseAttack, baseDefense and baseHP */
	protected abstract void setBaseStats();

	/** Sets attack1 - attack 4 */
	protected abstract void setAttacks();

	/** returns the String image Path for each of the Pokemon */
	public String getImage() {
		return imgPath;
	}

	public int getHp() {
		return hp;
	}

	public int getMaxHp() {
		return maxHP;
	}
	
	/**
	 * Damages the Pokemon, will never leave the Pokemon with a negative amount of
	 * hp. Subtracts the Pokemons defense, returns how much damage was taken.
	 */
	public void takeAttack(int damage) {
		hp = Math.max(0, hp - damage);
	}

	public String title() {
		return name + "(" + level + ")";
	}

	/** Heals a Pokemon like what would happen at a Pokecenter. */
	public void restoreHP() {
		hp = maxHP;
	}

	public String attack(Pokemon other, int num) {
		/** Uses attack number num (between 0 and 3) */
		Attack atk = getAttack(num);

		int maxHPDamage = atk.getPower() + (int) (getAttack() * 1.5 - other.getDefense());
		int hpDamage = binomialRand(1, maxHPDamage);
		int statChange = (int) hpDamage / 1;

		DamageType dt = atk.getDamageType();

		String firstSentence = getName() + " used " + atk.getName() + " on " + other.getName() + ".";
		String secondSentence = "";

		if (dt == DamageType.HP) {
			other.takeAttack(hpDamage);
			secondSentence = "Doing " + Math.max(1, hpDamage) + " damage.";
			if (hpDamage > maxHPDamage * 0.75)
				secondSentence += " Critical hit!";
		}
		if (dt == DamageType.ATKUP) {
			this.setTempAttack(statChange);
			secondSentence = this.getName() + "'s attack rose.";
		}
		if (dt == DamageType.ATKDOWN) {
			other.setTempAttack(-statChange);
			secondSentence = other.getName() + "'s attack fell.";
		}
		if (dt == DamageType.DEFUP) {
			this.setTempDefense(statChange);
			secondSentence = this.getName() + "'s defense rose.";
		}
		if (dt == DamageType.DEFDOWN) {
			other.setTempDefense(-statChange);
			secondSentence = other.getName() + "'s defense fell.";
		}
		return firstSentence + " " + secondSentence;
	}

	/**
	 * Attacks other with a random none null attack.
	 */
	public String randomAttack(Pokemon other) {
		int rand = (int) (Math.random() * 4);
		while (!hasAttack(rand))
			rand = (rand + 1) % 4;
		return attack(other, rand);
	}
	
	/** 
	 *  Should only be used internally, except for testing. see getAttackNames
	 *  for a list of strings
	 */
	private Attack getAttack(int num) {
		return new Attack[] { attack1, attack2, attack3, attack4 }[num];
	}

	/**
	 * Increases the Pokemons stats fills it's health (like in the Pokemon games)
	 * increments its level.
	 */
	public void levelUp() {
		attack += binomialRand(1, baseAttack);
		defense += binomialRand(1, baseDefense);
		maxHP += binomialRand(1, baseHP * 5);

		hp = maxHP;
	}

	public Boolean isFainted() {
		return hp == 0;
	}

	/** Use a negative a negative number to subtract */
	public void setTempAttack(int value) {
		tempAttack = value;
	}

	public void setTempDefense(int value) {
		tempDefense = value;
	}

	/**
	 * Returns a list names of attacks, null attacks are blank strings.
	 */
	public String[] getAttackNames() {
		String[] names = new String[4];
		for (int i = 0; i < 3; i++) {
			if (this.hasAttack(1))
				names[i] = this.getAttack(i).getName();
			else
				names[i] = "";
		}
		return names;
	}

	/**
	 * Returns true if the Pokemon has an attack in slot num WARNING: Slots are
	 * indexed from one.
	 */
	private Boolean hasAttack(int num) {
		return getAttack(num) != null;
	}

	public int getAttack() {
		return Math.max(1, attack + tempAttack);
	}

	public int getDefense() {
		return Math.max(1, defense + tempDefense);
	}

	public String getName() {
		return name;
	}

	public static int binomialRand(int min, int max) {
		if (NORANDOM) {
			return max / 2;
		}
		return min + (int) (Math.random() * max / 2 + Math.random() * max / 2);
	}

	/**
	 * USED ONLY FOR TESTING Prevents pokemon for using random values for anything
	 * instead they will always use half of the max amount for damage calculation
	 * assigning stats on level up etc
	 */
	public static void setNoRand() {
		NORANDOM = true;
	}

	/**
	 * should be called in the teardown methods of any test
	 */
	public static void setRand() {
		NORANDOM = false;
	}

	/** Resets the temporary stats, should be called at the before battle */
	public void resetTemp() {
		setTempDefense(0);
		setTempAttack(0);
	}

	/**
	 * Clone constructor: Should return a clone of the current Pokemon. Clone is
	 * necessary because the random Pokemon option utilizes the prototype design
	 * pattern and there is no way to clone a generic object in java.
	 * 
	 * Note setAttacks MUST be called in this method as clone only creats a shallow
	 * copy of the pokemon.
	 */
	@Override
	public Pokemon clone() {
		Pokemon clone = null;
		try {
			clone = (Pokemon) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		clone.setAttacks();
		clone.setBaseStats();
		return clone;
	}

}
