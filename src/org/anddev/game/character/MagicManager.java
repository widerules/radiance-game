package org.anddev.game.character;

public class MagicManager {

	private final Character myCharacter;
	
	private int magicPoints;

	
	
	
	/////////////////////
	//	Constructor
	/////////////////////
	public MagicManager(Character mychar) {
		this.myCharacter = mychar;
	}
	
	
	
	
	
	
	
	
	///////////////////////
	//	Public Functions
	///////////////////////
	public int modMP(int value) {
		/** add the value to HP. (value can be negative) */
		if(value > 0) {
			return this.addMP(value);
		}
		else {
			return this.subtractMP(-value);
		}
	}
	
	public int addMP(int value) {
		int mpmax = this.myCharacter.getSkillsManager().getBaseMagicPointMax();
		if((this.magicPoints + value) > mpmax) {
			this.magicPoints = mpmax;
			return (mpmax - this.magicPoints);
		}
		else {
			this.magicPoints += value;
			return value;
		}	
	}
	
	public int subtractMP(int value) {
		int tmp = this.magicPoints;
		if((this.magicPoints - value) < 0) {
			this.magicPoints = 0;
			return tmp;
		}
		else {
			this.magicPoints -= value;
			return value;
		}
	}	
	
	
	
	
	
	
	
	
	
	///////////////////////
	// Getters
	///////////////////////
	public int MP() { return this.magicPoints; }
}
