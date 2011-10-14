package org.anddev.game.spells.effects;

import java.util.Random;

import org.anddev.engine.variables.EnumValue;
import org.anddev.game.character.Character;
import org.anddev.game.combat.Damage;
import org.anddev.game.constants.Types.templateType;
import org.anddev.game.template.Template.TemplateInfo;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;

public class HealEffect implements BaseEffect{
	
	private float basePower;
	private float basePowerVariation;
	private float magicPowerModifier;
	private EnumValue dmgType;
	private TemplateInfo effectTemplate;
	
	///////////////////////////////
	//	Constructor
	///////////////////////////////
	public HealEffect() {
		this.basePower = 1;
		this.basePowerVariation = 0;
		this.magicPowerModifier = 0;
		this.dmgType = Damage.DMGtype.defaultValue();
		effectTemplate = new TemplateInfo();
	}
	@Override
	public BaseEffect create(Node neffect) throws Exception {
		loadFromNode(neffect);
		return this;
	}

	
	
	
	
	////////////////////////////////
	//	Public Functions
	////////////////////////////////
	@Override
	public void apply(Character caster, Character target) {
		/**
		 * Heals Hit Points to the target
		 * Damage is calculated as 
		 * (BasePower) +
		 * (+ or - a random percent between 0% and basePowerVariation of BasePower) +
		 * (magicPowerModifier percent the caster's Magic attribute)
		 */
		Random generator = new Random();
		double percent = ((generator.nextDouble()*2.0)/this.basePowerVariation) - this.basePowerVariation;
		int damageFinal = (int) Math.round( this.basePower + 
				( caster.getSkillsManager().getMagic() * this.magicPowerModifier ) + 
				( ((double)this.basePower) * percent) );
		Damage dmg = Damage.create(damageFinal, dmgType);
		
		target.doDamage(dmg);
	}

	@Override
	public TemplateInfo getEffectTemplate() {
		return this.effectTemplate;
	}
	
	
	
	
	/////////////////////////////////
	//	Private Functions
	/////////////////////////////////
	private void loadFromNode(Node neffect) throws Exception {
		this.basePower = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "power"));
		this.basePowerVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "powerVar"));
		this.magicPowerModifier = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "magicMod"));
		this.dmgType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(neffect, "dmgType"));
		this.effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(neffect, "effectTemplate"));
		this.effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "in"));
		this.effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "out"));
	}
	
}