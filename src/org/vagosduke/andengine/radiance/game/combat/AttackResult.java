package org.vagosduke.andengine.radiance.game.combat;

import java.util.ArrayList;
import java.util.Random;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.constants.Constants;

public class AttackResult {

	private Attack attack;
	private boolean dodged;
	private boolean blocked;
	private boolean criticaled;
	private ArrayList<Damage> finalDamage;
	
	
	
	public AttackResult(Attack iattack) {
		this.attack = iattack;
		this.finalDamage = new ArrayList<Damage>();
	}
	
	public AttackResult resolve(Character defender) {
		
		double finalDodgeChance = defender.getAttributesModule().getDodgeChance();
		double finalBlockChance = defender.getAttributesModule().getBaseBlockChance();
		double finalCriticalChance = this.attack.getCriticalChance();
		double finalAttackResistance = defender.getAttributesModule().getAttackResistance();
		Random generator = new Random();
		double roll=0.0;
		
		// Modify critical/dodge/block chances and resit
		int offenseVsDefense = this.attack.getOffenseSkill() - defender.getAttributesModule().getDefense();
		int offenseVsArmor = defender.getAttributesModule().getTotalArmor() - this.attack.getOffenseSkill();
		if(offenseVsDefense >= 0) {
			finalCriticalChance += (offenseVsDefense*Constants.offenseVsDefenseCriticalMod) * (1.0/100.0);
		}
		else {
			offenseVsDefense = -offenseVsDefense;
			finalDodgeChance += (offenseVsDefense*Constants.offenseVsDefenseCriticalMod) * (1.0/100.0);
			finalBlockChance += (offenseVsDefense*Constants.offenseVsDefenseCriticalMod) * (1.0/100.0);
		}
		finalAttackResistance += offenseVsArmor*Constants.offenseVsArmorResistMod * (1.0/100.0);
		
		// resolve dodge/block/resist
		roll = generator.nextDouble();
		if(roll < finalDodgeChance) {
			this.dodged = true;
			return this;
		}
		roll = generator.nextDouble();
		if(roll < finalBlockChance) {
			this.blocked = true;
			return this;
		}
		
		// resolve critical
		roll = generator.nextDouble();
		if(roll < finalCriticalChance) {
			this.criticaled = true;
		}
		
		// resolve damage
		for(Damage dmg: this.attack.getDamageVector()) {
			double tempfinalDamage = dmg.getValue();
			if(criticaled) { tempfinalDamage *= Constants.attackCriticalBonusPercent; }
			tempfinalDamage -= tempfinalDamage*finalAttackResistance;
			tempfinalDamage -= tempfinalDamage*defender.getCombatModule().getResisance(dmg.getType());
			this.finalDamage.add(new Damage((int)tempfinalDamage, dmg.getType()));	
		}
		return this;
	}
	
	
	public boolean dodged() { return this.dodged; }
	public boolean blobked() { return this.blocked; }
	public boolean criticaled() { return this.criticaled; }
	public ArrayList<Damage> finalDamage() { return this.finalDamage; }
}
