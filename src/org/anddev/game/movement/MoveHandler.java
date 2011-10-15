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
