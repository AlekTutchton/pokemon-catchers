package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;

public class Koffing extends Pokemon {
	
	public Koffing(int level) {
		super("Koffing", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 16;
		baseDefense = 6;
		baseHP = 12;
		imgPath  = "file:Images/koffing.gif";
		rarity = rarity.LUXURY;
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Black Smog", DamageType.HP, 15);
		attack2 = new Attack("Poision Gas", DamageType.ATKUP, 10);
		attack3 = null;
		attack4 = null;
	}


}
