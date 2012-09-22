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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.vagosduke.andengine.radiance.game.items.ItemTypes.ItemType;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.FileUtil;
import org.vagosduke.andengine.radiance.util.YAMLutil;

import android.util.Log;



public class GlobalItemList {
	/** It is used to keep listing of items available in the game
	 * Each type of item is held in two separate collections:
	 * 		a hash map (for by-name retrieval)
	 * 		and an ArrayList (for by-level bulk retrieval) 
	 * All the items all loaded from external xml or yaml files 
	 * It can be instantiated, or referenced from the static handler if initialized*/
	
	////////////////////////////////////
	//	Static globalItemList handler
	/////////////////////////////////////
	static public GlobalItemList globalList;
	
	
	
	
	
	
	private HashMap<String, BaseItem> weaponNameMap;
	private HashMap<String, BaseItem> shieldNameMap;
	private HashMap<String, BaseItem> armorNameMap;
	private HashMap<String, BaseItem> accessoryNameMap;
	private HashMap<String, BaseItem> movementNameMap;
	private HashMap<String, BaseItem> consumableNameMap;
	private HashMap<String, BaseItem> otherNameMap;
	
	private ArrayList<BaseItem> weaponLevelList;
	private ArrayList<BaseItem> shieldLevelList;
	private ArrayList<BaseItem> armorLevelList;
	private ArrayList<BaseItem> accessoryLevelList;
	private ArrayList<BaseItem> movementLevelList;
	private ArrayList<BaseItem> consumableLevelList;
	private ArrayList<BaseItem> otherLevelList;

	
	
	///////////////////////
	//	Construcotr
	///////////////////////
	public GlobalItemList(String path) {
		this.weaponNameMap = new HashMap<String, BaseItem>();
		this.shieldNameMap = new HashMap<String, BaseItem>();
		this.armorNameMap = new HashMap<String, BaseItem>();
		this.accessoryNameMap = new HashMap<String, BaseItem>();
		this.movementNameMap = new HashMap<String, BaseItem>();
		this.consumableNameMap = new HashMap<String, BaseItem>();
		this.otherNameMap = new HashMap<String, BaseItem>();
				
		this.weaponLevelList = new ArrayList<BaseItem>();
		this.shieldLevelList = new ArrayList<BaseItem>();
		this.armorLevelList = new ArrayList<BaseItem>();	
		this.accessoryLevelList = new ArrayList<BaseItem>();
		this.movementLevelList = new ArrayList<BaseItem>();
		this.consumableLevelList = new ArrayList<BaseItem>();
		this.otherLevelList = new ArrayList<BaseItem>();
		
		InputStream masterListFile = null;
		try {
			masterListFile = FileUtil.open(path);
			DataDictionary dict = DataDictionary.makeDictionary(YAMLutil.loadData(masterListFile));
			
			loadItemLists(dict.getSubArray("weapons"), weaponNameMap, weaponLevelList, new ItemFactory(ItemType.WEAPON));
			loadItemLists(dict.getSubArray("shields"), shieldNameMap, shieldLevelList, new ItemFactory(ItemType.SHIELD));
			loadItemLists(dict.getSubArray("armors"), armorNameMap, armorLevelList, new ItemFactory(ItemType.ARMOR));
			loadItemLists(dict.getSubArray("accessories"), accessoryNameMap, accessoryLevelList, new ItemFactory(ItemType.ACCESSORY));
			loadItemLists(dict.getSubArray("movements"), movementNameMap, movementLevelList, new ItemFactory(ItemType.MOVEMENT));
			loadItemLists(dict.getSubArray("consumables"), consumableNameMap, consumableLevelList, new ItemFactory(ItemType.CONSUMABLE));
			loadItemLists(dict.getSubArray("others"), otherNameMap, otherLevelList, new ItemFactory(ItemType.OTHER));
		} catch (Exception err) {
			FileErrors.globalErrors.addError("FILE-ERROR", ("GlobalItemList.GlobalItemList, File = " + path), err);	
		}
		finally {
			if(masterListFile != null) { FileUtil.close(path); }
		}
	}
	
