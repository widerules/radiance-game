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

package org.vagosduke.andengine.radiance.game.condition;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.combat.Attack;
import org.vagosduke.andengine.radiance.game.combat.AttackResult;
import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.combat.SpellAttack;
import org.vagosduke.andengine.radiance.game.combat.SpellAttackResult;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;


public class DamageOverTime implements BaseCondition {

	private Character myCharacter;
	
	private String name;
	private String description;
	private boolean stackable;
	private boolean unique;
	
	private int potency;
	private int ability;
	private int duration;
	private EnumValue damageType;
	
	
	///////////////////////
	//	Constructor
	///////////////////////
	public DamageOverTime(String iname, String idescription, int ipotency, int iability, int iduration, 
			EnumValue idamageType, boolean istackable, boolean iunique) throws IllegalArgumentException {
		if (Damage.DMGtype.memberOf(idamageType)) {
			throw new IllegalArgumentException();
		}
		this.name = iname;
		this.description = idescription;
		this.potency = ipotency;
		this.ability = iability;
		this.duration = iduration;
		this.damageType = idamageType;
		this.stackable = istackable;
		this.unique = iunique;
	}
	
	
	
	
	
	/////////////////////////
	//	Public Methods
	/////////////////////////
	@Override
	public void onApply(Character mychar) {
		this.myCharacter = mychar;
		this.myCharacter.getConditionModule().addRoundStartEffect(this);
	}
	
	@Override
	public void onRoundStart() {
		if (this.duration > 0) {
			this.myCharacter.getCombatModule().doSpellDefend(this.makeDamage(), null);
			
			this.duration--;
		}
		if (this.duration <= 0) { 
			this.onExpire();
		}
	}
	
	@Override
	public void onExpire() {
		this.myCharacter.getConditionModule().removeRoundStartEffect(this);
		this.myCharacter.getConditionModule().removeCondition(this);
	}

//	@Override
//	public void create(InputStream ifile) {		
//	}

	@Override
	public void onRoundEnd() {		
	}

	@Override
	public void onAttack(Character target, Attack att) {		
	}

	@Override
	public void onAttackDamage(Character target, AttackResult result) {		
	}

	@Override
	public void onBeingAttacked(Character attacker, Attack att) {		
	}

	@Override
	public void onBeingAttackDamaged(Character attacker, AttackResult result) {		
	}

	@Override
	public void onCast(TargetedSpell spell) {		
	}
	
	@Override
	public void onBeingCast(Character caster, SpellAttack attack) {
	}

	@Override
	public void onSpellDamage(Character target, SpellAttackResult result) {
	}

	@Override
	public void onBeingSpellDamaged(Character caster, SpellAttackResult result) {		
	}

	@Override
	public String name(){ return this.name; }
	@Override
	public String desciption() { return this.description; }
	@Override
	public boolean stackable() { return this.stackable; }
	@Override
	public boolean unique() { return this.unique; }


	@Override
	public void stack(BaseCondition newCond) {
		if(newCond instanceof DamageOverTime) {
			DamageOverTime cond = (DamageOverTime) newCond;
			this.potency += cond.getPotency(); 
			if(cond.getDuration() >= this.duration) {
				this.duration = cond.getDuration();
			}
		}
		
	}
	
	
	////////////////////////////////////
	//	Condition-specific functions
	/////////////////////////////////////
	protected int getPotency() { return this.potency; }
	protected int getDuration() { return this.duration; }
	
	
	/////////////////////
	//	Private Methods
	/////////////////////
	SpellAttack makeDamage() {
		ArrayList<Damage> dmgs = new ArrayList<Damage>();
		dmgs.add(new Damage(this.potency, this.damageType));
		return new SpellAttack(null, dmgs, this.ability);
	}





	
}
