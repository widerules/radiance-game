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

package org.anddev.game.condition;
import java.io.InputStream;

import org.anddev.game.character.Character;
import org.anddev.game.combat.Attack;
import org.anddev.game.combat.Damage;
import org.anddev.game.spells.TargetedSpell;
import org.anddev.program.variables.EnumValue;


public class DamageOverTime implements BaseCondition {

	private Character myCharacter;
	
	private String name;
	private String description;
	
	private int potency;
	private int duration;
	private EnumValue damageType;
	
	
	///////////////////////
	//	Constructor
	///////////////////////
	public DamageOverTime(String iname, String idescription, int ipotency, int iduration, EnumValue idamageType) throws IllegalArgumentException {
		if (Damage.DMGtype.memberOf(idamageType)) {
			throw new IllegalArgumentException();
		}
		this.name = iname;
		this.description = idescription;
		this.potency = ipotency;
		this.duration = iduration;
		this.damageType = idamageType;
	}
	
	
	
	
	
	/////////////////////////
	//	Public Methods
	/////////////////////////
	@Override
	public void onApply(Character thisChar) {
		this.myCharacter = thisChar;
		this.myCharacter.getConditionManager().addRoundStartEffect(this);
	}
	
	@Override
	public void onRoundStart() {
		if (this.duration > 0) {
			Damage dmg = Damage.create(potency, damageType);
			this.myCharacter.doDamage(dmg);
			this.duration--;
		}
		if (this.duration <= 0) { 
			this.onExpire();
		}
	}
	
	@Override
	public void onExpire() {
		this.myCharacter.getConditionManager().removeRoundStartEffect(this);
		this.myCharacter.getConditionManager().removeCondition(this);
	}

	@Override
	public void create(InputStream ifile) {		
	}

	@Override
	public void onRoundEnd() {		
	}

	@Override
	public void onAttack(Character target, Attack att) {		
	}

	@Override
	public void onAttackDamage(Character target, Damage dmg) {		
	}

	@Override
	public void onAttacked(Character attacker, Attack att) {		
	}

	@Override
	public void onAttackDamaged(Character attacker, Damage dmg) {		
	}

	@Override
	public void onCast(TargetedSpell spell) {		
	}

	@Override
	public void onSpellDamage(Character target, TargetedSpell spell, Damage dmg) {		
	}

	@Override
	public void onSpellDamaged(Character caster, TargetedSpell spell, Damage dmg) {		
	}

	@Override
	public String name(){ return this.name; }
	@Override
	public String desciption() { return this.description; }

}
