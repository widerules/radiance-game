package org.anddev.game;

public class WorldItem {
	/** Object that stores details on blocks and sprites */
	
	// Max world dimensions
	private int tileWidth, tileHeight;
	
	// a list of tileOccupier objects
	private arrayList items;
	
	public WorldItem(int w, int h){
		tileWidth = w; tileHeight = h;
		items = new arrayList();
	}
	
	private class arrayList {
		
	}
}
