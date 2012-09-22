package org.vagosduke.andengine.radiance.util.beanClasses;


import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.spells.effects.DamageEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect.effectTargets;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;

public class BeanDamageEffect implements BeanBaseEffect{
	
	private double basePower;
	private double basePowerVariation;
	private double abilityPowerModifier;
	private EnumValue damageType;
	private effectTargets targets;
	private BeanTemplateInfo effectTemplate;
	
	
	public BeanDamageEffect() {
		this.basePower = 1;
		this.basePowerVariation = 0;
		this.abilityPowerModifier = 0;
		this.damageType = Damage.DMGtype.defaultValue();
		this.targets = effectTargets.ENEMY;
		this.effectTemplate = new BeanTemplateInfo();
	}


	
	public double getBasePower() {
		return basePower;
	}
	public double getBasePowerVariation() {
		return basePowerVariation;
	}
	public double getAbilityPowerModifier() {
		return abilityPowerModifier;
	}
	public String getDamageType() {
		return damageType.toString();
	}
	public effectTargets getTargets() {
		return targets;
	}
	public BeanTemplateInfo getEffectTemplate() {
		return effectTemplate;
	}
	
	public void setBasePower(double basePower) {
		this.basePower = basePower;
	}
	public void setBasePowerVariation(double basePowerVariation) {
		this.basePowerVariation = basePowerVariation;
	}
	public void setAbilityPowerModifier(double magicPowerModifier) {
		this.abilityPowerModifier = magicPowerModifier;
	}
	public void setDamageType(String damageType) {
		this.damageType = Damage.DMGtype.valueOf(damageType);
	}
	public void setTargets(effectTargets targets) {
		this.targets = targets;
	}
	public void setEffectTemplate(BeanTemplateInfo effectTemplate) {
		this.effectTemplate = effectTemplate;
	}
	
	
	
	
	
	
	@Override
	public DamageEffect extract() {
		return new DamageEffect(this.basePower, this.basePowerVariation, this.abilityPowerModifier, this.damageType,
				this.targets, this.effectTemplate.extract());
	}
}
