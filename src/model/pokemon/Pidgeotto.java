package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;

public class Pidgeotto extends Pokemon {
	
	public Pidgeotto(int level) {
		super("Pidgeotto", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 7;
		baseDefense = 7;
		baseHP = 12;
		imgPath  = "file:Images/pidgeotto.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Peek", DamageType.HP, 10);
		attack2 = new Attack("Dive", DamageType.ATKUP, 10);
		attack3 = new Attack("BIRD MOVE", DamageType.ATKUP, 10);
		attack4 = null;

	}


}
