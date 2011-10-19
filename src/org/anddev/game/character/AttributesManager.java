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

package org.anddev.game.character;


public class AttributesManager {
	/**
	 * This manages derived attributes from skills, items, buffs, etc.
	 */
	
	public static enum Attributes { ARMOR, DODGE, BLOCK, CRITICAL, ATTACKRES, MAGICRES };
	
	
	private static final double staticDodge = 0.02;			//	2%
	private static final double staticBlock = 0.02;
	private static final double staticCritical = 0.02;
	private static final double staticMagicResist = 0.02;
	private static final double staticAttackResist = 0.02;
	
	////////////////////////////
	//	attribute modifiers
	////////////////////////////
	private final static double dodgeOffenseMod = 0.0;
	private final static double dodgeDefenseMod = 0.0005;
	private final static double dodgeMagicMod = 0.0;
	private final static double dodgeSpeedMod = 0.001;
	
	private final static double blockOffenseMod = 0.0005;
	private final static double blockDefenseMod = 0.0005;
	private final static double blockMagicMod = 0.0;
	private final static double blockSpeedMod = 0.0;
	private final static double blockShieldMod = 1.0;

	private final static double criticalOffenseMod = 0.001;
	private final static double criticalDefenseMod = 0.0;
	private final static double criticalMagicMod = 0.0;
	private final static double criticalSpeedMod = 0.0005;
	
	private final static double attackResistOffenseMod = 0.0;
	private final static double attackResistDefenseMod = 0.0005;
	private final static double attackResistMagicMod = 0.0;
	private final static double attackResistSpeedMod = 0.0;
	private final static double attackResistArmorMod = 0.002;
	
	private final static double magicResistOffenseMod = 0.0;
	private final static double magicResistDefenseMod = 0.0005;
	private final static double magicResistMagicMod = 0.001;
	private final static double magicResistSpeedMod = 0.0;
	
	
	// Character
	private Character myCharacter;
		
	// Base statistical values (calculated from attributes etc.)
	private int baseArmor;									// total armor
	private double baseDodgeChance;							// % chance to evade totally an attack
	private double baseBlockChance;							// % chance to totally block or parry an attack
	private double baseCriticalChance;						// chance to do critical strike
	private double baseAttackResistance;					// general attack damage reduction
	private double baseMagicResistance;						// general magic damage reduction

	
	// Bonus statistical values (from buffs etc.
	private int bonusArmor;									// total armor
	private double bonusDodgeChance;						// % chance to evade totally an attack
	private double bonusBlockChance;						// % chance to totally block or parry an attack
	private double bonusCriticalChance;						// chance to do critical strike
	private double bonusAttackResistance;					// general attack damage reduction
	private double bonusMagicResistance;					// general magic damage reduction
	
	
	
	
	
	
	
	
	
	
	////////////////////////
	//	Costructor
	/////////////////////////
	public AttributesManager(Character icharacter) {
		this.myCharacter = icharacter;
		
		this.baseArmor = 0;
		this.baseDodgeChance = staticDodge;
		this.baseBlockChance = staticBlock;
		this.baseCriticalChance = staticAttackResist;
		this.baseAttackResistance = staticMagicResist;
		this.baseMagicResistance =  staticCritical;
		
		this.bonusArmor = 0;
		this.bonusDodgeChance = 0.0;
		this.bonusBlockChance = 0.0;
		this.bonusCriticalChance = 0.0;
		this.bonusAttackResistance = 0.0;
		this.bonusMagicResistance = 0.0;
	}
	
	
	
	
	
	
	
	
	
	//////////////////////////
	//	Publc Functions
	//////////////////////////
	//	dodge, critical etc. are based on the 4 attributes plus various bonuses.
	//	We assume attributes have an absolute maximum of 99 in order to calculate maximum base
	public void calculateValues() {
		this.calculateDodgeChance();
		this.calculateBlockChance();
		this.calculateCriticalChance();
		this.calculateAttackResistance();
		this.calculatMagicResistance();
	}
	
