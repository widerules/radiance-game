package org.anddev.game.template;

import org.anddev.engine.variables.GPoint;
import org.anddev.game.constants.Types.templateType;


public class Template {
	public static class TemplateInfo {
		public int rangeIn;
		public int rangeOut;
		public templateType shape;
	}
	
	
	public GPoint[] pointList;
	
	
	////////////////////////////////
	// Static template GPoints
	// used by the constructor
	////////////////////////////////
	static private final GPoint[][] radiousDiamond = new GPoint[][] { 
			/* rad = 0*/	{ new GPoint(0,0) }, 
			/* rad = 1*/	{ new GPoint(0,1), new GPoint(1,0),
								new GPoint(-1,0), new GPoint(0,-1)},
			/* rad = 2*/	{ new GPoint(-1,-1), new GPoint(1,-1),
								new GPoint(1,1), new GPoint(-1,1),
								new GPoint(2,0), new GPoint(0,2),
								new GPoint(-2,0), new GPoint(0,-2)},
			/* rad = 3*/	{ new GPoint(3,0), new GPoint(0,3), 
								new GPoint(-3,0), new GPoint(0,-3), 
								new GPoint(1,2), new GPoint(2,1),
								new GPoint(-1,2), new GPoint(-2,1),
								new GPoint(1,-2), new GPoint(2,-1),
								new GPoint(-1,-2), new GPoint(-2,-1)} };
	
	static private final GPoint[][] radiousSquare = new GPoint[][] { 
		/* rad = 0*/	{ new GPoint(0,0) }, 
		/* rad = 1*/	{ new GPoint(0,1), new GPoint(1,0),
							new GPoint(-1,0), new GPoint(0,-1),
							new GPoint(1,1), new GPoint(1,-1),
							new GPoint(-1,1), new GPoint(-1,-1)},
		/* rad = 2*/	{ new GPoint(-2,2), new GPoint(2,2),
							new GPoint(-1,2), new GPoint(1,2),
							new GPoint(0,2), new GPoint(0,-2),
							new GPoint(-2,-2), new GPoint(2,-2),
							new GPoint(-1,-2), new GPoint(1,-2),
							new GPoint(2,1), new GPoint(-2,1),
							new GPoint(2,0), new GPoint(-2,0),
							new GPoint(2,-1), new GPoint(-2,-1)},
		/* rad = 3*/	{ new GPoint(-3,3), new GPoint(-3,-3),
							new GPoint(-2,3), new GPoint(-2,-3),
							new GPoint(-1,3), new GPoint(-1,-3),
							new GPoint(0,3), new GPoint(0,-3),
							new GPoint(1,3), new GPoint(1,-3),
							new GPoint(2,3), new GPoint(2,-3),
							new GPoint(3,3), new GPoint(3,-3),
							new GPoint(-3,2), new GPoint(3,2),
							new GPoint(-3,1), new GPoint(3,1),
							new GPoint(-3,0), new GPoint(3,0),
							new GPoint(-3,-1), new GPoint(3,-1),
							new GPoint(-3,-2), new GPoint(3,-2)} };	
	
	
	
	//////////////////////////////////
	// Private Constructor
	//////////////////////////////////
	private Template(GPoint[] GPoints) {
		this.pointList = GPoints;
	}
	
	///////////////////////////////
	//	Custom Constructors
	///////////////////////////////
	public static Template make_diamond_template(int inRad, int outRad) {
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
	
	
	
	public static Template make_square_template(int inRad, int outRad) {
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
	
}
