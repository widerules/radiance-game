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

import org.anddev.engine.config.Filepath;
import org.anddev.engine.variables.EnumClass;

import android.util.Log;

public abstract class ItemBase {
	
	///////////////////////
	// Global Item Enums
	///////////////////////
	public static enum ITMtype { W1H, W2H, SHIELD, ARMOR, CONSUMABLE, QUEST, MISC }		// Item
	public static enum CONtype { HEAL, RESTORE, DAMAGE, BUFF, DEBUF }					// Consumable
	
	public static EnumClass WEPtype;			// Wepaons
	public static EnumClass ARMtype;			// Armors
	//public static EnumClass DMGtype; 			// Damage
	
	
	
	///////////////////////
	//	Getters
	///////////////////////
	// Static
	public static EnumClass getWeaponType() { return WEPtype; }
	public static EnumClass getArmorType() { return ARMtype; }
	
	
	
	///////////////////////
	//	public methods
	///////////////////////
	
	public static void initItemTypes() {
		if ( !initWEPType(Filepath.xmlWeaponTypes) ) {Log.e("initWEPType", "Coult not load WEPtype");}
		if ( !initARMType(Filepath.xmlWeaponTypes) ) {Log.e("initARMType", "Coult not load ARMype");}
	}
	
	
	
	
	////////////////////////
	//	Private methods
	////////////////////////
	private static Boolean initWEPType(String path) {
		Boolean ret = true;
		try { WEPtype = EnumClass.create(path); }
		catch (Exception err) { ret = false; }
		return ret;
	}
	private static Boolean initARMType(String path) {
		Boolean ret = true;
		try { ARMtype = EnumClass.create(path); }
		catch (Exception err) { ret = false; }
		return ret;
	}
	
}
