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

package org.anddev.game.template;

import org.anddev.game.GridRange;
import org.anddev.game.engine.InputListener.inputType;
import org.anddev.program.variables.GPoint;

public class TemplateHandler {
	
	private GPoint position;
	private GridRange range;
	private Template currentTemplate;
	
	
	/////////////////////
	//	Constructor
	/////////////////////
	public TemplateHandler() {
		position = null;
		range = null;
		currentTemplate = null;
	}
	
	
	/////////////////////
	//	Public Methods
	/////////////////////
	public void setTemplate() {
		// TODO
	}
	
	public int moveTemplate(inputType direction) {
		
		// TODO
		GPoint newPosition = new GPoint(position);
		
		if(direction == inputType.UP) {
			newPosition.y++;
			if(range.inGrid(newPosition)) {
				// move up
				return 0;
			}
			else return -1;
		}
		else if(direction == inputType.DOWN) {
			newPosition.y--;
			if(range.inGrid(newPosition)) {
				// move down
				return 0;
			}
			else return -1;
		}
		else if(direction == inputType.LEFT) {
			newPosition.y++;
			if(range.inGrid(newPosition)) {
				// move left
				return 0;
			}
			else return -1;
		}
		else if(direction == inputType.RIGHT) {
			newPosition.y++;
			if(range.inGrid(newPosition)) {
				// move right
				return 0;
			}
			else return -1;
		}
		else {
			return -1;
		}
	}
	
}
