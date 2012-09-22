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

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.combat.Attack;
import org.vagosduke.andengine.radiance.game.combat.AttackResult;
import org.vagosduke.andengine.radiance.game.combat.SpellAttack;
import org.vagosduke.andengine.radiance.game.combat.SpellAttackResult;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;

public interface BaseCondition {

	
	//public void create(InputStream ifile);
	public void onApply(Character mychar);
	public void onExpire();
	
	//////////////////
	//	Events
	//////////////////
	public void onRoundStart();		// called at round end
	public void onRoundEnd();		// called at round start
	public void onAttack(Character target, Attack att);				// before damage takes place
	public void onAttackDamage(Character target, AttackResult result);		// after damage takes place
	public void onBeingAttacked(Character attacker, Attack att);
	public void onBeingAttackDamaged(Character attacker, AttackResult result);
	public void onCast(TargetedSpell spell);
	public void onBeingCast(Character caster, SpellAttack attack);
	public void onSpellDamage(Character target, SpellAttackResult result);
	public void onBeingSpellDamaged(Character caster, SpellAttackResult result);
	
	//////////////////
	//	Getters
	//////////////////
	public String name();
	public String desciption();
	public boolean stackable();
	public boolean unique();
	
	/////////////////////
	// Universal methods
	/////////////////////
	/**
	 * Stacking usually includes adding the potency and extending the duration
	 * if the new condition instance has longer duration remaining.
	 */
	public void stack(BaseCondition newCond);
	
	
}
