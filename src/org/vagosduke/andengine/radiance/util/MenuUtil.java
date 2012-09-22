package org.vagosduke.andengine.radiance.util;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.engine.menu.MenuItem;

public class MenuUtil {

	
	public static class MenuItemString implements MenuItem {
		/** the simplest menu Item */
		
		private int retCode;
		private String label;
		private String description;
		
		public MenuItemString(int retCode, String label, String description) {
			this.retCode = retCode;
			this.label = label;
			this.description = description;
		}
		
		public int getRetCode() { return retCode; }
		@Override
		public String getMenuLabel() { return label; }
		@Override
		public String getMenuInfo() { return description; }
	}
	
	
	
	
	
	public static <L extends MenuItem> ArrayList<MenuItem> arrayToList(L[] array){
		ArrayList<MenuItem> ret = new ArrayList<MenuItem>();
		for(L it: array) {
			ret.add(it);
		}
		return ret;
	}
	
	public static <L> ArrayList<MenuItem> arrayToList(L[] array){
		ArrayList<MenuItem> ret = new ArrayList<MenuItem>();
		for(int i=0; i<array.length; i++) {
			ret.add(new MenuItemString(i, "", array[i].toString()));
		}
		return ret;
	}
}
