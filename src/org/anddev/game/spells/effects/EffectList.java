package org.anddev.game.spells.effects;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.anddev.util.XMLutil;
import org.w3c.dom.Node;

import android.util.Log;

public class EffectList {

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
