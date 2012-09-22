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

package org.vagosduke.andengine.radiance.game.character;

import java.text.DecimalFormat;

import org.vagosduke.andengine.radiance.util.DataDictionary;


public class AttributesModule {
	/**
	 * This manages derived attributes from skills, items, buffs, etc.
	 * dodge, critical etc. are based on the 4 attributes plus various bonuses.
	 * We assume attributes have an absolute maximum of 99 in order to calculate maximum base
	 */
	
	public static enum Attributes { ARMOR, DODGE, BLOCK, CRITICAL, ATTACKRESIST, MAGICRESIST, 
									OFFENSE, DEFENSE, ABILITY, SPEED, HITPOINTMAX, ABILITYPOINTMAX };	
	
	
	////////////////////////////
	//	default values
	////////////////////////////
	public static final int defaulBaseLevel = 1;
	public static final int defaulBaseOffense = 10;
	public static final int defaulBaseDefense = 10;
	public static final int defaulBaseAbility = 10;
	public static final int defaulBaseSpeed = 10;
	public static final int defaulBaseHitPointMax = 10;
	public static final int defaulBaseAbilityPointMax = 10;
	public static final int defaulbaseArmor = 0;
	
	public static final double defaultStaticDodge = 0.02;
	public static final double defaultStaticBlock = 0.02;
	public static final double defaultStaticCritical = 0.02;
	public static final double defaultStaticMagicResist = 0.02;
	public static final double defaultStaticAttackResist = 0.02;
	
	public static final double defaultDodgeOffenseMod = 0.0;
	public static final double defaultDodgeDefenseMod = 0.0005;
	public static final double defaultDodgeAbilityMod = 0.0;
	public static final double defaultDodgeSpeedMod = 0.001;
	
	public static final double defaultBlockOffenseMod = 0.0005;
	public static final double defaultBlockDefenseMod = 0.0005;
	public static final double defaultBlockAbilityMod = 0.0;
	public static final double defaultBlockSpeedMod = 0.0;

	public static final double defaultCriticalOffenseMod = 0.001;
	public static final double defaultCriticalDefenseMod = 0.0;
	public static final double defaultCriticalAbilityMod = 0.0;
	public static final double defaultCriticalSpeedMod = 0.0005;
	
	public static final double defaultAttackResistOffenseMod = 0.0;
	public static final double defaultAttackResistDefenseMod = 0.0005;
	public static final double defaultAttackResistAbilityMod = 0.0;
	public static final double defaultAttackResistSpeedMod = 0.0;
	public static final double defaultAttackResistArmorMod = 0.002;
	
	public static final double defaultMagicResistOffenseMod = 0.0;
	public static final double defaultMagicResistDefenseMod = 0.0005;
	public static final double defaultMagicResistAbilityMod = 0.001;
	public static final double defaultMagicResistSpeedMod = 0.0;
	
	
	
	
	
	// Character
	private Character myCharacter;
	
	////////////////////////////
	//	base attributes
	////////////////////////////
	// Base non-derived class attributes
	private int baseLevel;
	private int baseOffense;
	private int baseDefense;
	private int baseAbility;
	private int baseSpeed;
	private int baseHitPointMax;
	private int baseAbilityPointMax;
		
	// Bonus non-derived class attributes
	private int level;
	private int bonusOffense;
	private int bonusDefense;
	private int bonusAbility;
	private int bonusSpeed;
	private int bonusHitPointMax;
	private int bonusAbilityPointMax;
	
	// Derived attributes flat initial values (no items or bonuses)
	private int baseArmor;
	private double staticDodge;
	private double staticBlock;
	private double staticCritical;
	private double staticMagicResist;
	private double staticAttackResist;
	
	// Base derived attributes	(calculated from non-derived)
	private double baseDodgeChance;							// % chance to totally evade an attack
	private double baseBlockChance;							// % chance to totally block or parry an attack
	private double baseCriticalChance;						// chance to do critical strike
	private double baseAttackResistance;					// general attack damage reduction
	private double baseMagicResistance;						// general magic damage reduction
	