	public static void initGlobalItemList(String path) {
		/** 
		 * Global Item Listing
		 * Initializes the individual item Lists by reading the location of each Item file
		 * from the static master itemFiles file
		 * (Item description files can contain individual items or more,
		 *  up to all the items of the specific subtype)
		 */
		
		globalList = new GlobalItemList(path);
	}
	
	
	
	
	//////////////////////
	//	Getters
	//////////////////////
	public WeaponItem getWeapon(int index) { return (WeaponItem) this.weaponLevelList.get(index); }
	public ShieldItem getShield(int index) { return (ShieldItem) this.shieldLevelList.get(index); }
	public ArmorItem getArmor(int index) { return (ArmorItem) this.armorLevelList.get(index); }
	public AccessoryItem getAccessory(int index) { return (AccessoryItem) this.accessoryLevelList.get(index); }
	public MovementItem getMovement(int index) { return (MovementItem) this.movementLevelList.get(index); } 
	public ConsumableItem getConsumable(int index) { return (ConsumableItem) this.consumableLevelList.get(index); }
	public OtherItem getOther(int index) { return (OtherItem) this.otherLevelList.get(index); }
	
	public WeaponItem getWeapon(String name) { return (WeaponItem) this.weaponNameMap.get(name); }
	public ShieldItem getShield(String name) { return (ShieldItem) this.shieldNameMap.get(name); }
	public ArmorItem getArmor(String name) { return (ArmorItem) this.armorNameMap.get(name); }
	public AccessoryItem getAccessory(String name) { return (AccessoryItem) this.accessoryNameMap.get(name); }
	public MovementItem getMovement(String name) { return (MovementItem) this.movementNameMap.get(name); } 
	public ConsumableItem getConsumable(String name) { return (ConsumableItem) this.consumableNameMap.get(name); }
	public OtherItem getOther(String name) { return (OtherItem) this.otherNameMap.get(name); }
	public BaseItem getItem(String name) {
		BaseItem item = this.getWeapon(name);
		if(item != null) { return item; }
		item = this.getShield(name);
		if(item != null) { return item; }
		item = this.getArmor(name);
		if(item != null) { return item; }
		item = this.getAccessory(name);
		if(item != null) { return item; }
		item = this.getMovement(name);
		if(item != null) { return item; }
		item = this.getConsumable(name);
		if(item != null) { return item; }
		item = this.getOther(name);
		return item;
	}


	
	//////////////////////
	//	public methods
	//////////////////////
	public String toString() {
		return "\n===WEAPONS===\n" + weaponLevelList.toString() + "\n===SHIELDS===\n" + shieldLevelList.toString() +  
				"\n===ARMORS===\n" + armorLevelList.toString() + "\n===ACCESSORIES===\n" + accessoryLevelList.toString() +
				"\n===MOVEMENT ITEMS===\n" + movementLevelList.toString() + 
				"\n===CONSUMABLES===\n" + consumableLevelList.toString() + "\n===OTHERS===\n" + otherLevelList.toString();
	}


	
	
	

	//////////////////////
	//	private methods
	//////////////////////
	private static void loadItemLists(DataDictionary fileDict, HashMap<String, BaseItem> mapping,
										ArrayList<BaseItem> listing, ItemFactory factory) {
		/** 
		 * Loads both map and list with all items described in all the fileDict filenames
		 * AND sorts them by natural ordering
		 */
		for(int i=0; i<fileDict.getArraySize(); i++) {
			InputStream itemsFile = null;
			String itemfilename = "";
			try {
				itemfilename = fileDict.getString(i);
				itemsFile = FileUtil.open(itemfilename);
				DataDictionary dict = DataDictionary.makeDictionary(YAMLutil.loadData(itemsFile));
				for(int j=0; j<dict.getArraySize(); j++) {
					BaseItem itm = factory.create(dict.getSubDictionary(j));
					mapping.put(itm.getName(), itm);
					listing.add(itm);
				}
			}
			catch (Exception err) {
				FileErrors.globalErrors.addError("FILE-ERROR", ("GlobalItemList.loadItemLists, File = " + itemfilename), err);
			}
			finally {
				if(itemsFile != null) { FileUtil.close(itemfilename); }
			}
		}

		Collections.sort(listing);
	}
}

