package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class Lugia extends Pokemon {
	
	public Lugia(int level) {
		super("Lugia", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 12;
		baseDefense = 12;
		baseHP = 12;
		rarity = Rarity.EXECUTIVEPLATINUM;
		imgPath  = "file:Images/lugia.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Mega Beam", DamageType.HP, 10);
		attack2 = new Attack("God Smash", DamageType.HP, 15);
		attack3 = new Attack("Fear ray", DamageType.ATKDOWN, 10);
		attack4 = null;

	}


}
