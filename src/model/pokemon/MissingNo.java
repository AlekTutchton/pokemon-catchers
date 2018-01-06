package model.pokemon;

import model.Attack;
import model.DamageType;
import model.Pokemon;
import model.Rarity;

public class MissingNo extends Pokemon {
	/** Used only for testing probably isn't catchable in the game ;) */
	
	public MissingNo(int level) {
		super("MissingNo", level);
	}
	
	protected void setBaseStats() {
		baseAttack = 10;
		baseDefense = 10;
		baseHP = 10;
		rarity = Rarity.EXECUTIVEPLATINUM;
		imgPath = "file:Images/Mno.gif/";
	}
	protected void setAttacks() {
		attack1 = new Attack("Ice Beam", DamageType.HP, 10);
		attack2 = new Attack("Zeus Lazer", DamageType.HP, 25);
		attack3 = new Attack("Good vibes", DamageType.ATKUP, 25);
		attack4 = new Attack("Bad vibes", DamageType.ATKDOWN, 25);
	}

}
