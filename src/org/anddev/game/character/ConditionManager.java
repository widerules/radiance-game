package org.anddev.game.character;

import java.util.Iterator;
import java.util.LinkedList;

import org.anddev.game.combat.Attack;
import org.anddev.game.combat.Damage;
import org.anddev.game.spells.TargetedSpell;
import org.anddev.game.spells.effects.BaseEventEffect;

public class ConditionManager {
	/**
	 * Character Class Module that manages character condition information
	 * and is used to calculate bonuses or penalties during combat
	 */
	
	private Character myCharacter;
	
	private LinkedList<BaseEventEffect> conditionList;
	
	private LinkedList<BaseEventEffect> onRoundStartList;
	private LinkedList<BaseEventEffect> onRoundEndList;
	private LinkedList<BaseEventEffect> onAttackList;
	private LinkedList<BaseEventEffect> onAttackDamageList;
	private LinkedList<BaseEventEffect> onAttackedList;
	private LinkedList<BaseEventEffect> onAttackDamagedList;
	private LinkedList<BaseEventEffect> onCastList;
	private LinkedList<BaseEventEffect> onSpellDamageList;
	private LinkedList<BaseEventEffect> onSpellDamagedList;
	
	
	////////////////////////////
	//	Public Constructors
	////////////////////////////
	public static ConditionManager newConditionManager() {
		return new ConditionManager();
	}
	
	
	
	
	
	
	////////////////////////////
	//	Public Methods
	////////////////////////////
	public void addCondition(BaseEventEffect cond) {
		cond.onApply(myCharacter);
		conditionList.add(cond);
	}
	public void removeCondition(BaseEventEffect cond) {
		conditionList.remove(cond);
	}
	
	
	
	public void roundStartEffects() {
		Iterator<BaseEventEffect> it = this.onRoundStartList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onRoundStart();
		}
	}
	public void roundEndEffects() {
		Iterator<BaseEventEffect> it = this.onRoundEndList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onRoundEnd();
		}
	}
	public void AttackEffects(Character target, Attack att) {
		Iterator<BaseEventEffect> it = this.onAttackedList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onAttack(target, att);
		}
	}
	public void AttackDamageEffects(Character target, Damage dmg) {
		Iterator<BaseEventEffect> it = this.onAttackDamageList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onAttackDamage(target, dmg);
		}
	}
	public void AttackedEffects(Character attacker, Attack att) {
		Iterator<BaseEventEffect> it = this.onAttackedList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onAttacked(attacker, att);
		}
	}
	public void AttackDamagedEffects(Character attacker, Damage dmg) {
		Iterator<BaseEventEffect> it = this.onAttackDamagedList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onAttackDamaged(attacker, dmg);
		}
	}
	public void CastEffects(TargetedSpell spell) {
		Iterator<BaseEventEffect> it = this.onCastList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onCast(spell);
		}
	}
	public void SpellDamageEffects(Character target, TargetedSpell spell, Damage dmg) {
		Iterator<BaseEventEffect> it = this.onSpellDamageList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onSpellDamage(target, spell, dmg);
		}
	}
	public void SpellDamagedEffects(Character caster, TargetedSpell spell, Damage dmg) {
		Iterator<BaseEventEffect> it = this.onSpellDamagedList.iterator();
		while(it.hasNext()) {
			BaseEventEffect cond = it.next();
			cond.onSpellDamaged(caster, spell, dmg);
		}
	}
	
	
	
	
	
	
	
	
	/////////////////////////////
	//	Getters/Setters
	/////////////////////////////
	public void addRoundStartEffect(BaseEventEffect eventEffect) { onRoundStartList.add(eventEffect); }
	public void addRoundEndEffect(BaseEventEffect eventEffect) { onRoundEndList.add(eventEffect); }
	public void addAttackEffect(BaseEventEffect eventEffect) { onAttackList.add(eventEffect); }
	public void addAttackDamageEffect(BaseEventEffect eventEffect) { onAttackDamageList.add(eventEffect); }
	public void addAttackedEffect(BaseEventEffect eventEffect) { onAttackedList.add(eventEffect); }
	public void addAttackDamagedEffect(BaseEventEffect eventEffect) { onAttackDamagedList.add(eventEffect); }
	public void addCastEffect(BaseEventEffect eventEffect) { onCastList.add(eventEffect); }
	public void addSpellDamageEffect(BaseEventEffect eventEffect) { onSpellDamageList.add(eventEffect); }
	public void addSpellDamagedEffect(BaseEventEffect eventEffect) { onSpellDamagedList.add(eventEffect); }
	
	public void removeRoundStartEffect(BaseEventEffect eventEffect) { onRoundStartList.remove(eventEffect); }
	public void removeRoundEndEffect(BaseEventEffect eventEffect) { onRoundEndList.remove(eventEffect); }
	public void removeAttackEffect(BaseEventEffect eventEffect) { onAttackList.remove(eventEffect); }
	public void removeAttackDamageEffect(BaseEventEffect eventEffect) { onAttackDamageList.remove(eventEffect); }
	public void removeAttackedEffect(BaseEventEffect eventEffect) { onAttackedList.remove(eventEffect); }
	public void removeAttackDamagedEffect(BaseEventEffect eventEffect) { onAttackDamagedList.remove(eventEffect); }
	public void removeCastEffect(BaseEventEffect eventEffect) { onCastList.remove(eventEffect); }
	public void removeSpellDamageEffect(BaseEventEffect eventEffect) { onSpellDamageList.remove(eventEffect); }
	public void removeSpellDamagedEffect(BaseEventEffect eventEffect) { onSpellDamagedList.remove(eventEffect); }
	
	
	///////////////////////////
	//	Private Constructors
	///////////////////////////
	private ConditionManager() {
		conditionList = new LinkedList<BaseEventEffect>();
		
		onRoundStartList = new LinkedList<BaseEventEffect>();
		onRoundEndList = new LinkedList<BaseEventEffect>();
		onAttackList = new LinkedList<BaseEventEffect>();
		onAttackDamageList = new LinkedList<BaseEventEffect>();
		onAttackedList = new LinkedList<BaseEventEffect>();
		onAttackDamagedList = new LinkedList<BaseEventEffect>();
		onCastList = new LinkedList<BaseEventEffect>();
		onSpellDamageList = new LinkedList<BaseEventEffect>();
		onSpellDamagedList = new LinkedList<BaseEventEffect>();
	}
	
}
