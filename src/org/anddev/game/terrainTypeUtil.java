package org.anddev.game;


public class terrainTypeUtil {
	public static enum terrainType {
		LAND, WATER, WALL, FOREST, DESERT, BUILDING, IMPASSABLE
	}

	public static terrainType char2tt (char b) {
		// matching between bytes and terrain types
		if(b=='a')
			return terrainType.LAND;
		if(b=='w')
			return terrainType.WATER;
		if(b=='d')
			return terrainType.DESERT;
		if(b=='f')
			return terrainType.FOREST;
		if(b=='b')
			return terrainType.BUILDING;
		
		return terrainType.IMPASSABLE;
	}
}