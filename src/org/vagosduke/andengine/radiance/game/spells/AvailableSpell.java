package org.vagosduke.andengine.radiance.game.spells;

import org.vagosduke.andengine.radiance.engine.menu.MenuItem;

public class AvailableSpell implements MenuItem {
	/** utility class to hold abilities and their 
	 * respective available ranks for Characters
	 */
	

	private TargetedSpell ability;
	private Integer[] availableRanks;
	
	
	
	
	///////////////////
	//	Constructor
	////////////////////
	public AvailableSpell(TargetedSpell ability, Integer[] availableRanks) {
		this.ability = ability;
		this.availableRanks = availableRanks;
	}


	
	
	
	
	//////////////
	//	Getters
	///////////////
	public TargetedSpell getAbility() {	return ability; }
	public Integer[] getAvailableRanks() { return availableRanks; }



	@Override
	public String getMenuLabel() {
		return ability.getName();
	}
	@Override
	public String getMenuInfo() {
		// TODO Auto-generated method stub
		return ability.getDescription();
	}

}
