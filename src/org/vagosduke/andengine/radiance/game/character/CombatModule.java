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


import org.vagosduke.andengine.radiance.game.combat.Attack;
import org.vagosduke.andengine.radiance.game.combat.AttackResult;
import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.combat.ResistList;
import org.vagosduke.andengine.radiance.game.combat.SpellAttack;
import org.vagosduke.andengine.radiance.game.combat.SpellAttackResult;
import org.vagosduke.andengine.radiance.game.items.WeaponItem;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;

public class CombatModule {
	/**
	 * Character-Class module that manages derived combat stats.
	 * (and possible combat methods)
	 */
	private final Character myCharacter;
	

	private int hitPoints;								// health left
	private ResistList baseResistList;		// % damage reduction by damage type
	private ResistList bonusResistList;		// % damage reduction by damage type

	
	
	//////////////////////
	//	Constructors
	//////////////////////
	public CombatModule(Character mychar, ResistList baseResistList) {
		this.myCharacter = mychar;
		
		this.baseResistList = baseResistList;
		this.bonusResistList = new ResistList();
	}
	
	
	
	
	////////////////////////
	//	Public Functions
	////////////////////////
	public Attack doAttack(WeaponItem weapon, Character target) {
		Attack att = new Attack(myCharacter, weapon);
		this.myCharacter.getConditionModule().onAttackEffects(target, att);
		return att;
	}
	public AttackResult doDefend(Attack attack, Character attacker) {
		this.myCharacter.getConditionModule().onBeingAttackedEffects(attacker, attack);
		AttackResult result = new AttackResult(attack); 
		result.resolve(myCharacter);
		if(!result.dodged() && !result.blobked()) {
			this.myCharacter.getConditionModule().onBeingAttackDamagedEffects(attacker, result);
			for(Damage dmg: result.finalDamage()) {
				this.subtractHP(dmg.getValue());
			}
		}
		return result;
	}
	public void attackResult(AttackResult result, Character target) {
		if(!result.dodged() && !result.blobked()) {
			this.myCharacter.getConditionModule().onAttackDamageEffects(target, result);
		}
	}
	
	
	
	public SpellAttackResult doSpellDefend(SpellAttack attack, Character caster) {
		if(caster !=null) {
			this.myCharacter.getConditionModule().onBeingCastEffects(caster, attack);
		}
		SpellAttackResult result = new SpellAttackResult(attack); 
		result.resolve(myCharacter);
		
		if(!result.resisted()) {
			if(caster !=null) {
				this.myCharacter.getConditionModule().onBeingSpellDamagedEffects(caster, result);
			}
			for(Damage dmg: result.finalDamage()) {
				this.subtractHP(dmg.getValue());
			}
		}
		return result;
	}
	
	
	
	
	
	
 	public int subtractHP(int value) {
		int tmp = this.hitPoints;
		if((this.hitPoints - value) < 0) {
			this.hitPoints = 0;
			//myCharacter.die();
			return tmp;
		}
		else {
			this.hitPoints -= value;
			return value;
		}
	}
	public int addHP(int value) {
		int hpmax = myCharacter.getAttributesModule().getHitPointMax();
		if((this.hitPoints + value) > hpmax) {
			this.hitPoints = hpmax;
			return (hpmax - this.hitPoints);
		}
		else {
			this.hitPoints += value;
			return value;
		}	
	}

	
	
	public double getResisance(EnumValue type) { return this.baseResistList.get(type) + this.bonusResistList.get(type); }
	
}