	// Bonus derived attributes (from buffs etc.)
	private int bonusArmor;	
	private double bonusDodgeChance;
	private double bonusBlockChance;
	private double bonusCriticalChance;
	private double bonusAttackResistance;
	private double bonusMagicResistance;
	
	

	
	
	
	////////////////////////////////////////////////
	//	Derived attributes calculation modifiers
	////////////////////////////////////////////////
	private double dodgeOffenseMod;
	private double dodgeDefenseMod;
	private double dodgeAbilityMod;
	private double dodgeSpeedMod;
	
	private double blockOffenseMod;
	private double blockDefenseMod;
	private double blockAbilityMod;
	private double blockSpeedMod;

	private double criticalOffenseMod;
	private double criticalDefenseMod;
	private double criticalAbilityMod;
	private double criticalSpeedMod;
	
	private double attackResistOffenseMod;
	private double attackResistDefenseMod;
	private double attackResistAbilityMod;
	private double attackResistSpeedMod;
	private double attackResistArmorMod;
	
	private double magicResistOffenseMod;
	private double magicResistDefenseMod;
	private double magicResistAbilityMod;
	private double magicResistSpeedMod;
	
	
	
	
	
	
	
	
	
	
	////////////////////////
	//	Costructor
	/////////////////////////
	public AttributesModule(Character myCharacter, int baseLevel, int baseOffense, int baseDefense, int baseAbility, 
			int baseSpeed, int baseHitPointMax, int baseAbilityPointMax, int baseArmor, double staticDodge,
			double staticBlock, double staticCritical, double staticMagicResist, double staticAttackResist,
			double dodgeOffenseMod, double dodgeDefenseMod, double dodgeAbilityMod, double dodgeSpeedMod,
			double blockOffenseMod, double blockDefenseMod, double blockAbilityMod, double blockSpeedMod,
			double criticalOffenseMod, double criticalDefenseMod, double criticalAbilityMod, double criticalSpeedMod,
			double attackResistOffenseMod, double attackResistDefenseMod, double attackResistAbilityMod, 
			double attackResistSpeedMod, double attackResistArmorMod, double magicResistOffenseMod,
			double magicResistDefenseMod, double magicResistAbilityMod, double magicResistSpeedMod) {
		this.myCharacter = myCharacter;
		this.baseLevel = baseLevel;
		this.baseOffense = baseOffense;
		this.baseDefense = baseDefense;
		this.baseAbility = baseAbility;
		this.baseSpeed = baseSpeed;
		this.baseHitPointMax = baseHitPointMax;
		this.baseAbilityPointMax = baseAbilityPointMax;
		this.baseArmor = baseArmor;
		this.staticDodge = staticDodge;
		this.staticBlock = staticBlock;
		this.staticCritical = staticCritical;
		this.staticMagicResist = staticMagicResist;
		this.staticAttackResist = staticAttackResist;
		this.dodgeOffenseMod = dodgeOffenseMod;
		this.dodgeDefenseMod = dodgeDefenseMod;
		this.dodgeAbilityMod = dodgeAbilityMod;
		this.dodgeSpeedMod = dodgeSpeedMod;
		this.blockOffenseMod = blockOffenseMod;
		this.blockDefenseMod = blockDefenseMod;
		this.blockAbilityMod = blockAbilityMod;
		this.blockSpeedMod = blockSpeedMod;
		this.criticalOffenseMod = criticalOffenseMod;
		this.criticalDefenseMod = criticalDefenseMod;
		this.criticalAbilityMod = criticalAbilityMod;
		this.criticalSpeedMod = criticalSpeedMod;
		this.attackResistOffenseMod = attackResistOffenseMod;
		this.attackResistDefenseMod = attackResistDefenseMod;
		this.attackResistAbilityMod = attackResistAbilityMod;
		this.attackResistSpeedMod = attackResistSpeedMod;
		this.attackResistArmorMod = attackResistArmorMod;
		this.magicResistOffenseMod = magicResistOffenseMod;
		this.magicResistDefenseMod = magicResistDefenseMod;
		this.magicResistAbilityMod = magicResistAbilityMod;
		this.magicResistSpeedMod = magicResistSpeedMod;
		
		this.refresh();
	}






	//////////////////////////
	//	Public Functions
	//////////////////////////
	public void refresh() {
		this.purgeBonuses();
		this.calculateDerivedValues();
	}
	
