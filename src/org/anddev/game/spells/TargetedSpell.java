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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.anddev.game.character.Character;
import org.anddev.game.spells.effects.BaseEffect;
import org.anddev.game.template.Template.TemplateInfo;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class TargetedSpell implements BaseSpell{
	
	private String name;
	private String description;
	private int ranksNum;
	private ArrayList<SpellRank> ranks;
	
	
	
	
	/////////////////////////
	//	Constructor
	/////////////////////////
	public TargetedSpell() {
		this.name = "";
		this.description = "";
		this.ranksNum = 0;
		this.ranks = new ArrayList<SpellRank>();
	}
	@Override
	public BaseSpell create(Node nspell) throws Exception {
		this.loadFromNode(nspell);
		return this;
	}
	
	
	

	
	
	/////////////////////////////////
	//	Getters
	/////////////////////////////////
	@Override
	public String name() { return this.name; }
	@Override
	public String description() { return this.description; }
	@Override
	public int totalRanks() { return this.ranksNum; }

	
	@Override
	public int getMPcost(int rank) { return ranks.get(rank).getMPcost(); }
	@Override
	public Vector<BaseEffect> getEffects(int rank) { return ranks.get(rank).getcEffectList(); }
	@Override
	public TemplateInfo getRangeTemplate(int rank) { return ranks.get(rank).getRangeTemplate(); }
	@Override
	public TemplateInfo getEffectTemplate(int rank) { return ranks.get(rank).getEffectTemplate(); }

	
	
	
	

	@Override
	public Boolean cast(int rank, Character caster, Vector<Character> targetList) {
		/**
		 * The spell applies all its effects of the selected rank
		 * to all targets in targetList
		 * 
		 */
		Vector<BaseEffect> spellEffects = this.getEffects(rank);
		
		Iterator<BaseEffect> eit = spellEffects.iterator();
		while(eit.hasNext()) {
			BaseEffect effect = eit.next();
			
			Iterator<Character> cit = targetList.iterator();
			while(cit.hasNext()) {
				Character target = cit.next();
				effect.apply(caster, target);
			}
		}

		return true;
	}
	
	
	
	
	
	

	
	
	
	
	////////////////////////////////
	//	Private Constructor
	////////////////////////////////
	private void loadFromNode(Node nspell) {
		this.name = XMLutil.getFirstElementValue(nspell, "name");
		this.description = XMLutil.getFirstElementValue(nspell, "description");
		this.ranksNum = Integer.parseInt(XMLutil.getFirstElementValue(nspell, "rankNum"));
		
		NodeList listOfRanks = XMLutil.getSubTree(nspell, "ranks");
		for(int s=0; s<listOfRanks.getLength() ; s++){
			Node rankNode = listOfRanks.item(s);
			if(rankNode.getNodeType() == Node.ELEMENT_NODE){
				try {
					int grade = Integer.parseInt(XMLutil.getThisNodeAttribute(rankNode, "grade"));
					this.ranks.add(grade, SpellRank.class.newInstance().createRank(rankNode));
				}catch (Exception t) {
					StringWriter sw = new StringWriter();
					PrintWriter pw = new PrintWriter(sw);
					t.printStackTrace(pw);
					Log.e("SPELL-EFFECT-CREATE", sw.toString());
				}
			}
		}
		
	}

}
