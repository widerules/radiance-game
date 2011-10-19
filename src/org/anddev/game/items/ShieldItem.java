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

public class ShieldItem extends Item{
	protected int armor;				// amount of bonus armor/defense it confers
	protected float blockChance;		// percent chance to block damage
	protected EnumValue specialType;		// type of special damage it can resist
	protected float specialPercent;		// percent amount of damage from specialType it resists
	
	//////////////////////
	//	Constructors
	//////////////////////
	public static ShieldItem newItem(Node eitem) throws Exception {
		return new ShieldItem(eitem);
	}
	public ShieldItem() {
		this.armor = 0;
		this.blockChance = 0;
		this.specialType = Damage.DMGtype.defaultValue();
		this.specialPercent = 0;
		this.levelRequired = 0;
	}
	public ShieldItem(Node sItem) {
		name = XMLutil.getFirstElementValue(sItem, "name");
		type = ITMtype.SHIELD;
		value = Integer.parseInt(XMLutil.getFirstElementValue(sItem, "value"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(sItem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(sItem, "shop"));
		armor = Integer.parseInt(XMLutil.getFirstElementValue(sItem, "armor"));
		blockChance = Float.parseFloat(XMLutil.getFirstElementValue(sItem, "blockChance"));
		specialType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(sItem, "specialType"));
		specialPercent = Float.parseFloat(XMLutil.getFirstElementValue(sItem, "specialPercent"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(sItem, "level"));
	}
	
	
	///////////////////////
	//	Master Setter
	///////////////////////
	@Override
	public void load(Node sItem) throws Exception{
		name = XMLutil.getFirstElementValue(sItem, "name");
		type = ITMtype.SHIELD;
		value = Integer.parseInt(XMLutil.getFirstElementValue(sItem, "value"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(sItem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(sItem, "shop"));
		armor = Integer.parseInt(XMLutil.getFirstElementValue(sItem, "armor"));
		blockChance = Float.parseFloat(XMLutil.getFirstElementValue(sItem, "blockChance"));
		specialType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(sItem, "specialType"));
		specialPercent = Float.parseFloat(XMLutil.getFirstElementValue(sItem, "specialPercent"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(sItem, "level"));
	}
	
	
	
	
	///////////////////////
	//	Getters
	///////////////////////
	public int getArmor() { return this.armor; }
	public float getBlockChance() { return this.blockChance; }
	public EnumValue getSpecialType() { return this.specialType; }
	public float getSpecialPercent() {return this.specialPercent; }
	public int getLevelRequired() { return this.levelRequired; }
	
	
	public String toString() {
		return (super.toString() + " a=" + this.armor + " b=" + this.blockChance + "%");
	}
	
	
	
}
