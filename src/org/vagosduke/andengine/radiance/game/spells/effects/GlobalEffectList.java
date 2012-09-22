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

import java.util.HashMap;

import org.vagosduke.andengine.radiance.program.log.ProgLog;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.w3c.dom.Node;

import android.util.Log;

public class GlobalEffectList {

	/**
	 * Global Effect list.
	 * Used to dynamically pick and create effects from all available.
	 */
	////////////////////////////////
	//	Static global effect handler
	////////////////////////////////
	public static GlobalEffectList globalList;
	
	
	
	
	private HashMap<String, EffectFactory> targetedEffectList;

	
	
	
	//////////////////
	//	Constructor/
	////////////////////
	public GlobalEffectList() {
		targetedEffectList = new HashMap<String, EffectFactory>();
	}
	public static void initGlobalEffectsList() {
		globalList = new GlobalEffectList();
		globalList.addEffect("DAMAGE", DamageEffect.getFactory());
		globalList.addEffect("DAMAGEOVERTIME", DamageOverTimeEffect.getFactory());
		globalList.addEffect("HEAL", HealEffect.getFactory());
		globalList.addEffect("STATSBUFF", StatsBuffEffect.getFactory());
	}
	public void addEffect(String name, EffectFactory factory) {
		this.targetedEffectList.put(name, factory);
	}
	
	
	
	
	
	
	
	
	////////////////////////
	//	Public Methods
	////////////////////////
	public BaseEffect createEffect(DataDictionary dict) throws Exception {
		/**
		 * Creates an instance of one of the stored effects with the parameters described in the input DataDictionary
		 */
		String effectKey = dict.getString("class").toUpperCase();
		BaseEffect instance = targetedEffectList.get(effectKey).createEffect(dict);
		return instance;
	}
	
//	public BaseEffect createEffect(Node neffect) throws Exception {
//		String effectKey = XMLutil.getThisNodeAttribute(neffect, "class").toUpperCase();
//		BaseEffect instance = null;
//		instance = targetedEffectList.get(effectKey).newInstance().load(neffect);
//		return instance;
//	}
	
}
