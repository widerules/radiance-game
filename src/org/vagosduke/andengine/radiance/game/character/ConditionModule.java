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

import java.util.Iterator;
import java.util.LinkedList;

import org.vagosduke.andengine.radiance.game.combat.Attack;
import org.vagosduke.andengine.radiance.game.combat.AttackResult;
import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.combat.SpellAttack;
import org.vagosduke.andengine.radiance.game.combat.SpellAttackResult;
import org.vagosduke.andengine.radiance.game.condition.BaseCondition;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;

public class ConditionModule {
	/**
	 * Character Class Module that manages character condition information
	 * and is used to calculate bonuses or penalties during combat 
	 */
	
	private Character myCharacter;
	
	private LinkedList<BaseCondition> conditionList;
	
	private LinkedList<BaseCondition> characterPassiveList;		// Passive abilities from character class
	
	private LinkedList<BaseCondition> onRoundStartList;
	private LinkedList<BaseCondition> onRoundEndList;
	private LinkedList<BaseCondition> onAttackList;
	private LinkedList<BaseCondition> onAttackDamageList;
	private LinkedList<BaseCondition> onBeingAttackedList;
	private LinkedList<BaseCondition> onBeingAttackDamagedList;
	private LinkedList<BaseCondition> onCastList;
	private LinkedList<BaseCondition> onBeingCastList;
	private LinkedList<BaseCondition> onSpellDamageList;
	private LinkedList<BaseCondition> onBeingSpellDamagedList;
	
	
	////////////////////////////
	//	Public Constructors
	////////////////////////////
	public ConditionModule(Character myCharacter) {
		this.myCharacter = myCharacter;
		
		conditionList = new LinkedList<BaseCondition>();
		onRoundStartList = new LinkedList<BaseCondition>();
		onRoundEndList = new LinkedList<BaseCondition>();
		onAttackList = new LinkedList<BaseCondition>();
		onAttackDamageList = new LinkedList<BaseCondition>();
		onBeingAttackedList = new LinkedList<BaseCondition>();
		onBeingAttackDamagedList = new LinkedList<BaseCondition>();
		onCastList = new LinkedList<BaseCondition>();
		onSpellDamageList = new LinkedList<BaseCondition>();
		onBeingSpellDamagedList = new LinkedList<BaseCondition>();
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
	
	
	
	/**
	 * 	get the linked list of effects and apply them.
	 * 	The effects are actually iterated from a cloned list so that checkForComodification 
	 *	won't occur, should the effect expire and remove itself from the iterated list
	 */
	public void onRoundStartEffects() {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onRoundStartList.clone();
		for(BaseCondition cond: tempList) {
			cond.onRoundStart();
		}
	}
	public void onRoundEndEffects() {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onRoundEndList.clone();
		for(BaseCondition cond: tempList) {
			cond.onRoundEnd();
		}
	}
	public void onAttackEffects(Character target, Attack att) {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onAttackList.clone();
		for(BaseCondition cond: tempList) {
			cond.onAttack(target, att);
		}
	}
	public void onAttackDamageEffects(Character target, AttackResult result) {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onAttackDamageList.clone();
		for(BaseCondition cond: tempList) {
			cond.onAttackDamage(target, result);
		}
	}
	public void onBeingAttackedEffects(Character attacker, Attack att) {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onBeingAttackedList.clone();
		for(BaseCondition cond: tempList) {
			cond.onBeingAttacked(attacker, att);
		}
	}
	public void onBeingAttackDamagedEffects(Character attacker, AttackResult result) {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onBeingAttackDamagedList.clone();
		for(BaseCondition cond: tempList) {
			cond.onBeingAttackDamaged(attacker, result);
		}
	}
	public void onCastEffects(TargetedSpell spell) {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onCastList.clone();
		for(BaseCondition cond: tempList) {
			cond.onCast(spell);
		}
	}
	public void onBeingCastEffects(Character caster, SpellAttack attack) {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onBeingCastList.clone();
		for(BaseCondition cond: tempList) {
			cond.onBeingCast(caster, attack);
		}
	}
	public void onSpellDamageEffects(Character target, SpellAttackResult result) {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onSpellDamageList.clone();
		for(BaseCondition cond: tempList) {
			cond.onSpellDamage(target, result);
		}
	}
	public void onBeingSpellDamagedEffects(Character caster, SpellAttackResult result) {
		LinkedList<BaseCondition> tempList = (LinkedList<BaseCondition>) this.onBeingSpellDamagedList.clone();
		for(BaseCondition cond: tempList) {
			cond.onBeingSpellDamaged(caster, result);
		}
	}

	
	
	
	/////////////////////////////
	//	Getters/Setters
	/////////////////////////////
	public void addRoundStartEffect(BaseCondition eventEffect) { onRoundStartList.add(eventEffect); }
	public void addRoundEndEffect(BaseCondition eventEffect) { onRoundEndList.add(eventEffect); }
	public void addAttackEffect(BaseCondition eventEffect) { onAttackList.add(eventEffect); }
	public void addAttackDamageEffect(BaseCondition eventEffect) { onAttackDamageList.add(eventEffect); }
	public void addAttackedEffect(BaseCondition eventEffect) { onBeingAttackedList.add(eventEffect); }
	public void addAttackDamagedEffect(BaseCondition eventEffect) { onBeingAttackDamagedList.add(eventEffect); }
	public void addCastEffect(BaseCondition eventEffect) { onCastList.add(eventEffect); }
	public void addSpellDamageEffect(BaseCondition eventEffect) { onSpellDamageList.add(eventEffect); }
	public void addSpellDamagedEffect(BaseCondition eventEffect) { onBeingSpellDamagedList.add(eventEffect); }
	
	public void removeRoundStartEffect(BaseCondition eventEffect) { onRoundStartList.remove(eventEffect); }
	public void removeRoundEndEffect(BaseCondition eventEffect) { onRoundEndList.remove(eventEffect); }
	public void removeAttackEffect(BaseCondition eventEffect) { onAttackList.remove(eventEffect); }
	public void removeAttackDamageEffect(BaseCondition eventEffect) { onAttackDamageList.remove(eventEffect); }
	public void removeAttackedEffect(BaseCondition eventEffect) { onBeingAttackedList.remove(eventEffect); }
	public void removeAttackDamagedEffect(BaseCondition eventEffect) { onBeingAttackDamagedList.remove(eventEffect); }
	public void removeCastEffect(BaseCondition eventEffect) { onCastList.remove(eventEffect); }
	public void removeSpellDamageEffect(BaseCondition eventEffect) { onSpellDamageList.remove(eventEffect); }
	public void removeSpellDamagedEffect(BaseCondition eventEffect) { onBeingSpellDamagedList.remove(eventEffect); }
	
	
	
	
}
