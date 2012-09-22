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

package org.vagosduke.andengine.radiance.game.character;



public class PlayerCharacter extends Character {
	
	/**
	 * CharClass extends Character. CharClass adds the "Growth" module.
	 * It is intended for characters that can gain levels
	 */
	// Additional modules
	protected GrowthModule charGrowth;
	
	
	
	
	
	
	
	
	
	
	///////////////////////////
	// Constructors
	///////////////////////////
	public PlayerCharacter(InfoModule info, AttributesModule attributes, 
			InventoryModule inventory, ConditionModule cond, CombatModule combat, 
			AbilitiesModule magic, GrowthModule growth) {
		super (info, attributes, inventory, cond, combat, magic);
		this.charGrowth = growth;
	}
	
	
	
	
	
	
	
	////////////////////////
	//	public methods
	////////////////////////
	
	
	
	
	
	////////////////////////
	// getters
	////////////////////////
	public GrowthModule getGrowthModule() { return this.charGrowth; }
	
	
	
	
	
	////////////////////////////////////
	// Private Constructors
	////////////////////////////////////
}
