package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;

public class Pikachu extends Pokemon {
	
	public Pikachu(int level) {
		super("Pikachu", level);
	}

	@Override
	protected void setBaseStats() {
		baseAttack = 10;
		baseDefense = 10;
		baseHP = 10;
		imgPath  = "file:Images/pikachu.gif";
	}

	@Override
	protected void setAttacks() {
		attack1 = new Attack("Lightning Bolt", DamageType.HP, 10);
		attack2 = new Attack("Thunder growl", DamageType.ATKUP, 10);
		attack3 = new Attack("Rat Look", DamageType.ATKUP, 10);
		attack4 = new Attack("Menace", DamageType.DEFDOWN, 10);

	}


}
