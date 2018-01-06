package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class Mew extends Pokemon {
	public Mew(int level) {
		super("Mew", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 13;
		baseDefense = 13;
		baseHP = 13;
		rarity = Rarity.EXECUTIVEPLATINUM;
		imgPath  = "file:Images/mew.gif";
	}
// focus
	@Override
	protected void setAttacks() {
		attack1 = new Attack("Exalted Ray", DamageType.HP, 17);
		attack2 = new Attack("Peace Beam", DamageType.ATKDOWN, 15);
		attack3 = new Attack("Focuse", DamageType.ATKUP, 15);
		attack4 = null;

	}


}
