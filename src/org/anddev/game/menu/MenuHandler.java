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