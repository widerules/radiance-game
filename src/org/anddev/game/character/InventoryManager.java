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
import org.anddev.game.items.ArmorItem;
import org.anddev.game.items.Item;
import org.anddev.game.items.ItemBase;
import org.anddev.game.items.ItemBase.ITMtype;
import org.anddev.game.items.ShieldItem;
import org.anddev.game.items.WeaponItem;

public class InventoryManager {

	private final Character myCharacter;

	private CharacterInventory inventory;
	
	
	
	
	/////////////////////////////
	//	Constructor
	/////////////////////////////
	public InventoryManager(Character mychar) {
		this.myCharacter = mychar;
		this.inventory = new CharacterInventory();
	}
	
	
	
	
	
	
	///////////////////////////////////
	//	Public Equip - Unequip
	///////////////////////////////////
	public Boolean unequipHandSlot1() {
		return inventory.unequipHandSlot1();
	}
	public Boolean unequipHandSlot2() {
		return inventory.unequipHandSlot2();
	}
	public Boolean unequipShieldSlot() {
		return inventory.unequipShieldSlot();
	}
	public Boolean unequipArmorSlot() {
		return inventory.unequipArmorSlot();
	}
	
	
	public Boolean equipHandSlot1(int index) {
		return inventory.equipHandSlot1(index);
	}
	public Boolean equipHandSlot2(int index) {
		return inventory.equipHandSlot2(index);
	}
	public Boolean equipShieldSlot(int index) {
		return inventory.equipShieldSlot(index);
	}
	public Boolean equipArmorSlot(int index) {
		return inventory.equipArmorSlot(index);
	}
	
	
	
	///////////////////////////////////
	//	Public Functions
	///////////////////////////////////
	public WeaponItem getWeaponSlot1() { return inventory.getHandSlot1(); }
	public WeaponItem getWeaponSlot2() { return inventory.getHandSlot2(); }
	public ShieldItem getShieldSlot() { return inventory.getShieldSlot(); }
	public ArmorItem getArmorSlot() { return inventory.getArmorSlot(); }
	
}
