package org.anddev.game.items;

import java.util.ArrayList;
import java.util.LinkedList;

import org.anddev.game.items.ItemBase.ITMtype;
import org.anddev.game.spells.effects.BaseEffect;
import org.anddev.program.config.Filepath;
import org.anddev.program.variables.EnumClass;
import org.anddev.program.variables.EnumValue;

import android.util.Log;

public class BaseItem implements Comparable<BaseItem>{

	private String name;
	private String description;
	private ITMtype type;
	private EnumValue subtype;
	private Integer level;
	private Boolean shoppable;
	private Boolean dropable;
	private ArrayList<BaseEffect> onEquipEffects;
	//private ArrayList<ItemBonus> onEquipBonus;
	
	
	
	
	
	
	
	
	////////////////////////////
	//	Public Functions
	////////////////////////////
	@Override
	public int compareTo(BaseItem another) {
		int ret = this.type.compareTo(another.type);
		if(ret == 0) {										// same type
			ret = this.subtype.getEnum().compare(this.subtype, another.subtype);
			if (ret == 0) {		// same subtype
				ret = this.level.compareTo(another.level);
				if(ret == 0) {							// same level
					ret = this.name.compareTo(another.name);
					return ret;
				}
				else { return ret; }
			}
			else { return ret; }
		}
		else { return ret; }
	}	
	
	
	
	
	
	//////////////////////
	//	Getters
	//////////////////////
	public String name() { return this.name; }
	public String description() { return this.description; }
	public ITMtype type() { return this.type; }
	public EnumValue subType() { return this.subtype; }
	public Integer level() { return this.level; }
	public Boolean shoppable() { return this.shoppable; }
	public Boolean dropable() { return this.dropable; }
	public ArrayList<BaseEffect> onEquipEffects() { return this.onEquipEffects; }
	//public ArrayList<ItemBonus> onEquipBonus() { return this.onEquipBonus; }
}
