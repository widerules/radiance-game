package org.vagosduke.andengine.radiance.game.map;

import java.util.ArrayList;
import java.util.Collections;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.character.InfoModule.friendOrFoe;
import org.vagosduke.andengine.radiance.game.template.Template;
import org.vagosduke.andengine.radiance.game.template.Template.returnTarget;
import org.vagosduke.andengine.radiance.game.template.Template.snapTarget;
import org.vagosduke.andengine.radiance.program.variables.GPoint;
import org.vagosduke.andengine.radiance.program.variables.GPointComparator;

public class TacticalMap {

	/** 
	 * Represents the tactical tile map
	 * 
	 */
	
	
	private TacticalMapTile[][] mapTiles;
	private int width;
	private int height;
	
	
	
	////////////////////////////
	//	Constructor
	////////////////////////////
	public TacticalMap(TacticalMapTile[][] mapTiles) {
		this.mapTiles = mapTiles;
		this.width = mapTiles[0].length;
		this.height = mapTiles.length;
	}
	
	
	
	
	
	
	
	
	
	
	///////////////////////////
	//	Public Methods
	///////////////////////////
	public ArrayList<Character> getTargets(Template effect, GPoint origin) {
		ArrayList<GPoint> inRangeTargets = getClosestTargetPoints(effect, origin);
		ArrayList<Character> charList = new ArrayList<Character>();
		for(GPoint point: inRangeTargets) {
			charList.add(this.getTile(point).getThisCharacter());
		}
		return charList;
	}
	
	public GPoint getClosestPoint(Template range, GPoint origin) {
		ArrayList<GPoint> inRangeTargets = getClosestSnappingPoints(range, origin);
		for(GPoint point: inRangeTargets) {
			if (range.getSnaps() == snapTarget.NONE) {
				return point;
			}
			else if (compareFoFSnap(this.getTile(point).getThisCharacter().getInfoModule().getFoF(), range.getSnaps())){
				return point;
			}
		}
		return null;
	}
	
	public GPoint getClosestPointUP(Template range, GPoint origin, GPoint currentPoint) {
		ArrayList<GPoint> inRangeTargets = getClosestSnappingPoints(range, origin);
		for(GPoint point: inRangeTargets) {
			if (range.getSnaps() == snapTarget.NONE) {
				return point;
			}
			else if ((compareFoFSnap(this.getTile(point).getThisCharacter().getInfoModule().getFoF(), range.getSnaps())) &&
					(point.y > currentPoint.y)) {
				return point;
			}
		}
		return null;
	}
		
	public GPoint getClosestPointDOWN(Template range, GPoint origin, GPoint currentPoint) {
		ArrayList<GPoint> inRangeTargets = getClosestSnappingPoints(range, origin);
		for(GPoint point: inRangeTargets) {
			if (range.getSnaps() == snapTarget.NONE) {
				return point;
			}
			else if ((compareFoFSnap(this.getTile(point).getThisCharacter().getInfoModule().getFoF(), range.getSnaps())) &&
					(point.y < currentPoint.y)) {
				return point;
			}
		}
		return null;
	}
	
	public GPoint getClosestPointLEFT(Template range, GPoint origin, GPoint currentPoint) {
		ArrayList<GPoint> inRangeTargets = getClosestSnappingPoints(range, origin);
		for(GPoint point: inRangeTargets) {
			if (range.getSnaps() == snapTarget.NONE) {
				return point;
			}
			else if ((compareFoFSnap(this.getTile(point).getThisCharacter().getInfoModule().getFoF(), range.getSnaps())) &&
					(point.x < currentPoint.x)) {
				return point;
			}
		}
		return null;
	}
	
