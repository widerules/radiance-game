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

package org.vagosduke.andengine.radiance.game.template;

import org.vagosduke.andengine.radiance.game.engine.InputListener.inputType;
import org.vagosduke.andengine.radiance.program.variables.GPoint;

public class TemplateHandler {
	
	private GPoint position;
	private Template currentTemplate;
	
	
	/////////////////////
	//	Constructor
	/////////////////////
	public TemplateHandler() {
		position = null;
		currentTemplate = null;
	}
	
	
	/////////////////////
	//	Public Methods
	/////////////////////
	public void setTemplate() {
		// TODO
	}
	
}
