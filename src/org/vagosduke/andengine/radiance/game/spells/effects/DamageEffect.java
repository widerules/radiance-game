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

import java.util.ArrayList;
import java.util.Random;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.combat.SpellAttack;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.program.loader.GameEffectLoader;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class DamageEffect implements BaseEffect{
	/**
	 * Inflicts damage to targets
	 * Damage is calculated as 
	 * (BasePower) +
	 * (+ or - a random percent between 0% and basePowerVariation of BasePower) +
	 * (magicPowerModifier percent the caster's AbilityPower attribute)
	 */

	private double basePower;
	private double basePowerVariation;
	private double abilityPowerModifier;
	private EnumValue damageType;
	private effectTargets targets;
	private TemplateInfo effectTemplate;
	
	///////////////////////////////
	//	Constructor
	///////////////////////////////
	public DamageEffect() {
		this.basePower = 1;
		this.basePowerVariation = 0;
		this.abilityPowerModifier = 0;
		this.damageType = Damage.DMGtype.defaultValue();
		this.targets = effectTargets.ENEMY;
		this.effectTemplate = new TemplateInfo();
	}
	public DamageEffect(double ibasePower,  double ibasePowerVariation, double imagicPowerModifier, EnumValue idmgType,
			effectTargets itargets, TemplateInfo ieffectTemplate) {
		this.basePower = ibasePower;
		this.basePowerVariation = ibasePowerVariation;
		this.abilityPowerModifier = imagicPowerModifier;
		this.damageType = idmgType;
		this.targets = itargets;
		effectTemplate = ieffectTemplate;
	}
//	@Override
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
		double percent = ((generator.nextDouble()*2.0)/this.basePowerVariation) - this.basePowerVariation;
		int damageFinal = (int) Math.round( this.basePower * (1.0 + percent));
		if(caster != null) {
			damageFinal += ((double)caster.getAttributesModule().getAbility()) * this.abilityPowerModifier;
		}
		
		target.getCombatModule().doSpellDefend(this.makeDamage(damageFinal, caster), caster);
	}

	@Override
	public effectTargets getTargets() {
		return this.targets;
	}
	@Override
	public TemplateInfo getEffectTemplate() {
		return this.effectTemplate;
	}
	public static EffectFactory getFactory() { return new EffectFactory() {
		public BaseEffect createEffect(DataDictionary dict) throws Exception {
			return GameEffectLoader.loadDamageEffect(dict);
		}}; 
	}
	
	
	
	
	
	/////////////////////
	//	Private Methods
	/////////////////////
	SpellAttack makeDamage(int amount, Character caster) {
		ArrayList<Damage> dmgs = new ArrayList<Damage>();
		dmgs.add(new Damage(amount, this.damageType));
		return new SpellAttack(this, dmgs, caster);
	}
	
	
	
	/////////////////////////////////
	//	Private Loaders
	/////////////////////////////////
//	private void loadFromDict(DataDictionary dict) throws Exception {
//		String job = "N/A";
//		try {
//			job = "power"; this.basePower = dict.getDouble("power");
//			job = "powerVar"; this.basePowerVariation = dict.getDouble("powerVar");
//			job = "abilityPowerMod"; this.magicPowerModifier = dict.getDouble("abilityPowerMod");
//			job = "damageType"; this.dmgType = Damage.DMGtype.valueOf(dict.getString("damageType").toUpperCase());
//			job = "targets"; if(dict.exists("targets")) { this.targets = effectTargets.valueOf(dict.getString("targets").toUpperCase()); }
//			job = "effectTemplate"; this.effectTemplate.load(dict.getSubDictionary("effectTemplate"));
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("EFFECT-ERROR", ("DamageEffect.load, at=" + job), err);
//			throw err;
//		}
//			
//	}
//	private void loadFromNode(Node neffect) throws Exception {
//		String job = "N/A";
//		try {
//			job = "power"; this.basePower = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "power"));
//			job = "powerVar"; this.basePowerVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "powerVar"));
//			job = "abilityPowerMod"; this.magicPowerModifier = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "abilityPowerMod"));
//			job = "damageType"; this.dmgType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(neffect, "damageType"));
//			job = "targets"; this.targets = effectTargets.valueOf(XMLutil.getFirstElementValue(neffect, "targets"));
//			job = "effectTemplate"; this.effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(neffect, "effectTemplate"));
//			this.effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "in"));
//			this.effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "out"));
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("EFFECT-ERROR", ("DamageEffect.load, at=" + job), err);
//			throw err;
//		}
//	}
	
}
