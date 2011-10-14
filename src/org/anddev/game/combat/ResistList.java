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
