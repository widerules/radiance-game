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

package org.anddev.game;

import java.util.ArrayList;

import org.anddev.game.template.Template;
import org.anddev.program.variables.GPoint;

public class GridRange {
	/**
	 * GridRange is a collection of absolute Points representing a (possibly)
	 * irregular area on the Grid. It is used for movement range.
	 */
	
	public GPoint[] pointArray; 
	
	public GridRange() {
		pointArray = new GPoint[1];
		pointArray[0] = new GPoint(0,0);
	}
	public GridRange(Template templ, GPoint center) {
		this.pointArray = new GPoint[templ.pointList.length];
		for(int i=0; i< this.pointArray.length; i++) {
			this.pointArray[i] = templ.pointList[i].add(center);
		}
	}
	public GridRange(ArrayList<GPoint> pointList) {
		this.pointArray = (GPoint[]) pointList.toArray();
	}
	
	
	/////////////////////////
	//	Public Methods
	/////////////////////////
	public Boolean inGrid(GPoint cand) {
		for(int i=0; i<pointArray.length; i++) {
			if (pointArray[i].equals(cand)) {
				return true;
			}
		}
		return false;
	}
}
