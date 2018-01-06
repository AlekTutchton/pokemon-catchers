package model;

import java.io.Serializable;

// Author: Rowan Lochrin
public class Attack implements Serializable {
	String name;
	// The damageType of the attack.
	DamageType damageType;
	// A value between 1 and 10.
	int power;  
	public Attack(String nm, DamageType dt, int p) {
		name 		= nm;
		damageType 	= dt;
		power 		= p;
	}
	public String getName() {
		return name;
	}
	public DamageType getDamageType() {
		return damageType;
	}
	public int getPower() {
		return power;
	}
}
