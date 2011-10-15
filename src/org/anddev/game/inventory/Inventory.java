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

package org.anddev.game.inventory;


import org.anddev.game.items.ArmorItem;
import org.anddev.game.items.ConsumableItem;
import org.anddev.game.items.Item;
import org.anddev.game.items.ItemBase;
import org.anddev.game.items.MiscItem;
import org.anddev.game.items.ShieldItem;
import org.anddev.game.items.WeaponItem;

import java.util.ArrayList;

public class Inventory {
	
	/**
	 * General Inventory class which holds only the objects' lists
	 */
	
	protected ArrayList<? extends Item> itemList;
	
	protected ArrayList<WeaponItem> weaponList;
	protected ArrayList<ShieldItem> shieldList;
	protected ArrayList<ArmorItem> armorList;
	protected ArrayList<ConsumableItem> consumableList;
	protected ArrayList<MiscItem> otherList;
	
	//////////////////
	//	Constructors
	//////////////////
	public Inventory() {
		itemList = new ArrayList<Item>();
		weaponList = new ArrayList<WeaponItem>();
		shieldList = new ArrayList<ShieldItem>();
		armorList = new ArrayList<ArmorItem>();
		consumableList = new ArrayList<ConsumableItem>();
		otherList = new ArrayList<MiscItem>();
	}
	
	
	
	////////////////////
	//	Getters
	////////////////////
	public ArrayList<WeaponItem> getWeaponInventory() { return weaponList; }
	public ArrayList<ShieldItem> getShieldInventory() { return shieldList; }
	public ArrayList<ArmorItem> getArmorInventory() { return armorList; }
	public ArrayList<ConsumableItem> getConsumableInventory() { return consumableList; }
	public ArrayList<MiscItem> getOtherInventory() { return otherList; }
	
	public WeaponItem getWeapon(int index) { return weaponList.get(index); }
	public ShieldItem getShield(int index) { return shieldList.get(index); }
	public ArmorItem getArmor(int index) { return armorList.get(index); }
	public ConsumableItem getConsumable(int index) { return consumableList.get(index); }
	public MiscItem getOther(int index) { return otherList.get(index); }
	
	
	
	////////////////////
	//	Setters
	////////////////////
 	public Boolean addWeapon(WeaponItem witem) {
		if( witem.getType().equals(ItemBase.ITMtype.W1H) || witem.getType().equals(ItemBase.ITMtype.W2H)) {
			weaponList.add(witem);
			return true;
		}
		else { return false; }
	}
	public Boolean addShield(ShieldItem sitem) {
		if( sitem.getType().equals(ItemBase.ITMtype.SHIELD)) {
			shieldList.add(sitem);
			return true;
		}
		else { return false; }
	}
	public Boolean addArmor(ArmorItem aitem) {
		if( aitem.getType().equals(ItemBase.ITMtype.ARMOR)) {
			armorList.add(aitem);
			return true;
		}
		else { return false; }
	}
	public Boolean addConsumable(ConsumableItem citem) {
		if( citem.getType().equals(ItemBase.ITMtype.CONSUMABLE)) {
			consumableList.add(ConsumableItem.create(citem));
			return true;
		}
		else { return false; }
	}
	public Boolean addOther(MiscItem oitem) {
		if( oitem.getType().equals(ItemBase.ITMtype.MISC) || oitem.getType().equals(ItemBase.ITMtype.QUEST)) {
			otherList.add(oitem);
			return true;
		}
		else { return false; }
	}


	////////////////////
	//	Public Methods
	////////////////////
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("Inventory\n");
		result.append("-----------------------\n\n");
		result.append("\t :weapons:\n");
		for(int i=0; i<this.weaponList.size(); i++) {
			result.append(i + ") " + this.weaponList.get(i).toString() + "\n");
		}
		result.append("\t :shields:\n");
		for(int i=0; i<this.shieldList.size(); i++) {
			result.append(i + ") " + this.shieldList.get(i).toString() + "\n");
		}
		result.append("\t :armor:\n");
		for(int i=0; i<this.armorList.size(); i++) {
			result.append(i + ") " + this.armorList.get(i).toString() + "\n");
		}
		result.append("\t :consumables:\n");
		for(int i=0; i<this.consumableList.size(); i++) {
			result.append(i + ") " + this.consumableList.get(i).toString() + "\n");
		}
		result.append("\t :others: \n");
		for(int i=0; i<this.otherList.size(); i++) {
			result.append(i + ") " + this.otherList.get(i).toString() + "\n");
		}
		result.append("\n");
		return result.toString();
	}
	
}
