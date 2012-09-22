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


import java.util.ArrayList;

import org.vagosduke.andengine.radiance.engine.menu.MenuItem;
import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.constants.Constants;
import org.vagosduke.andengine.radiance.game.items.ItemTypes.ItemType;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.GlobalEffectList;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.log.ProgLog;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.TraceUtil;

public class BaseItem implements Comparable<BaseItem>, MenuItem{

	private String name;
	private String description;
	private ItemType type;			//
	private EnumValue subtype;		//	ItemTypes.somesubtype
	private int value;
	private int level;
	private int size;
	private int stacks;
	private double weight;
	private boolean equiped;
	private boolean usable;
	private boolean stackable;
	private boolean shoppable;
	private boolean droppable;
	private ArrayList<BaseEffect> onEquipEffects;
	private ArrayList<BaseEffect> onUseEffects;
	private ArrayList<StatBonus> onEquipBonuses;
	
	
	
	
	
	/////////////////////////////
	// Constructor
	/////////////////////////////
	public BaseItem() {
		this.name = "N/A";
		this.description = "N/A";
		this.type = ItemType.OTHER;
		this.subtype = ItemTypes.OTHERtype.defaultValue();
		this.value = 0;
		this.level = 0;
		this.size = 0;
		this.stacks = -1;
		this.weight = 0.0;
		this.equiped = false;
		this.usable = false;
		this.stackable = false;
		this.shoppable = false;
		this.droppable = false;
		this.onEquipEffects = new ArrayList<BaseEffect>();
		this.onUseEffects = new ArrayList<BaseEffect>();
		this.onEquipBonuses = new ArrayList<StatBonus>();
	}
	public BaseItem(String iname, String idescription, ItemType itype, EnumValue isubType, int ivalue, int ilevel,
			int isize, double iweight, boolean iusable, boolean istackable, boolean ishoppable, boolean idroppable,
			ArrayList<BaseEffect> ionEquipEffects, ArrayList<BaseEffect> ionUseEffects, 
			ArrayList<StatBonus> ionEquipBonuses) {
		this.name = iname;
		this.description = idescription;
		this.type = itype;
		this.subtype = isubType;
		this.value = ivalue;
		this.level = ilevel;
		this.size = isize;
		this.stacks = -1;
		this.weight = iweight;
		this.equiped = false;
		this.usable = iusable;
		this.stackable = istackable;
		this.shoppable = ishoppable;
		this.droppable = idroppable;
		this.onEquipEffects = ionEquipEffects;
		this.onUseEffects = ionUseEffects;
		this.onEquipBonuses = ionEquipBonuses;
	}
	
	
	
	////////////////////////////
	//	Public Functions
	////////////////////////////
	@Override
	public int compareTo(BaseItem another) {
		/**
		 * 	Natural ordering: first by type, then by subtype, then by name.
		 */
		Integer lvl1 = new Integer(this.level); 
		Integer lvl2 = new Integer(another.level);
		int ret = this.type.compareTo(another.type);
		if(ret == 0) {										// same type
			ret = this.subtype.getEnum().compare(this.subtype, another.subtype);
			if (ret == 0) {									// same subtype
				ret = lvl1.compareTo(lvl2);
				if(ret == 0) {								// same level
					ret = this.name.compareTo(another.name);
					return ret;
				}
				else { return ret; }
			}
			else { return ret; }
		}
		else { return ret; }
	}	
	
	public BaseItem getInstance() {
		/**
		 * Stackable Items may be stacked. So a pointer to the static global list
		 * is not enough to keep track of the item stacks. This clones the item
		 * from the global Item List.
		 * CAREFULL!!
		 */
		BaseItem instance = new BaseItem();
		try {
			instance = (BaseItem) this.clone();
		} catch (CloneNotSupportedException e) { e.printStackTrace(); }
		instance.stacks = 1;
		return instance;
	}
	
	public void addStacks(int amount) {
		if(this.stackable) {
			if(stacks >=0 ) {	// check if this isn't master item
				this.stacks += amount;
			}
		}
	}
	public void removeStacks(int amount) {
		if(this.stackable) {
			if(stacks >=0 ) {	// check if this isn't master item
				if(this.stacks >= amount) {
					this.stacks -= amount;
				}
			}
		}
	}
	
	public void onEquip(Character mychar) {
		for(BaseEffect effect: this.onEquipEffects) {
			effect.apply(null, mychar);
		}
		for(StatBonus effect: this.onEquipBonuses) {
			effect.apply(mychar);
		}
	}
	
