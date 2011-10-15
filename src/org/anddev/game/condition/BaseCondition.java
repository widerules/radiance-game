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

public interface BaseCondition {

	
	public void create(InputStream ifile);
	
	public void onApply(Character mychar);
	public void onExpire();
	
	//////////////////
	//	Events
	//////////////////
	public void onRoundStart();		// called at round end
	public void onRoundEnd();		// called at round start
	public void onAttack(Character target, Attack att);			// before damage takes place
	public void onAttackDamage(Character target, Damage dmg);		// after damage takes place
	public void onAttacked(Character attacker, Attack att);
	public void onAttackDamaged(Character attacker, Damage dmg);
	public void onCast(TargetedSpell spell);
	public void onSpellDamage(Character target, TargetedSpell spell, Damage dmg);
	public void onSpellDamaged(Character caster, TargetedSpell spell, Damage dmg);
	
	public String name();
	public String desciption();
}
