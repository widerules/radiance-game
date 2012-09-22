package org.vagosduke.andengine.radiance.util.beanClasses;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.spells.SpellRank;
import org.vagosduke.andengine.radiance.game.spells.effects.BaseEffect;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;

public class BeanSpellRank {
	private int rank;
	private int MPcost;
	private BeanTemplateInfo rangeTemplate;
	private BeanTemplateInfo effectTemplate;
	private ArrayList<BeanBaseEffect> effectList;
	
	
	public BeanSpellRank() {
		this.rank = 0;
		this.MPcost = 0;
		this.rangeTemplate = new BeanTemplateInfo();
		this.effectTemplate = new BeanTemplateInfo();
		this.effectList = new ArrayList<BeanBaseEffect>();
	}
	
	
	
	public int getRank() {
		return rank;
	}
	public int getMPcost() {
		return MPcost;
	}
	public BeanTemplateInfo getRangeTemplate() {
		return rangeTemplate;
	}
	public BeanTemplateInfo getEffectTemplate() {
		return effectTemplate;
	}
	public ArrayList<BeanBaseEffect> getEffectList() {
		return effectList;
	}
	
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void setMPcost(int mPcost) {
		MPcost = mPcost;
	}
	public void setRangeTemplate(BeanTemplateInfo rangeTemplate) {
		this.rangeTemplate = rangeTemplate;
	}
	public void setEffectTemplate(BeanTemplateInfo effectTemplate) {
		this.effectTemplate = effectTemplate;
	}
	public void setEffectList(ArrayList<BeanBaseEffect> effectList) {
		this.effectList = effectList;
	}
	
	
	//////////////
	// Methods
	//////////////
	public SpellRank extract() {
		ArrayList<BaseEffect> trueEffectList = new ArrayList<BaseEffect>();
		for(BeanBaseEffect effect: this.effectList) {
			trueEffectList.add(effect.extract());
		}
		return new SpellRank(this.rank, this.MPcost, this.rangeTemplate.extract(), 
				this.effectTemplate.extract(), trueEffectList);
	}







	
}
