package org.anddev.game.spells.effects;

import org.anddev.game.character.Character;
import org.anddev.game.template.Template.TemplateInfo;
import org.w3c.dom.Node;

public interface BaseEffect {

	//BaseTargetEffect create(Node neffect);
	public BaseEffect create(Node neffect) throws Exception;
	public TemplateInfo getEffectTemplate();
	public void apply(Character caster, Character target);
	
}
