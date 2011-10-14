package org.anddev.game.menu;


import org.anddev.game.constants.Types.menuType;
import org.anddev. game.menu.it.MenuCharAttack;
import org.anddev.game.menu.it.MenuCharItem;
import org.anddev.game.menu.it.MenuCharMagic;
import org.anddev.game.menu.it.MenuCharStay;
import org.anddev.game.menu.it.MenuEntry;

import java.util.ArrayList;

public class MenuObj extends ArrayList<MenuEntry>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1611353576901123717L;
	////////////////////////
	// Static variables
	////////////////////////
	
	
	
	////////////////////////
	//	Variables
	////////////////////////
	protected int selectedItem;
	
	/*
	 * 	Character Menu has these static entries
	 */
	protected static final MenuEntry[] CharMenuList = 
		{	new MenuCharAttack(),
			new MenuCharMagic(),
			new MenuCharItem(), 
			new MenuCharStay() };
	
	/*
	 * 	Main options menu has these entries
	 */
	protected static final MenuEntry[] MainMenuList = 
		{ 	new MenuCharAttack() };
	
	
	
	
	
	
	
	//////////////////
	//	Constructors
	//////////////////
	public static MenuObj makeCharMenu() {
		return new MenuObj(menuType.CHAR);
	}
	public static MenuObj makeMainMenu() {
		return new MenuObj(menuType.MAIN);
	}
	public static MenuObj makeMagicMenu() {
		return new MenuObj(menuType.MAGIC);
	}
	public static MenuObj makeItemActionMenu() {
		return new MenuObj(menuType.ITEMACTION);
	}
	public static MenuObj makeItemMenu() {
		return new MenuObj(menuType.ITEM);
	}

	public MenuObj(){
		selectedItem = -1;
	}
	
	public MenuObj(MenuEntry[] lst){
		for(int i=0; i<lst.length; i++) {
			this.addItem(lst[i]);
		}
	}
	
	private MenuObj(menuType type) {
		// Create Menu by-type
		if(type == menuType.CHAR) {
			for(int i=0; i<CharMenuList.length; i++) {
				this.addItem(CharMenuList[i]);
			}
		}
		else if(type == menuType.MAIN){
			for(int i=0; i<MainMenuList.length; i++) {
				this.addItem(MainMenuList[i]);
			}
		}
		else if(type == menuType.MAGIC) {
			// CharClass currentChar = GameState.currentCharacter();
			// for(int i=0; i<currentChar.Spells.length; i++) {
			//		this.addItem(new MenuCharMagic(currentChar.Spells[i]));
			//	}
		}
	}
	
	
	////////////////////
	//	Public Methods
	////////////////////
	public void addItem(MenuEntry entry) {
		this.add(entry);
		if(this.size() == 1) {
			selectedItem = 0;
		}
	}
	
	public int select() {
		if ((selectedItem < this.size()) && (selectedItem >= 0) && this.get(selectedItem).enabled()) {
			this.get(selectedItem).activate();
			return selectedItem;
		}
		else return -1;
	}
	
	public int moveSelectionUp() {
		if (selectedItem < (this.size()-1) ) {
			selectedItem++;
			return 0;
		}
		else return -1;
	}
	
	public int moveSelectionDown() {
		if (selectedItem > 0 ) {
			selectedItem--;
			return 0;
		}
		else return -1;
	}
	
	//////////////////////////
	//	Getters
	//////////////////////////
	public int selected() { return selectedItem; }
	
}
