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


import org.anddev.game.inventory.CharacterInventory;

public class PlayerCharacter extends Character {
	
	/**
	 * CharClass extends Character. CharClass adds the "Growth" module.
	 * It is intended for characters that can gain levels
	 */
	// Additional modules
	protected GrowthManager charGrowth;
	
	
	
	
	
	
	
	
	
	
	///////////////////////////
	// Constructors
	///////////////////////////
	static public PlayerCharacter make_empty_PlayerCharacter() {
		/**
		 *  makes an empty instance with default values from the default private constructor
		 */
		return new PlayerCharacter();
	}
	
//	
//	static public PlayerCharacter make_new_PlayerCharacter(InfoManager info, StatsManager stats, GrowthManager growth, 
//			InventoryEquip inventory) {
//		/**
//		 * creates a PlayerCharacter as new. The module arguments are imported, but 
//		 * condition, temporary HP and MP values, etc. are reset. 
//		 */
//		// TODO
//		return new PlayerCharacter(info, stats, growth, inventory);
//	}
//	
//	static public PlayerCharacter make_saved_PlayerCharacter(InfoManager info, StatsManager stats, GrowthManager growth, 
//			InventoryEquip inventory) {
//		/** 
//		 * creates a PlayerCharacter importing exactly the modules from arguments
//		 */
//		return new PlayerCharacter(info, stats, growth, inventory);
//	}
	
	
	
	
	
	
	////////////////////////
	//	public methods
	////////////////////////
	
	
	
	
	
	////////////////////////
	// getters
	////////////////////////
	public int getAttackGrowth() { return this.charGrowth.attackGrowth(); }
	public int getDefenceGrowth() { return this.charGrowth.defenceGrowth(); }
	public int getMagicGrowth() { return this.charGrowth.magicGrowth(); }
	public int getSpeedGrowth() { return this.charGrowth.speedGrowth(); }
	public int getHPGrowth() { return this.charGrowth.HPGrowth(); }
	public int getMPGrowth() { return this.charGrowth.MPGrowth(); }
	public int getEXP() { return this.charGrowth.EXP(); }
	public int getEXPnext() { return this.charGrowth.EXPnext(); }
	public int getEXP2next() { return this.charGrowth.EXP2next(); }
	
	
	
	
	
	////////////////////////////////////
	// Private Constructors
	////////////////////////////////////
	private PlayerCharacter() {
		this.charInfo = new InfoManager(this);
		this.charSkills = new SkillsManager(this);
		this.charAttributes = new AttributesManager(this);
		this.charGrowth = new GrowthManager(this);
		this.characterInventory = new InventoryManager(this);
	}
	private PlayerCharacter(InfoManager info, SkillsManager stats, GrowthManager growth, 
				InventoryManager inventory) {
		this.charInfo = info;
		this.charSkills = new SkillsManager(this);
		this.charAttributes = new AttributesManager(this);
		this.charGrowth = growth;
		this.characterInventory = inventory;
	}
}
