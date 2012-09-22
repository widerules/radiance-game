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

import org.vagosduke.andengine.radiance.engine.menu.MenuItem;
import org.vagosduke.andengine.radiance.game.inventory.CharacterInventory;
import org.vagosduke.andengine.radiance.game.items.BaseItem;
import org.vagosduke.andengine.radiance.game.items.StatBonus;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;

public class InventoryModule {
	private final Character myCharacter;

	private CharacterInventory inventory;
	
	
	
	/////////////////////////////
	//	Constructor
	/////////////////////////////
	public InventoryModule(Character mychar) {
		this.myCharacter = mychar;
		this.inventory = new CharacterInventory();
	}
	public InventoryModule(Character mychar, CharacterInventory inventory) {
		this.myCharacter = mychar;
		this.inventory = inventory;
	}
	
	
	
	
	
	
	//////////////////////////////////////////
	//	Public Equip - Unequip - Give - Drop
	//////////////////////////////////////////
	public boolean equipItem(BaseItem itm) {
		if(this.inventory.equipItem(itm) == false) { return false; }
		for(StatBonus bonus: itm.getOnEquipBonuses()) {
			bonus.apply(this.myCharacter);
		}
		for(BaseEffect effect: itm.getOnEquipEffects()) {
			effect.apply(null, this.myCharacter);
		}
		return true;
	}
	public boolean unequipItem(BaseItem itm) {
		if((itm.isEquiped() == false)) { return false; }
		for(StatBonus bonus: itm.getOnEquipBonuses()) {
			bonus.apply(this.myCharacter);
		}
		for(BaseEffect effect: itm.getOnEquipEffects()) {
			effect.apply(null, this.myCharacter);
		}
		return true;
	}
	
	
	
	
	
	
	///////////////////////////////////
	//	Public Functions
	///////////////////////////////////
	
	
	
	///////////////////////////////////
	//	getters
	///////////////////////////////////
	public CharacterInventory getInventory() { return this.inventory; }
	
	public String toString() {
		return this.inventory.toString();
	}
	
	
	
	
	
	
	//////////////////////////////
	//	Private Functions
	/////////////////////////////

}
