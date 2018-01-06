package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;

public class Cubone extends Pokemon {
	
	public Cubone(int level) {
		super("Cubone", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 9;
		baseDefense = 15;
		baseHP = 9;
		imgPath  = "file:Images/cubone.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Punch", DamageType.HP, 10);
		attack2 = new Attack("Bone flex", DamageType.ATKUP, 10);
		attack3 = new Attack("Backstory", DamageType.ATKDOWN, 15);
		attack4 = null;

	}


}
