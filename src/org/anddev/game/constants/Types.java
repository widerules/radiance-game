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