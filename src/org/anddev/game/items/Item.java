package org.anddev.game.items;

import org.anddev.util.XMLutil;
import org.w3c.dom.Node;


public abstract class Item extends ItemBase{	

	
	
	
	
	///////////////////////////
	//	Member variables
	///////////////////////////
	protected String name;
	protected ITMtype type;				// itype
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