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

package org.vagosduke.andengine.radiance.game.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import org.vagosduke.andengine.radiance.game.spells.AvailableSpell;
import org.vagosduke.andengine.radiance.game.spells.GlobalSpellList;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.MyEntry;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class AbilitiesModule {
	private final Character myCharacter;
	private TargetedSpell[] abilityList;				// All spells available to this character class
	private Integer[][] unlockLevel;
	private ArrayList<AvailableSpell> availableAbilityList;
	//private ArrayList<TargetedSpell> availableAbilityList;		// Spells available to the character of this level
	private int abilityPoints;								// current mp
	
	
	

	
	
	
	/////////////////////
	//	Constructor
	/////////////////////
	public AbilitiesModule(Character myCharacter) {
		this.myCharacter = myCharacter;
		this.refreshAll();
	}
	public AbilitiesModule(Character myCharacter, TargetedSpell[] abilityList, Integer[][] unlockLevel) {
		this.myCharacter = myCharacter;
		this.abilityList = abilityList;
		this.unlockLevel = unlockLevel;
		this.refreshAll();
	}
	
	
	
	
	
	
	
	
	///////////////////////
	//	Public Functions
	///////////////////////
	public void refreshAll() {
		this.updateCurrentAbilities();
		this.abilityPoints = this.myCharacter.getAttributesModule().getAbilityPointMax();
	}
	
	public boolean canCast(TargetedSpell ability, int rank) {
		// TODO
		if (this.abilityPoints >= ability.getMPcost(rank)) {
			//if(this.myCharacter.getSkillsModule().getLevel() >= )
			return true;
		} else {
			return false;
		}
	}
	
	public int addMP(int value) {
		int mpmax = this.myCharacter.getAttributesModule().getBaseAbilityPointMax();
		if((this.abilityPoints + value) > mpmax) {
			this.abilityPoints = mpmax;
			return (mpmax - this.abilityPoints);
		}
		else {
			this.abilityPoints += value;
			return value;
		}	
	}
	
	public int subtractMP(int value) {
		int tmp = this.abilityPoints;
		if((this.abilityPoints - value) < 0) {
			this.abilityPoints = 0;
			return tmp;
		}
		else {
			this.abilityPoints -= value;
			return value;
		}
	}	
	
	
	
	
	
	
	
	
	
	///////////////////////
	// Getters
	///////////////////////
	public int getAbilityPoints() { return this.abilityPoints; }
//	public ArrayList<Entry<TargetedSpell, int[]>> {
//		availableAbilityList
//	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("=== ABILITIES ===\n");
		for(int i=0; i<this.availableAbilityList.size(); i++) {
			str.append(this.availableAbilityList.get(i).getAbility().getName() + " [");
			for(int j=0; j<this.availableAbilityList.get(i).getAvailableRanks().length; j++) {
				str.append(this.availableAbilityList.get(i).getAvailableRanks()[j] + " ");
			}
			str.deleteCharAt(str.length()-1);
			str.append("]\n");
		}
		return str.toString();
	}
	
	public ArrayList<AvailableSpell> getAvailableAbilityList() {
		return availableAbilityList;
	}
	
	
	
	
	
	
	
	////////////////////////
	//	Private Methods
	////////////////////////
	private void updateCurrentAbilities() {
		/** Compares the unlock levels of all abilities with character level
		 *  and make a new list containing the abilities that the character can cast at least one rank
		 *  accompanied by an array of levels that can cast
		 */
		this.availableAbilityList = new ArrayList<AvailableSpell>();
		for(int i=0; i<abilityList.length; i++) {
			if(getRanksCanCast(i) > 0) {
				this.availableAbilityList.add(new AvailableSpell(abilityList[i], getRanksArrayCanCast(i)));
			}
		}
	}
	
//	private int getRanksCanCast(TargetedSpell ability) {
//		int maxAbilityRanks = ability.getTotalRanks();
//		int abilityIndex = findAbilityIndex(ability);
//		int myLevel = this.myCharacter.getAttributesModule().getLevel();
//		if(abilityIndex != -1) {
//			int ret = 0;
//			for(int ulevel: this.unlockLevel[abilityIndex]) {
//				if(myLevel >= ulevel) { ret++; }
//			}
//			if(ret > maxAbilityRanks) { return maxAbilityRanks; }
//			else { return ret; }
//		}
//		else {
//			return 0;
//		}
//	}
	private int getRanksCanCast(int abilityIndex) {
		int maxAbilityRanks = this.abilityList[abilityIndex].getTotalRanks();
		int myLevel = this.myCharacter.getAttributesModule().getLevel();
		if(abilityIndex != -1) {
			int ret = 0;
			for(int ulevel: this.unlockLevel[abilityIndex]) {
				if(myLevel >= ulevel) { ret++; }
			}
			if(ret > maxAbilityRanks) { return maxAbilityRanks; }
			else { return ret; }
		}
		else {
			return 0;
		}
	}

	private Integer[] getRanksArrayCanCast(int abilityIndex) {
		int levels = getRanksCanCast(abilityIndex);
		if(levels > 0 ) {
			Integer[] ret = new Integer[levels];
			for(int i=0; i<levels; i++) { ret[i] = i+1; }
			return ret;
		}
		else {
			return new Integer[0];
		}
	}
	
	private int findAbilityIndex(TargetedSpell ability) {
		/** search the abilities array for the matching ability using .equals */
		for(int i=0; i<this.abilityList.length; i++) {
			if (ability.equals(this.abilityList[i])) {
				return i;
			}
		}
		return -1;
	}

}