	public void calculateDodgeChance() {
		this.baseDodgeChance =  (staticDodge + 
						(((double)this.getOffense()) * dodgeOffenseMod) +
						(((double)this.getDefense()) * dodgeDefenseMod) +
						(((double)this.getAbility())* dodgeAbilityMod) +
						(((double)this.getSpeed()) * dodgeSpeedMod));
	}
	public void calculateBlockChance() {
		this.baseBlockChance =  (staticBlock + 
					(((double)this.getOffense()) * blockOffenseMod) +
					(((double)this.getDefense()) * blockDefenseMod) +
					(((double)this.getAbility())* blockAbilityMod) +
					(((double)this.getSpeed()) * blockSpeedMod));
	}
	public void calculateCriticalChance() {
		this.baseCriticalChance = (staticCritical + 
					(((double)this.getOffense()) * criticalOffenseMod) +
					(((double)this.getDefense()) * criticalDefenseMod) +
					(((double)this.getAbility())* criticalAbilityMod) +
					(((double)this.getSpeed()) * criticalSpeedMod));
	}
	public void calculateAttackResistance() {
		this.baseAttackResistance = (staticAttackResist + 
					(((double)this.getOffense()) * attackResistOffenseMod) +
					(((double)this.getDefense()) * attackResistDefenseMod) +
					(((double)this.getAbility())* attackResistAbilityMod) +
					(((double)this.getSpeed()) * attackResistSpeedMod) +
					(((double)this.getTotalArmor()) * attackResistArmorMod));
	}
	public void calculatMagicResistance() {
		this.baseMagicResistance = (staticMagicResist + 
					(((double)this.getOffense()) * magicResistOffenseMod) +
					(((double)this.getDefense()) * magicResistDefenseMod) +
					(((double)this.getAbility())* magicResistAbilityMod) +
					(((double)this.getSpeed()) * magicResistSpeedMod));
	}

	
	
	
	
	
	
	/////////////////////
	// Getters/Setters
	/////////////////////
	public int getLevel() { return this.level; }
	public int getOffense() { return (this.baseOffense + this.bonusOffense); }
	public int getDefense() { return (this.baseDefense + this.bonusDefense); }
	public int getAbility() { return (this.baseAbility + this.bonusAbility); }
	public int getSpeed() { return (this.baseSpeed + this.bonusSpeed); }
	public int getHitPointMax() { return (this.baseHitPointMax + this.bonusHitPointMax); }
	public int getAbilityPointMax() { return (this.baseAbilityPointMax + this.bonusAbilityPointMax); }
	public int getTotalArmor() { return (this.baseArmor + this.bonusArmor); }
	public double getDodgeChance() { return (this.baseDodgeChance + this.bonusDodgeChance); }
	public double getBlockChance() { return (this.baseBlockChance + this.bonusBlockChance); }
	public double getCriticalChance() { return (this.baseCriticalChance + this.bonusCriticalChance); }
	public double getAttackResistance() { return (this.baseAttackResistance + this.bonusAttackResistance); }
	public double getMagicResistance() { return (this.baseMagicResistance + this.bonusMagicResistance); }
	
	public int getBaseLevel() { return this.baseLevel; }
	public int getBaseOffense() { return this.baseOffense; }
	public int getBaseDefense() { return this.baseDefense; }
	public int getBaseAbility() { return this.baseAbility; }
	public int getBaseSpeed() { return this.baseSpeed; }
	public int getBaseHitPointMax() { return this.baseHitPointMax; }
	public int getBaseAbilityPointMax() { return this.baseAbilityPointMax; }
	public int getbaseArmor() { return this.baseArmor; }
	public double getBaseDodgeChance() { return this.baseDodgeChance; }
	public double getBaseBlockChance() { return this.baseBlockChance; }
	public double getBaseMagicResistance() { return this.baseMagicResistance; }
	public double getBaseAttackResistance() { return this.baseAttackResistance; }
	public double getBaseCriticalChance() { return this.baseCriticalChance; }
	
