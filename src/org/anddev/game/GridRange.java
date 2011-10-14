package org.anddev.game;

import java.util.ArrayList;

import org.anddev.engine.variables.GPoint;
import org.anddev.game.template.Template;

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
