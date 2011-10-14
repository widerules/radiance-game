package org.anddev.game.items;


import java.util.ArrayList;


public class SortingItemList<I extends Item> extends ArrayList<I> {
	/**
	 * Provides basic xml-loading and sorting functions for Item Lists
	 */
	private static final long serialVersionUID = 2589866866746832479L;
	
	
	
	//////////////////////
	//	Constructors
	//////////////////////
	
	
	
	
	/////////////////////
	//	getters
	/////////////////////
	public I getByIndex(int index) {
		return this.get(index);
	}
	
	
	
	
	

	
	
	
	
	/////////////////////
	//	public methods
	/////////////////////
	public void sortLevelThenName() {
		// first sort all by level, and then sub-sort by name
		sortByLevel();
		int start = 0;
		int end = 0;
		int curlvl = this.get(0).levelRequired;
		int i = 1;
		while (i < this.size()) {
			if (this.get(i).getLevelRequired() != curlvl) {
				end = i;
				sortByName(start, end);
				start = i;
			}
			i++;
		}
	}
	
	public void sortByLevel() {
		sortByLevel(0, this.size());
	}
	public void sortByName() {
		sortByName(0, this.size());
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		for(int i=0; i<this.size(); i++) {
			result.append(i + ") " + this.get(i).toString() + "\n");
		}
		result.append("\n");
		return result.toString();
	}
	
	
	
	
	
	
	
	/////////////////////
	//	private methods
	/////////////////////
//	private I pGetByName(String iname) {
//		if(this.indexedByName) {
//			// since indexed by name, then do binary search
//			int start = 0;
//			int end = this.size()-1;
//			int mid = (start + end) / 2;
//			while(start <= end) {
//				if(this.get(mid).getName().equals(iname)) {
//					return this.get(mid);
//				}
//				else {
//					if(this.get(mid).getName().compareTo(iname) > 0) {
//						end = mid - 1;
//						mid = (start + end) / 2;
//					}
//					else {
//						start = mid + 1;
//						mid = (start + end) / 2;
//					}
//				}
//			}
//			return null;
//		}
//		else {
//			for(int i=0; i<this.size(); i++) {
//				if(this.get(i).getName().equals(iname)) {
//					return this.get(i);
//				}
//			}
//			return null;
//		}
//	}
	protected void generateIDs() {
		// all items have an ID field. After internal sorting etc, 
		// ID should contain a serial number for each item.
		// By default this number is the final position in the ArrayList.
		for(int i=0; i<this.size(); i++) {
			this.get(i).ID = i;
		}
	}
	
	protected void sortByLevel(int left, int right){
		// uses fixed-space quicksort
		if(left < right) {
			int pivotIndex = (right-left)/2 + left;
			int newPivotIndex = this.partitionLevel(left, right, pivotIndex);
			this.sortByLevel(left, newPivotIndex);
			this.sortByLevel(newPivotIndex+1, right);
		}
	}
	protected void sortByName(int left, int right){
		// uses fixed-space quicksort
		if(left < right) {
			int pivotIndex = (right-left)/2 + left;
			int newPivotIndex = this.partitionName(left, right, pivotIndex);
			this.sortByName(left, newPivotIndex);
			this.sortByName(newPivotIndex+1, right);
		}
	}
	
	private int partitionName(int left, int right, int pivotIndex) {
		// swap pivot index to right end
		I temp;
		I pivot = this.get(pivotIndex);
		this.set(pivotIndex, this.get(right-1));
		this.set(right-1, pivot);
		
		int storeIndex = left;
		for(int i=left; i<right-1; i++) {
			if(this.get(i).getName().compareTo(pivot.name) < 0) {
				temp = this.get(i);
				this.set(i, this.get(storeIndex));
				this.set(storeIndex, temp);
				storeIndex++;
			}
		}
		// swap back pivot 
		this.set(right-1, this.get(storeIndex));
		this.set(storeIndex, pivot);
		return storeIndex;
	}
	private int partitionLevel(int left, int right, int pivotIndex) {
		// swap pivot index to right end
		I temp;
		I pivot = this.get(pivotIndex);
		this.set(pivotIndex, this.get(right-1));
		this.set(right-1, pivot);
		
		int storeIndex = left;
		for(int i=left; i<right-1; i++) {
			if(this.get(i).getLevelRequired() < pivot.levelRequired) {
				temp = this.get(i);
				this.set(i, this.get(storeIndex));
				this.set(storeIndex, temp);
				storeIndex++;
			}
		}
		// swap back pivot 
		this.set(right-1, this.get(storeIndex));
		this.set(storeIndex, pivot);
		return storeIndex;
	}
	
	
}