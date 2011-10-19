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

package org.anddev.game.items;

import java.io.InputStream;

import org.anddev.game.items.WeaponList;
import org.anddev.program.config.Filepath;
import org.anddev.util.FileUtil;

import android.util.Log;



public class GlobalItemList {
	/** Keeps a global list for all items available.
	 * By default, all the items all loaded from external xml files */
	
	
	// 	Item lists
	//	"Main" Lists are sorted by level(1st) and type(2nd) so they can be presentable
	//	"Name" Lists are sorted by name so find_byName works in log(n) time
	//	Both lists point at the same objects. So the overhead is only for pointer size
	
	
	static private WeaponList weaponList;
	static private ShieldList shieldList;
	static private ArmorList armorList;
	static private ConsumableList consumableList;
	static private MiscList otherList;
	
	
	///////////////////////
	//	Static Loader
	///////////////////////
	public static void loadListXML() {
		// Initializer should be called at program startup
		try {
			weaponList = WeaponList.createEmpty();
			InputStream wepFile = FileUtil.open(Filepath.xmlWeapons);
			weaponList.initialize(wepFile);
			FileUtil.close(Filepath.xmlWeapons);
		}
		catch (Exception err) {
			Log.e("GLOBALITEMLIST", err.getMessage());
		}
		try {
			shieldList = ShieldList.createEmpty();
			InputStream shdFile = FileUtil.open(Filepath.xmlShields);
			shieldList.initialize(shdFile);
			FileUtil.close(Filepath.xmlShields);
		}
		catch (Exception err) {
			Log.e("GLOBALITEMLIST", err.getMessage());
		}
		try {
			armorList = ArmorList.createEmpty();
			InputStream armFile = FileUtil.open(Filepath.xmlArmors);
			armorList.initialize(armFile);
			FileUtil.close(Filepath.xmlArmors);
		}
		catch (Exception err) {
			Log.e("GLOBALITEMLIST", err.getMessage());
		}
		try {
			consumableList = ConsumableList.createEmpty();
			InputStream conFile = FileUtil.open(Filepath.xmlConsumables);
			consumableList.initialize(conFile);
			FileUtil.close(Filepath.xmlConsumables);
		}
		catch (Exception err) {
			Log.e("GLOBALITEMLIST", err.getMessage());
		}
		try {
			otherList = MiscList.createEmpty();
			InputStream misFile = FileUtil.open(Filepath.xmlOthers);
			otherList.initialize(misFile);
			FileUtil.close(Filepath.xmlOthers);
		}
		catch (Exception err) {
			Log.e("GLOBALITEMLIST", err.getMessage());
		}
	}
	
	
	
	
	//////////////////////
	//	Getters
	//////////////////////
	public static WeaponItem getWeapon(int index) { return weaponList.getByIndex(index); }
	public static ShieldItem getShield(int index) { return shieldList.getByIndex(index); }
	public static ArmorItem getArmor(int index) { return armorList.getByIndex(index); }
	public static ConsumableItem getConsumable(int index) { return consumableList.getByIndex(index); }
	public static MiscItem getOther(int index) { return otherList.getByIndex(index); }
	
	public static WeaponItem getWeapon(String iname) { return weaponList.getByName(iname); }
	public static ShieldItem getShield(String iname) { return shieldList.getByName(iname); }
	public static ArmorItem getArmor(String iname) { return armorList.getByName(iname); }
	public static ConsumableItem getConsumable(String iname) { return consumableList.getByName(iname); }
	public static MiscItem getOther(String iname) { return otherList.getByName(iname); }
	
	
	public static WeaponList getWeaponList(int lvlStart, int lvlEnd, Boolean drop, Boolean shop){
		WeaponList temp_list = WeaponList.createEmpty();
		for(int i=0; i<weaponList.size(); i++) {
			if ( (weaponList.getByIndex(i).getLevelRequired() >= lvlStart) &&
					(weaponList.getByIndex(i).getLevelRequired() <= lvlEnd) &&
					 ( 	(drop && weaponList.getByIndex(i).getDroppable()) ||
						(shop && weaponList.getByIndex(i).getShoppable()) ) ) {
				temp_list.add(weaponList.getByIndex(i));
			}
		}
		return temp_list;
	}
	public static ShieldList getShieldList(int lvlStart, int lvlEnd, Boolean drop, Boolean shop){
		ShieldList temp_list = ShieldList.createEmpty();
		for(int i=0; i<shieldList.size(); i++) {
			if ( (shieldList.getByIndex(i).getLevelRequired() >= lvlStart) &&
					(shieldList.getByIndex(i).getLevelRequired() <= lvlEnd) &&
					 ( 	(drop && shieldList.getByIndex(i).getDroppable()) ||
						(shop && shieldList.getByIndex(i).getShoppable()) ) ) {
				temp_list.add(shieldList.getByIndex(i));
			}
		}
		return temp_list;
	}
	public static ArmorList getArmorList(int lvlStart, int lvlEnd, Boolean drop, Boolean shop){
		ArmorList temp_list = ArmorList.createEmpty();
		for(int i=0; i<armorList.size(); i++) {
			if ( (armorList.getByIndex(i).getLevelRequired() >= lvlStart) &&
					(armorList.getByIndex(i).getLevelRequired() <= lvlEnd) &&
					 ( 	(drop && armorList.getByIndex(i).getDroppable()) ||
						(shop && armorList.getByIndex(i).getShoppable()) ) ) {
				temp_list.add(armorList.getByIndex(i));
			}
		}
		return temp_list;
	}
	public static ConsumableList getConsumableList(int lvlStart, int lvlEnd, Boolean drop, Boolean shop){
		ConsumableList temp_list = ConsumableList.createEmpty();
		for(int i=0; i<consumableList.size(); i++) {
			if ( (consumableList.getByIndex(i).getLevelRequired() >= lvlStart) &&
					(consumableList.getByIndex(i).getLevelRequired() <= lvlEnd) &&
					 ( 	(drop && consumableList.getByIndex(i).getDroppable()) ||
						(shop && consumableList.getByIndex(i).getShoppable()) ) ) {
				temp_list.add(consumableList.getByIndex(i));
			}
		}
		return temp_list;
	}
	public static MiscList getOtherList(int lvlStart, int lvlEnd, Boolean drop, Boolean shop){
		MiscList temp_list = MiscList.createEmpty();
		for(int i=0; i<otherList.size(); i++) {
			if ( (otherList.getByIndex(i).getLevelRequired() >= lvlStart) &&
					(otherList.getByIndex(i).getLevelRequired() <= lvlEnd) &&
					 ( 	(drop && otherList.getByIndex(i).getDroppable()) ||
						(shop && otherList.getByIndex(i).getShoppable()) ) ) {
				temp_list.add(otherList.getByIndex(i));
			}
		}
		return temp_list;
	}
	
	//////////////////////
	//	public methods
	// 
	//////////////////////
	public static String staticToString() {
		return weaponList.toString() + shieldList.toString() +  
		armorList.toString() + consumableList.toString() + otherList.toString();
	}




	public static WeaponList getGlobalWeaponList() {return weaponList;}
	public static ShieldList getGlobalShieldList() {return shieldList;}
	public static ArmorList getGlobalArmorList() {return armorList;}
	public static ConsumableList getGlobalConsumableListM() {return consumableList;}
	public static MiscList getGlobalOtherList() {return otherList;}

	//////////////////////
	//	private methods
	// 
	//////////////////////
	
}

