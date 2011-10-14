package org.anddev.game.spells;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Vector;

import org.anddev.game.constants.Types.templateType;
import org.anddev.game.spells.effects.BaseEffect;
import org.anddev.game.spells.effects.EffectList;
import org.anddev.game.template.Template.TemplateInfo;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class SpellRank {
	private int MPcost;
	private TemplateInfo RangeTemplate;
	private TemplateInfo EffectTemplate;
	private Vector<BaseEffect> cEffectList;
	
	
	/////////////////
	//	Constructor
	/////////////////
	public SpellRank() {
		this.MPcost = 0;
		this.RangeTemplate = new TemplateInfo();
		this.EffectTemplate = new TemplateInfo();
		this.cEffectList = new Vector<BaseEffect>();
	}
	
	public SpellRank createRank(Node nrank) {
		this.loadFromNode(nrank);
		return this;
	}
	
	
	
	
	
	
	////////////////////////
	//	Getters
	////////////////////////
	public int getMPcost() { return MPcost; }
	public TemplateInfo getRangeTemplate() { return RangeTemplate; }
	public TemplateInfo getEffectTemplate() { return EffectTemplate; }
	public Vector<BaseEffect> getcEffectList() { return cEffectList; }
	
	
	
	
	
	//////////////////////////
	//	Private Constructor
	//////////////////////////
	private void loadFromNode(Node nrank) {
		this.MPcost = Integer.parseInt(XMLutil.getFirstElementValue(nrank, "MPcost"));
		this.RangeTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(nrank, "range"));
		this.RangeTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(nrank, "range", "in"));
		this.RangeTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(nrank, "range", "out"));
		this.EffectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(nrank, "effectTemplate"));
		this.EffectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(nrank, "effectTemplate", "in"));
		this.EffectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(nrank, "effectTemplate", "out"));
		
		NodeList listOfEffects = XMLutil.getSubTree(nrank, "effects");
		for(int s=0; s<listOfEffects.getLength() ; s++){
			Node effectNode = listOfEffects.item(s);
			if(effectNode.getNodeType() == Node.ELEMENT_NODE){
				try { 
					this.cEffectList.add(EffectList.createEffect(effectNode));
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