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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.vagosduke.andengine.radiance.program.config.Filepath;
import org.vagosduke.andengine.radiance.program.loader.GameEffectLoader;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.log.ProgLog;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.FileUtil;
import org.vagosduke.andengine.radiance.util.TraceUtil;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.vagosduke.andengine.radiance.util.YAMLutil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class GlobalSpellList {
	
	////////////////////////////////////
	//	Static globalSpellList handler
	/////////////////////////////////////
	static public GlobalSpellList globalList;
	
	
	
	
	
	
	private HashMap<String, BaseSpell> spellList;
	
	
	/////////////////////////////
	//	Constructor
	/////////////////////////////
	public GlobalSpellList(String path) {
		this.spellList = new HashMap<String, BaseSpell>();
		readSpellList(path);
	}
	
	static public void initGlobalSpellList(String path) {
		/** 
		 * Global Spell Listing
		 * Initializes the spellList by reading the location of each spell file
		 * from the static master spellList file
		 * (so that each spell can be saved in a separate file)
		 */
	
		globalList = new GlobalSpellList(path);
	}
		
	
	
	
	
	
	//////////////////////////
	//	Public Functions
	//////////////////////////
	public TargetedSpell getSpell(String name) {
		return (TargetedSpell) spellList.get(name.toUpperCase());
	}
	
	
	
	
	//////////////////////////
	//	Private Functions
	//////////////////////////
	private void readSpellList(String spellListFile) {
		try {
			InputStream masterListFile = FileUtil.open(spellListFile);
			if(spellListFile.endsWith("xml")) {
//				try {
//					NodeList listOfSpellsNames = XMLutil.getXMLroot(masterListFile).getChildNodes();
//					readSpellList(listOfSpellsNames);
//				}
//				catch (Exception err) {
//					ProgLog.error("SPELLLIST", err);
//				}
//				masterListFile.close();
			}
			
			else if(spellListFile.endsWith("yaml")){				// YAML file
				DataDictionary dict = DataDictionary.makeDictionary(YAMLutil.loadData(masterListFile));
				readSpellList(dict);
				masterListFile.close();
			} 
		}
		catch (Exception err) {
			ProgLog.error("SPELLLIST-FILE", err);
		}
	}
	
	private void readSpellList(DataDictionary dict) throws Exception {
		for(int i=0; i<dict.getArraySize(); i++) {
			String spellPath = dict.getString(i);
			loadSpell(spellPath);
		}
	}
//	private void readSpellList(NodeList listOfSpellsNames) throws Exception {
//		for(int s=0; s<listOfSpellsNames.getLength() ; s++) {
//			Node SpellFileNode = listOfSpellsNames.item(s);
//			if(SpellFileNode.getNodeType() == Node.ELEMENT_NODE) {
//				String spellPath = XMLutil.getThisNodeValue(SpellFileNode);
//				loadSpell(spellPath);
//			}
//		}
//	}
		
	
	
	
	
	private void loadSpell(String spellPath) {
		// Creates and stores a new spell in the spellList
		String ext = spellPath.substring(spellPath.lastIndexOf(".")+1, spellPath.length()).toLowerCase();
		InputStream spellFile = null;
		String spellName = "N/A";
		try {
			spellFile = FileUtil.open(spellPath);	
//			if(ext.equals("xml")) {
//				Node spellNode = XMLutil.getXMLroot(spellFile);
//				BaseSpell newSpell = GameEffectLoader.loadTatrgetedSpell(dict);
//				spellName = newSpell.name().toUpperCase();
//				spellList.put(spellName, newSpell);
//			} 
				// YAML file
			DataDictionary dict = DataDictionary.makeDictionary(YAMLutil.loadData(spellFile));
			BaseSpell newSpell = GameEffectLoader.loadTatrgetedSpell(dict);
			spellName = newSpell.getName().toUpperCase();
			spellList.put(spellName, newSpell); 
		}
		catch (IOException err){
			FileErrors.globalErrors.addError("FILE-ERROR", ("GlobalSpellList.loadSpell, File = " + spellPath), err);
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("SPELL-LOAD", ("GlobalSpellList.loadSpell, Spell = " + spellName), err);
		}
		finally { 
			if (spellFile != null) { FileUtil.close(spellPath); }
		}
	}
}
