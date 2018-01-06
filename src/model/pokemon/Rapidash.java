package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class Rapidash extends Pokemon {
	
	public Rapidash(int level) {
		super("Rapidash", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 10;
		baseDefense = 10;
		baseHP = 10;
		rarity = Rarity.LUXURY;
		imgPath = "file:Images/rapidash.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Fire Tale", DamageType.HP, 15);
		attack2 = new Attack("Flame Dash", DamageType.ATKDOWN, 12);
		attack3 = new Attack("Infernal Mane", DamageType.DEFUP, 10);
		attack3 = new Attack("Fire Word Horse Word", DamageType.ATKUP, 10);

	}


}
