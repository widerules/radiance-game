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

public class MagicManager {

	private final Character myCharacter;
	
	private int magicPoints;

	
	
	
	/////////////////////
	//	Constructor
	/////////////////////
	public MagicManager(Character mychar) {
		this.myCharacter = mychar;
	}
	
	
	
	
	
	
	
	
	///////////////////////
	//	Public Functions
	///////////////////////
	public int modMP(int value) {
		/** add the value to HP. (value can be negative) */
		if(value > 0) {
			return this.addMP(value);
		}
		else {
			return this.subtractMP(-value);
		}
	}
	
	public int addMP(int value) {
		int mpmax = this.myCharacter.getSkillsManager().getBaseMagicPointMax();
		if((this.magicPoints + value) > mpmax) {
			this.magicPoints = mpmax;
			return (mpmax - this.magicPoints);
		}
		else {
			this.magicPoints += value;
			return value;
		}	
	}
	
	public int subtractMP(int value) {
		int tmp = this.magicPoints;
		if((this.magicPoints - value) < 0) {
			this.magicPoints = 0;
			return tmp;
		}
		else {
			this.magicPoints -= value;
			return value;
		}
	}	
	
	
	
	
	
	
	
	
	
	///////////////////////
	// Getters
	///////////////////////
	public int MP() { return this.magicPoints; }
}
