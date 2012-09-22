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
import java.util.Vector;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.log.ProgLog;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.TraceUtil;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class TargetedSpell implements BaseSpell{
	
	private String name;
	private String description;
	private int ranksNum;
	private SpellRank[] ranks;
	
	
	
	
	/////////////////////////
	//	Constructor
	/////////////////////////
	public TargetedSpell() {
		this.name = "N/A";
		this.description = "N/A";
		this.ranksNum = 0;
		this.ranks = null;
	}
	public TargetedSpell(String name, String description, int ranksNum,
			SpellRank[] ranks) {
		this.name = name;
		this.description = description;
		this.ranksNum = ranksNum;
		this.ranks = ranks;
	}
	
	

	
	
	
	//////////////////////
	//	Public Methods
	//////////////////////
	@Override
	public boolean canCast(int rank, Character caster) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cast(int rank, Character caster, ArrayList<Character> targetList) {
		/**
		 * The spell applies all its effects of the selected rank
		 * to all targets in targetList
		 * 
		 */
		if(!(caster.getAbilitiesModule().canCast(this, rank))) {
			return false;
		}
		ArrayList<BaseEffect> spellEffects = this.getEffects(rank);
		
		for(BaseEffect effect: spellEffects) {
			for(Character target: targetList) {
				effect.apply(caster, target);
			}
		}
		return true;
	}
	
	
	
	
	
	
	/////////////////////////////////
	//	Getters
	/////////////////////////////////
	@Override
	public String getName() { return this.name; }
	@Override
	public String getDescription() { return this.description; }
	@Override
	public int getTotalRanks() { return this.ranksNum; }

	
	@Override
	public int getMPcost(int rank) { return this.ranks[rank+1].getMPcost(); }
	@Override
	public ArrayList<BaseEffect> getEffects(int rank) { return this.ranks[rank+1].getcEffectList(); }
	@Override
	public TemplateInfo getRangeTemplate(int rank) { return this.ranks[rank+1].getRangeTemplate(); }
	@Override
	public TemplateInfo getEffectTemplate(int rank) { return this.ranks[rank+1].getEffectTemplate(); }

	
	
	
	
	////////////////////////////////
	//	Private Constructor
	////////////////////////////////
	
//	private TargetedSpell loadFromDict(DataDictionary dict) throws Exception {
//		String job = "N/A";
//		try {
//			job = "name"; this.name = dict.getString("name");
//			job = "description"; this.description = dict.getString("description");
//			job = "rankNum"; this.ranksNum = dict.getInteger("rankNum");
//			job = "ranks"; this.ranks = new SpellRank[this.ranksNum];
//			
//			DataDictionary rankDict = dict.getSubArray("ranks");
//			for(int i=0; i<rankDict.getArraySize(); i++) {
//				int grade = rankDict.getSubDictionary(i).getInteger("rank") - 1;	
//				this.ranks[grade] = SpellRank.create(rankDict.getSubDictionary(i), (grade+1));
//			}
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("SPELL-LOAD", ("TargetedSpell.load, Spell=\"" + this.name + "\" at=" + job), err);
//			throw err;
//		}
//		return this;
//	}
}
