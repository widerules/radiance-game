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

package org.anddev.game.combat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.anddev.engine.variables.EnumValue;

public class ResistList extends HashMap<EnumValue, Double> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6318514762988783973L;

	/**
	 * Provides a struct to easily store and retrieve 
	 * percentages for each resistance type
	 */
	
	
	//////////////////////////
	//	Constructor
	/////////////////////////
	public ResistList() {
		//this.resistList = new HashMap<EnumValue, Float>();
		Vector<EnumValue> allValues = Damage.DMGtype.getAllValues();
		Iterator<EnumValue> it = allValues.iterator();
		while(it.hasNext()) {
			EnumValue value = it.next();
			this.put(value, 0.0);
		}
	}
	
	
	////////////////////////////
	//	Public Methods
	////////////////////////////
	
}
