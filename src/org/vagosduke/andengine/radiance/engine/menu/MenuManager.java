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

package org.vagosduke.andengine.radiance.engine.menu;

import java.util.Stack;

import org.vagosduke.andengine.radiance.game.GameCore;
import org.vagosduke.andengine.radiance.game.manager.ActionManager;
import org.vagosduke.andengine.radiance.program.input.DigitalInput;


public class MenuManager implements ActionManager {
	
	private Stack<MenuWindow> windows;

	@Override
	public void show() {
		for(MenuWindow win: windows) {
			win.show();
		}
	}

	@Override
	public void hide() {
		for(MenuWindow win: windows) {
			win.hide();
		}
	}
	
	@Override
	public void processInput(DigitalInput input) {
		MenuWindow win = windows.firstElement();
		switch(input.getInput()) {
			case UP: win.moveCursorUp(); break;
			case DOWN: win.moveCursorDown(); break;
			default: ;
		}
	}
	
	public void addWindow(MenuWindow win) {
		windows.firstElement().hide();
		windows.add(win);
		win.show();
	}
	public void addWindowNoHide(MenuWindow win) {
		windows.add(win);
		win.show();
	}
	public void addWindowNoShow(MenuWindow win) {
		windows.firstElement().hide();
		windows.add(win);
	}
	
	public void removeWindow() {
		if(!windows.empty()) {
			windows.pop().hide();
		}
		else {
			GameCore.getGameManager().popActiveManager();
		}
	}
	public void removeWindow(int index) {
		if(!windows.empty()) {
			windows.remove(index).hide();
		}
		else {
			GameCore.getGameManager().popActiveManager();
		}
	}
	
	
	public MenuWindow getWindow(int index) { return this.windows.get(index); }
	public MenuWindow getWindow() { return this.windows.firstElement(); }
}