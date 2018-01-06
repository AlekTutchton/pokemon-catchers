package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class Diglett extends Pokemon {
	
	public Diglett(int level) {
		super("Diglett", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 6;
		baseDefense = 7;
		baseHP = 8;
		rarity = Rarity.ECONOMY;
		imgPath  = "file:Images/diglett.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Tunnel", DamageType.HP, 10);
		attack2 = new Attack("Growl", DamageType.DEFDOWN, 10);
		attack3 = new Attack("Taunt", DamageType.ATKUP, 10);
		attack4 = null;

	}


}
