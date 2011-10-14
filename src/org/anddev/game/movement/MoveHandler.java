package org.anddev.game.movement;

import org.anddev.game.character.PlayerCharacter;
import org.anddev.game.engine.InputListener.Direction;

public class MoveHandler {
	
	public PlayerCharacter currentChar;
	
	
	
	
	//////////////////////
	//	Public Methods
	//////////////////////
	public int move(Direction direction) {
		// TODO
		// move character on the grid. Also begin animation
		// return -1 if cannot move that direction (obstacle, or out of range)
		
		/*
		 * Firstly: Take character from gridmap and move him to neighbor cell
		 * 			According to direction.
		 * Second:  - On graphics: generate on-cell path.
		 * 			- Make sprite follow that path (using the correct animation)
		 * 			- disable input
		 * 			- make a path listener so when finished, re-enable input
		 */
		return -1;
	}

}
