package org.vagosduke.andengine.radiance.game.condition;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.combat.Attack;
import org.vagosduke.andengine.radiance.game.combat.AttackResult;
import org.vagosduke.andengine.radiance.game.combat.SpellAttack;
import org.vagosduke.andengine.radiance.game.combat.SpellAttackResult;
import org.vagosduke.andengine.radiance.game.items.StatBonus;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;

public class StatsBuff implements BaseCondition {

	/////////////////
	//	Variables
	//////////////////
	private Character myCharacter;
	
	private String name;
	private String description;
	
	private int duration;
	private ArrayList<StatBonus> statsBonus;
	
	private boolean stackable;
	private boolean unique;
	
	
	
	////////////////////////
	//	Constructor
	///////////////////////
	public StatsBuff(String iname, String idescription, int iduration, ArrayList<StatBonus> istatsBonus,
			boolean istackable, boolean iunique) {
		this.name = iname;
		this.description = idescription;
		this.duration = iduration;
		this.statsBonus = istatsBonus;
		this.stackable = istackable;
		this.unique = iunique;
	}
	
	
	
	
	///////////////////
	// Methods
	//////////////////
	public void onApply(Character mychar) {
		this.myCharacter = mychar;
		for(StatBonus bonus: statsBonus) {
			bonus.apply(this.myCharacter);
		}
		this.myCharacter.getConditionModule().addRoundStartEffect(this);
	}
	
	public void onRoundStart() {
		this.duration--;
		if(this.duration <= 0) {
			this.onExpire();
		}
	}
	
	public void onExpire() {
		for(StatBonus bonus: statsBonus) {
			bonus.revert(this.myCharacter);
		}
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
		if(newCond instanceof StatsBuff) {
			StatsBuff cond = (StatsBuff) newCond;
			for(StatBonus bonus: cond.getBonusList()) {
				bonus.apply(myCharacter);
				this.statsBonus.add(bonus);
			}
			if (cond.getDuration() > this.duration) {
				this.duration = cond.getDuration();
			}
		}
	}
	
	
	////////////////////////////////////
	//	Condition-specific functions
	/////////////////////////////////////
	protected ArrayList<StatBonus> getBonusList() { return this.statsBonus; }
	protected int getDuration() { return this.duration; }





}
