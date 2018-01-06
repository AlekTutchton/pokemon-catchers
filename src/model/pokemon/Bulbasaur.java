package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;

public class Bulbasaur extends Pokemon {
	
	public Bulbasaur(int level) {
		super("Bulbasaur", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 7;
		baseDefense = 7;
		baseHP = 12;
		imgPath  = "file:Images/bulbasaur.gif";
		rarity = rarity.LUXURY;
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Vine Whip", DamageType.HP, 10);
		attack2 = new Attack("Leaf Cut", DamageType.ATKUP, 10);
		attack3 = new Attack("Plant Attack?", DamageType.ATKUP, 10);
		attack4 = null;
	}


}
