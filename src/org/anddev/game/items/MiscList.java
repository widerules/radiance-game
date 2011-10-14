package org.anddev.game.items;

import java.io.InputStream;



public class MiscList extends ItemList<MiscItem>{
	
	/////////////////////////////////
	//	Constructors
	/////////////////////////////////	
	
	public static MiscList createEmpty() {
		return new MiscList(MiscItem.class);
	}
	public MiscList(Class<MiscItem> clazz) {
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
