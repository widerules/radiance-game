package org.anddev.game.constants;



public class Types {
	/**
	 * Static and dynamically (runtime) generated constants
	 * 
	 * Static Enums
	 * -------------
	 * gstate = gamestate
	 * InputType = processed input type
	 * menuType = menu type
	 * templateType = template shape
	 * iType = item type
	 * cType = consumable type
	 * bType = buff type
	 * 
	 *
	 * 
	 * Dynamic Enums (must be loaded in onCreate())
	 * --------------------------------------------
	 * wType = weapon type
	 * aType = armor type
	 * dType = damage type
	 */
	
	
	
	public static enum gstate { MENU, MOVE, COMBAT, TEMPLATE, CINEMATIC, WAIT }
	public static enum InputType { UP, DOWN, LEFT, RIGHT, OK, BACK }
	public static enum menuType { MAIN, CHAR, MAGIC, ITEM, ITEMACTION }
	public static enum templateType { DIAMOND, SQUARE, CIRCLE }
	
	
	public static enum bType { ATTACK, DEFENSE, MAGIC, SPEED, HPMAX, MPMAX, DAMAGE, ARMOR } // buff
	
	
	
	////////////////////////////////////
	//	Getters
	////////////////////////////////////
	
	
}