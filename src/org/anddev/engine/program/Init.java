package org.anddev.engine.program;

import org.anddev.game.combat.Damage;
import org.anddev.game.items.GlobalItemList;
import org.anddev.game.items.ItemBase;

import android.app.Activity;

public class Init {
	
	public static void init_All(Activity activity) {
		
		GameState.init(activity);	// Initialize all handlers (menu, cursor, template, etc.)
		
		
		Damage.initDamageType();
		ItemBase.initItemTypes();
		
		GlobalItemList.loadListXML();
	}
}
