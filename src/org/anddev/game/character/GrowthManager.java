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

public class GrowthManager {
	/**
	 * Character-Class module that manages the growth rate for the Stats.
	 */
	
	private Character myCharacter;
	
	private float attackGrowth;
	private float defenceGrowth;
	private float magicGrowth;
	private float speedGrowth;
	
	private float HPGrowth;
	private float MPGrowth;
	
	private int EXP;
	private int EXP_next;
	
	
	
	
	
	
	
	
	///////////////////////
	//	Constructors
	///////////////////////
	public GrowthManager() {
		/**
		 * Default constructor
		 */
		this.attackGrowth = 1;
		this.defenceGrowth = 1;
		this.magicGrowth = 1;
		this.speedGrowth = 1;
		this.HPGrowth = 1;
		this.MPGrowth = 1;
		this.EXP = 0;
		this.EXP_next = 100;
	}
	public GrowthManager(Character mychar) {
		this.myCharacter = mychar;
		this.attackGrowth = 1;
		this.defenceGrowth = 1;
		this.magicGrowth = 1;
		this.speedGrowth = 1;
		this.HPGrowth = 1;
		this.MPGrowth = 1;
		this.EXP = 0;
		this.EXP_next = 100;
	}
	
	public GrowthManager(int iattackGrowth, int idefenceGrowth, int imagicGrowth, int ispeedGrowth,
			int iHPGrowth, int iMPGrowth, int exp, int level) {
		this.attackGrowth = iattackGrowth;
		this.defenceGrowth = idefenceGrowth;
		this.magicGrowth = imagicGrowth;
		this.speedGrowth = ispeedGrowth;
		this.HPGrowth = iHPGrowth;
		this.MPGrowth = iMPGrowth;
		this.EXP = exp;
		this.EXP_next = 100 * (level*level);
	}
	
	
	
	
	
	///////////////////////
	//	Pblic Methods
	///////////////////////
	
	protected int addEXP(int value) {
		Boolean ding = false;
		while((this.EXP+value) >= EXP_next) {
			this.levelUp();
			value -= (this.EXP_next - this.EXP);
			ding = true;
		}
		this.EXP += value;
		if(ding) { return 1; } 
		else { return 0; }
	}
	
	
	
	protected void levelUp() {
		
	}
	
	/////////////////////////////////////////
	//	Getters / setters
	/////////////////////////////////////////
	public int attackGrowth() { return Math.round(this.attackGrowth); }
	public int defenceGrowth() { return Math.round(this.defenceGrowth); }
	public int magicGrowth() { return Math.round(this.magicGrowth); }
	public int speedGrowth() { return Math.round(this.speedGrowth); }
	public int HPGrowth() { return Math.round(this.HPGrowth); }
	public int MPGrowth() { return Math.round(this.MPGrowth); }
	public int EXP() { return this.EXP; }
	public int EXPnext() { return this.EXP_next; }
	public int EXP2next() { return (this.EXP_next - this.EXP); }
}
