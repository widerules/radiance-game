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

import java.io.InputStream;
import java.util.HashMap;

import org.anddev.engine.config.Filepath;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class SpellList {
	
	
	static private HashMap<String, BaseSpell> spellList;
	
	
	
	
	/////////////////////////////
	//	Constructor
	/////////////////////////////
	static public void init() {
		/** 
		 * Static Spell Listing
		 * Initializes the spellList by reading the location of each spell file
		 * from the static master spellList file
		 * (so that each spell can be saved in a separate file)
		 */
	
		spellList = new HashMap<String, BaseSpell>();
		
		readSpellList(Filepath.xmlSpellFiles);
		
	}
		
	
	
	
	
	
	
	
	//////////////////////////
	//	Private Functions
	//////////////////////////
	static private void readSpellList(String spellListFile) {
		try{
			InputStream masterListFile = XMLutil.getAsset(spellListFile);
			try {
				NodeList listOfSpellsNames = XMLutil.getXMLroot(masterListFile).getChildNodes();
				for(int s=0; s<listOfSpellsNames.getLength() ; s++) {
					Node SpellFileNode = listOfSpellsNames.item(s);
					if(SpellFileNode.getNodeType() == Node.ELEMENT_NODE) {
						String spellPath = XMLutil.getThisNodeValue(SpellFileNode);
						loadSpell(spellPath);
					}
				}
			}
			catch (Exception err) {
				Log.e("SPELLLIST", err.getMessage());
			}
			masterListFile.close();
		} 
		catch (Exception err) {
			Log.e("SPELLLIST-FILE", err.getMessage());
		}
	}
			
	static private void loadSpell(String spellPath) {
		// Creates and stores a new spell in the spellList
		try {
			InputStream spellFile = XMLutil.getAsset(spellPath);	
			try {
				Node spellNode = XMLutil.getXMLroot(spellFile);
				BaseSpell newSpell = TargetedSpell.class.newInstance().create(spellNode);
				String spellName = newSpell.name().toUpperCase();
				spellList.put(spellName, newSpell);
			} 
			catch (Exception err){
				Log.e("LOADSPELL", err.getMessage());
			}
			spellFile.close();
		} 
		catch (Exception err){
			Log.e("LOADSPELL-FILE", err.getMessage());
		}
	}
}