	public void use(Character target) {
		for(BaseEffect effect: this.onUseEffects) {
			effect.apply(null, target);
		}
	}
	public void use(Character mychar, Character target) {
		for(BaseEffect effect: this.onUseEffects) {
			effect.apply(mychar, target);
		}
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("\n\t" + this.name + ": ");
		str.append(this.type + "(" + this.subtype + "), " + this.value + "$ ");
		if(this.usable) { str.append("u"); }
		if(this.stackable) { str.append("s"); }
		if(this.shoppable) { str.append("o"); }
		if(this.droppable) { str.append("d"); }
		if(this.onEquipBonuses.size() > 0) { str.append("|"); }
		for(StatBonus bonus: this.onEquipBonuses) {
			str.append(" ");
			str.append(bonus.toString());
		}
		return str.toString();
	}
	
	
	
	
	//////////////////////
	//	Getters
	//////////////////////
	public String getName() { return this.name; }
	public String getDescription() { return this.description; }
	public ItemType getType() { return this.type; }
	public EnumValue getSubType() { return this.subtype; }
	public int getValue() { return this.value; }
	public int getLevel() { return this.level; }
	public int getSize() { return this.size; }
	public int getStacks() { return this.stacks; }
	public double getWeight() { return this.weight; }
	public boolean isEquiped() { return this.equiped; }
	public boolean isUsable() { return this.usable; }
	public boolean isStackable() { return this.stackable; }
	public boolean isShoppable() { return this.shoppable; }
	public boolean isDropable() { return this.droppable; }
	public ArrayList<BaseEffect> getOnEquipEffects() { return this.onEquipEffects; }
	public ArrayList<BaseEffect> getOnUseEffects() { return this.onUseEffects; }
	public ArrayList<StatBonus> getOnEquipBonuses() { return this.onEquipBonuses; }
	@Override
	public String getMenuLabel() { return this.name; }
	@Override
	public String getMenuInfo() { return this.description; }
	
	
	
	////////////////////////
	// Loader
	////////////////////////
//	public BaseItem load(DataDictionary dict) {
//		String itemName = "N/A";
//		String job = "N/A";
//		try {
//			this.name = dict.getString("name");
//			itemName = this.name;
//			job = "description"; this.description = dict.getString("description");
//			job = "type"; this.type = ItemType.valueOf(dict.getString("type").toUpperCase());
//			switch(this.type) {
//				case WEAPON: this.subtype = ItemTypes.WEAPONtype.valueOf(dict.getString("subtype").toUpperCase()); break;
//				case SHIELD: this.subtype = ItemTypes.SHIELDtype.valueOf(dict.getString("subtype").toUpperCase()); break;
//				case ARMOR: this.subtype = ItemTypes.ARMORtype.valueOf(dict.getString("subtype").toUpperCase()); break;
//				case MOVEMENT: this.subtype = ItemTypes.MOVEMENTtype.valueOf(dict.getString("subtype").toUpperCase()); break;
//				case CONSUMABLE: this.subtype = ItemTypes.CONSUMABLEtype.valueOf(dict.getString("subtype").toUpperCase()); break;
//				case ACCESSORY: this.subtype = ItemTypes.ACCESSORYtype.valueOf(dict.getString("subtype").toUpperCase()); break;
//				case OTHER: this.subtype = ItemTypes.OTHERtype.valueOf(dict.getString("subtype").toUpperCase()); break;
//			}
//			job = "value"; this.value = dict.getInteger("value");
//			job = "level"; this.level = dict.getInteger("level");
//			job = "size"; this.size = dict.getInteger("size");
//			this.stacks = -1;
//			this.equiped = false;
//			job = "weight"; this.weight = dict.getDouble("weight");
//			job = "usable"; this.usable = dict.getBoolean("usable");
//			job = "stackable"; this.stackable = dict.getBoolean("stackable");
//			job = "shoppable"; this.shoppable = dict.getBoolean("shoppable");
//			job = "dropable"; this.droppable = dict.getBoolean("dropable");
//			job = "onEquipEffects"; 
//			if(dict.exists("onEquipEffects")) {
//				DataDictionary eEffects = dict.getSubArray("onEquipEffects");
//				for(int i=0; i<eEffects.getArraySize(); i++) {
//					this.onEquipEffects.add(GlobalEffectList.globalList.createEffect(eEffects.getSubDictionary(i)));
//				}
//			}
//			job = "onUseEffects"; 
//			if(dict.exists("onUseEffects")) {
//				DataDictionary uEffects = dict.getSubArray("onUseEffects");
//				for(int i=0; i<uEffects.getArraySize(); i++) {
//					this.onUseEffects.add(GlobalEffectList.globalList.createEffect(uEffects.getSubDictionary(i)));
//				}
//			}
//			job = "onEquipBonuses"; 
//			if(dict.exists("onEquipBonuses")) {
//				DataDictionary eBonus = dict.getSubArray("onEquipBonuses");
//				for(int i=0; i<eBonus.getArraySize(); i++) {
//					this.onEquipBonuses.add(StatBonus.create(eBonus.getSubDictionary(i)));
//				}
//			}
//		}
//		
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("ITEM-LOAD", ("BaseItem.load, Item=\"" + itemName + "\" at=" + job), err);
//		}
//		return this;
//	}


}
