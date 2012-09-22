package org.vagosduke.andengine.radiance.game;

import org.vagosduke.andengine.radiance.game.constants.DynamicTypes;
import org.vagosduke.andengine.radiance.game.items.GlobalItemList;
import org.vagosduke.andengine.radiance.game.manager.GameManager;
import org.vagosduke.andengine.radiance.game.spells.GlobalSpellList;
import org.vagosduke.andengine.radiance.game.spells.effects.GlobalEffectList;
import org.vagosduke.andengine.radiance.program.config.Filepath;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.log.ProgLog;

public class GameCore {
	
	public static boolean out = true;
	
	private static GameManager gameManager; 
	
	public static void init() {
		
		// 1)
		if(out) { ProgLog.message("INIT-CORE", "Loading external dynamic types..."); }
		DynamicTypes.initDynamicTypes();
		if(out) { assertErrors(false); }
		
		
		
		// 2)
		if(out) { ProgLog.message("INIT-CORE", "Loading effects..."); }
		GlobalEffectList.initGlobalEffectsList();
		if(out) { assertErrors(false); }
		
		
		
		// 3)
		if(out) { ProgLog.message("INIT-CORE", "Loading items..."); }
		GlobalItemList.initGlobalItemList(Filepath.getItemFiles());
		if(out) { assertErrors(false); }
		
		
		
		// 4)
		if(out) { ProgLog.message("INIT-CORE", "Loading spells..."); }
		GlobalSpellList.initGlobalSpellList(Filepath.getSpellFiles());
		if(out) { assertErrors(false); }
		
		
		// 5)
		if(out) { ProgLog.message("INIT-CORE", "Initializing Game Manager..."); }
		gameManager = new GameManager();
		if(out) { assertErrors(false); }
	}
	
	
	//////////////////////////
	//	Public Methods
	/////////////////////////
	public static GameManager getGameManager() {
		return gameManager;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////
	//	Private methods
	/////////////////////////
	public static void assertErrors(boolean all) { 
		if(FileErrors.globalErrors.hasErrors() || FileErrors.globalErrors.hasWarnings()) { 
			ProgLog.message("INIT-CORE", "Found: " + FileErrors.globalErrors.getWarningsNum() + " Warnings " +
				"and " + FileErrors.globalErrors.getErrorsNum() + " Errors"); 
			if(all) {
				FileErrors.globalErrors.printAll();
			}
			else {
				FileErrors.globalErrors.printMessages();
			}
		}
		else {
			ProgLog.message("INIT-CORE", "No Problems found");
		}
		FileErrors.globalErrors.clear();
	}

}
