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

package org.vagosduke.andengine.radiance.game.template;

import org.vagosduke.andengine.radiance.program.variables.GPoint;


public class Template {	
	//////////////////////////////////////////////////////
	// Static template GPoints used by the constructor
	// and enums
	//////////////////////////////////////////////////////
	
	public static enum snapTarget { ENEMY, ALLY, BOTH, NONE };
	public static enum returnTarget { ENEMY, ALLY, BOTH };
	public static enum templateType { DIAMOND, SQUARE, CIRCLE };
	
	static private final GPoint[][] radiousDiamond = new GPoint[][] { 
			/* rad = 0*/	{ 	new GPoint(0,0) }, 
			/* rad = 1*/	{ 	new GPoint(0,1), 	new GPoint(1,0),
								new GPoint(-1,0), 	new GPoint(0,-1)},
			/* rad = 2*/	{ 	new GPoint(-1,-1), 	new GPoint(1,-1),
								new GPoint(1,1), 	new GPoint(-1,1),
								new GPoint(2,0), 	new GPoint(0,2),
								new GPoint(-2,0), 	new GPoint(0,-2)},
			/* rad = 3*/	{ 	new GPoint(3,0), 	new GPoint(0,3), 
								new GPoint(-3,0), 	new GPoint(0,-3), 
								new GPoint(1,2), 	new GPoint(2,1),
								new GPoint(-1,2), 	new GPoint(-2,1),
								new GPoint(1,-2), 	new GPoint(2,-1),
								new GPoint(-1,-2), 	new GPoint(-2,-1)} };
	
	static private final GPoint[][] radiousSquare = new GPoint[][] { 
		/* rad = 0*/	{ 	new GPoint(0,0) }, 
		/* rad = 1*/	{ 	new GPoint(0,1), 	new GPoint(1,0),
							new GPoint(-1,0), 	new GPoint(0,-1),
							new GPoint(1,1), 	new GPoint(1,-1),
							new GPoint(-1,1), 	new GPoint(-1,-1)},
		/* rad = 2*/	{ 	new GPoint(-2,2), 	new GPoint(2,2),
							new GPoint(-1,2),	new GPoint(1,2),
							new GPoint(0,2), 	new GPoint(0,-2),
							new GPoint(-2,-2), 	new GPoint(2,-2),
							new GPoint(-1,-2), 	new GPoint(1,-2),
							new GPoint(2,1), 	new GPoint(-2,1),
							new GPoint(2,0),	new GPoint(-2,0),
							new GPoint(2,-1), 	new GPoint(-2,-1)},
		/* rad = 3*/	{ 	new GPoint(-3,3), 	new GPoint(-3,-3),
							new GPoint(-2,3), 	new GPoint(-2,-3),
							new GPoint(-1,3), 	new GPoint(-1,-3),
							new GPoint(0,3), 	new GPoint(0,-3),
							new GPoint(1,3), 	new GPoint(1,-3),
							new GPoint(2,3), 	new GPoint(2,-3),
							new GPoint(3,3), 	new GPoint(3,-3),
							new GPoint(-3,2), 	new GPoint(3,2),
							new GPoint(-3,1), 	new GPoint(3,1),
							new GPoint(-3,0), 	new GPoint(3,0),
							new GPoint(-3,-1), 	new GPoint(3,-1),
							new GPoint(-3,-2), 	new GPoint(3,-2)} };	
	
	
	

	
	
	///////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	private GPoint[] pointList;
	public snapTarget snaps;
	public returnTarget targets;
	
	
	
	///////////////////////////////
	//	Custom Constructors
	///////////////////////////////
	public static Template makeTemplate(TemplateInfo info) {
		Template temp;
		if(info.shape == templateType.DIAMOND) {
			temp = makeDiamondTemplate(info.rangeIn, info.rangeOut);
		}
		else if(info.shape == templateType.SQUARE) {
			temp = makeSquareTemplate(info.rangeIn, info.rangeOut);
		}
		else {
			temp = new Template();
		}
		temp.snaps = info.snaps;
		temp.targets = info.targets;
		return temp;
	}
	public static Template makeDiamondTemplate(int inRad, int outRad) {
		/*
		 * Make a zero-centered Diamond template of various radiuses
		 * from "radiousDiamond" static list of GPoints
		 * rad = 1			2			3
		 *                              #
		 *                  #          # #
		 *      #          # #        #   #
		 *     #@#        # @ #      #  @  #
		 *      #          # #        #   #
		 *                  #          # #
		 *                              #
		 */ 
	
		int countGPoint = 0;
		for (int i=inRad; i<=outRad; i++) {
			countGPoint += radiousDiamond[i].length;
		}
		GPoint[] tempGPoints = new GPoint[countGPoint];
		countGPoint = 0;
		for (int i=inRad; i<=outRad; i++) {
			System.arraycopy(radiousDiamond[i], 0, tempGPoints, countGPoint, radiousDiamond[i].length);
			countGPoint += radiousDiamond[i].length;
		}
		return new Template(tempGPoints);
	}
	public static Template makeSquareTemplate(int inRad, int outRad) {
		/*
		 * Make a zero-centered square template of various radiuses
		 * from "radiousSquare" static list of GPoints
		 * rad= 1			2			3
		 *                           #######
		 *                #####      #     #
		 *     ###        #   #      #     #
		 *     #@#        # @ #      #  @  #
		 *     ###        #   #      #     #
		 *                #####      #     #
		 *                           #######
		 */

		int countGPoint = 0;
		for (int i=inRad; i<=outRad; i++) {
			countGPoint += radiousSquare[i].length;
		}
		GPoint[] tempGPoints = new GPoint[countGPoint];
		countGPoint = 0;
		for (int i=inRad; i<=outRad; i++) {
			System.arraycopy(radiousSquare[i], 0, tempGPoints, countGPoint, radiousSquare[i].length);
			countGPoint += radiousSquare[i].length;
		}
		return new Template(tempGPoints);
	}
	
	//////////////////////////////////
	// Public Constructors
	//////////////////////////////////
	public Template() {
		this.pointList = new GPoint[0];
		this.snaps = snapTarget.NONE;
		this.targets = returnTarget.BOTH;
	}
	public Template(GPoint[] GPoints) {
		this.pointList = GPoints;
		this.snaps = snapTarget.NONE;
		this.targets = returnTarget.BOTH;
	}
//	public Template(TemplateInfo info) {
//		Template temp = makeTemplate(info);
//		this.pointList = temp.pointList;
//		this.snaps = temp.snaps;
//		this.targets = temp.targets;
//	}
	
	
	
	
	
	
	
	
	
	
	////////////////////
	//	Getters
	////////////////////
	public GPoint[] getPointList() { return pointList; }
	public snapTarget getSnaps() { return snaps; }
	public returnTarget getTargets() { return targets; }


	
}
