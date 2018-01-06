package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class Rayquaza extends Pokemon {
	public Rayquaza(int level) {
		super("Rayquaza", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 15;
		baseDefense = 15;
		baseHP = 10;
		rarity = Rarity.EXECUTIVEPLATINUM;
		imgPath  = "file:Images/rayquaza.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("God Beam", DamageType.HP, 15);
		attack2 = new Attack("War Cry", DamageType.DEFDOWN, 15);
		attack3 = new Attack("Mega up", DamageType.ATKUP, 15);
		attack4 = null;

	}


}
