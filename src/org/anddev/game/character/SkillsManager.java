package org.anddev.game.character;


public class SkillsManager {
	/**
	 * Character-Class module that manages non-derived (class-specific) skill values
	 */
	
	// Character
	private Character myCharacter;
	
	
	
	
	// Base class skills (normally from growth etc)
	private int baseLevel;
	private int baseOffense;
	private int baseDefense;
	private int baseMagic;
	private int baseSpeed;
	private int baseHitPointMax;
	private int baseMagicPointMax;
	
	// bonuses from effects, items, etc
	private int level;
	private int bonusOffense;
	private int bonusDefense;
	private int bonusMagic;
	private int bonusSpeed;
	private int bonusHitPointMax;
	private int bonusMagicPointMax;
	

	
	
	
	
	
	
	///////////////////////
	//	Constructors
	///////////////////////
	public SkillsManager(Character icharacter) {
		/**
		 * Default constructor
		 */
		this.myCharacter = icharacter;
		
		this.baseLevel = 1;
		this.baseOffense = 10;
		this.baseDefense = 10;
		this.baseMagic = 10;
		this.baseSpeed = 10;
		this.baseHitPointMax = 10;
		this.baseMagicPointMax = 10;
		
		this.level = this.baseLevel;
		this.bonusOffense = 0;
		this.bonusDefense = 0;
		this.bonusMagic = 0;
		this.bonusSpeed = 0;
		this.bonusHitPointMax = 0;
		this.bonusMagicPointMax = 0;
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
	public void increaseBonusOffense(int ammount) { this.bonusOffense += ammount; }
	public void increaseBonusDefense(int ammount) { this.bonusDefense += ammount; }
	public void increaseBonusMagic(int ammount) { this.bonusMagic += ammount; }
	public void increaseBonusSpeed(int ammount) { this.bonusSpeed += ammount; }
	public void increaseBonusHitPointMax(int ammount) { this.bonusHitPointMax += ammount; }
	public void increaseBonusMagicPointMax(int ammount) { this.bonusMagicPointMax += ammount; }
	
	public void decreaseBonusOffense(int ammount) { this.bonusOffense -= ammount; }
	public void decreaseBonusDefense(int ammount) { this.bonusDefense -= ammount; }
	public void decreaseBonusMagic(int ammount) { this.bonusMagic -= ammount; }
	public void decreaseBonusSpeed(int ammount) { this.bonusSpeed -= ammount; }
	public void decreaseBonusHitPointMax(int ammount) { this.bonusHitPointMax -= ammount; }
	public void decreaseBonusMagicPointMax(int ammount) { this.bonusMagicPointMax -= ammount; }
	
	public int getBaseLevel() { return this.baseLevel; }
	public int getBaseOffense() { return this.baseOffense; }
	public int getBaseDefense() { return this.baseDefense; }
	public int getBaseMagic() { return this.baseMagic; }
	public int getBaseSpeed() { return this.baseSpeed; }
	public int getBaseHitPointMax() { return this.baseHitPointMax; }
	public int getBaseMagicPointMax() { return this.baseMagicPointMax; }
	
	public int getLevel() { return this.level; }
	public int getOffense() { return (this.baseOffense + this.bonusOffense); }
	public int getDefense() { return (this.baseDefense + this.bonusDefense); }
	public int getMagic() { return (this.baseMagic + this.bonusMagic); }
	public int getSpeed() { return (this.baseSpeed + this.bonusSpeed); }
	public int getHitPointMax() { return (this.baseHitPointMax + this.bonusHitPointMax); }
	public int getMagicPointMax() { return (this.baseMagicPointMax + this.bonusMagicPointMax); }
	
	
	
	
	////////////////////////////////////////
	// Private Functions
	/////////////////////////////////////////
	protected void increaseBaseOffense(int ammount) { this.baseOffense += ammount; }
	protected void increaseBaseDefense(int ammount) { this.baseDefense += ammount; }
	protected void increaseBaseMagic(int ammount) { this.baseMagic += ammount; }
	protected void increaseBaseSpeed(int ammount) { this.baseSpeed += ammount; }
	protected void increaseBaseHitPointMax(int ammount) { this.baseHitPointMax += ammount; }
	protected void increaseBaseMagicPointMax(int ammount) { this.baseMagicPointMax += ammount; }
	
	protected void decreaseBaseOffense(int ammount) { this.baseOffense -= ammount; }
	protected void decreaseBaseDefense(int ammount) { this.baseDefense -= ammount; }
	protected void decreaseBaseMagic(int ammount) { this.baseMagic -= ammount; }
	protected void decreaseBaseSpeed(int ammount) { this.baseSpeed -= ammount; }
	protected void decreaseBaseHitPointMax(int ammount) { this.baseHitPointMax -= ammount; }
	protected void decreaseBaseMagicPointMax(int ammount) { this.baseMagicPointMax -= ammount; }
}
