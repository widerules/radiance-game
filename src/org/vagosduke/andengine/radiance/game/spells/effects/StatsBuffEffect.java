package org.vagosduke.andengine.radiance.game.spells.effects;

import java.util.ArrayList;
import java.util.Random;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.character.AttributesModule.Attributes;
import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.condition.StatsBuff;
import org.vagosduke.andengine.radiance.game.items.StatBonus;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect.effectTargets;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.game.template.Template.*;
import org.vagosduke.andengine.radiance.program.loader.GameEffectLoader;
import org.vagosduke.andengine.radiance.program.loader.GameMiscLoader;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.w3c.dom.Node;

public class StatsBuffEffect implements BaseEffect {
	/**
	 * Buffs or drains target of one or more stats.
	 * The bonus or penalty is calculated as
	 * (BasePower) +
	 * (+ or - a random percent between 0% and basePowerVariation of BasePower) +
	 * (magicPowerModifier percent the caster's AbilityPower attribute)
	 */
	
	
	public static class BuffElement {
		protected double basePower;
		protected double basePowerVariation;
		protected double magicPowerModifier;
		protected Attributes statName;
		//private double amount;
		
		
		public BuffElement() {
			basePower = 0.0;
			basePowerVariation = 0.0;
			magicPowerModifier = 0.0;
			statName = Attributes.ABILITY;
		}	
		public BuffElement(double basePower, double basePowerVariation,
				double magicPowerModifier, Attributes statName) {
			this.basePower = basePower;
			this.basePowerVariation = basePowerVariation;
			this.magicPowerModifier = magicPowerModifier;
			this.statName = statName;
		}

//		public BuffElement load(DataDictionary dict) throws Exception {
//			String job = "N/A";
//			try {
//				job = "stat"; this.statName = charScores.valueOf(dict.getString("stat").toUpperCase());
//				job = "power"; this.basePower = dict.getDouble("power");
//				job = "powerVar"; this.basePowerVariation = dict.getDouble("powerVar");
//				job = "abilityPowerMod"; this.magicPowerModifier = dict.getDouble("abilityPowerMod");
//			}
//			catch (Exception err) {
//				FileErrors.globalErrors.addError("TEPLATE-LOAD", ("BuffElement.load, at=" + job), err);
//				throw err;
//			}
//			return this;
//		}
		
		private double calculateAmout(Character caster, Character target) {
			Random generator = new Random();
			double percent = ((generator.nextDouble()*2.0)/this.basePowerVariation) - this.basePowerVariation;
			double amountFinal = this.basePower + (((double)this.basePower) * percent);
			if(caster != null) {
				amountFinal += caster.getAttributesModule().getAbility() * this.magicPowerModifier;
			}
			return amountFinal;
		}
	}
	
	
	/////////////////////////
	//	Private Variables
	////////////////////////
	ArrayList<BuffElement> buffStats;
	private String condName;
	private String condDescription;
	private double baseDuration;
	private double baseDurationVariation;
	private double magicDurationModifier;
	
	private boolean stackable;
	private boolean unique;
	
