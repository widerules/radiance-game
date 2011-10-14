package org.anddev.game.combat;

import java.util.LinkedList;

public class Attack {

	private LinkedList<Damage> DamageVector;
	private int attackSkill;
	
	
	public Attack(int iattack) {
		this.DamageVector = new LinkedList<Damage>();
		this.attackSkill = iattack;
	}
	
	
	
	public void add(Damage dmg) {
		DamageVector.add(dmg);
	}
	public Damage nextDmg() {
		return DamageVector.remove();
	}
}
