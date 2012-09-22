package org.vagosduke.andengine.radiance.program.variables;

import java.util.Comparator;

public class GPointComparator implements Comparator<GPoint> {
	/** Compares the distance of two points from origin */
	
	GPoint origin;
	
	
	public GPointComparator(GPoint origin) {
		this.origin = origin;
	}
	
	@Override
	public int compare(GPoint object1, GPoint object2) {
		return object1.compareTo(origin) - object2.compareTo(origin);
	}
	

}
