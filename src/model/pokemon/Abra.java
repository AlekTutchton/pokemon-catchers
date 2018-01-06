package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;

public class Abra extends Pokemon {
	
	public Abra(int level) {
		super("Abra", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 15;
		baseDefense = 7;
		baseHP = 7;
		imgPath  = "file:Images/abra-1.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Psy Ray", DamageType.HP, 10);
		attack2 = new Attack("Focus", DamageType.ATKUP, 10);
		attack3 = null;
		attack4 = null;

	}


}
