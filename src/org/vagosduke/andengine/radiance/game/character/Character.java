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

import org.vagosduke.andengine.radiance.game.combat.Damage;

public class Character {
	
	/**
	 *  The general character "class" is composed from several 
	 *  modules:
	 *  - Info
	 *  - Stats
	 *  - Inventory
	 *  - Condition
	 *  - Combat
	 */
	
	// Modules
	protected InfoModule charInfo;					// Manages static character info like name etc.
	protected AttributesModule charAttributes;
	protected InventoryModule characterInventory;	// Manages items and inventory functions
	protected ConditionModule charCondition;		// Manages temporary character states like buffs etc.
	protected CombatModule charCombat;				// Manages combat functions 
	protected AbilitiesModule charMagic;				// Manages spell-list and spellcasting functions
	
	
	
	
	/////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////
	public Character() {
		this.charInfo = null;
		this.charAttributes = null;
		this.characterInventory = null;
		this.charCondition = null;
		this.charCombat = null;
		this.charMagic = null;
	}
	public Character(InfoModule info, AttributesModule attributes,
			InventoryModule inventory, ConditionModule cond, CombatModule combat, AbilitiesModule magic) {
		this.charInfo = info;
		this.charAttributes = attributes;
		this.characterInventory = inventory;
		this.charCondition = cond;
		this.charCombat = combat;
		this.charMagic = magic;
	}
	
	
	
	/////////////////////////////
	//	Public functions
	/////////////////////////////
	public boolean canAttack() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean canCast() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////
	//	Getters / setters
	/////////////////////////////////////////
	
	public InfoModule getInfoModule() { return this.charInfo; }
	public AttributesModule getAttributesModule() { return this.charAttributes; }
	public InventoryModule getInventoryModule() { return this.characterInventory; }
	public ConditionModule getConditionModule() { return this.charCondition; }
	public CombatModule getCombatModule() { return this.charCombat; }
	public AbilitiesModule getAbilitiesModule() { return this.charMagic; }

	public void setCharInfo(InfoModule charInfo) { this.charInfo = charInfo;}
	public void setCharAttributes(AttributesModule charAttributes) { this.charAttributes = charAttributes; }
	public void setCharacterInventory(InventoryModule characterInventory) { this.characterInventory = characterInventory; }
	public void setCharCondition(ConditionModule charCondition) { this.charCondition = charCondition; }
	public void setCharCombat(CombatModule charCombat) { this.charCombat = charCombat; }
	public void setCharMagic(AbilitiesModule charMagic) { this.charMagic = charMagic; }
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////
	// Private Constructors
	/////////////////////////////////////////
	
}
