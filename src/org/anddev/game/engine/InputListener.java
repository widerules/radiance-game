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

package org.anddev.game.engine;

public class InputListener {
	public static enum inputType { 
		UP, DOWN, LEFT, RIGHT, OK, BACK;
		
		public Boolean isDirection() {
			switch(this) {
				case UP: return true;
				case DOWN: return true;
				case LEFT: return true;
				case RIGHT: return true;
				default: return false;
			}
		}
	
		public Boolean isCommand() {
			switch(this) {
				case OK: return true;
				case BACK: return true;
				default: return false;
			}
		}
		
		public Direction getDirection() {
			// Warning, Unsafe
			switch(this) {
				case UP: return Direction.UP;
				case DOWN: return Direction.DOWN;
				case LEFT: return Direction.LEFT;
				case RIGHT: return Direction.RIGHT;
				default: return Direction.UP;
			}
		}
	
	}
	
	public static enum Direction { UP, DOWN, LEFT, RIGHT }
	
	
	// public void engineInput ("androidEngineRawInput") {
	// 	
	//	filter out unwanted inputs and feed the desired inputs 
	//	to the engine according to engineState
	/*
	 * if (gameState != wait) {
	 * 	int res = GameState.processInput(nativeInput);
	 * }
	 * 
	 */
	
	
	
}
