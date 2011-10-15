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
