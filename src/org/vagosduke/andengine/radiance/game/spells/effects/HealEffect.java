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
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect.effectTargets;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.game.template.Template.*;
import org.vagosduke.andengine.radiance.program.loader.GameEffectLoader;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.w3c.dom.Node;

public class HealEffect implements BaseEffect{
	/**
	 * Restores a number of Hit Points to the targets
	 * Heal is calculated as 
	 * (BasePower) +
	 * (plus or minus a random percent between 0% and basePowerVariation of BasePower) +
	 * (magicPowerModifier percent the caster's AbilityPower attribute)
	 */
	
	private double basePower;
	private double basePowerVariation;
	private double magicPowerModifier;
	private effectTargets targets;
	private TemplateInfo effectTemplate;
	
	///////////////////////////////
	//	Constructor
	///////////////////////////////
	public HealEffect() {
		this.basePower = 1.0;
		this.basePowerVariation = 0.0;
		this.magicPowerModifier = 0.0;
		this.targets = effectTargets.ALLY;
		effectTemplate = new TemplateInfo();
	}
	public HealEffect(double basePower, double basePowerVariation, double magicPowerModifier, 
			effectTargets targets, TemplateInfo effectTemplate) {
		this.basePower = basePower;
		this.basePowerVariation = basePowerVariation;
		this.magicPowerModifier = magicPowerModifier;
		this.targets = targets;
		this.effectTemplate = effectTemplate;
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
		double percent = ((generator.nextDouble()*2.0)/this.basePowerVariation) - this.basePowerVariation;
		int healFinal = (int) Math.round( this.basePower + ((double)this.basePower) * percent);
		if(caster != null) {
			healFinal += caster.getAttributesModule().getAbility() * this.magicPowerModifier;
		}
		target.getCombatModule().addHP(healFinal);
	}

	@Override
	public effectTargets getTargets() { return this.targets; }
	@Override
	public TemplateInfo getEffectTemplate() { return this.effectTemplate; }
	public static EffectFactory getFactory() { return new EffectFactory() {
		public BaseEffect createEffect(DataDictionary dict) throws Exception {
			return GameEffectLoader.loadHealEffect(dict);
		}}; 
	}
	
	
	
	/////////////////////////////////
	//	Private Functions
	/////////////////////////////////
//	private void loadFromDict(DataDictionary dict) throws Exception {
//		String job = "N/A";
//		try {
//			job = "power"; this.basePower = dict.getDouble("power");
//			job = "powerVar"; this.basePowerVariation = dict.getDouble("powerVar");
//			job = "abilityPowerMod"; this.magicPowerModifier = dict.getDouble("abilityPowerMod");
//			job = "targets"; if(dict.exists("targets")) { this.targets = effectTargets.valueOf(dict.getString("targets").toUpperCase()); }
//			job = "effectTemplate"; this.effectTemplate.load(dict.getSubDictionary("effectTemplate"));
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("EFFECT-ERROR", ("HealEffect.load, at=" + job), err);
//			throw err;
//		}
//	}
//	private void loadFromNode(Node neffect) throws Exception {
//		this.basePower = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "power"));
//		this.basePowerVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "powerVar"));
//		this.magicPowerModifier = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "abilityPowerMod"));
//		this.targets = effectTargets.valueOf(XMLutil.getFirstElementValue(neffect, "targets"));
//		this.effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(neffect, "effectTemplate"));
//		this.effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "in"));
//		this.effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "out"));
//	}
	
	
}
