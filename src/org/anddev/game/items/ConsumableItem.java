package org.anddev.game.items;

import org.anddev.engine.variables.EnumValue;
import org.anddev.game.combat.Damage;
import org.anddev.game.constants.Types.templateType;
import org.anddev.game.template.Template.TemplateInfo;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;

public class ConsumableItem extends Item {
	
	/**	ConsumableItem is an Inventory Item with methods to create 
	 * 	this item type from a predetermined xml format
	 *  
	 * 
	 */
	
	protected CONtype effectType;
	protected EnumValue effectDamageType;
	protected TemplateInfo effectTemplate = new TemplateInfo();
	protected TemplateInfo rangeTemplate = new TemplateInfo();
	protected int potency;
	protected int duration;
	protected int quantity;
	
	
	
	//////////////////////
	//	Constructors
	//////////////////////
	public static ConsumableItem newItem(Node eitem) throws Exception {
		return new ConsumableItem(eitem);
	}
	public ConsumableItem() {
		type = ITMtype.CONSUMABLE;
		this.effectType = CONtype.HEAL;
		this.effectDamageType = Damage.DMGtype.defaultValue();
		this.potency = 0;
		this.duration = 0;
		this.quantity = 0;
		this.levelRequired = 0;
	}
	public ConsumableItem(Node cItem) {
		name = XMLutil.getFirstElementValue(cItem, "name");
		type = ITMtype.CONSUMABLE;
		//type = iType.values()[Integer.parseInt(cItem.getFirstChildElement("type").getValue())];
		value = Integer.parseInt(XMLutil.getFirstElementValue(cItem, "value"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(cItem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(cItem, "shop"));
		effectType = CONtype.valueOf(XMLutil.getFirstElementValue(cItem, "effect"));
		effectDamageType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(cItem, "damageType"));
		rangeTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(cItem, "range"));
		rangeTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(cItem, "range", "out"));
		rangeTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(cItem, "range", "out"));
		effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(cItem, "effectTemplate"));
		effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(cItem, "effectTemplate", "in"));
		effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(cItem, "effectTemplate", "out"));
		potency = Integer.parseInt(XMLutil.getFirstElementValue(cItem, "potency"));
		duration = Integer.parseInt(XMLutil.getFirstElementValue(cItem, "duration"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(cItem, "level"));
		quantity = 0;		
	}
	public static ConsumableItem create(ConsumableItem reference) {
		ConsumableItem temp = new ConsumableItem();
		temp.name = reference.name;
		temp.type = reference.type;
		temp.value = reference.value;
		temp.effectType = reference.effectType;
		temp.effectDamageType = reference.effectDamageType;
		temp.potency = reference.potency;
		temp.duration = reference.duration;
		temp.levelRequired = reference.levelRequired;
		temp.quantity = 1;
		
		return temp;
	}
	
	
	///////////////////////
	//	Master Setter
	///////////////////////
	@Override
	public void load(Node cItem) throws Exception {
		name = XMLutil.getFirstElementValue(cItem, "name");
		type = ITMtype.CONSUMABLE;
		//type = iType.values()[Integer.parseInt(cItem.getFirstChildElement("type").getValue())];
		value = Integer.parseInt(XMLutil.getFirstElementValue(cItem, "value"));
		droppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(cItem, "droppable"));
		shoppable = Boolean.parseBoolean(XMLutil.getFirstElementValue(cItem, "shop"));
		effectType = CONtype.valueOf(XMLutil.getFirstElementValue(cItem, "effect"));
		effectDamageType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(cItem, "damageType"));
		rangeTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(cItem, "range"));
		rangeTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(cItem, "range", "out"));
		rangeTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(cItem, "range", "out"));
		effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(cItem, "effectTemplate"));
		effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(cItem, "effectTemplate", "in"));
		effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(cItem, "effectTemplate", "out"));
		potency = Integer.parseInt(XMLutil.getFirstElementValue(cItem, "potency"));
		duration = Integer.parseInt(XMLutil.getFirstElementValue(cItem, "duration"));
		levelRequired = Integer.parseInt(XMLutil.getFirstElementValue(cItem, "level"));
		quantity = 0;
	}
	
	
	
	
	///////////////////////
	//	Getters
	///////////////////////
	public CONtype getEffectType() { return this.effectType; }
	public EnumValue getEffectDamageType() { return this.effectDamageType; }
	public TemplateInfo getEffectTemplate() { return this.effectTemplate; }
	public TemplateInfo getRangeTemplate() { return this.rangeTemplate; }
	public int getPotency() { return this.potency; }
	public int getDuration() {return this.duration; }
	public int getQuantity() { return this.quantity; }
	
	
	public String toString() {
		String appen = "";
		if(this.quantity > 0) { appen = (" q=" + this.quantity); }
		return (super.toString() + appen);
	}
}
