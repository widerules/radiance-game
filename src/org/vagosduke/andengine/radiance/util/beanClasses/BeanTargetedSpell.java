package org.vagosduke.andengine.radiance.util.beanClasses;

import org.vagosduke.andengine.radiance.game.spells.SpellRank;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;


public class BeanTargetedSpell {
	private String name;
	private String description;
	private int ranksNum;
	private BeanSpellRank[] ranks;


	public BeanTargetedSpell() {
		this.name = "N/A";
		this.description = "N/A";
		this.ranksNum = 0;
		this.ranks = null;
	}


	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getRanksNum() {
		return ranksNum;
	}
	public BeanSpellRank[] getRanks() {
		return ranks;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRanksNum(int ranksNum) {
		this.ranksNum = ranksNum;
	}
	public void setRanks(BeanSpellRank[] ranks) {
		this.ranks = ranks;
	}
	
	
	
	
	//////////////////
	// Methods
	///////////////////
	public TargetedSpell extract() {
		
		SpellRank[] trueRanks = new SpellRank[this.ranks.length];
		for(int i=0; i<this.ranks.length; i++) {
			trueRanks[i] = this.ranks[i].extract();
		} 
		return new TargetedSpell(this.name, this.description, this.ranksNum, trueRanks);
	}
}

	