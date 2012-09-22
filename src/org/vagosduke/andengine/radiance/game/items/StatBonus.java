package org.vagosduke.andengine.radiance.game.items;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.character.AttributesModule.Attributes;

public class StatBonus {
	
	
	
	private Attributes statName;
	private double amount;
	
	
	/////////////////////
	//	Constructors
	/////////////////////
	public StatBonus(Attributes istatName, double iamount) {
		this.statName = istatName;
		this.amount = iamount;
	}


	
	
		
	/////////////////////
	//	Public Functions
	/////////////////////
	public void apply(Character mychar) {
		switch (this.statName) {
		case ARMOR: mychar.getAttributesModule().increaseBonusArmor((int) amount);
		case DODGE: mychar.getAttributesModule().increaseBonusDodge(amount);
		case BLOCK: mychar.getAttributesModule().increaseBonusBlock(amount);
		case CRITICAL: mychar.getAttributesModule().increaseBonusCritical(amount);
		case ATTACKRESIST: mychar.getAttributesModule().increaseBonusAttackResistance(amount);
		case MAGICRESIST: mychar.getAttributesModule().increaseBonusMagicResistance(amount);
		case OFFENSE: mychar.getAttributesModule().increaseBonusOffense((int) amount);
		case DEFENSE: mychar.getAttributesModule().increaseBonusDefense((int) amount);
		case ABILITY: mychar.getAttributesModule().increaseBonusAbility((int) amount);
		case SPEED: mychar.getAttributesModule().increaseBonusSpeed((int) amount);
		case HITPOINTMAX: mychar.getAttributesModule().increaseBonusHitPointMax((int) amount);
		case ABILITYPOINTMAX: mychar.getAttributesModule().increaseBonusAbilityPointMax((int) amount);
		}
	}
	public void revert(Character mychar) {
		switch (this.statName) {
		case ARMOR: mychar.getAttributesModule().decreaseBonusArmor((int) amount);
		case DODGE: mychar.getAttributesModule().decreaseBonusDodge(amount);
		case BLOCK: mychar.getAttributesModule().decreaseBonusBlock(amount);
		case CRITICAL: mychar.getAttributesModule().decreaseBonusCritical(amount);
		case ATTACKRESIST: mychar.getAttributesModule().decreaseBonusAttackResistance(amount);
		case MAGICRESIST: mychar.getAttributesModule().decreaseBonusMagicResistance(amount);
		case OFFENSE: mychar.getAttributesModule().decreaseBonusOffense((int) amount);
		case DEFENSE: mychar.getAttributesModule().decreaseBonusDefense((int) amount);
		case ABILITY: mychar.getAttributesModule().decreaseBonusAbility((int) amount);
		case SPEED: mychar.getAttributesModule().decreaseBonusSpeed((int) amount);
		case HITPOINTMAX: mychar.getAttributesModule().decreaseBonusHitPointMax((int) amount);
		case ABILITYPOINTMAX: mychar.getAttributesModule().decreaseBonusAbilityPointMax((int) amount);
		}
	}
	
	
	public String toString() {
		char sign = '+';
		if(this.amount < 0) { sign = '-'; }
		return (this.statName.toString() +":" + sign + this.amount);
	}

	
	
	
	/////////////////////
	//	Loader
	///////////////////////
//	public StatBonus load(DataDictionary dict) throws Exception{
//		/**
//		 *  matches to: 
//		 *  stat:	*charScores*
//		 *  amount:	*int or double*
//		 */
//		String job = "N/A";
//		String statn = "N/A";
//		try {
//			job = "stat";
//			statn = dict.getString("stat").toUpperCase();
//			this.statName = Attributes.valueOf(statn);
//			job = "amount"; this.amount = dict.getDouble("amount");
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("STATBONUS-LOAD", ("StatBonus.load, stat=\"" + statn + "\" at=" + job), err);
//			throw err;
//		}
//		return this;
//	}
	


}
