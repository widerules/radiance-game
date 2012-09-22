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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Vector;

import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.game.spells.effects.GlobalEffectList;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.game.template.Template.*;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class SpellRank {
	private int rank;
	private int MPcost;
	private TemplateInfo rangeTemplate;
	private TemplateInfo effectTemplate;
	private ArrayList<BaseEffect> EffectList;
	
	
	/////////////////
	//	Constructor
	/////////////////
	public SpellRank() {
		this.rank = 0;
		this.MPcost = 0;
		this.rangeTemplate = new TemplateInfo();
		this.effectTemplate = new TemplateInfo();
		this.EffectList = new ArrayList<BaseEffect>();
	}
	public SpellRank(int rank, int MPcost, TemplateInfo rangeTemplate,
			TemplateInfo effectTemplate, ArrayList<BaseEffect> cEffectList) {
		this.rank = rank;
		this.MPcost = MPcost;
		this.rangeTemplate = rangeTemplate;
		this.effectTemplate = effectTemplate;
		this.EffectList = cEffectList;
	}
	
	////////////////////////
	//	Getters
	////////////////////////
	public int getRank() { return rank; }
	public int getMPcost() { return MPcost; }
	public TemplateInfo getRangeTemplate() { return rangeTemplate; }
	public TemplateInfo getEffectTemplate() { return effectTemplate; }
	public ArrayList<BaseEffect> getcEffectList() { return EffectList; }
	
}