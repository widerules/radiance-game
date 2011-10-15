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

package org.anddev.engine.variables;

public class EnumValue {
	/**
	 * Holds the Value of a custom enum (created at runtime)
	 * 
	 * superID: the custom enum identifier
	 * thisValue: the enum value
	 */
	
	private Class<? extends EnumClass> superID;
	private int thisValue;
	
		
	protected EnumValue(Class<? extends EnumClass> enumClass, int value) {
		this.superID = enumClass;
		this.thisValue = value;
	}
		
	public Class<? extends EnumClass> getEnum() { return superID; }
	public int getValue() { return thisValue; }
	public String toString() { return String.valueOf(thisValue); }
	
}
