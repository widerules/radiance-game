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

package org.vagosduke.andengine.radiance.game.engine;

import org.vagosduke.andengine.radiance.engine.cursor.TargetSelectCursor;
import org.vagosduke.andengine.radiance.engine.menu.MenuManager;
import org.vagosduke.andengine.radiance.game.character.PlayerCharacter;
import org.vagosduke.andengine.radiance.game.engine.InputListener.inputType;
import org.vagosduke.andengine.radiance.game.movement.MoveHandler;
import org.vagosduke.andengine.radiance.game.template.TemplateHandler;

public class GameState {
	public static enum gstate 		// Primary Game state
				{ MAIN, CURSOR, MOVE, MENU, COMBAT, TEMPLATE, CINEMATIC, TEXT, WAIT }
	
	
	public static enum mstate		// secondary game state
				{ DI, MENUANIM, COMBATANIM }
	
	
	 
	
	/////////////////////////////////
	// Global static engine variables
	/////////////////////////////////
	private static gstate gameState;
	private static PlayerCharacter currentCharacter;
	
	public static MenuManager menuHandler;
	public static MenuManager mainHandler;
	public static TemplateHandler templateHandler;
	public static MoveHandler moveHandler;
	public static TargetSelectCursor cursorHandler;
	
	//////////////////////////////
	//	Constructors
	//		VS initializer
	//////////////////////////////
	public GameState() {
		gameState = gstate.WAIT;
		//curMenu = null;
		currentCharacter = null;
		menuHandler = new MenuManager();	// register a new menuHandler
		
		//mainHandler = new MenuHandler();
	}
	
	//////////////////////////////
	//	Public methods
	//////////////////////////////
	public void init() {
		
	}
	
	public int processInput(inputType input) {
		try {
			
//		// CURSOR state (character Select)
//			if (gameState == gstate.CURSOR) {
//				if(input.isDirection()) { return cursorHandler.move(input); }
//				if(input == inputType.OK) {
//					currentCharacter = cursorHandler.findCharByCoords();
//					if(currentCharacter == null) {
//						return -1;
//					}
//					cursorHandler.exit();
//					menuHandler.showCharMenu(currentCharacter);
//					gameState = gstate.MAIN;
//					return 0;
//				}
//				if(input == inputType.BACK) {
//					cursorHandler.exit();
//					currentCharacter = null;
//					menuHandler.showMainMenu();
//					gameState = gstate.MAIN;
//					return 0;
//				}
//			}
//			
//			
//		// MENU State (main menu or character menu)
//			if (gameState == gstate.MENU) {
//				switch(input) {
//					case UP: return menuHandler.currentMenu().moveSelectionUp();
//					case DOWN: return menuHandler.currentMenu().moveSelectionDown();
//					case OK: return menuHandler.currentMenu().select();
//					case BACK: return menuHandler.back();
//					default: return -1;
//				}
//			}
//			
//			
//		// MOVE State
//			else if(gameState == gstate.MOVE) {
//				if(input.isDirection()) { return moveHandler.move(input.getDirection()); }
//				return 0;
//			}
//		
//		
//		// COMBAT State
//			else if(gameState == gstate.COMBAT) {
//				return 0;
//			}
//			
//		
//		// TEMPLATE State (setting template for an effect)
//			else if(gameState == gstate.TEMPLATE) {
//				if(input.isDirection()) { return templateHandler.moveTemplate(input); }
//				return 0;
//			}
//			
//	
//		// CINEMATIC State
//			else if(gameState == gstate.CINEMATIC) {
//				return 0;
//			}
//			
//			
//		// WAIT State
//			else if(gameState == gstate.WAIT) {
//				return 0;
//			}
//			
//			
//		// Other
//			else {
//				return -1;
//			}
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	
	//////////////////////
	//	Getters / Setters
	//////////////////////
	public static PlayerCharacter currentCharacter() { return currentCharacter; }
	
}
