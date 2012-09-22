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
import java.util.Random;

import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.items.ItemTypes.ItemType;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.program.loader.GameItemLoader;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class WeaponItem extends BaseItem {
	
	public static class WeaponDamage {
		double min;
		double max;
		EnumValue DamageType;
		
		public WeaponDamage(double minDamage, double maxDamage, EnumValue damType) {
			min = minDamage; 
			this.max = maxDamage;
			this.DamageType = damType;
		}
	}
	
	private ArrayList<WeaponDamage> damage;
	private TemplateInfo range;
	private TemplateInfo effectTemplate;
	
	
	
	///////////////////
	//	Constructor
	///////////////////
	public static WeaponItem create(DataDictionary dict) {
		return GameItemLoader.loadWeaponItem(dict);
	}
	public WeaponItem() {
		super();
		this.damage = new ArrayList<WeaponDamage>();
		this.range = new TemplateInfo();
		this.effectTemplate = new TemplateInfo();
	}
	public WeaponItem(String iname, String idescription, ItemType itype, EnumValue isubType, int ivalue, int ilevel,
			int isize, double iweight, boolean iusable, boolean istackable, boolean ishoppable, boolean idroppable,
			ArrayList<WeaponDamage> idamage, TemplateInfo irange, TemplateInfo ieffectTemplate,ArrayList<BaseEffect> ionEquipEffects,
			ArrayList<BaseEffect> ionUseEffects, ArrayList<StatBonus> ionEquipBonuses) {
		super(iname, idescription, itype, isubType, ivalue, ilevel, isize, iweight, iusable, istackable, 
				ishoppable, idroppable, ionEquipEffects, ionUseEffects, ionEquipBonuses);
		this.damage = idamage;
		this.range = irange;
		this.effectTemplate = ieffectTemplate;
	}
	
	
	
	
	
	
	/////////////////////
	//	Public Functions
	/////////////////////
	public ArrayList<Damage> generateDamage() {
		/** 
		 * generate damage values for all min-max damageType pairs in Gaussian distribution
		 */
		Random gen = new Random();
		ArrayList<Damage> rawDamage = new ArrayList<Damage>();
		for(WeaponDamage wepdmg: this.damage) {
			double amount = gen.nextGaussian();
			double mean = (wepdmg.max - wepdmg.min)/2.0;
			amount += mean + wepdmg.min;		// set mean value
			amount *= mean;
			rawDamage.add(new Damage((int)amount, wepdmg.DamageType));
		}
		return rawDamage;
		
	}
	
	
	
	/////////////////
	//	Getters
	////////////////
	public ArrayList<WeaponDamage> getDamage() { return this.damage; }
	public TemplateInfo getRange() { return this.range; }
	public TemplateInfo geEffectTemplate() { return this.effectTemplate; }
	
	
	

	
	
	/////////////
	//	Loader
	//////////////
//	public WeaponItem load(DataDictionary dict) {
//		super.load(dict);
//		
//		String job = "N/A";
//		try {
//			job = "range"; this.range = TemplateInfo.create(dict.getSubDictionary("range"));
//			job = "effectTemplate"; this.effectTemplate = TemplateInfo.create(dict.getSubDictionary("effectTemplate"));
//			job = "damage"; 
//			DataDictionary dmgArray = dict.getSubArray("damage");
//			for(int i=0; i<dmgArray.getArraySize(); i++) {
//				DataDictionary dmgDict = dmgArray.getSubDictionary(i);
//				EnumValue dtype = Damage.DMGtype.valueOf(dmgDict.getString("damageType").toUpperCase());
//				double min = dmgDict.getDouble("min");
//				double max = dmgDict.getDouble("max");
//				this.damage.add(new WeaponDamage(min, max, dtype));
//			}
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("ITEM-LOAD", ("Weapon.load, Item = "+this.getName()+" at = " + job), err);
//		}
//		return this;
//	}
}