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
