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
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.program.loader.GameItemLoader;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class ConsumableItem extends BaseItem {
	
	private TemplateInfo range;
	private TemplateInfo effectTemplate;
	
	
	
	///////////////////
	//	Constructor
	///////////////////
	public static ConsumableItem create(DataDictionary dict) {
		return GameItemLoader.loadConsumableItem(dict);
	}
	public ConsumableItem() {
		super();
		this.range = new TemplateInfo();
		this.effectTemplate = new TemplateInfo();
	}
	public ConsumableItem(String iname, String idescription, ItemType itype, EnumValue isubType, int ivalue, int ilevel,
			int isize, double iweight, boolean iusable, boolean istackable, boolean ishoppable, boolean idroppable,
			TemplateInfo irange, TemplateInfo ieffectTemplate, ArrayList<BaseEffect> ionEquipEffects, 
			ArrayList<BaseEffect> ionUseEffects, ArrayList<StatBonus> ionEquipBonuses) {
		super(iname, idescription, itype, isubType, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, ionEquipEffects, ionUseEffects, ionEquipBonuses);
		this.range = irange;
		this.effectTemplate = ieffectTemplate;
	}
	
	
	
	
	//////////////////////
	//	Public Functions
	//////////////////////
	
	
	/////////////////////
	//	Getters/Setters
	//////////////////////
	public TemplateInfo getRange() { return this.range; }
	public TemplateInfo geEffectTemplate() { return this.effectTemplate; }
	
	
	
	
	//////////////
	//	Loader
	//////////////
//	public ConsumableItem load(DataDictionary dict) {
//		super.load(dict);
//		String job = "N/A";
//		try {
//			job = "range"; this.range = TemplateInfo.create(dict.getSubDictionary("range"));
//			job = "effectTemplate"; this.effectTemplate = TemplateInfo.create(dict.getSubDictionary("effectTemplate"));
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("ITEM-LOAD", ("Consumable.load, Item = "+this.getName()+" at = " + job), err);
//		}
//		return this;
//	}

	

}