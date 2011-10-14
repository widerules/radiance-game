package org.anddev.game.items;

import org.anddev.game.combat.Damage;
import org.anddev.engine.variables.EnumValue;
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
