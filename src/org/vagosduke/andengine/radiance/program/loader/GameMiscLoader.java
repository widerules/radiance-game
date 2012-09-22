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

import org.vagosduke.andengine.radiance.game.character.AttributesModule.Attributes;
import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.combat.ResistList;
import org.vagosduke.andengine.radiance.game.inventory.CharacterInventory;
import org.vagosduke.andengine.radiance.game.items.BaseItem;
import org.vagosduke.andengine.radiance.game.items.GlobalItemList;
import org.vagosduke.andengine.radiance.game.items.StatBonus;
import org.vagosduke.andengine.radiance.game.spells.effects.StatsBuffEffect.BuffElement;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.game.template.Template.returnTarget;
import org.vagosduke.andengine.radiance.game.template.Template.snapTarget;
import org.vagosduke.andengine.radiance.game.template.Template.templateType;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class GameMiscLoader {

	
	public static TemplateInfo loadTemplateInfo(DataDictionary dict) throws Exception {
		int rangeIn = 0;
		int rangeOut = 0;
		templateType shape = templateType.DIAMOND;
		snapTarget snaps = snapTarget.ENEMY;
		returnTarget targets = returnTarget.BOTH;
		String job = "N/A";
		try {
			job = "rangeIn"; rangeIn = dict.getInteger("rangeIn");
			job = "rangeOut"; rangeOut = dict.getInteger("rangeOut");
			job = "shape"; if(dict.exists("shape")) {shape = templateType.valueOf(dict.getString("shape")); }
			job = "snaps"; if(dict.exists("snaps")) {snaps = snapTarget.valueOf(dict.getString("snaps")); }
			job = "targets"; if(dict.exists("targets")) {targets = returnTarget.valueOf(dict.getString("targets")); }
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("TEPLATE-LOAD", ("loadTemplateInfo, at=" + job), err);
			throw err;
		}
		return new TemplateInfo(rangeIn, rangeOut, shape, snaps, targets);
	}
	
	
	public static BuffElement loadBuffElement(DataDictionary dict) throws Exception {
		double basePower = 0.0;
		double basePowerVariation = 0.0;
		double magicPowerModifier = 0.0;
		Attributes statName = Attributes.ABILITY;
		String job = "N/A";
		try {
			job = "stat"; statName = Attributes.valueOf(dict.getString("stat").toUpperCase());
			job = "power"; basePower = dict.getDouble("power");
			job = "powerVar"; basePowerVariation = dict.getDouble("powerVar");
			job = "abilityPowerMod"; magicPowerModifier = dict.getDouble("abilityPowerMod");
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("TEPLATE-LOAD", ("loadBuffElement, at=" + job), err);
			throw err;
		}
		return new BuffElement(basePower, basePowerVariation, magicPowerModifier, statName);
	}
	
	
	public static StatBonus loadStatBonus(DataDictionary dict) throws Exception{
		/**
		 *  matches to: 
		 *  stat:	*charScores*
		 *  amount:	*int or double*
		 */
		Attributes statName;
		double amount;
		
		String job = "N/A";
		String statn = "N/A";
		try {
			job = "stat";
			statn = dict.getString("stat").toUpperCase();
			statName = Attributes.valueOf(statn);
			job = "amount"; amount = dict.getDouble("amount");
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("STATBONUS-LOAD", ("StatBonus.load, stat=\"" + statn + "\" at=" + job), err);
			throw err;
		}
		return new StatBonus(statName, amount);
	}
	
	
	
	
	public static ResistList loadResistList(DataDictionary dict) throws Exception {
		ResistList ret = new ResistList();
		String job = "N/A";
		try {
			for(int i=0; i<dict.getArraySize(); i++) {
				DataDictionary element = dict.getSubDictionary(i);
				job = element.getString("name").toUpperCase();
				double value = element.getDouble("value");
				ret.setResistance(Damage.DMGtype.valueOf(job),  value);
			}
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("CHAR-ERROR", ("loadResistList, at=" + job), err);
			throw err;
		}
		return ret;
	}
	
	
	
	

}
