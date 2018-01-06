package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;

public class Vulpix extends Pokemon {
	
	public Vulpix(int level) {
		super("Vuplix", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 7;
		baseDefense = 7;
		baseHP = 12;
		imgPath  = "file:Images/vulpix.gif";
		rarity = rarity.LUXURY;
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Fire Tale", DamageType.HP, 15);
		attack2 = new Attack("Flame Dash", DamageType.ATKDOWN, 12);
		attack3 = new Attack("Infernal Mane", DamageType.DEFUP, 10);
		attack3 = new Attack("Fire Word Horse Word", DamageType.ATKUP, 10);
	}


}
