package org.vagosduke.andengine.radiance.game.items;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.items.ItemTypes.ItemType;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.program.loader.GameItemLoader;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class OtherItem extends BaseItem {
	
	
	
	///////////////////
	//	Constructor
	///////////////////
	public static OtherItem create(DataDictionary dict) {
		return GameItemLoader.loadOtherItem(dict);
	}
	public OtherItem() {
		super();
	}
	public OtherItem(String iname, String idescription, ItemType itype, EnumValue isubType, int ivalue, int ilevel,
			int isize, double iweight, boolean iusable, boolean istackable, boolean ishoppable, boolean idroppable,
			ArrayList<BaseEffect> ionEquipEffects, ArrayList<BaseEffect> ionUseEffects, 
			ArrayList<StatBonus> ionEquipBonuses) {
		super(iname, idescription, itype, isubType, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, ionEquipEffects, ionUseEffects, ionEquipBonuses);
	}


	
	//////////////////////
	//	Public Functions
	//////////////////////
	
	
	
	/////////////////////
	//	Getters/Setters
	//////////////////////	
	
	
	
	//////////////
	//	Loader
	//////////////
//	public OtherItem load(DataDictionary dict) {
//		super.load(dict);
//		return this;
//	}
}
