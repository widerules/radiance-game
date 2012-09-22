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


package org.vagosduke.andengine.radiance.game.items;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.items.ItemTypes.ItemType;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.program.loader.GameItemLoader;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class AccessoryItem extends BaseItem {

	
	///////////////////
	//	Constructor
	///////////////////
	public static AccessoryItem create(DataDictionary dict) {
		return GameItemLoader.loadAccessoryItem(dict);
	}
	public AccessoryItem() {
		super();
	}
	public AccessoryItem(String iname, String idescription, ItemType itype, EnumValue isubtype, int ivalue, int ilevel,
			int isize, double iweight, boolean iusable, boolean istackable, boolean ishoppable, boolean idroppable,
			ArrayList<BaseEffect> ionEquipEffects, ArrayList<BaseEffect> ionUseEffects, 
			ArrayList<StatBonus> ionEquipBonuses) {
		super(iname, idescription, itype, isubtype, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}
	
	/////////////////
	//	Getters
	////////////////
	
	
	
	
	
	/////////////
	//	Loader
	//////////////
//	public AccessoryItem load(DataDictionary dict) {
//		super.load(dict);
//		return this;
//	}

}
