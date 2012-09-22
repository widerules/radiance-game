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


package org.vagosduke.andengine.radiance.program.loader;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.spells.SpellRank;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.DamageEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.DamageOverTimeEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.GlobalEffectList;
import org.vagosduke.andengine.radiance.game.spells.effects.HealEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.StatsBuffEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect.effectTargets;
import org.vagosduke.andengine.radiance.game.spells.effects.StatsBuffEffect.BuffElement;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class GameEffectLoader {
	
	
	
	
	
	////////////////////////
	//	DAMAGE
	////////////////////////
	
	public static DamageEffect loadDamageEffect(DataDictionary dict) throws Exception {
		double basePower = 1;
		double basePowerVariation = 0;
		double magicPowerModifier = 0;
		EnumValue dmgType = Damage.DMGtype.defaultValue();
		effectTargets targets = effectTargets.ENEMY;
		TemplateInfo effectTemplate = new TemplateInfo();
		String job = "N/A";
		try {
			job = "power"; basePower = dict.getDouble("power");
			job = "powerVar"; basePowerVariation = dict.getDouble("powerVar");
			job = "abilityPowerMod"; magicPowerModifier = dict.getDouble("abilityPowerMod");
			job = "damageType"; dmgType = Damage.DMGtype.valueOf(dict.getString("damageType").toUpperCase());
			job = "targets"; if(dict.exists("targets")) { targets = effectTargets.valueOf(dict.getString("targets").toUpperCase()); }
			job = "effectTemplate"; effectTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("effectTemplate"));
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("EFFECT-ERROR", ("loadDamageEffect, at=" + job), err);
			throw err;
		}
		return new DamageEffect(basePower, basePowerVariation, magicPowerModifier, dmgType, targets, effectTemplate);
	}
	
	
	
	
	
	
	////////////////////////
	//	DAMAGE-OVER-TIME
	////////////////////////
	
	public static DamageOverTimeEffect loadDamageOverTimeEffect(DataDictionary dict) throws Exception {
		String condName = "NOCOND";
		String condDescription = "NOCONDDESC";;
		double basePower = 1.0;
		double basePowerVariation = 0.0;
		double magicPowerModifier = 0.0;
		EnumValue dmgType = Damage.DMGtype.defaultValue();
		double baseDuration = 1.0;
		double baseDurationVariation = 0.0;
		double magicDurationModifier = 0.0;
		effectTargets targets = effectTargets.ENEMY;
		TemplateInfo effectTemplate = new TemplateInfo();
		boolean stackable = false;
		boolean unique = false;
		String job = "N/A";
		try {
			job = "ConditionName"; condName = dict.getString("ConditionName");
			job = "description"; condDescription = dict.getString("description");
			job = "stackable"; if(dict.exists("stackable")) {unique = dict.getBoolean("stackable");}
			job = "unique"; if(dict.exists("unique")) {unique = dict.getBoolean("unique");}
			job = "power"; basePower = dict.getDouble("power");
			job = "duration"; baseDuration = dict.getDouble("duration");
			job = "powerVar"; basePowerVariation = dict.getDouble("powerVar");
			job = "durationVar"; baseDurationVariation = dict.getDouble("durationVar");
			job = "abilityPowerMod"; magicPowerModifier = dict.getDouble("abilityPowerMod");
			job = "abilityDurationMod"; magicDurationModifier = dict.getDouble("abilityDurationMod");
			job = "damageType"; dmgType = Damage.DMGtype.valueOf(dict.getString("damageType"));
			job = "targets"; if(dict.exists("targets")) { targets = effectTargets.valueOf(dict.getString("targets").toUpperCase()); }
			job = "effectTemplate"; effectTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("effectTemplate"));
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("EFFECT-ERROR", ("loadDamageOverTimeEffect, at=" + job), err);
			throw err;
		}
		return new DamageOverTimeEffect(condName, condDescription, basePower, basePowerVariation, 
				magicPowerModifier, dmgType, baseDuration, baseDurationVariation, magicDurationModifier, 
				targets, effectTemplate, stackable, unique);
		
	}
	
	
	
	
	////////////////////////
	//	HEAL
	////////////////////////
	
	public static HealEffect loadHealEffect(DataDictionary dict) throws Exception {
		double basePower = 1;
		double basePowerVariation = 0;
		double magicPowerModifier = 0;
		effectTargets targets = effectTargets.ALLY;
		TemplateInfo effectTemplate = new TemplateInfo();
		String job = "N/A";
		try {
			job = "power"; basePower = dict.getDouble("power");
			job = "powerVar"; basePowerVariation = dict.getDouble("powerVar");
			job = "abilityPowerMod"; magicPowerModifier = dict.getDouble("abilityPowerMod");
			job = "targets"; if(dict.exists("targets")) { targets = effectTargets.valueOf(dict.getString("targets").toUpperCase()); }
			job = "effectTemplate"; effectTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("effectTemplate"));
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("EFFECT-ERROR", ("HealEffect.load, at=" + job), err);
			throw err;
		}
		return new HealEffect(basePower, basePowerVariation, magicPowerModifier, targets, effectTemplate);
	}
	
	
	
	
	
	
	////////////////////////
	//	STATS-BUFF
	////////////////////////
	
	public static StatsBuffEffect loadStatsBuffEffect(DataDictionary dict) throws Exception {
		ArrayList<BuffElement> buffStats = new ArrayList<BuffElement>();
		String condName = "NOCOND";
		String condDescription = "NOCONDDESC";
		double baseDuration = 1.0;
		double baseDurationVariation = 0.0;
		double magicDurationModifier = 0.0;
		boolean stackable = false;
		boolean unique = true;
		effectTargets targets = effectTargets.ALLY;
		TemplateInfo effectTemplate = new TemplateInfo();

		String job = "N/A";
		try {
			job = "ConditionName"; condName = dict.getString("ConditionName");
			job = "description"; condDescription = dict.getString("description");
			job = "stackable"; if(dict.exists("stackable")) { stackable = dict.getBoolean("stackable"); }
			job = "unique"; if(dict.exists("unique")) { unique = dict.getBoolean("unique"); }
			job = "duration"; baseDuration = dict.getDouble("duration");
			job = "durationVar"; baseDurationVariation = dict.getDouble("durationVar");
			job = "abilityDurationMod"; magicDurationModifier = dict.getDouble("abilityDurationMod");
			job = "targets"; if(dict.exists("targets")) { targets = effectTargets.valueOf(dict.getString("targets").toUpperCase()); }
			job = "effectTemplate"; effectTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("effectTemplate"));
			job = "buffStats"; 
			DataDictionary subdict = dict.getSubArray("buffStats");
			for(int i=0; i<subdict.getArraySize(); i++) {
				buffStats.add(GameMiscLoader.loadBuffElement(subdict.getSubDictionary(i)));
			}
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("EFFECT-ERROR", ("StatsBuffEffect.load, at=" + job), err);
			throw err;
		}
		return new StatsBuffEffect(buffStats, condName, condDescription, baseDuration, baseDurationVariation, 
				magicDurationModifier, stackable, unique, targets, effectTemplate);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////////////
	//	GENERIC SPELL
	/////////////////////////////
	public static TargetedSpell loadTatrgetedSpell(DataDictionary dict) {
		String name = "NOSPELL";
		String description = "N/A";
		int ranksNum = 0;
		SpellRank[] ranks = new SpellRank[0];
		
		String job = "N/A";
		try {
			job = "name"; name = dict.getString("name");
			job = "description"; description = dict.getString("description");
			job = "rankNum"; ranksNum = dict.getInteger("rankNum");
			job = "ranks"; ranks = new SpellRank[ranksNum];
			
			DataDictionary rankDict = dict.getSubArray("ranks");
			for(int i=0; i<rankDict.getArraySize(); i++) {
				int grade = rankDict.getSubDictionary(i).getInteger("rank") - 1;	
				ranks[grade] = GameEffectLoader.loadSpellRank(rankDict.getSubDictionary(i), (grade+1));
			}
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("SPELL-LOAD", ("loadTatrgetedSpell, Spell=\"" + name + "\" at=" + job), err);
		}
		return new TargetedSpell(name, description, ranksNum, ranks);
	}
	
	
	
	
	
	
	
	
	//////////////////////
	//	SPELL-RANK
	/////////////////////
	public static SpellRank loadSpellRank(DataDictionary dict, int irank) throws Exception{
		int rank = 0;
		int MPcost = 0;
		TemplateInfo rangeTemplate = new TemplateInfo();
		TemplateInfo effectTemplate = new TemplateInfo();
		ArrayList<BaseEffect> cEffectList = new ArrayList<BaseEffect>();
		
		String job = "N/A";
		try {
			job = "rank"; rank = irank;
			job = "MPcost"; MPcost = dict.getInteger("MPcost");
			job = "range"; rangeTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("range"));
			job = "effectTemplate"; effectTemplate = GameMiscLoader.loadTemplateInfo(dict.getSubDictionary("effectTemplate"));
			job = "effects"; 
			DataDictionary effectDict = dict.getSubArray("effects");
			for(int i=0; i<effectDict.getArraySize(); i++) {
				cEffectList.add(GlobalEffectList.globalList.createEffect(effectDict.getSubDictionary(i)));
			}
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("RANK-LOAD", ("SpellRank.load, rank=" + rank + " at = " + job), err);
			throw err;
		}
		return new SpellRank(rank, MPcost, rangeTemplate, effectTemplate, cEffectList);
	}
}
