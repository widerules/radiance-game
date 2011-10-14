package org.anddev.game.inventory;

import org.anddev.game.items.ArmorItem;
import org.anddev.game.items.ItemBase;
import org.anddev.game.items.ShieldItem;
import org.anddev.game.items.WeaponItem;


public class CharacterInventory extends Inventory {
	private WeaponItem handSlot1;
	private WeaponItem handSlot2;
	private ShieldItem shieldSlot;
	private ArmorItem armorSlot;
	
	////////////////////////////////
	//	Constructors
	////////////////////////////////
	public CharacterInventory() {
		handSlot1 = null;
		handSlot2 = null;
		shieldSlot = null;
		armorSlot = null;
	}
	
	
	
	
	
	
	
	//////////////////////////////////////
	//	Public Equip - Unequip
	/////////////////////////////////////
	public Boolean unequipHandSlot1() {
		if(handSlot1 != null){
			if(handSlot1.getType() == ItemBase.ITMtype.W2H) { handSlot2 = null; }
			handSlot1 = null;
			return true;
		}
		else {return false;}
	}
	public Boolean unequipHandSlot2() {
		if(handSlot2 != null){
			if(handSlot2.getType() == ItemBase.ITMtype.W2H) { handSlot1 = null; }
			handSlot2 = null;
			return true;
		}
		else {return false;}
	}
	public Boolean unequipShieldSlot() {
		if(shieldSlot != null){
			shieldSlot = null;
			return true;
		}
		else {return false;}
	}
	public Boolean unequipArmorSlot() {
		if(armorSlot != null){
			armorSlot = null;
			return true;
		}
		else {return false;}
	}
	
	public Boolean equipHandSlot1(int index) {
		WeaponItem tempItem = weaponList.get(index);
		if( tempItem.getType()== ItemBase.ITMtype.W2H ) {
			if (handSlot2 == null && shieldSlot == null) {
				handSlot1 = tempItem;
				handSlot2 = tempItem;
				return true;
			}
			else { return false; }
		}
		else if ( tempItem.getType() == ItemBase.ITMtype.W1H ) {
			if (handSlot2 == null) {
				handSlot1 = tempItem;
				return true;
			}
			else { return false; }
		}
		else { return false; }
	}
	public Boolean equipHandSlot2(int index) {
		WeaponItem tempItem = weaponList.get(index);
		if( tempItem.getType() == ItemBase.ITMtype.W2H ) {
			if (handSlot1 == null && shieldSlot == null) {
				handSlot1 = tempItem;
				handSlot2 = tempItem;
				return true;
			}
			else { return false; }
		}
		else if ( tempItem.getType() == ItemBase.ITMtype.W1H ) {
			if (handSlot1 == null) {
				handSlot2 = tempItem;
				return true;
			}
			else { return false; }
		}
		else { return false; }
	}
	public Boolean equipShieldSlot(int index) {
		ShieldItem tempItem = shieldList.get(index);
		if (tempItem.getType() == ItemBase.ITMtype.SHIELD) {
			if(handSlot1 == null || handSlot2 == null) {
				shieldSlot = tempItem;
				return true;
			}
			else{ return false; }
		}
		else{ return false; }
	}
	public Boolean equipArmorSlot(int index) {
		ArmorItem tempItem = armorList.get(index);
		if (tempItem.getType() == ItemBase.ITMtype.ARMOR) {
			if(armorSlot == null) {
				armorSlot = tempItem;
				return true;
			}
			else{ return false; }
		}
		else{ return false; }
	}
	
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append(super.toString());
		result.append("Equiped:\n");
		result.append("slot1: " + this.handSlot1 + "\n");
		result.append("slot2: " + this.handSlot2 + "\n");
		result.append("shield: " + this.shieldSlot + "\n");
		result.append("armor: " + this.armorSlot + "\n");
		return result.toString();
	}
	
	
	
	
	
	/////////////////////////////////
	//	Getters
	/////////////////////////////////
	public WeaponItem getHandSlot1() { return handSlot1; }
	public WeaponItem getHandSlot2() { return handSlot2; }
	public ShieldItem getShieldSlot() { return shieldSlot; }
	public ArmorItem getArmorSlot() { return armorSlot; }
}