	private effectTargets targets;
	private TemplateInfo effectTemplate;
	
	
	////////////////////////////
	//	Constructors/Loaders
	/////////////////////////////
	public StatsBuffEffect() {
		this.buffStats = new ArrayList<BuffElement>();
		this.condName = "NOCOND";
		this.condDescription = "NOCONDDESC";
		this.baseDuration = 1.0;
		this.baseDurationVariation = 0.0;
		this.magicDurationModifier = 0.0;
		this.effectTemplate = new TemplateInfo();
		this.targets = effectTargets.ALLY;
		this.stackable = false;
		this.unique = true;
	}
	public StatsBuffEffect(ArrayList<BuffElement> buffStats, String condName, String condDescription, 
			double baseDuration, double baseDurationVariation, double magicDurationModifier,
			boolean stackable, boolean unique, effectTargets targets, TemplateInfo effectTemplate) {
		this.buffStats = buffStats;
		this.condName = condName;
		this.condDescription = condDescription;
		this.baseDuration = baseDuration;
		this.baseDurationVariation = baseDurationVariation;
		this.magicDurationModifier = magicDurationModifier;
		this.stackable = stackable;
		this.unique = unique;
		this.targets = targets;
		this.effectTemplate = effectTemplate;
	}

//	public BaseEffect load(DataDictionary dict) throws Exception {
//		loadFromDict(dict);
//		return this;
//	}

	

	
	////////////////////////
	//	Public methods
	/////////////////////////
	@Override
	public void apply(Character caster, Character target) {
		Random generator = new Random();
		double durationVar = ((generator.nextDouble()*2.0)/this.baseDurationVariation) - this.baseDurationVariation;
		int durationFinal = (int) Math.round( this.baseDuration + ((double)this.baseDuration) * durationVar);
		if(caster != null) {
			durationFinal += caster.getAttributesModule().getAbility() * this.magicDurationModifier;
		}
		
		ArrayList<StatBonus> tempStatsBonus = new ArrayList<StatBonus>();
		for(BuffElement buff: buffStats) {
			tempStatsBonus.add(new StatBonus(buff.statName, buff.calculateAmout(caster, target)));
		}
		
		StatsBuff cond = new StatsBuff(this.condName, this.condDescription, durationFinal, 
				tempStatsBonus, this.stackable, this.unique);
		target.getConditionModule().addCondition(cond);
	}

	@Override
	public effectTargets getTargets() { return this.targets; }
	@Override
	public TemplateInfo getEffectTemplate() { return this.effectTemplate; }
	public static EffectFactory getFactory() { return new EffectFactory() {
		public BaseEffect createEffect(DataDictionary dict) throws Exception {
			return GameEffectLoader.loadStatsBuffEffect(dict);
		}}; 
	}
	
	
	
	
	
	
	/////////////////////////////////
	//	Private Loaders
	/////////////////////////////////
//	private void loadFromDict(DataDictionary dict) throws Exception {
//		String job = "N/A";
//		try {
//			job = "ConditionName"; this.condName = dict.getString("ConditionName");
//			job = "description"; this.condDescription = dict.getString("description");
//			job = "stackable"; if(dict.exists("stackable")) { this.stackable = dict.getBoolean("stackable"); }
//			job = "unique"; if(dict.exists("unique")) { this.unique = dict.getBoolean("unique"); }
//			job = "duration"; this.baseDuration = dict.getDouble("duration");
//			job = "durationVar"; this.baseDurationVariation = dict.getDouble("durationVar");
//			job = "abilityDurationMod"; this.magicDurationModifier = dict.getDouble("abilityDurationMod");
//			job = "targets"; if(dict.exists("targets")) { this.targets = effectTargets.valueOf(dict.getString("targets").toUpperCase()); }
//			job = "effectTemplate"; this.effectTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("effectTemplate"));
//			job = "buffStats"; 
//			DataDictionary subdict = dict.getSubArray("buffStats");
//			for(int i=0; i<subdict.getArraySize(); i++) {
//				buffStats.add(GameMiscLoader.loadBuffElement(subdict.getSubDictionary(i)));
//			}
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("EFFECT-ERROR", ("StatsBuffEffect.load, at=" + job), err);
//			throw err;
//		}
//	}
//	private void loadFromNode(Node neffect) throws Exception {
//		this.basePower = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "power"));
//		this.basePowerVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "powerVar"));
//		this.magicPowerModifier = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "abilityPowerMod"));
//		this.dmgType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(neffect, "dmgType"));
//		this.targets = effectTargets.valueOf(XMLutil.getFirstElementValue(neffect, "targets"));
//		this.effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(neffect, "effectTemplate"));
//		this.effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "in"));
//		this.effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "out"));
//	}
	
	

}
