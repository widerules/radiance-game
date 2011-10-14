package org.anddev.engine.program;

import org.anddev.game.character.PlayerCharacter;
import org.anddev.game.constants.Types.gstate;
import org.anddev.game.cursor.CursorHandler;
import org.anddev.game.menu.MenuHandler;
import org.anddev.game.movement.MoveHandler;
import org.anddev.game.template.TemplateHandler;

import android.app.Activity;

public class GameState {
	public static gstate gameState;
	
	public static PlayerCharacter currentCharacter; 
	
	public static MenuHandler menuHandler;
	public static CursorHandler cursorHandler;
	public static TemplateHandler templateHandler;
	public static MoveHandler movementHandler;
	
	public static ProgramInterface programInterface;
	
	
	
	//////////////////////////////
	//	static Initializer
	//////////////////////////////
	public static void init (Activity activity) {
		menuHandler = new MenuHandler();
		programInterface = new ProgramInterface();
		programInterface.loadAssets(activity);
	}
	

	
	
	/**
	 * @return the currentCharacter
	 */
	public static PlayerCharacter getCurrentCharacter() {
		return currentCharacter;
	}

	/**
	 * @return the menuHandler
	 */
	public static MenuHandler getMenuHandler() {
		return menuHandler;
	}

	/**
	 * @return the cursorHandler
	 */
	public static CursorHandler getCursorHandler() {
		return cursorHandler;
	}

	/**
	 * @return the templateHandler
	 */
	public static TemplateHandler getTemplateHandler() {
		return templateHandler;
	}

	/**
	 * @return the movementHandler
	 */
	public static MoveHandler getMovementHandler() {
		return movementHandler;
	}

	/**
	 * @return the programInterface
	 */
	public static ProgramInterface getProgramInterface() {
		return programInterface;
	}
	
	
	
	
}
