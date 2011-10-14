package org.anddev.game.character;


import org.anddev.game.combat.Attack;
import org.anddev.game.combat.ResistList;

public class CombatManager {
	/**
	 * Character-Class module that manages derived combat stats.
	 * (and possible combat methods)
	 */
	private final Character myCharacter;
	
	
	
	
	
	

	
	
	
	
	private int hitPoints;								// health left
	private ResistList resistList;		// % damage reduction by damage type

	

	
	
	
	
	
	//////////////////////
	//	Constructors
	//////////////////////
	public CombatManager(Character mychar) {
		this.myCharacter = mychar;
		
		
		this.resistList = new ResistList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void doAttacked(Character attacker, Attack att) {
		
	}
	
	
 	public int subtractHP(int value) {
		int tmp = this.hitPoints;
		if((this.hitPoints - value) < 0) {
			this.hitPoints = 0;
			return tmp;
		}
		else {
			this.hitPoints -= value;
			return value;
		}
	}
	public int addHP(int value) {
		int hpmax = myCharacter.getSkillsManager().getHitPointMax();
		if((this.hitPoints + value) > hpmax) {
			this.hitPoints = hpmax;
			return (hpmax - this.hitPoints);
		}
		else {
			this.hitPoints += value;
			return value;
		}	
	}








	
	
	
	
	
	
}
