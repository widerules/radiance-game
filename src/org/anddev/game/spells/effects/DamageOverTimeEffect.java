package org.anddev.game.spells.effects;

import java.util.Random;

import org.anddev.engine.variables.EnumValue;
import org.anddev.game.character.Character;
import org.anddev.game.combat.Damage;
import org.anddev.game.condition.DamageOverTime;
import org.anddev.game.constants.Types.templateType;
import org.anddev.game.template.Template.TemplateInfo;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;

public class DamageOverTimeEffect implements BaseEffect{

	private String condName;
	private String condDescription;
	private float basePower;
	private float basePowerVariation;
	private float magicModifierPower;
	private EnumValue dmgType;
	private float baseDuration;
	private float baseDurationVariation;
	private float magicModifierDuration;
	
	private TemplateInfo effectTemplate;
	
	
	
	
	///////////////////////////////
	//	Constructor
	///////////////////////////////
	public DamageOverTimeEffect() {
		this.basePower = 1;
		this.basePowerVariation = 0;
		this.magicModifierPower = 0;
		this.dmgType = Damage.DMGtype.defaultValue();
		this.baseDuration = 1;
		this.baseDurationVariation = 0;
		this.magicModifierDuration = 0;
		this.effectTemplate = new TemplateInfo();
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
		 * Applies a Damage-over-time condition to the target
		 * Damage per round is calculated as 
		 * (BasePower) +
		 * (+ or - a random percent between 0% and basePowerVariation of BasePower) +
		 * (magicPowerModifier percent the caster's Magic attribute)
		 * 
		 * round duration is calculated as
		 * (Base Duration) +
		 * (+ or - a random percent between 0% and baseDurationVariation of baseDuration) +
		 * (
		 */
		Random generator = new Random();
		double damageVar = ((generator.nextDouble()*2.0)/this.basePowerVariation) - this.basePowerVariation;
		int damageFinal = (int) Math.round( this.basePower + 
				( caster.getSkillsManager().getMagic() * this.magicModifierPower ) + 
				( ((double)this.basePower) * damageVar) );
		double durationVar = ((generator.nextDouble()*2.0)/this.baseDurationVariation) - this.baseDurationVariation;
		int durationFinal = (int) Math.round( this.baseDuration + 
				( caster.getSkillsManager().getMagic() * this.magicModifierDuration ) + 
				( ((double)this.baseDuration) * durationVar) );
		DamageOverTime cond = new DamageOverTime(this.condName, this.condDescription, damageFinal, durationFinal, this.dmgType);
		
		target.getConditionManager().addCondition(cond);	
	}

	@Override
	public TemplateInfo getEffectTemplate() {
		return this.effectTemplate;
	}
	
	
	
	
	/////////////////////////////////
	//	Private Functions
	/////////////////////////////////
	private void loadFromNode(Node neffect) throws Exception {
		this.condName = XMLutil.getFirstElementValue(neffect, "name");
		this.condDescription = XMLutil.getFirstElementValue(neffect, "description");
		this.basePower = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "power"));
		this.baseDuration = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "duration"));
		this.basePowerVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "powerVar"));
		this.baseDurationVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "durationVar"));
		this.magicModifierPower = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "magicModPow"));
		this.magicModifierDuration = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "magicModDur"));;
		this.dmgType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(neffect, "dmgType"));
		this.effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(neffect, "effectTemplate"));
		this.effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "in"));
		this.effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "out"));
	}
	
}