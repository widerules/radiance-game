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
