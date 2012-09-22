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

package org.vagosduke.andengine.radiance.game.spells.effects;

import java.util.Random;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.condition.DamageOverTime;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect.effectTargets;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.game.template.Template.*;
import org.vagosduke.andengine.radiance.program.loader.GameEffectLoader;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.w3c.dom.Node;

public class DamageOverTimeEffect implements BaseEffect{
	/**
	 * Applies a Damage-over-time condition to the target
	 * Damage per round is calculated as 
	 * (BasePower) +
	 * (magicModifierPower percent the caster's AbilityPower attribute)
	 * (+ or - a random percent between 0% and basePowerVariation of BasePower) +
	 * 
	 * round duration is calculated as
	 * (Base Duration) +
	 * (+ or - a random percent between 0% and baseDurationVariation of baseDuration) +
	 * (magicModifierDuration percent the caster's AbilityPower attribute)
	 */

	private String condName;
	private String condDescription;
	private double basePower;
	private double basePowerVariation;
	private double magicPowerModifier;
	private EnumValue dmgType;
	private double baseDuration;
	private double baseDurationVariation;
	private double magicDurationModifier;
	
	private effectTargets targets;
	private TemplateInfo effectTemplate;
	
	private boolean stackable;
	private boolean unique;
	
	
	
	///////////////////////////////
	//	Constructor
	///////////////////////////////
	public DamageOverTimeEffect() {
		this.condName = "NOCOND";
		this.condDescription = "NOCONDDESC";
		this.basePower = 1.0;
		this.basePowerVariation = 0.0;
		this.magicPowerModifier = 0.0;
		this.dmgType = Damage.DMGtype.defaultValue();
		this.baseDuration = 1.0;
		this.baseDurationVariation = 0.0;
		this.magicDurationModifier = 0.0;
		this.targets = effectTargets.ENEMY;
		this.effectTemplate = new TemplateInfo();
		this.stackable = false;
		this.unique = false;
	}
	public DamageOverTimeEffect(String icondName, String icondDescription, double ibasePower, 
			double ibasePowerVariation, double imagicPowerModifier, EnumValue idmgType, 
			double ibaseDuration, double ibaseDurationVariation, double imagicDurationModifier,
			effectTargets itargets, TemplateInfo ieffectTemplate, boolean istackable, boolean iunique) {
		this.condName = icondName;
		this.condDescription = icondDescription;
		this.basePower = ibasePower;
		this.basePowerVariation = ibasePowerVariation;
		this.magicPowerModifier = imagicPowerModifier;
		this.dmgType = idmgType;
		this.baseDuration = ibaseDuration;
		this.baseDurationVariation = ibaseDurationVariation;
		this.magicDurationModifier = imagicDurationModifier;
		this.targets = itargets;
		this.effectTemplate = ieffectTemplate;
		this.stackable = istackable;
		this.unique = iunique;
	}
//	public BaseEffect load(DataDictionary dict) throws Exception {
//		loadFromDict(dict);
//		return this;
//	}
	
	
	
	
	
	////////////////////////////////
	//	Public Functions
	////////////////////////////////
	@Override
	public void apply(Character caster, Character target) {
		Random generator = new Random();
		double damageVar = ((generator.nextDouble()*2.0)/this.basePowerVariation) - this.basePowerVariation;
		int damageFinal = (int) Math.round( this.basePower + ((double)this.basePower) * damageVar);
		if(caster != null) {
			damageFinal += caster.getAttributesModule().getAbility() * this.magicPowerModifier;
		}
		
		double durationVar = ((generator.nextDouble()*2.0)/this.baseDurationVariation) - this.baseDurationVariation;
		int durationFinal = (int) Math.round( this.baseDuration + ((double)this.baseDuration) * durationVar);
		if(caster != null) {
			durationFinal += caster.getAttributesModule().getAbility() * this.magicDurationModifier;
		}
		
		DamageOverTime cond = new DamageOverTime(this.condName, this.condDescription, damageFinal, 
				caster.getAttributesModule().getAbility(), durationFinal, this.dmgType, this.stackable, this.unique);
		target.getConditionModule().addCondition(cond);	
	}

	@Override
	public effectTargets getTargets() { return this.targets; }
	@Override
	public TemplateInfo getEffectTemplate() { return this.effectTemplate; }
	public static EffectFactory getFactory() { return new EffectFactory() {
		public BaseEffect createEffect(DataDictionary dict) throws Exception {
			return GameEffectLoader.loadDamageOverTimeEffect(dict);
		}}; 
	}
	
	
	
	/////////////////////////////////
	//	Private Functions
	/////////////////////////////////
//	private void loadFromDict(DataDictionary dict) throws Exception {
//		String job = "N/A";
//		try {
//			job = "ConditionName"; this.condName = dict.getString("ConditionName");
//			job = "description"; this.condDescription = dict.getString("description");
//			job = "stackable"; if(dict.exists("stackable")) {this.unique = dict.getBoolean("stackable");}
//			job = "unique"; if(dict.exists("unique")) {this.unique = dict.getBoolean("unique");}
//			job = "power"; this.basePower = dict.getDouble("power");
//			job = "duration"; this.baseDuration = dict.getDouble("duration");
//			job = "powerVar"; this.basePowerVariation = dict.getDouble("powerVar");
//			job = "durationVar"; this.baseDurationVariation = dict.getDouble("durationVar");
//			job = "abilityPowerMod"; this.magicPowerModifier = dict.getDouble("abilityPowerMod");
//			job = "abilityDurationMod"; this.magicDurationModifier = dict.getDouble("abilityDurationMod");
//			job = "damageType"; this.dmgType = Damage.DMGtype.valueOf(dict.getString("damageType"));
//			job = "targets"; if(dict.exists("targets")) { this.targets = effectTargets.valueOf(dict.getString("targets").toUpperCase()); }
//			job = "effectTemplate"; this.effectTemplate.load(dict.getSubDictionary("effectTemplate"));
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("EFFECT-ERROR", ("DamageOverTimeEffect.load, at=" + job), err);
//			throw err;
//		}
//	}
//	private void loadFromNode(Node neffect) throws Exception {
//		this.condName = XMLutil.getFirstElementValue(neffect, "name");
//		this.condDescription = XMLutil.getFirstElementValue(neffect, "description");
//		this.stackable = Boolean.parseBoolean(XMLutil.getFirstElementValue(neffect, "stackable"));
//		this.unique = Boolean.parseBoolean(XMLutil.getFirstElementValue(neffect, "unique"));
//		this.basePower = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "power"));
//		this.baseDuration = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "duration"));
//		this.basePowerVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "powerVar"));
//		this.baseDurationVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "durationVar"));
//		this.magicPowerModifier = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "abilityPowerMod"));
//		this.magicDurationModifier = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "abilityDurationMod"));;
//		this.dmgType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(neffect, "dmgType"));
//		this.targets = effectTargets.valueOf(XMLutil.getFirstElementValue(neffect, "targets"));
//		this.effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(neffect, "effectTemplate"));
//		this.effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "in"));
//		this.effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "out"));
//	}
	
}