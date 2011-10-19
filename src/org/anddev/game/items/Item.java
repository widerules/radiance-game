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

import org.anddev.program.variables.EnumValue;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;


public abstract class Item extends ItemBase{	

	
	
	
	
	///////////////////////////
	//	Member variables
	///////////////////////////
	protected String name;
	protected ITMtype type;				// itype
	protected EnumValue subtype;		// item subtype
	protected int value;				// normal cost
	protected int ID;					// serial number after sorting (temporary)
	protected Boolean droppable;		// items that can be dropped (by chance, not explicitly)
	protected Boolean shoppable;		// items that can be found at shops
	protected int levelRequired;		// level required to use or game level (for shops)
	
	
	
	
	
	
	////////////////////////
	//	Constructor
	////////////////////////
	public Item(){
		name = "noname"; 
		type = ITMtype.MISC; 
		value = 0;
	}
	public Item(Node eitem) {
		name = XMLutil.getFirstElementValue(eitem, "name");
		type = ITMtype.values()[Integer.parseInt(XMLutil.getFirstElementValue(eitem, "type"))];
		value = Integer.parseInt(XMLutil.getFirstElementValue(eitem, "value"));
		ID = Integer.parseInt(XMLutil.getFirstElementValue(eitem, "ID"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(eitem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(eitem, "shop"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(eitem, "level"));
	}
	
	///////////////////////
	//	Getters
	///////////////////////
	public String getName() { return name; }
	public ITMtype getType() { return type; }
	public int getValue() { return value; }
	public int getID()  { return ID; }
	public Boolean getDroppable()  { return droppable; }
	public Boolean getShoppable()  { return shoppable; }
	public int getLevelRequired()  { return levelRequired; }
	
	
	///////////////////////
	//	public methods
	///////////////////////
	
	public String toString() {
		return this.name + " " + this.type + "(" + this.levelRequired + ") " + this.value+"$";
	}
	
	////////////////////
	//	Master Setter
	////////////////////
	public abstract void load(Node iItem) throws Exception;
	
	
	
}