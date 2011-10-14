package org.anddev.engine.variables;

import android.graphics.Point;


public class GPoint extends Point{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5879405235472152839L;
	
	////////////////////
	//	Constructor
	////////////////////
	public GPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public GPoint(GPoint point) {
		this.x = point.x;
		this.y = point.y;
	}
	
	public GPoint add (Point b) {
		/**
		 * Returns a NEW point by adding the coordinates of the 2 points
		 */
		return new GPoint((this.x + b.x), (this.y + b.y));
	}

}
