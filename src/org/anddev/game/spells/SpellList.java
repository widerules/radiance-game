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
