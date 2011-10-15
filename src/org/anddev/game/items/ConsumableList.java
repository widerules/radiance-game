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

package org.anddev.game.items;

import java.io.InputStream;





public class ConsumableList extends ItemList<ConsumableItem>{
	
	//////////////////////////////
	//	Constructors
	//////////////////////////////
	
	public static ConsumableList createEmpty() {
		return new ConsumableList(ConsumableItem.class);
	}
	private ConsumableList(Class<ConsumableItem> clazz) {
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
		this.sortLevelThenType();
	}
	
	
	
	
	
	
	///////////////////////
	// Private Methods
	///////////////////////
	
	private void sortLevelThenType() {
		// sort by level and then sub-sort by atype
		this.sortByLevel();
		this.subsortByType();
	}
	private void sortByLevel() {
		this.sortingList.sortByLevel();
	}
	private void subsortByType() {
		int start = 0;
		int end = 0;
		int curlvl = sortingList.get(0).levelRequired;
		int i = 1;
		while (i < sortingList.size()) {
			if (sortingList.get(i).levelRequired != curlvl) {
				end = i;
				sort_by_cType(start, end);
				start = i;
			}
			i++;
		}
		sort_by_cType(start, sortingList.size());
	}
	
	private void sort_by_cType(int left, int right){
		// uses fixed-space quicksort
		if(left < right) {
			int pivotIndex = (right-left)/2 + left;
			int newPivotIndex = partition_cType(left, right, pivotIndex);
			sort_by_cType(left, newPivotIndex);
			sort_by_cType(newPivotIndex+1, right);
		}
	}
	
	private int partition_cType(int left, int right, int pivotIndex) {
		// swap pivot index to right end
		ConsumableItem temp;
		ConsumableItem pivot = sortingList.get(pivotIndex);
		sortingList.set(pivotIndex, sortingList.get(right-1));
		sortingList.set(right-1, pivot);
		
		int storeIndex = left;
		for(int i=left; i<right-1; i++) {
			if(sortingList.get(i).effectType.compareTo(pivot.effectType) < 0) {
				temp = sortingList.get(i);
				sortingList.set(i, sortingList.get(storeIndex));
				sortingList.set(storeIndex, temp);
				storeIndex++;
			}
		}
		// swap back pivot 
		sortingList.set(right-1, sortingList.get(storeIndex));
		sortingList.set(storeIndex, pivot);
		return storeIndex;
	}

}
