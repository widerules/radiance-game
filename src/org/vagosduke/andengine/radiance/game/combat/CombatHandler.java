package org.vagosduke.andengine.radiance.game.combat;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.items.WeaponItem;

public class CombatHandler {
	/**
	 * 	A wrapper class that handles combat attack-defend-result sequence
	 * 	and other functions
	 */
	
	public static final int SINGLE_WEAPON_ATTACK = 0;	// default
	public static final int FULL_ATTACK = 1;
	
	
	private boolean unarmedEnable;
	private int attackMode;

	
	public CombatHandler(boolean unarmedEnable, int attackMode) {
		this.unarmedEnable = unarmedEnable;
		if((attackMode < 0) || (attackMode > 1)) {
			this.attackMode = 0; }
		else {
			this.attackMode = attackMode; }
	}
	
	
	
	
	
	
	public void basicAttack(Character attacker, Character defenfer, WeaponItem weapon) {
		Attack atck = attacker.getCombatModule().doAttack(weapon, defenfer);
		AttackResult result = defenfer.getCombatModule().doDefend(atck, attacker);
		attacker.getCombatModule().attackResult(result, defenfer);
	}

}
