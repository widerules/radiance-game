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

package org.anddev.game.spells.effects;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.anddev.util.XMLutil;
import org.w3c.dom.Node;

import android.util.Log;

public class GlobalEffectList {

	/**
	 * Global Effect list.
	 * Used to dynamically pick and create effects from all available.
	 */
	
	private static HashMap<String, Class<? extends BaseEffect>> targetedEffectList;

	public static void initEffectsList() {
		targetedEffectList.put("DAMAGE", DamageEffect.class);
		targetedEffectList.put("DAMAGEOVERTIME", DamageEffect.class);
		
		
	}
	
	public static BaseEffect createEffect(Node neffect) {
		String effectKey = XMLutil.getThisNodeAttribute(neffect, "class");
		BaseEffect instance = null;
		try {
			instance = targetedEffectList.get(effectKey).newInstance().create(neffect);
		}catch (Exception t) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			Log.e("EFFECT-LIST", sw.toString());
		}
		return instance;
	}
	
}
