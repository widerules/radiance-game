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

package org.vagosduke.andengine.radiance.program.config;

public class Filepath {
	/**
	 * Contains all predefined asset file locations
	 */
	
	// Enum Types
	private final static String ItemSubtypes = "config/types/ItemSubType.yaml";

	private final static String DamageTypes = "config/types/DamageType.yaml";
	
	// Item lists
	private final static String ItemFiles = "config/items/ItemFiles.yaml";
	
	// Spell file list
	private final static String SpellFiles = "config/spells/SpellFileList.yaml";

	
	
	
	public static String getItemSubtypes() {
		return ModPath.modPath + ItemSubtypes;
	}
	public static String getDamageTypes() {
		return ModPath.modPath + DamageTypes;
	}
	public static String getItemFiles() {
		return ModPath.modPath + ItemFiles;
	}
	public static String getSpellFiles() {
		return ModPath.modPath + SpellFiles;
	}
	
	
}