	public void calculateDodgeChance() {
		SkillsManager stats = myCharacter.getSkillsManager();
		this.baseDodgeChance =  (staticDodge + 
						(((double)stats.getOffense()) * dodgeOffenseMod) +
						(((double)stats.getDefense()) * dodgeDefenseMod) +
						(((double)stats.getMagic())* dodgeMagicMod) +
						(((double)stats.getSpeed()) * dodgeSpeedMod));
	}
	public void calculateBlockChance() {
		SkillsManager stats = myCharacter.getSkillsManager();
		this.baseBlockChance =  (staticDodge + 
					(((double)stats.getOffense()) * blockOffenseMod) +
					(((double)stats.getDefense()) * blockDefenseMod) +
					(((double)stats.getMagic())* blockMagicMod) +
					(((double)stats.getSpeed()) * blockSpeedMod) +
					(myCharacter.getInventoryManager().getShieldSlot().getArmor() * blockShieldMod));
	}
	public void calculateCriticalChance() {
		SkillsManager stats = myCharacter.getSkillsManager();
		this.baseCriticalChance = (staticCritical + 
					(((double)stats.getOffense()) * criticalOffenseMod) +
					(((double)stats.getDefense()) * criticalDefenseMod) +
					(((double)stats.getMagic())* criticalMagicMod) +
					(((double)stats.getSpeed()) * criticalSpeedMod));
	}
	public void calculateAttackResistance() {
		SkillsManager stats = myCharacter.getSkillsManager();
		this.baseAttackResistance = (staticAttackResist + 
					(((double)stats.getOffense()) * attackResistOffenseMod) +
					(((double)stats.getDefense()) * attackResistDefenseMod) +
					(((double)stats.getMagic())* attackResistMagicMod) +
					(((double)stats.getSpeed()) * attackResistSpeedMod) +
					(((double)this.getTotalArmor()) * attackResistArmorMod));
	}
	public void calculatMagicResistance() {
		SkillsManager stats = myCharacter.getSkillsManager();
		this.baseMagicResistance = (staticMagicResist + 
					(((double)stats.getOffense()) * magicResistOffenseMod) +
					(((double)stats.getDefense()) * magicResistDefenseMod) +
					(((double)stats.getMagic())* magicResistMagicMod) +
					(((double)stats.getSpeed()) * magicResistSpeedMod));
	}

	
	
	
	
	
	
	/////////////////////
	// Getters/Setters
	/////////////////////
	public void increaseBonusArmor(int ammount) { this.bonusArmor += ammount; }
	public void increaseBonusDodge(double ammount) { this.bonusDodgeChance += ammount; }
	public void increaseBonusBlock(double ammount) { this.bonusBlockChance += ammount; }
	public void increaseBonusCritical(double ammount) { this.bonusCriticalChance += ammount; }
	public void increaseBonusAttackResistance(double ammount) { this.bonusAttackResistance += ammount; }
	public void increaseBonusMagicResistance(double ammount) { this.bonusMagicResistance += ammount; }
	
	public void decreaseBonusArmor(int ammount) { this.bonusArmor -= ammount; }
	public void decreaseBonusDodge(double ammount) { this.bonusDodgeChance -= ammount; }
	public void decreaseBonusBlock(double ammount) { this.bonusBlockChance -= ammount; }
	public void decreaseBonusCritical(double ammount) { this.bonusCriticalChance -= ammount; }
	public void decreaseBonusAttackResistance(double ammount) { this.bonusAttackResistance -= ammount; }
	public void decreaseBonusMagicResistance(double ammount) { this.bonusMagicResistance -= ammount; }
	
	public int getBaseArmor() { return this.baseArmor; }
	public double getBaseDodgeChance() { return this.baseDodgeChance; }
	public double getBaseBlockChance() { return this.baseBlockChance; }
	public double getBaseMagicResistance() { return this.baseMagicResistance; }
	public double getBaseAttackResistance() { return this.baseAttackResistance; }
	public double getBaseCriticalChance() { return this.baseCriticalChance; }
	
	public int getTotalArmor() { return (this.baseArmor + this.bonusArmor); }
	public double getDodgeChance() { return (this.baseDodgeChance + this.bonusDodgeChance); }
	public double getBlockChance() { return (this.baseBlockChance + this.bonusBlockChance); }
	public double getCriticalChance() { return (this.baseCriticalChance + this.bonusCriticalChance); }
	public double getAttackResistance() { return (this.baseAttackResistance + this.bonusAttackResistance); }
	public double getMagicResistance() { return (this.baseMagicResistance + this.bonusMagicResistance); }
}
