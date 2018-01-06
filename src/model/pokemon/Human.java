package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class Human extends Pokemon {
	
	private static final long serialVersionUID = 5771228615619784042L;

	/** This is the form the trainer takes in combat. It's convenient to represent
	 * the trainer as a Pokemon in combat to keep track of there sprites and the two
	 * attacks you can use in battle (throwing a rock/throwing bait).
	 * Note that this version of the trainer should only be used in safari encouters,
	 * and the number of items it has should be determined by the main trainer object  */
	public Human() {
		super("Trainer", 0);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 0;
		baseDefense = 0;
		baseHP = 0;
		rarity = Rarity.LUXURY;
		imgPath = "file:Images/trainerSprtite.png";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Rock", DamageType.HP, 0); 
		attack2 = new Attack("Bait", DamageType.HP, 0);
		attack3 = new Attack("Pokeball", DamageType.HP, 0);
		attack4 = null;
	}
}
