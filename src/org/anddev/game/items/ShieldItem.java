package org.anddev.game.items;

import org.anddev.engine.variables.EnumValue;
import org.anddev.game.combat.Damage;
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
