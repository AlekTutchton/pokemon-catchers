package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class Charizard extends Pokemon {
	
	public Charizard(int level) {
		super("Charizard", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 15;
		baseDefense = 7;
		baseHP = 10;
		rarity = Rarity.LUXURY;
		imgPath = "file:Images/charizard.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Lizard Kick", DamageType.HP, 10);
		attack2 = new Attack("Growl", DamageType.DEFDOWN, 10);
		attack3 = new Attack("Ruminate", DamageType.DEFUP, 20);
		attack4 = null;

	}


}