	public void increaseBonusOffense(int amount) { this.bonusOffense += amount; }
	public void increaseBonusDefense(int amount) { this.bonusDefense += amount; }
	public void increaseBonusAbility(int amount) { this.bonusAbility += amount; }
	public void increaseBonusSpeed(int amount) { this.bonusSpeed += amount; }
	public void increaseBonusHitPointMax(int amount) { this.bonusHitPointMax += amount; }
	public void increaseBonusAbilityPointMax(int amount) { this.bonusAbilityPointMax += amount; }
	public void increaseBonusArmor(int amount) { this.bonusArmor += amount; }
	public void increaseBonusDodge(double amount) { this.bonusDodgeChance += amount; }
	public void increaseBonusBlock(double amount) { this.bonusBlockChance += amount; }
	public void increaseBonusCritical(double amount) { this.bonusCriticalChance += amount; }
	public void increaseBonusAttackResistance(double amount) { this.bonusAttackResistance += amount; }
	public void increaseBonusMagicResistance(double amount) { this.bonusMagicResistance += amount; }
	
	public void decreaseBonusOffense(int amount) { this.bonusOffense -= amount; }
	public void decreaseBonusDefense(int amount) { this.bonusDefense -= amount; }
	public void decreaseBonusAbility(int amount) { this.bonusAbility -= amount; }
	public void decreaseBonusSpeed(int amount) { this.bonusSpeed -= amount; }
	public void decreaseBonusHitPointMax(int amount) { this.bonusHitPointMax -= amount; }
	public void decreaseBonusAbilityPointMax(int amount) { this.bonusAbilityPointMax -= amount; }
	public void decreaseBonusArmor(int amount) { this.bonusArmor -= amount; }
	public void decreaseBonusDodge(double amount) { this.bonusDodgeChance -= amount; }
	public void decreaseBonusBlock(double amount) { this.bonusBlockChance -= amount; }
	public void decreaseBonusCritical(double amount) { this.bonusCriticalChance -= amount; }
	public void decreaseBonusAttackResistance(double amount) { this.bonusAttackResistance -= amount; }
	public void decreaseBonusMagicResistance(double amount) { this.bonusMagicResistance -= amount; }
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		str.append("=== ATTRIBUTES ===\n");
		str.append("BASE:\n");
		str.append("   Level = " + this.getLevel() + "\n");
		str.append("   Offense = " + this.getOffense() + "\n");
		str.append("   Defense = " + this.getDefense() + "\n");
		str.append("   Ability = " + this.getAbility() + "\n");
		str.append("   Speed = " + this.getSpeed() + "\n");
		str.append("   HitPointMax = " + this.getHitPointMax() + "\n");
		str.append("   AbilityPointMax = " + this.getAbilityPointMax() + "\n");
		str.append("DERIVED:\n");
		str.append("   Armor = " + this.getTotalArmor() + "\n");
		str.append("   Dodge = " + twoDForm.format(this.getDodgeChance()*100.0) + "%\n");
		str.append("   Block = " + twoDForm.format(this.getBlockChance()*100.0) + "%\n");
		str.append("   Critical = " + twoDForm.format(this.getCriticalChance()*100.0) + "%\n");
		str.append("   Attack Resistance = " + twoDForm.format(this.getAttackResistance()*100.0) + "%\n");
		str.append("   Magic Resistance = " + twoDForm.format(this.getMagicResistance()*100.0) + "%\n");
		return str.toString();
	}
	
	
	
	
	
	
	////////////////////////
	//	Private Methods
	////////////////////////
	private void calculateDerivedValues() {
		this.calculateDodgeChance();
		this.calculateBlockChance();
		this.calculateCriticalChance();
		this.calculateAttackResistance();
		this.calculatMagicResistance();
	}
	private void purgeBonuses() {
		level = baseLevel;
		bonusOffense = 0;
		bonusDefense = 0;
		bonusAbility = 0;
		bonusSpeed = 0;
		bonusHitPointMax = 0;
		bonusAbilityPointMax = 0;
		
		bonusArmor = 0;
		bonusDodgeChance = 0.0;
		bonusBlockChance = 0.0;
		bonusCriticalChance = 0.0;
		bonusAttackResistance = 0.0;
		bonusMagicResistance = 0.0;
	}
	
}
