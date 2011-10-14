package org.anddev.game.spells.effects;

import java.io.InputStream;

import org.anddev.game.character.Character;
import org.anddev.game.combat.Attack;
import org.anddev.game.combat.Damage;
import org.anddev.game.spells.TargetedSpell;

public interface BaseEventEffect {

	
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
