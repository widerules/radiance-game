package org.anddev.game.items;

import java.io.InputStream;


public class ShieldList extends ItemList<ShieldItem>{
	
	public static ShieldList createEmpty() {
		return new ShieldList(ShieldItem.class);
	}
	public ShieldList(Class<ShieldItem> clazz) {
		super(clazz);
	}
	
	
	
	//////////////////////////////
	//	Public Methods
	//////////////////////////////
	public void initialize(InputStream ifile) {
		this.loadFromXML(ifile);
		this.sort();
	}
	public void sort() {
		this.sortByLevel();
	}
	
	
	
	
	////////////////////////////////////
	//	Private Methods
	////////////////////////////////////
	private void sortByLevel() {
		this.sortingList.sortByLevel();
	}
}
