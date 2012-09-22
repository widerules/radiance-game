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


package org.vagosduke.andengine.radiance.program.loader;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.items.AccessoryItem;
import org.vagosduke.andengine.radiance.game.items.ArmorItem;
import org.vagosduke.andengine.radiance.game.items.BaseItem;
import org.vagosduke.andengine.radiance.game.items.ConsumableItem;
import org.vagosduke.andengine.radiance.game.items.ItemTypes;
import org.vagosduke.andengine.radiance.game.items.MovementItem;
import org.vagosduke.andengine.radiance.game.items.OtherItem;
import org.vagosduke.andengine.radiance.game.items.ShieldItem;
import org.vagosduke.andengine.radiance.game.items.StatBonus;
import org.vagosduke.andengine.radiance.game.items.WeaponItem;
import org.vagosduke.andengine.radiance.game.items.ItemTypes.ItemType;
import org.vagosduke.andengine.radiance.game.items.WeaponItem.WeaponDamage;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.GlobalEffectList;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumClass;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.FileUtil;
import org.vagosduke.andengine.radiance.util.YAMLutil;

public class GameItemLoader {	
	
	///////////////////////////////////
	//	temporary variables 
	///////////////////////////////////
	private static String iname;
	private static String idescription;
	private static ItemType itype;			//
	private static EnumValue isubtype;		//	ItemTypes.somesubtype
	private static int ivalue;
	private static int ilevel;
	private static int isize;
	private static int istacks;
	private static double iweight;
	private static boolean iequiped;
	private static boolean iusable;
	private static boolean istackable;
	private static boolean ishoppable;
	private static boolean idroppable;
	private static ArrayList<BaseEffect> ionEquipEffects;
	private static ArrayList<BaseEffect> ionUseEffects;
	private static ArrayList<StatBonus> ionEquipBonuses;
	
	
	
	

	
	
	
	
	
	
