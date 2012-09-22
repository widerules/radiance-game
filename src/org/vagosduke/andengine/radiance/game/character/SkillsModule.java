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


public class SkillsModule {
	/**
	 * Character-Class module that manages non-derived (class-specific) skill values
	 */
	
	public static enum skills { OFFENSE, DEFENSE, ABILITY, SPEED, HITPOINTMAX, ABILITYPOINTMAX }
	
	// Character
	private Character myCharacter;
	
	
	
	
	// Base class skills (normally from growth etc)
	private int baseLevel;
	private int baseOffense;
	private int baseDefense;
	private int baseAbility;
	private int baseSpeed;
	private int baseHitPointMax;
	private int baseAbilityPointMax;
	
	// bonuses from effects, items, etc
	private int level;
	private int bonusOffense;
	private int bonusDefense;
	private int bonusAbility;
	private int bonusSpeed;
	private int bonusHitPointMax;
	private int bonusAbilityPointMax;
	

	
	
	
	
	
	
	///////////////////////
	//	Constructors
	///////////////////////
	public SkillsModule(Character icharacter) {
		/**
		 * Default constructor
		 */
		this.myCharacter = icharacter;
		
		this.baseLevel = 1;
		this.baseOffense = 10;
		this.baseDefense = 10;
		this.baseAbility = 10;
		this.baseSpeed = 10;
		this.baseHitPointMax = 10;
		this.baseAbilityPointMax = 10;
		
		this.level = this.baseLevel;
		this.bonusOffense = 0;
		this.bonusDefense = 0;
		this.bonusAbility = 0;
		this.bonusSpeed = 0;
		this.bonusHitPointMax = 0;
		this.bonusAbilityPointMax = 0;
	}
	
	
	
	
	
	
	
	///////////////////////
	//	Public Methods
	///////////////////////
	
	/*
	protected void levelUP(GrowthManager growth) {
		Random generator = new Random();
		float inc;
		
		// stat increase should be a random number 
		// from 50% to 150% of growth stat. Rounded to the nearest
		inc = (float) ( (generator.nextDouble()+0.5) * (double)growth.HPGrowth());
		this.baseHitPointMax = Math.round(inc);
		inc = (float) ( (generator.nextDouble()+0.5) * (double)growth.MPGrowth());
		this.baseMagicPointMax = Math.round(inc);
		inc = (float) ( (generator.nextDouble()+0.5) * (double)growth.attackGrowth());
		this.baseAttack = Math.round(inc);
		inc = (float) ( (generator.nextDouble()+0.5) * (double)growth.defenceGrowth());
		this.baseDefense = Math.round(inc);
		inc = (float) ( (generator.nextDouble()+0.5) * (double)growth.magicGrowth());
		this.baseMagic = Math.round(inc);
		inc = (float) ( (generator.nextDouble()+0.5) * (double)growth.speedGrowth());
		this.baseSpeed = Math.round(inc);
	}
	*/
	
	
	
	
	

	
	
	
	/////////////////////////////////////////
	//	Getters / setters
	/////////////////////////////////////////
	public void increaseBonusOffense(int amount) { this.bonusOffense += amount; }
	public void increaseBonusDefense(int amount) { this.bonusDefense += amount; }
	public void increaseBonusAbility(int amount) { this.bonusAbility += amount; }
	public void increaseBonusSpeed(int amount) { this.bonusSpeed += amount; }
	public void increaseBonusHitPointMax(int amount) { this.bonusHitPointMax += amount; }
	public void increaseBonusAbilityPointMax(int amount) { this.bonusAbilityPointMax += amount; }
	
	public void decreaseBonusOffense(int amount) { this.bonusOffense -= amount; }
	public void decreaseBonusDefense(int amount) { this.bonusDefense -= amount; }
	public void decreaseBonusAbility(int amount) { this.bonusAbility -= amount; }
	public void decreaseBonusSpeed(int amount) { this.bonusSpeed -= amount; }
	public void decreaseBonusHitPointMax(int amount) { this.bonusHitPointMax -= amount; }
	public void decreaseBonusAbilityPointMax(int amount) { this.bonusAbilityPointMax -= amount; }
	
	public int getBaseLevel() { return this.baseLevel; }
	public int getBaseOffense() { return this.baseOffense; }
	public int getBaseDefense() { return this.baseDefense; }
	public int getBaseAbility() { return this.baseAbility; }
	public int getBaseSpeed() { return this.baseSpeed; }
	public int getBaseHitPointMax() { return this.baseHitPointMax; }
	public int getBaseAbilityPointMax() { return this.baseAbilityPointMax; }
	
	public int getLevel() { return this.level; }
	public int getOffense() { return (this.baseOffense + this.bonusOffense); }
	public int getDefense() { return (this.baseDefense + this.bonusDefense); }
	public int getAbility() { return (this.baseAbility + this.bonusAbility); }
	public int getSpeed() { return (this.baseSpeed + this.bonusSpeed); }
	public int getHitPointMax() { return (this.baseHitPointMax + this.bonusHitPointMax); }
	public int getAbilityPointMax() { return (this.baseAbilityPointMax + this.bonusAbilityPointMax); }
	
	
	
	
	////////////////////////////////////////
	// Private Functions
	/////////////////////////////////////////
	protected void increaseBaseOffense(int amount) { this.baseOffense += amount; }
	protected void increaseBaseDefense(int amount) { this.baseDefense += amount; }
	protected void increaseBaseAbility(int amount) { this.baseAbility += amount; }
	protected void increaseBaseSpeed(int amount) { this.baseSpeed += amount; }
	protected void increaseBaseHitPointMax(int amount) { this.baseHitPointMax += amount; }
	protected void increaseBaseAbilityPointMax(int amount) { this.baseAbilityPointMax += amount; }
	
	protected void decreaseBaseOffense(int amount) { this.baseOffense -= amount; }
	protected void decreaseBaseDefense(int amount) { this.baseDefense -= amount; }
	protected void decreaseBaseAbility(int amount) { this.baseAbility -= amount; }
	protected void decreaseBaseSpeed(int amount) { this.baseSpeed -= amount; }
	protected void decreaseBaseHitPointMax(int amount) { this.baseHitPointMax -= amount; }
	protected void decreaseBaseAbilityPointMax(int amount) { this.baseAbilityPointMax -= amount; }
}
