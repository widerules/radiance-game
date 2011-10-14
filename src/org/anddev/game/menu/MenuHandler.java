package org.anddev.game.menu;

import org.anddev.game.character.PlayerCharacter;


import java.util.Stack;


public class MenuHandler {
	
	protected Stack<MenuObj> currentMenu;
	
	
	protected PlayerCharacter currentChar;
	
	
	public MenuHandler() {
		currentMenu = new Stack<MenuObj>();
	}
	
	////////////////////
	//	Getters
	////////////////////
	public MenuObj currentMenu() { if (currentMenu.size( ) >0) {
										return currentMenu.get(0); 
								  }else {
										return null;
								  } }
	
	
	/////////////////////
	//	Public Methods
	/////////////////////
	public int back() {
		if (currentMenu.size() > 0) {
			currentMenu.remove(currentMenu.size() - 1);
		}
		else {
			return -1;
		}
		return currentMenu.size();
	}
	
	public void showCharMenu(PlayerCharacter selectedChar) {
		/**
		 * Called after selecting and moving a char 
		 * to show the character main options
		 */
		currentChar = selectedChar;
		currentMenu.push(MenuObj.makeCharMenu());
		
		// TODO
		// Create and show the main character menu (attack, spell, etc.).
		// This should reset all menuObj items.
	}
	
	public void showMainMenu() {
		/**
		 * Called after going back from character selection (with cursor)
		 * to show the main menu game options
		 */
		currentChar = null;
		currentMenu.push(MenuObj.makeMainMenu());
		// TODO
		// Create and show the main options menu (end turn, quit, save, etc.)
		// This should reset all menuObj items.
	}
	
	
	
}