/*
 *  RADIANCE - An Android 2D turn-based tactics-rpg game.
 *  
 *  Copyright (C) 2011  VagosDuke (vagosduke@gmail.com)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * @author VagosDuke
 */

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
