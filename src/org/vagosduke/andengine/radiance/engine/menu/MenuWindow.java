package org.vagosduke.andengine.radiance.engine.menu;

import java.util.ArrayList;
import java.util.List;

public class MenuWindow {
	/**
	 * 	Menu is a core method for player interaction.
	 * 	The menu is essentially a sorted list of objects.
	 * 	Objects can point on anything such as items, targets, actions, etc. 
	 */
	
	
	private ArrayList<MenuItem> menuItems;
	private int size;
	private int cursor;
	private boolean loops;
	
	private int returnCode;
	
	
	/////////////////////////
	//	Constructor
	/////////////////////////
	public MenuWindow(List<MenuItem> ilist) {
		this.menuItems = new ArrayList<MenuItem>(ilist);
		this.size = this.menuItems.size();
		this.cursor = 0;
		this.loops = false;
	}
	public MenuWindow(List<MenuItem> ilist, boolean iloop) {
		this.menuItems = new ArrayList<MenuItem>(ilist);
		this.size = this.menuItems.size();
		this.cursor = 0;
		this.loops = iloop;
	}
	public MenuWindow(List<MenuItem> ilist, int cursorSet, boolean iloop) {
		this.menuItems = new ArrayList<MenuItem>(ilist);
		this.size = this.menuItems.size();
		this.cursor = cursorSet;
		this.loops = iloop;
	}
	
	
	
	
	/////////////////////
	//	Public Methods
	/////////////////////
	public void show() {
		// draws the menu
	}
	public void hide() {
		// removes menu from screen
	}
	public void moveCursorUp() {
		int newpos = this.cursor + 1;
		if(newpos >= this.size) {
			if(loops) { newpos = 0; }
			else { newpos = this.size - 1; }
		}
		this.cursor = newpos;
	}
	public void moveCursorDown() {
		int newpos = this.cursor - 1;
		if(newpos < 0) {
			if(loops) { newpos = this.size - 1; }
			else { newpos = 0; }
		}
		this.cursor = newpos;
	}
	
	public Object getSelection() {
		return this.menuItems.get(cursor);
	}
	
	
	
	
	//////////////////////
	//	Getters/Setters
	//////////////////////
	public MenuItem selectItem(int index) { return this.menuItems.get(index); }
	public int getCurosr() { return this.cursor; }
	public void setCursor(int index) { if((index>=0) && (index<this.size)) { this.cursor = index; }} 
}