	//////////////////////////////////
	// Specific Item Loader
	/////////////////////////////////
	public static WeaponItem loadWeaponItem(DataDictionary dict) {
		BaseItemLoader(dict);
		ArrayList<WeaponDamage> damage = new ArrayList<WeaponDamage>();
		TemplateInfo range = new TemplateInfo();
		TemplateInfo effectTemplate = new TemplateInfo();
		String job = "N/A";
		try {
			job = "range"; range = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("range"));
			job = "effectTemplate"; effectTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("effectTemplate"));
			job = "damage"; 
			DataDictionary dmgArray = dict.getSubArray("damage");
			for(int i=0; i<dmgArray.getArraySize(); i++) {
				DataDictionary dmgDict = dmgArray.getSubDictionary(i);
				EnumValue dtype = Damage.DMGtype.valueOf(dmgDict.getString("damageType").toUpperCase());
				double min = dmgDict.getDouble("min");
				double max = dmgDict.getDouble("max");
				damage.add(new WeaponDamage(min, max, dtype));
			}
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("ITEM-LOAD", ("loadWeaponItem, Item = "+iname+" at = " + job), err);
		}
		return new WeaponItem(iname, idescription, itype, isubtype, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, damage, range, effectTemplate, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}
	
	
	
	
	public static ArmorItem loadArmorItem(DataDictionary dict) {
		BaseItemLoader(dict);
		double speedModifier = 0.0;
		String job = "N/A";
		try {
			job = "speedModifier"; speedModifier = dict.getInteger("speedModifier");
		}	
		catch (Exception err) {
			FileErrors.globalErrors.addError("ITEM-ERROR", ("loadArmorItem, Item = "+iname+" at = "+job), err);
		}
		return new ArmorItem(iname, idescription, itype, isubtype, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, speedModifier, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}
	
	
	
	public static MovementItem loadMovementItem(DataDictionary dict) {
		BaseItemLoader(dict);
		double speedModifier = 0.0;
		String job = "N/A";
		try {
			job = "speedModifier"; speedModifier = dict.getInteger("speedModifier");
		}	
		catch (Exception err) {
			FileErrors.globalErrors.addError("ITEM-ERROR", ("loadMovementItem, Item = "+iname+" at = "+job), err);
		}
		return new MovementItem(iname, idescription, itype, isubtype, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, speedModifier, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}
	
	
	

	
	public static ConsumableItem loadConsumableItem(DataDictionary dict) {
		BaseItemLoader(dict);
		TemplateInfo irange = new TemplateInfo();
		TemplateInfo ieffectTemplate = new TemplateInfo();
		String job = "N/A";
		try {
			job = "range"; irange = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("range"));
			job = "effectTemplate"; ieffectTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("effectTemplate"));
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("ITEM-LOAD", ("loadConsumableItem, Item = "+iname+" at = " + job), err);
		}
		return new ConsumableItem(iname, idescription, itype, isubtype, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, irange, ieffectTemplate, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}
	
	
	
	
	public static AccessoryItem loadAccessoryItem(DataDictionary dict) {
		BaseItemLoader(dict);
		return new AccessoryItem(iname, idescription, itype, isubtype, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}
	
	
	public static OtherItem loadOtherItem(DataDictionary dict) {
		BaseItemLoader(dict);
		return new OtherItem(iname, idescription, itype, isubtype, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}
	
	
	public static ShieldItem loadShieldItem(DataDictionary dict) {
		BaseItemLoader(dict);
		return new ShieldItem(iname, idescription, itype, isubtype, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}
	
	
	//////////////////////////
	// Base Item Loader
	/////////////////////////
	private static void BaseItemLoader(DataDictionary dict) {
		purge();
		String job = "N/A";
		try {
			iname = dict.getString("name");
			job = "description"; idescription = dict.getString("description");
			job = "type";  itype = ItemType.valueOf(dict.getString("type").toUpperCase());
			switch(itype) {
				case WEAPON: isubtype = ItemTypes.WEAPONtype.valueOf(dict.getString("subtype").toUpperCase()); break;
				case SHIELD: isubtype = ItemTypes.SHIELDtype.valueOf(dict.getString("subtype").toUpperCase()); break;
				case ARMOR: isubtype = ItemTypes.ARMORtype.valueOf(dict.getString("subtype").toUpperCase()); break;
				case MOVEMENT: isubtype = ItemTypes.MOVEMENTtype.valueOf(dict.getString("subtype").toUpperCase()); break;
				case CONSUMABLE: isubtype = ItemTypes.CONSUMABLEtype.valueOf(dict.getString("subtype").toUpperCase()); break;
				case ACCESSORY: isubtype = ItemTypes.ACCESSORYtype.valueOf(dict.getString("subtype").toUpperCase()); break;
				case OTHER: isubtype = ItemTypes.OTHERtype.valueOf(dict.getString("subtype").toUpperCase()); break;
			}
			job = "value"; ivalue = dict.getInteger("value");
			job = "level"; ilevel = dict.getInteger("level");
			job = "size"; isize = dict.getInteger("size");
			job = "weight"; iweight = dict.getDouble("weight");
			job = "usable";  iusable = dict.getBoolean("usable");
			job = "stackable";  istackable = dict.getBoolean("stackable");
			job = "shoppable"; ishoppable = dict.getBoolean("shoppable");
			job = "dropable"; idroppable = dict.getBoolean("droppable");
			job = "onEquipEffects"; 
			if(dict.exists("onEquipEffects")) {
				DataDictionary eEffects = dict.getSubArray("onEquipEffects");
				for(int i=0; i<eEffects.getArraySize(); i++) {
					ionEquipEffects.add(GlobalEffectList.globalList.createEffect(eEffects.getSubDictionary(i)));
				}
			}
			job = "onUseEffects"; 
			ArrayList<BaseEffect> ionUseEffects = new ArrayList<BaseEffect>();
			if(dict.exists("onUseEffects")) {
				DataDictionary uEffects = dict.getSubArray("onUseEffects");
				for(int i=0; i<uEffects.getArraySize(); i++) {
					ionUseEffects.add(GlobalEffectList.globalList.createEffect(uEffects.getSubDictionary(i)));
				}
			}
			job = "onEquipBonuses";
			ArrayList<StatBonus> ionEquipBonuses = new ArrayList<StatBonus>();
			if(dict.exists("onEquipBonuses")) {
				DataDictionary eBonus = dict.getSubArray("onEquipBonuses");
				for(int i=0; i<eBonus.getArraySize(); i++) {
					ionEquipBonuses.add(GameMiscLoader.loadStatBonus(eBonus.getSubDictionary(i)));
				}
			}
		}
		
		catch (Exception err) {
			FileErrors.globalErrors.addError("ITEM-LOAD", ("BaseItemLoader, Item=\"" + iname + "\" at=" + job), err);
		}
	}
	
	private static void purge() {
		iname = "N/A";
		idescription = "N/A";
		itype = ItemType.OTHER;
		isubtype = ItemTypes.OTHERtype.defaultValue();
		ivalue = 0;
		ilevel = 0;
		isize = 0;
		istacks = -1;
		iweight = 0.0;
		iequiped = false;
		iusable = false;
		istackable = false;
		ishoppable = false;
		idroppable = false;
		ionEquipEffects = new ArrayList<BaseEffect>();
		ionUseEffects = new ArrayList<BaseEffect>();
		ionEquipBonuses = new ArrayList<StatBonus>();
		
	}
	
	
	
	
	
	
	
	
	
	//////////////////////////////
	// Item SubTypes Loader
	////////////////////////////
	
	
	public static void initItemSubTypes(DataDictionary dict) {
		EnumClass iWEAPONtype;
		EnumClass iARMORtype;
		EnumClass iSHIELDtype;
		EnumClass iMOVEMENTtype;
		EnumClass iCONSUMABLEtype;
		EnumClass iACCESSORYtype;
		EnumClass iOTHERtype;
		String job = "N/A";
		try {
			job = "WeaponType"; iWEAPONtype = EnumClass.create(dict.getSubArray("WeaponType"));
			job = "ArmorType"; iARMORtype = EnumClass.create(dict.getSubArray("ArmorType"));
			job = "ShieldType"; iSHIELDtype = EnumClass.create(dict.getSubArray("ShieldType"));
			job = "MovementType"; iMOVEMENTtype = EnumClass.create(dict.getSubArray("MovementType"));
			job = "ConsumableType"; iCONSUMABLEtype = EnumClass.create(dict.getSubArray("ConsumableType"));
			job = "AccessoryType"; iACCESSORYtype = EnumClass.create(dict.getSubArray("AccessoryType"));
			job = "OtherType"; iOTHERtype = EnumClass.create(dict.getSubArray("OtherType"));
			job = "InitTypes"; ItemTypes.initItemSubTypes(iWEAPONtype, iSHIELDtype, iARMORtype, iACCESSORYtype, 
					iMOVEMENTtype, iCONSUMABLEtype, iOTHERtype);
		} catch (Exception err) {
			FileErrors.globalErrors.addError("FILE-ERROR", ("loadItemSubTypes, at=" + job), err);
		}
	}
}
