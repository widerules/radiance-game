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

import java.util.Iterator;
import java.util.LinkedList;

import org.anddev.game.combat.Attack;
import org.anddev.game.combat.Damage;
import org.anddev.game.condition.BaseCondition;
import org.anddev.game.spells.TargetedSpell;

public class ConditionManager {
	/**
	 * Character Class Module that manages character condition information
	 * and is used to calculate bonuses or penalties during combat
	 */
	
	private Character myCharacter;
	
	private LinkedList<BaseCondition> conditionList;
	
	private LinkedList<BaseCondition> onRoundStartList;
	private LinkedList<BaseCondition> onRoundEndList;
	private LinkedList<BaseCondition> onAttackList;
	private LinkedList<BaseCondition> onAttackDamageList;
	private LinkedList<BaseCondition> onAttackedList;
	private LinkedList<BaseCondition> onAttackDamagedList;
	private LinkedList<BaseCondition> onCastList;
	private LinkedList<BaseCondition> onSpellDamageList;
	private LinkedList<BaseCondition> onSpellDamagedList;
	
	
	////////////////////////////
	//	Public Constructors
	////////////////////////////
	public static ConditionManager newConditionManager() {
		return new ConditionManager();
	}
	
	
	
	
	
	
	////////////////////////////
	//	Public Methods
	////////////////////////////
	public void addCondition(BaseCondition cond) {
		cond.onApply(myCharacter);
		conditionList.add(cond);
	}
	public void removeCondition(BaseCondition cond) {
		conditionList.remove(cond);
	}
	
	
	
	public void roundStartEffects() {
		Iterator<BaseCondition> it = this.onRoundStartList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onRoundStart();
		}
	}
	public void roundEndEffects() {
		Iterator<BaseCondition> it = this.onRoundEndList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onRoundEnd();
		}
	}
	public void AttackEffects(Character target, Attack att) {
		Iterator<BaseCondition> it = this.onAttackedList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onAttack(target, att);
		}
	}
	public void AttackDamageEffects(Character target, Damage dmg) {
		Iterator<BaseCondition> it = this.onAttackDamageList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onAttackDamage(target, dmg);
		}
	}
	public void AttackedEffects(Character attacker, Attack att) {
		Iterator<BaseCondition> it = this.onAttackedList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onAttacked(attacker, att);
		}
	}
	public void AttackDamagedEffects(Character attacker, Damage dmg) {
		Iterator<BaseCondition> it = this.onAttackDamagedList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onAttackDamaged(attacker, dmg);
		}
	}
	public void CastEffects(TargetedSpell spell) {
		Iterator<BaseCondition> it = this.onCastList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onCast(spell);
		}
	}
	public void SpellDamageEffects(Character target, TargetedSpell spell, Damage dmg) {
		Iterator<BaseCondition> it = this.onSpellDamageList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onSpellDamage(target, spell, dmg);
		}
	}
	public void SpellDamagedEffects(Character caster, TargetedSpell spell, Damage dmg) {
		Iterator<BaseCondition> it = this.onSpellDamagedList.iterator();
		while(it.hasNext()) {
			BaseCondition cond = it.next();
			cond.onSpellDamaged(caster, spell, dmg);
		}
	}
	
	
	
	
	
	
	
	
	/////////////////////////////
	//	Getters/Setters
	/////////////////////////////
	public void addRoundStartEffect(BaseCondition eventEffect) { onRoundStartList.add(eventEffect); }
	public void addRoundEndEffect(BaseCondition eventEffect) { onRoundEndList.add(eventEffect); }
	public void addAttackEffect(BaseCondition eventEffect) { onAttackList.add(eventEffect); }
	public void addAttackDamageEffect(BaseCondition eventEffect) { onAttackDamageList.add(eventEffect); }
	public void addAttackedEffect(BaseCondition eventEffect) { onAttackedList.add(eventEffect); }
	public void addAttackDamagedEffect(BaseCondition eventEffect) { onAttackDamagedList.add(eventEffect); }
	public void addCastEffect(BaseCondition eventEffect) { onCastList.add(eventEffect); }
	public void addSpellDamageEffect(BaseCondition eventEffect) { onSpellDamageList.add(eventEffect); }
	public void addSpellDamagedEffect(BaseCondition eventEffect) { onSpellDamagedList.add(eventEffect); }
	
	public void removeRoundStartEffect(BaseCondition eventEffect) { onRoundStartList.remove(eventEffect); }
	public void removeRoundEndEffect(BaseCondition eventEffect) { onRoundEndList.remove(eventEffect); }
	public void removeAttackEffect(BaseCondition eventEffect) { onAttackList.remove(eventEffect); }
	public void removeAttackDamageEffect(BaseCondition eventEffect) { onAttackDamageList.remove(eventEffect); }
	public void removeAttackedEffect(BaseCondition eventEffect) { onAttackedList.remove(eventEffect); }
	public void removeAttackDamagedEffect(BaseCondition eventEffect) { onAttackDamagedList.remove(eventEffect); }
	public void removeCastEffect(BaseCondition eventEffect) { onCastList.remove(eventEffect); }
	public void removeSpellDamageEffect(BaseCondition eventEffect) { onSpellDamageList.remove(eventEffect); }
	public void removeSpellDamagedEffect(BaseCondition eventEffect) { onSpellDamagedList.remove(eventEffect); }
	
	
	///////////////////////////
	//	Private Constructors
	///////////////////////////
	private ConditionManager() {
		conditionList = new LinkedList<BaseCondition>();
		
		onRoundStartList = new LinkedList<BaseCondition>();
		onRoundEndList = new LinkedList<BaseCondition>();
		onAttackList = new LinkedList<BaseCondition>();
		onAttackDamageList = new LinkedList<BaseCondition>();
		onAttackedList = new LinkedList<BaseCondition>();
		onAttackDamagedList = new LinkedList<BaseCondition>();
		onCastList = new LinkedList<BaseCondition>();
		onSpellDamageList = new LinkedList<BaseCondition>();
		onSpellDamagedList = new LinkedList<BaseCondition>();
	}
	
}
