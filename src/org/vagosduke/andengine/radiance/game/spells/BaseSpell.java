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

package org.vagosduke.andengine.radiance.game.spells;


import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;

// All individual spells inherit from this class
public interface BaseSpell {
	
	// Spell info
	public String getName();
	public String getDescription();
	public int getTotalRanks();
		
	// spell attributes by rank
	public int getMPcost(int rank);
	public ArrayList<BaseEffect> getEffects(int rank);
	public TemplateInfo getRangeTemplate(int rank);
	public TemplateInfo getEffectTemplate(int rank);
	
	// public functions
	public boolean canCast(int rank, Character caster);
	public boolean cast(int rank, Character caster, ArrayList<Character>targetList);
}
