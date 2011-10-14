package org.anddev.game.items;

import org.anddev.engine.variables.EnumValue;
import org.anddev.game.combat.Damage;
import org.anddev.game.constants.Types.templateType;
import org.anddev.game.template.Template.TemplateInfo;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;

public class WeaponItem extends Item{
	
	protected int damage;	// amount of base damage type
	protected TemplateInfo rangeTemplate = new TemplateInfo();
	protected EnumValue wepType;		// damage type
	protected EnumValue specialType;	// damage type
	protected float specialPercent;	// percent amount of damage dealt as piercing
	
	//////////////////////
	//	Constructors
	//////////////////////
	public static WeaponItem newItem(Node witem) throws Exception {
		return new WeaponItem(witem);
	}
	/*
	public WeaponItem() {
		this.type = ITMtype.W1H;
		this.damage = 0;
		this.specialType = Damage.DMGtype.defaultValue();
		this.specialPercent = 0;
		this.levelRequired = 0;
	}
	*/
	public WeaponItem(Node wItem) throws Exception{
		this.name = XMLutil.getFirstElementValue(wItem, "name");
		this.type = ITMtype.valueOf(XMLutil.getFirstElementValue(wItem, "type"));
		if(this.type != ITMtype.W1H && this.type != ITMtype.W2H){
			System.out.println("WARNING (item creation from document)"+
					": item " + name + "does not have correct type (W1H or W2H)");
			throw new Exception("xml read error."); }
		this.value = Integer.parseInt(XMLutil.getFirstElementValue(wItem, "value"));
		this.damage = Integer.parseInt(XMLutil.getFirstElementValue(wItem, "damage"));
		this.rangeTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(wItem, "range"));
		this.rangeTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(wItem, "range", "in"));
		this.rangeTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(wItem, "range", "out"));
		this.droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(wItem, "droppable"));
		this.shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(wItem, "shop"));
		this.wepType = WEPtype.valueOf(XMLutil.getFirstElementValue(wItem, "weaponType"));
		this.specialType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(wItem, "specialType"));
		this.specialPercent = Float.parseFloat(XMLutil.getFirstElementValue(wItem, "specialPercent"));
		this.levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(wItem, "level"));
	}
	
	
	///////////////////////
	//	Master Setter
	///////////////////////
	@Override
	public void load(Node wItem) throws Exception{
		this.name = XMLutil.getFirstElementValue(wItem, "name");
		this.type = ITMtype.valueOf(XMLutil.getFirstElementValue(wItem, "type"));
		if(this.type != ITMtype.W1H && this.type != ITMtype.W2H){
			System.out.println("WARNING (item creation from document)"+
					": item " + name + "does not have correct type (W1H or W2H)");
			throw new Exception("xml read error."); }
		this.value = Integer.parseInt(XMLutil.getFirstElementValue(wItem, "value"));
		this.damage = Integer.parseInt(XMLutil.getFirstElementValue(wItem, "damage"));
		this.rangeTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(wItem, "range"));
		this.rangeTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(wItem, "range", "in"));
		this.rangeTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(wItem, "range", "out"));
		this.droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(wItem, "droppable"));
		this.shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(wItem, "shop"));
		this.wepType = WEPtype.valueOf(XMLutil.getFirstElementValue(wItem, "weaponType"));
		this.specialType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(wItem, "specialType"));
		this.specialPercent = Float.parseFloat(XMLutil.getFirstElementValue(wItem, "specialPercent"));
		this.levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(wItem, "level"));
	}
	
	
	
	
	
	///////////////////////
	//	Getters
	///////////////////////
	public int getDamage() { return this.damage; }
	public TemplateInfo getRangeTemplate() { return this.rangeTemplate; }
	public EnumValue getWepType() { return this.wepType; }
	public EnumValue getSpecialType() { return this.specialType; }
	public float getSpecialPercent() {return this.specialPercent; }
	
	
	public String toString() {
		return super.toString() + " d=" + this.damage + "." + Damage.DMGtype.nameOf(this.wepType) +
		"(" + this.rangeTemplate.shape + "<"+this.rangeTemplate.rangeIn+"," + 
		this.rangeTemplate.rangeOut+">)";
	}
	
	
	
	
	
}
