package org.vagosduke.andengine.radiance.game.map;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.program.variables.GPoint;

public class TacticalMapTile {
	
	/** 
	 * 	each instance represents one map tile on the tactical map.
	 */
	
	GPoint position;

	private boolean hasCharacter; 
	private Character thisCharacter;
	
	private EnumValue TypeOfTerrain;
	private int traversalFactor;
	
	
	/////////////////////////
	//	Constructor
	///////////////////////
	public TacticalMapTile(GPoint position, boolean hasCharacter,
			Character thisCharacter, EnumValue typeOfTerrain,
			int traversalFactor) {
		this.position = position;
		this.hasCharacter = hasCharacter;
		this.thisCharacter = thisCharacter;
		TypeOfTerrain = typeOfTerrain;
		this.traversalFactor = traversalFactor;
	}
	
	
	
	
	
	
	
	
	////////////////////////
	//	Public Methods
	////////////////////////
	public GPoint getPosition() { return position; }
	public boolean hasCharacter() { return hasCharacter; }
	public Character getThisCharacter() { return thisCharacter; }
	public EnumValue getTypeOfTerrain() { return TypeOfTerrain; }
	public int getTraversalFactor() { return traversalFactor; }
	

}
