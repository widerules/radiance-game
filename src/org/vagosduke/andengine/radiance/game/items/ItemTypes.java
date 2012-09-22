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


package org.vagosduke.andengine.radiance.game.items;

import java.io.IOException;
import java.io.InputStream;

import org.vagosduke.andengine.radiance.program.config.Filepath;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumClass;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.FileUtil;
import org.vagosduke.andengine.radiance.util.TraceUtil;
import org.vagosduke.andengine.radiance.util.YAMLutil;

public class ItemTypes {
	
	///////////////////////
	// Global Item Enums
	///////////////////////
	public static enum ItemType { WEAPON, SHIELD, ARMOR, MOVEMENT, CONSUMABLE, ACCESSORY, OTHER }		// Item

	public static EnumClass WEAPONtype = null;			// Weapon subtype
	public static EnumClass SHIELDtype = null;			// shield subtype
	public static EnumClass ARMORtype = null;			// Armor subtype
	public static EnumClass ACCESSORYtype = null;		// accessory subtype
	public static EnumClass MOVEMENTtype = null;		// boots subtype
	public static EnumClass CONSUMABLEtype = null;		// consumable subtype
	public static EnumClass OTHERtype = null;			// other subtype
	
	
	
	///////////////////////
	//	public methods
	///////////////////////
	public static void initItemSubTypes(EnumClass iWEAPONtype, EnumClass iSHIELDtype, EnumClass iARMORtype, 
			EnumClass iACCESSORYtype, EnumClass iMOVEMENTtype, EnumClass iCONSUMABLEtype, EnumClass iOTHERtype) {
		WEAPONtype = iWEAPONtype;
		ARMORtype = iARMORtype;
		SHIELDtype = iSHIELDtype;
		MOVEMENTtype = iMOVEMENTtype;
		CONSUMABLEtype = iCONSUMABLEtype;
		ACCESSORYtype = iACCESSORYtype;
		OTHERtype = iOTHERtype;
	}
	
//	public static void initItemSubTypes(DataDictionary dict) {
//		String job = "N/A";
//		try {
//			job = "WeaponType"; WEAPONtype = EnumClass.create(dict.getSubArray("WeaponType"));
//			job = "ArmorType"; ARMORtype = EnumClass.create(dict.getSubArray("ArmorType"));
//			job = "ShieldType"; SHIELDtype = EnumClass.create(dict.getSubArray("ShieldType"));
//			job = "MovementType"; MOVEMENTtype = EnumClass.create(dict.getSubArray("MovementType"));
//			job = "ConsumableType"; CONSUMABLEtype = EnumClass.create(dict.getSubArray("ConsumableType"));
//			job = "AccessoryType"; ACCESSORYtype = EnumClass.create(dict.getSubArray("AccessoryType"));
//			job = "OtherType"; OTHERtype = EnumClass.create(dict.getSubArray("OtherType"));
//		} catch (Exception err) {
//			FileErrors.globalErrors.addError("FILE-ERROR", ("initItemSubTypes, at=" + job), err);
//		}
//	}
}
