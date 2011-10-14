package org.anddev.game.items;

import org.anddev.util.XMLutil;
import org.w3c.dom.Node;

public class MiscItem extends Item{

	protected int quantity;	// amount of damageType damage type
	
	
	
	//////////////////////
	//	Constructors
	//////////////////////
	public static MiscItem newItem(Node eitem) throws Exception {
		return new MiscItem(eitem);
	}
	public MiscItem() {
		this.type = ITMtype.MISC;
		this.levelRequired = 0;
		this.quantity = 0;
	}
	public MiscItem(Node oItem) {
		name = XMLutil.getFirstElementValue(oItem, "name");
		type = ITMtype.valueOf(XMLutil.getFirstElementValue(oItem, "type"));
		if(type != ITMtype.MISC && type != ITMtype.QUEST){
			System.out.println("WARNING (item creation from document)"+
					": item " + name + "does not have correct type (MISC or QUEST)"); }
		value = Integer.parseInt(XMLutil.getFirstElementValue(oItem, "value"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(oItem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(oItem, "shop"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(oItem, "level"));
		quantity = 0;
	}
	
	///////////////////////
	//	Master Setter
	///////////////////////
	@Override
	public void load(Node oItem) throws Exception{
		name = XMLutil.getFirstElementValue(oItem, "name");
		type = ITMtype.valueOf(XMLutil.getFirstElementValue(oItem, "type"));
		if(type != ITMtype.MISC && type != ITMtype.QUEST){
			System.out.println("WARNING (item creation from document)"+
					": item " + name + "does not have correct type (MISC or QUEST)"); }
		value = Integer.parseInt(XMLutil.getFirstElementValue(oItem, "value"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(oItem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(oItem, "shop"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(oItem, "level"));
		quantity = 0;
	}
	
	
	
	
	///////////////////////
	//	Getters
	///////////////////////
	public int getQuantity() { return this.quantity; }
	
	public String toString() {
		String appen = "";
		if(this.quantity > 0) { appen = (" q=" + this.quantity); }
		return (super.toString() + appen);
	}
	
}
