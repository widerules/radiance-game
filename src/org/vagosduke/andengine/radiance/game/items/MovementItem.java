package org.vagosduke.andengine.radiance.game.items;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.items.ItemTypes.ItemType;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.program.loader.GameItemLoader;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class MovementItem extends BaseItem {

	private double speedModifier;
	
	
	///////////////////
	//	Constructor
	///////////////////
	public static MovementItem create(DataDictionary dict) {
		return GameItemLoader.loadMovementItem(dict);
	}
	public MovementItem() {
		super();
		this.speedModifier = 0.0;
	}
	public MovementItem(String iname, String idescription, ItemType itype, EnumValue isubType, int ivalue, int ilevel,
			int isize, double iweight, boolean iusable, boolean istackable, boolean ishoppable, boolean idroppable,
			double ispeedModifier, ArrayList<BaseEffect> ionEquipEffects, ArrayList<BaseEffect> ionUseEffects, 
			ArrayList<StatBonus> ionEquipBonuses) {
		super(iname, idescription, itype, isubType, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, ionEquipEffects, ionUseEffects, ionEquipBonuses);
		this.speedModifier = ispeedModifier;
	}
	
	/////////////////
	//	Getters
	////////////////
	public double getSpeedModifier() { return this.speedModifier; }

	
	
	
	
	/////////////
	//	Loader
	//////////////
//	public MovementItem load(DataDictionary dict) {
//		super.load(dict);
//		String job = "N/A";
//		try {
//			job = "speedModifier"; this.speedModifier = dict.getInteger("speedModifier");
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("ITEM-LOAD", ("BaseItem.load, Item=\""+this.getName()+"\" at=" + job), err);
//		}
//		return this;
//	}
	
}
