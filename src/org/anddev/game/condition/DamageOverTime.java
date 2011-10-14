package org.anddev.game.condition;
import java.io.InputStream;

import org.anddev.engine.variables.EnumValue;
import org.anddev.game.character.Character;
import org.anddev.game.combat.Attack;
import org.anddev.game.combat.Damage;
import org.anddev.game.spells.TargetedSpell;
import org.anddev.game.spells.effects.BaseEventEffect;


public class DamageOverTime implements BaseEventEffect {

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