	public GPoint getClosestPointRIGHT(Template range, GPoint origin, GPoint currentPoint) {
		ArrayList<GPoint> inRangeTargets = getClosestSnappingPoints(range, origin);
		for(GPoint point: inRangeTargets) {
			if (range.getSnaps() == snapTarget.NONE) {
				return point;
			}
			else if ((compareFoFSnap(this.getTile(point).getThisCharacter().getInfoModule().getFoF(), range.getSnaps())) &&
					(point.x > currentPoint.x)) {
				return point;
			}
		}
		return null;
	}
	

	public TacticalMapTile getTile(GPoint point) {
		if((point.x < this.width) && (point.y < this.height)) {
			return this.mapTiles[point.y][point.x];
		}
		else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
	///////////////////////
	//	Private Methods
	///////////////////////
	public ArrayList<GPoint> getClosestSnappingPoints(Template range, GPoint origin) {
		ArrayList<TacticalMapTile> tileRange = getTilesInRange(range, origin);
		ArrayList<GPoint> closestPoints = new ArrayList<GPoint>();
		
		
		if(range.snaps != snapTarget.NONE) {
			for(TacticalMapTile tile: tileRange) {
				closestPoints.add(tile.getPosition());
			}
		}
		else {
			for(TacticalMapTile tile: tileRange) {
				if((tile.hasCharacter()) && (compareFoFSnap(tile.getThisCharacter().getInfoModule().getFoF(), range.snaps))) {
					closestPoints.add(tile.getPosition());
				}
			}
		}
		
		GPointComparator comparator = new GPointComparator(origin);
		Collections.sort(closestPoints, comparator);
		return closestPoints;
	}
	
	private ArrayList<GPoint> getClosestTargetPoints(Template effect, GPoint origin) {
		ArrayList<TacticalMapTile> tileRange = getTilesInRange(effect, origin);
		ArrayList<GPoint> targetedPoints = new ArrayList<GPoint>();
		
		for(TacticalMapTile tile: tileRange) {
			if(compareFoFTarget(tile.getThisCharacter().getInfoModule().getFoF(), effect.getTargets())) {
				targetedPoints.add(tile.getPosition());
			}
		}
		return targetedPoints;
	}
	
	private ArrayList<TacticalMapTile> getTilesInRange(Template range, GPoint origin) {
		/** gets all tiles in template's range (independently of snapping, or targeting)
		 */
		ArrayList<TacticalMapTile> retList = new ArrayList<TacticalMapTile>();
		
		GPoint[] PointList = range.getPointList();
		for(int i=0; i<PointList.length; i++) {
			GPoint mapPoint = new GPoint(PointList[i].x+origin.x, PointList[i].y+origin.y); 
			retList.add(getTile(mapPoint));
		}
		//Collections.sort(PointList);
		return retList;
	}
	
	
	private boolean compareFoFSnap(friendOrFoe fof, snapTarget snap) {
		switch(fof) {
		case PLAYER: 
			switch(snap) {
			case ALLY: return true;
			case BOTH: return true;
			case ENEMY: return false;
			default: return false;
			}
		case ENEMY: 
			switch(snap) {
			case ALLY: return false;
			case BOTH: return true;
			case ENEMY: return true;
			default: return false;
			}
		case ALLY: 
			switch(snap) {
			case ALLY: return true;
			case BOTH: return true;
			case ENEMY: return false;
			default: return false;
			}
		}
		return false;
	}
	private boolean compareFoFTarget(friendOrFoe fof, returnTarget returnTarget) {
		switch(fof) {
		case PLAYER: 
			switch(returnTarget) {
			case ALLY: return true;
			case BOTH: return true;
			case ENEMY: return false;
			default: return false;
			}
		case ENEMY: 
			switch(returnTarget) {
			case ALLY: return false;
			case BOTH: return true;
			case ENEMY: return true;
			default: return false;
			}
		case ALLY: 
			switch(returnTarget) {
			case ALLY: return true;
			case BOTH: return true;
			case ENEMY: return false;
			default: return false;
			}
		}
		return false;
	}
	
	
}


