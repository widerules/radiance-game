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


package org.vagosduke.andengine.radiance.game.inventory;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.items.AccessoryItem;
import org.vagosduke.andengine.radiance.game.items.ArmorItem;
import org.vagosduke.andengine.radiance.game.items.BaseItem;
import org.vagosduke.andengine.radiance.game.items.MovementItem;
import org.vagosduke.andengine.radiance.game.items.ShieldItem;
import org.vagosduke.andengine.radiance.game.items.WeaponItem;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class CharacterInventory1 extends Inventory{

	private static enum equipable {WEAPON, SHIELD, ARMOR, ACCESSORY, MOVEMENT};
	
	private class ReservationSlotArray {
		private int totalEquipmentSlots;
		private boolean[] reservationSlots;			// true means they are reserved
		private int weaponSlotStart; private int weaponSlotEnd;
		private int shieldSlotStart; private int shieldSlotEnd;
		private int armorSlotStart; private int armorSlotEnd;
		private int accessorySlotStart; private int accessorySlotEnd;
		private int movementSlotStart; private int movementSlotEnd;
		
		public ReservationSlotArray() {
			this.totalEquipmentSlots = 5;
			this.reservationSlots = new boolean[this.totalEquipmentSlots];
			this.weaponSlotStart = 0;  this.weaponSlotEnd = 0;
			this.shieldSlotStart = 1;  this.shieldSlotEnd = 1;
			this.armorSlotStart = 2;  this.armorSlotEnd = 2;
			this.accessorySlotStart = 3;  this.accessorySlotEnd = 3;
			this.movementSlotStart = 4;  this.movementSlotEnd = 4;
		}
		
		public ReservationSlotArray(int totalSize, 
				int iweaponSlotStart, int iweaponSlotEnd,
				int ishieldSlotStart, int ishieldSlotEnd,
				int iarmorSlotStart, int iarmorSlotEnd,
				int iaccessorySlotStart, int iaccessorySlotEnd,
				int imovementSlotStart, int imovementSlotEnd) {
			this.totalEquipmentSlots = totalSize;
			this.reservationSlots = new boolean[this.totalEquipmentSlots];
			this.weaponSlotStart = iweaponSlotStart; this.weaponSlotEnd = iweaponSlotEnd;
			this.shieldSlotStart = ishieldSlotStart; this.shieldSlotEnd = ishieldSlotEnd;
			this.armorSlotStart = iarmorSlotStart; this.armorSlotEnd= iarmorSlotEnd;
			this.accessorySlotStart = iaccessorySlotStart; this.accessorySlotEnd = iaccessorySlotEnd;
			this.movementSlotStart = imovementSlotStart; this.movementSlotEnd = imovementSlotEnd;
		}
		
		

		public int getFreeSlots(equipable slotType) {
			int ret = 0;
			int[] points = getStartEndPoints(slotType);
			int startPoint = points[0];
			int endPoint = points[1];
			for(int i=startPoint; i<=endPoint; i++) {
				if(this.reservationSlots[i] = false) { ret++; }
			}
			return ret;
		}
		
		public boolean reserveSlots(int amount, equipable slotType) {
			int[] points = getStartEndPoints(slotType);
			int startPoint = points[0];
			int endPoint = points[1];
			if(this.getFreeSlots(startPoint, endPoint) < amount) { return false; }
			else {
				for(int i=startPoint; i<=endPoint; i++) {
					if((this.reservationSlots[i] = false) && (amount>0)) { 
						this.reservationSlots[i] = true;
						amount--;
						if(amount <= 0) { break; }
					}
				}
				return true;
			}
		}
		
		public void freeSlots(int amount, equipable slotType) {
			int[] points = getStartEndPoints(slotType);
			int startPoint = points[0];
			int endPoint = points[1];
			for(int i=endPoint; i>=startPoint; i--) {
				if((this.reservationSlots[i] = true) && (amount>0)) { 
					this.reservationSlots[i] = false;
					amount--;
					if(amount <= 0) { break; }
				}
			}
			
		}
		
		
		//////////////
		//	Private
		/////////////
		private int getFreeSlots(int startPoint, int endPoint) {
			int ret = 0;
			for(int i=startPoint; i<=endPoint; i++) {
				if(this.reservationSlots[i] = false) { ret++; }
			}
			return ret;
		}
		private int[] getStartEndPoints(equipable slotType) {
			int[] ret = new int[2];
			switch(slotType) {
			case WEAPON: ret[0] = this.weaponSlotStart; ret[1] = this.weaponSlotEnd;
			case SHIELD: ret[0] = this.shieldSlotStart; ret[1] = this.shieldSlotEnd;
			case ARMOR: ret[0] = this.armorSlotStart; ret[1] = this.armorSlotEnd;
			case ACCESSORY: ret[0] = this.accessorySlotStart; ret[1] = this.accessorySlotEnd;
			case MOVEMENT: ret[0] = this.movementSlotStart; ret[1] = this.movementSlotEnd;
			}
			return ret;
		}
		
		public void load(DataDictionary dict) {
			String job = "N/A";
			try {
				job = "totalEquipmentSize"; totalEquipmentSlots = dict.getInteger("totalEquipmentSize");
				job = "reservationSlots"; reservationSlots = new boolean[this.totalEquipmentSlots];
				job = "weaponSlotStart"; weaponSlotStart = dict.getInteger("weaponSlotStart");
				job = "weaponSlotEnd"; weaponSlotEnd = dict.getInteger("weaponSlotEnd");
				job = "shieldSlotStart"; shieldSlotStart = dict.getInteger("shieldSlotStart");
				job = "shieldSlotEnd"; shieldSlotEnd = dict.getInteger("shieldSlotEnd");
				job = "armorSlotStart"; armorSlotStart = dict.getInteger("armorSlotStart");
				job = "armorSlotEnd"; armorSlotEnd = dict.getInteger("armorSlotEnd");
				job = "accessorySlotStart"; accessorySlotStart = dict.getInteger("accessorySlotStart");
				job = "accessorySlotEnd"; accessorySlotEnd = dict.getInteger("accessorySlotEnd");
				job = "movementSlotStart"; movementSlotStart = dict.getInteger("movementSlotStart"); 
				job = "movementSlotEnd"; movementSlotEnd= dict.getInteger("movementSlotEnd");
			}
			catch (Exception err) {
				FileErrors.globalErrors.addError("INVENTORY-LOAD", ("CharacterInventory.load, at=" + job), err);
			}
		}
	}
	



	
//////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////
	
	
	private ReservationSlotArray reservationSlots;
	
	private ArrayList<BaseItem> equipedWeapons;
	private ArrayList<BaseItem> equipedShields;
	private ArrayList<BaseItem> equipedArmors;
	private ArrayList<BaseItem> equipedAccessories;
	private ArrayList<BaseItem> equipedMovements;
	
	
	
	//////////////////////
	//	Constructor
	//////////////////////
	public CharacterInventory1() {
		reservationSlots = new ReservationSlotArray();
		equipedWeapons = new ArrayList<BaseItem>();
		equipedShields = new ArrayList<BaseItem>();
		equipedArmors = new ArrayList<BaseItem>();
		equipedAccessories = new ArrayList<BaseItem>();
		equipedMovements = new ArrayList<BaseItem>();
	}
	
	public CharacterInventory1(int totalSize, 
				int iweaponSlotStart, int iweaponSlotEnd,
				int ishieldSlotStart, int ishieldSlotEnd,
				int iarmorSlotStart, int iarmorSlotEnd,
				int iaccessorySlotStart, int iaccessorySlotEnd,
				int imovementSlotStart, int imovementSlotEnd) {
		reservationSlots = new ReservationSlotArray(totalSize, iweaponSlotStart, iweaponSlotEnd, ishieldSlotStart, 
				ishieldSlotEnd, iarmorSlotStart, iarmorSlotEnd, iaccessorySlotStart, iaccessorySlotEnd, 
				imovementSlotStart, imovementSlotEnd);
		equipedWeapons = new ArrayList<BaseItem>();
		equipedShields = new ArrayList<BaseItem>();
		equipedArmors = new ArrayList<BaseItem>();
		equipedAccessories = new ArrayList<BaseItem>();
		equipedMovements = new ArrayList<BaseItem>();
	}
	
	
	
	
	
	
	
	//////////////////////
	//	Public Functions
	//////////////////////
	public boolean equipItem(String name) {
		BaseItem itm = this.findItemByName(name);
		if(itm != null) {
			return equipItem(itm);
		}
		else { return false; }
	}
	
	public boolean equipItem(BaseItem itm) {
		if(itm instanceof WeaponItem) { return equipWeapon((WeaponItem) itm); }
		else if(itm instanceof ShieldItem) { return equipShield((ShieldItem) itm); }
		else if(itm instanceof ArmorItem) { return equipArmor((ArmorItem) itm); }
		else if(itm instanceof AccessoryItem) { return equipAccessory((AccessoryItem) itm); }
		else if(itm instanceof AccessoryItem) { return equipMovement((MovementItem) itm); }
		return false;
	}
	
//	public boolean equipItem2(BaseItem itm) {
//		equipable itmType = equipable.WEAPON;
//		if(itm instanceof WeaponItem) { itmType = equipable.WEAPON; }
//		else if(itm instanceof ShieldItem) { itmType = equipable.SHIELD; }
//		else if(itm instanceof ArmorItem) { itmType = equipable.ARMOR; }
//		else if(itm instanceof AccessoryItem) { itmType = equipable.ACCESSORY; }
//		else if(itm instanceof MovementItem) { itmType = equipable.MOVEMENT; }
//		
//		if(this.reservationSlots.reserveSlots(itm.getSize(), itmType) == false) {
//			return false;
//		}
//		else {
//			// slots reserved
//			switch (itmType) {
//			case WEAPON: this.equipedWeapons.add((WeaponItem) itm);
//			case SHIELD: this.equipedShields.add(itm);
//			case ARMOR: this.equipedArmors.add(itm);
//			case ACCESSORY: this.equipedAccassories.add(itm);
//			case MOVEMENT: this.equipedMovements.add(itm);
//			}
//			return true;
//		}
//	}
//		

	public boolean unequipItem(BaseItem itm) {
		if(itm instanceof WeaponItem) { return unequipWeapon((WeaponItem) itm); }
		else if(itm instanceof ShieldItem) { return unequipShield((ShieldItem) itm); }
		else if(itm instanceof ArmorItem) { return unequipArmor((ArmorItem) itm); }
		else if(itm instanceof AccessoryItem) { return unequipAccessory((AccessoryItem) itm); }
		else if(itm instanceof AccessoryItem) { return unequipMovement((MovementItem) itm); }
		return false;
	}
	
	
	
	public boolean unequipWeapon(WeaponItem itm) {
		if(itm.isEquiped() && this.equipedWeapons.indexOf(itm) != -1) {
			this.reservationSlots.freeSlots(itm.getSize(), equipable.WEAPON);
			this.equipedWeapons.remove(itm);
			return true;
		}else { return false; }
	}
	public boolean unequipShield(ShieldItem itm) {
		if(itm.isEquiped() && this.equipedShields.indexOf(itm) != -1) {
			this.reservationSlots.freeSlots(itm.getSize(), equipable.WEAPON);
			this.equipedWeapons.remove(itm);
			return true;
		}else { return false; }
	}
	public boolean unequipArmor(ArmorItem itm) {
		if(itm.isEquiped() && this.equipedArmors.indexOf(itm) != -1) {
			this.reservationSlots.freeSlots(itm.getSize(), equipable.WEAPON);
			this.equipedWeapons.remove(itm);
			return true;
		}else { return false; }
	}
	public boolean unequipAccessory(AccessoryItem itm) {
		if(itm.isEquiped() && this.equipedAccessories.indexOf(itm) != -1) {
			this.reservationSlots.freeSlots(itm.getSize(), equipable.WEAPON);
			this.equipedWeapons.remove(itm);
			return true;
		}else { return false; }
	}
	public boolean unequipMovement(MovementItem itm) {
		if(itm.isEquiped() && this.equipedMovements.indexOf(itm) != -1) {
			this.reservationSlots.freeSlots(itm.getSize(), equipable.WEAPON);
			this.equipedWeapons.remove(itm);
			return true;
		}else { return false; }
	}
	
	public boolean equipWeapon(WeaponItem itm) {
		if(this.reservationSlots.reserveSlots(itm.getSize(), equipable.WEAPON)) {
			this.equipedWeapons.add(itm);
			return true;
		} else {
			return false;
		}
	}
	public boolean equipShield(ShieldItem itm) {
		if(this.reservationSlots.reserveSlots(itm.getSize(), equipable.SHIELD)) {
			this.equipedShields.add(itm);
			return true;
		} else {
			return false;
		}
	}
	public boolean equipArmor(ArmorItem itm) {
		if(this.reservationSlots.reserveSlots(itm.getSize(), equipable.ARMOR)) {
			this.equipedArmors.add(itm);
			return true;
		} else {
			return false;
		}
	}
	public boolean equipAccessory(AccessoryItem itm) {
		if(this.reservationSlots.reserveSlots(itm.getSize(), equipable.ACCESSORY)) {
			this.equipedAccessories.add(itm);
			return true;
		} else {
			return false;
		}
	}
	public boolean equipMovement(MovementItem itm) {
		if(this.reservationSlots.reserveSlots(itm.getSize(), equipable.MOVEMENT)) {
			this.equipedMovements.add(itm);
			return true;
		} else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	//////////////////////
	//	Getters
	//////////////////////
	public ArrayList<BaseItem> getEquipedWeapons() { return this.equipedWeapons; }
	public ArrayList<BaseItem> getEquipedShields() { return this.equipedShields; }
	public ArrayList<BaseItem> getEquipedArmors() { return this.equipedArmors; }
	public ArrayList<BaseItem> getEquipedAccessories() { return this.equipedAccessories; }
	public ArrayList<BaseItem> getEquipedMovements() { return this.equipedMovements; }
	
	public int getFreeWeaponSlots() { return this.reservationSlots.getFreeSlots(equipable.WEAPON); }
	public int getFreeShieldSlots() { return this.reservationSlots.getFreeSlots(equipable.SHIELD); }
	public int getFreeArmorSlots() { return this.reservationSlots.getFreeSlots(equipable.ARMOR); }
	public int getFreeAccessorySlots() { return this.reservationSlots.getFreeSlots(equipable.ACCESSORY); }
	public int getFreeMovementSlots() { return this.reservationSlots.getFreeSlots(equipable.MOVEMENT); }
	
	
	
	
	
	
	//////////////////////////
	//	Private Functions
	//////////////////////////
	
	
	
	
	/////////////////
	//	Loader
	/////////////////
	public void load(DataDictionary dict) {
		super.load(dict);
		reservationSlots.load(dict);
	}
	
}
