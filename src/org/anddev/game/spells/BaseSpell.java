package org.anddev.game.spells;

import java.util.Vector;

import org.anddev.game.character.Character;
import org.anddev.game.spells.effects.BaseEffect;
import org.anddev.game.template.Template.TemplateInfo;
import org.w3c.dom.Node;

// All individual spells inherit from this class
public interface BaseSpell {
	
	// Spell info
	public String name();
	public String description();
	public int totalRanks();
		
	// spell attributes by rank
	public int getMPcost(int rank);
	public Vector<BaseEffect> getEffects(int rank);
	public TemplateInfo getRangeTemplate(int rank);
	public TemplateInfo getEffectTemplate(int rank);

	// Functions
	public BaseSpell create(Node nspell) throws Exception;
	public Boolean cast(int rank, Character caster, Vector<Character>targetList);
}
