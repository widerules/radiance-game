package org.vagosduke.andengine.radiance.game.combat;

import java.util.ArrayList;
import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;

public class SpellAttack {

	private BaseEffect spellEffect;
	private ArrayList<Damage> damageVector;
	private int abilitySkill;
	
//	public SpellAttack(BaseEffect ieffect, Character caster) {
//		this.spellEffect = ieffect;
//		this.damageVector = idamage;
//		this.abilitySkill = iabilitySkill;
//	}
	public SpellAttack(BaseEffect ieffect,  ArrayList<Damage> idamage, Character caster) {
		this.spellEffect = ieffect;
		this.damageVector = idamage;
		this.abilitySkill = caster.getAttributesModule().getAbility();
	}
	public SpellAttack(BaseEffect ieffect,  ArrayList<Damage> idamage, int iabilitySkill) {
		this.spellEffect = ieffect;
		this.damageVector = idamage;
		this.abilitySkill = iabilitySkill;
	}
	
	
	///////////////////////
	//	Getters/Setters
	///////////////////////
	public ArrayList<Damage> getDamageVector() { return this.damageVector; }
	public int getAbilitySkill() { return this.abilitySkill; }
	public BaseEffect getEffect() { return this.spellEffect; }
	
	public void setSpellEffect(BaseEffect ispellEffect) { this.spellEffect = ispellEffect;}
	public void setDamageVector(ArrayList<Damage> damageVector) { this.damageVector = damageVector; }
	public void setAbilitySkill(int offenseSkill) {this.abilitySkill = offenseSkill; }
}
