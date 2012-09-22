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

package org.vagosduke.andengine.radiance.game.combat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.vagosduke.andengine.radiance.program.variables.EnumValue;

public class ResistList {
	/**
	 * Provides a struct to easily store and retrieve 
	 * percentages for each resistance type
	 */

	
	
	private static class ResistVariableValue {
		/**
		 * resist variable class
		 */
		private double value;
		public ResistVariableValue(double ivalue) {
			this.value = ivalue;
		}
		public double getValue() { return this.value; }
		public void setValue(double ivalue) { this.value = ivalue; }
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	
	HashMap<Integer, ResistVariableValue> resistList;
	
	
	//////////////////////////
	//	Constructor
	/////////////////////////
	public ResistList() {
		this.resistList = new HashMap<Integer, ResistVariableValue>();
		ArrayList<EnumValue> allTypes = Damage.DMGtype.getAllValues();
		for(EnumValue type : allTypes) {
			this.resistList.put(type.getValue(), new ResistVariableValue(0.0));
		}
	}
	
	
	////////////////////////////
	//	Public Methods
	////////////////////////////
	public void addResistance(EnumValue type, double amount) {
		ResistVariableValue currentResit = resistList.get(type.getValue());
		double newValue = currentResit.getValue() + amount;
		currentResit.setValue(newValue);
	}
	
	public void subtractResistance(EnumValue type, double amount) {
		ResistVariableValue currentResit = resistList.get(type.getValue());
		double newValue = currentResit.getValue() - amount;
		currentResit.setValue(newValue);
	}
	
	public void setResistance(EnumValue type, double amount) {
		resistList.get(type.getValue()).setValue(amount);
	}
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		Set<Entry<Integer, ResistVariableValue>> set = resistList.entrySet();
		str.append("{ ");
		for (Entry<Integer, ResistVariableValue> entry: set) {
			str.append("[" + Damage.DMGtype.valueOf(entry.getKey()).toString() + "=");
			str.append(entry.getValue().getValue() + "] ");
		}
		str.append("}");
		return str.toString();
	}
	
	////////////////////////////
	//	Public Methods
	////////////////////////////
	public double get(EnumValue type) { return this.resistList.get(type.getValue()).getValue(); }
	//public HashMap<Integer, ResistVariableValue> getResistList() { return this.resistList; }
}
