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


package org.vagosduke.andengine.radiance.game.inventory;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.items.BaseItem;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class Inventory {
	
	
	protected ArrayList<BaseItem> inventoryList;
	private int maxSize;
	private double maxWeight;
	
	//////////////////
	//	Constructors
	//////////////////
	public Inventory() {
		this.inventoryList = new ArrayList<BaseItem>();
		this.maxSize = 1;
		this.maxWeight = 1.0;
	}
	public Inventory(int isize, double imaxWeight) {
		this.inventoryList = new ArrayList<BaseItem>();
		this.maxSize = isize;
		this.maxWeight = imaxWeight;
	}
	
	
	
	
	
	///////////////////////
	//	Public Functions
	///////////////////////
	public void add(BaseItem item) {
		if(item.isStackable()) {
			int index = this.findByName(item.getName());
			if(index != -1) {
				// If stackable item exists, add stacks instead
				this.inventoryList.get(index).addStacks(item.getStacks());
			}
			else {
				this.inventoryList.add(item.getInstance());
			}
		}
		else {
			// Item is not stackable. multiple instances allowed
			this.inventoryList.add(item);
		}
	}
	public void remove(BaseItem item) {
		this.inventoryList.remove(item);
	}
	public void remove(int index) {
		this.inventoryList.remove(index);
	}
	
	
	
	public boolean exists(BaseItem item) { 
		if(this.inventoryList.indexOf(item) == -1) { return false; }
		else { return true; }
	}
	public int find(BaseItem item) { 
		return this.inventoryList.indexOf(item); 
	}
	public int findByName(String name) { 
		for(int i=0; i<this.inventoryList.size(); i++) {
			if(this.inventoryList.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	public BaseItem findItemByName(String name) { 
		for(int i=0; i<this.inventoryList.size(); i++) {
			if(this.inventoryList.get(i).getName().equals(name)) {
				return this.inventoryList.get(i);
			}
		}
		return null;
	}
	
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("=== INVENTORY ===\n");
		for(BaseItem itm: inventoryList) {
			str.append(itm.getName() + "\n");
		}
		return str.toString();
	}
	
	
	
	////////////////////
	//	Getters
	////////////////////
	public BaseItem getItem(int index) { return this.inventoryList.get(index); }
	public int getMaxSize() { return this.maxSize; }
	public double getMaxWeight() { return this.maxWeight; }
	
	
	
	
	
	///////////////////
	//	Loader
	///////////////////
	public void load(DataDictionary dict) {
		String job = "N/A";
		try {
			job = "maxSize"; this.maxSize = dict.getInteger("maxSize");
			job = "maxWeight"; this.maxWeight = dict.getDouble("maxWeight");
			job = "items";
			if(dict.exists("items")) {
				DataDictionary itemDict = dict.getSubArray("items");
				for(int i=0; i<itemDict.getArraySize(); i++ ){
					//TODO
					//this.inventoryList.add(GlobalItemList.getItem(dict.getString(i)));
				}
			}
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("INVENTORY-LOAD", ("Inventory.load, at=" + job), err);
		}
	}
}
