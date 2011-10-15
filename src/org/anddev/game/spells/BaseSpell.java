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
