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

import org.anddev.game.combat.Damage;
import org.anddev.program.variables.EnumValue;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;


public class ArmorItem extends Item{

	protected int armor;					// amount of bonus armor/defense it confers
	protected EnumValue armorType;
	protected EnumValue specialType;		// type of special damage it can resist
	protected float specialPercent;			// percent amount of damage from specialType it resists
	
	//////////////////////
	//	Constructors
	//////////////////////
	public static ArmorItem newItem(Node eitem) throws Exception {
		return new ArmorItem(eitem);
	}
	public ArmorItem() {
		this.type = ITMtype.ARMOR;
		this.armor = 0;
		this.specialType = Damage.DMGtype.defaultValue();
		this.specialPercent = 0;
		this.levelRequired = 0;
	}
	public ArmorItem(Node aItem) throws Exception {
		name = XMLutil.getFirstElementValue(aItem, "name");
		type = ITMtype.valueOf(XMLutil.getFirstElementValue(aItem, "type"));
		value = Integer.parseInt(XMLutil.getFirstElementValue(aItem, "value"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(aItem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(aItem, "shop"));
		armor = Integer.parseInt(XMLutil.getFirstElementValue(aItem, "armor"));
		armorType = ARMtype.valueOf(XMLutil.getFirstElementValue(aItem, "armorType"));
		specialType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(aItem, "specialType"));
		specialPercent = Float.parseFloat(XMLutil.getFirstElementValue(aItem, "specialPercent"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(aItem, "level"));
	}
	
	///////////////////////
	//	Master Setter
	///////////////////////
	@Override
	public void load(Node aItem) throws Exception {
		name = XMLutil.getFirstElementValue(aItem, "name");
		type = ITMtype.valueOf(XMLutil.getFirstElementValue(aItem, "type"));
		value = Integer.parseInt(XMLutil.getFirstElementValue(aItem, "value"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(aItem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(aItem, "shop"));
		armor = Integer.parseInt(XMLutil.getFirstElementValue(aItem, "armor"));
		armorType = ARMtype.valueOf(XMLutil.getFirstElementValue(aItem, "armorType"));
		specialType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(aItem, "specialType"));
		specialPercent = Float.parseFloat(XMLutil.getFirstElementValue(aItem, "specialPercent"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(aItem, "level"));
	}
	
	
	
	
	
	///////////////////////
	//	Getters
	///////////////////////
	public int getArmor() { return this.armor; }
	public EnumValue getArmType() { return this.armorType; }
	public EnumValue getSpecialType() { return this.specialType; }
	public float getSpecialPercent() {return this.specialPercent; }

	
	public String toString() {
		return (super.toString() + " a=" + this.armor + " b=");
	}
	
	
}
