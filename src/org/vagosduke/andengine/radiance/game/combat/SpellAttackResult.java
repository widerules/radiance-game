package org.vagosduke.andengine.radiance.game.combat;

import java.util.ArrayList;
import java.util.Random;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.constants.Constants;

public class SpellAttackResult {
	
	
	private SpellAttack attack;
	private boolean criticaled;
	private boolean resisted;
	private ArrayList<Damage> finalDamage;
	
	
	//////////////////////
	//	Constructor
	/////////////////////
	public SpellAttackResult(SpellAttack iattack) {
		this.attack = iattack;
		this.resisted = false;
		this.finalDamage = new ArrayList<Damage>();
	}

	
	
	///////////////////
	//	Public Methods
	///////////////////
	public SpellAttackResult resolve(Character defender) {
		
		double finalCriticalChance = 0.0;
		double finalSpecialResistance = defender.getAttributesModule().getMagicResistance();
		double finalCompleteResistanceChance = 0.0;
		Random generator = new Random();
		double roll=0.0;
		
		// Modify critical chances and resistance
		int offAbilityVsDefAbility = this.attack.getAbilitySkill() - defender.getAttributesModule().getAbility();
		if(offAbilityVsDefAbility > 0) {
			finalCriticalChance += (offAbilityVsDefAbility*Constants.abilityVsAbilityCriticalMod) * (1.0/100.0);
		}
		finalSpecialResistance += offAbilityVsDefAbility*Constants.abilityVsAbilityResistMod * (1.0/100.0);
		finalCompleteResistanceChance += (finalSpecialResistance*Constants.specialCompleteResistMod);
		
		// resolve complete resist
		roll = generator.nextDouble();
		if(roll < finalCompleteResistanceChance) {
			this.resisted = true;
			return this;
		}
		// resolve critical
		roll = generator.nextDouble();
		if(roll < finalCriticalChance) {
			this.criticaled = true;
		}
		
		// resolve damage
		for(Damage dmg: this.attack.getDamageVector()) {
			double tempfinalDamage = dmg.getValue();
			if(criticaled) { tempfinalDamage *= Constants.spellCriticalBonusPercent; }
			tempfinalDamage -= tempfinalDamage*finalSpecialResistance;
			tempfinalDamage -= tempfinalDamage*defender.getCombatModule().getResisance(dmg.getType());
			this.finalDamage.add(new Damage((int)tempfinalDamage, dmg.getType()));	
		}
				
		return this;
	}


	
	public boolean resisted() { return this.resisted; }
	public boolean criticaled() { return this.criticaled; }
	public ArrayList<Damage> finalDamage() { return this.finalDamage; }
}
