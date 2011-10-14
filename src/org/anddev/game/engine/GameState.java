package org.anddev.game.engine;

import org.anddev.game.character.PlayerCharacter;
import org.anddev.game.cursor.CursorHandler;
import org.anddev.game.engine.InputListener.inputType;
import org.anddev.game.menu.MenuHandler;
import org.anddev.game.movement.MoveHandler;
import org.anddev.game.template.TemplateHandler;

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
	
	public static MenuHandler menuHandler;
	public static MenuHandler mainHandler;
	public static TemplateHandler templateHandler;
	public static MoveHandler moveHandler;
	public static CursorHandler cursorHandler;
	
	//////////////////////////////
	//	Constructors
	//		VS initializer
	//////////////////////////////
	public GameState() {
		gameState = gstate.WAIT;
		//curMenu = null;
		currentCharacter = null;
		menuHandler = new MenuHandler();	// register a new menuHandler
		
		//mainHandler = new MenuHandler();
	}
	
	//////////////////////////////
	//	Public methods
	//////////////////////////////
	public void init() {
		
	}
	
	public int processInput(inputType input) {
		try {
			
		// CURSOR state (character Select)
			if (gameState == gstate.CURSOR) {
				if(input.isDirection()) { return cursorHandler.move(input); }
				if(input == inputType.OK) {
					currentCharacter = cursorHandler.findCharByCoords();
					if(currentCharacter == null) {
						return -1;
					}
					cursorHandler.exit();
					menuHandler.showCharMenu(currentCharacter);
					gameState = gstate.MAIN;
					return 0;
				}
				if(input == inputType.BACK) {
					cursorHandler.exit();
					currentCharacter = null;
					menuHandler.showMainMenu();
					gameState = gstate.MAIN;
					return 0;
				}
			}
			
			
		// MENU State (main menu or character menu)
			if (gameState == gstate.MENU) {
				switch(input) {
					case UP: return menuHandler.currentMenu().moveSelectionUp();
					case DOWN: return menuHandler.currentMenu().moveSelectionDown();
					case OK: return menuHandler.currentMenu().select();
					case BACK: return menuHandler.back();
					default: return -1;
				}
			}
			
			
		// MOVE State
			else if(gameState == gstate.MOVE) {
				if(input.isDirection()) { return moveHandler.move(input.getDirection()); }
				return 0;
			}
		
		
		// COMBAT State
			else if(gameState == gstate.COMBAT) {
				return 0;
			}
			
		
		// TEMPLATE State (setting template for an effect)
			else if(gameState == gstate.TEMPLATE) {
				if(input.isDirection()) { return templateHandler.moveTemplate(input); }
				return 0;
			}
			
	
		// CINEMATIC State
			else if(gameState == gstate.CINEMATIC) {
				return 0;
			}
			
			
		// WAIT State
			else if(gameState == gstate.WAIT) {
				return 0;
			}
			
			
		// Other
			else {
				return -1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		
	}
	
	
	//////////////////////
	//	Getters / Setters
	//////////////////////
	public static PlayerCharacter currentCharacter() { return currentCharacter; }
	
}
