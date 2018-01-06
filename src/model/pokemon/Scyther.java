package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class Scyther extends Pokemon {
	
	public Scyther(int level) {
		super("Scyther", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 18;
		baseDefense = 5;
		baseHP = 7;
		rarity = Rarity.LUXURY;
		imgPath  = "file:Images/scyther.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Flying Cut", DamageType.HP, 10);
		attack2 = new Attack("Rend", DamageType.HP, 15);
		attack3 = new Attack("Scare", DamageType.ATKDOWN, 10);
		attack4 = null;

	}


}
