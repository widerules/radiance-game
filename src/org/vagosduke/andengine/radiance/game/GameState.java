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

package org.vagosduke.andengine.radiance.game;

import org.vagosduke.andengine.radiance.engine.cursor.TargetSelectCursor;
import org.vagosduke.andengine.radiance.engine.menu.MenuManager;
import org.vagosduke.andengine.radiance.game.character.PlayerCharacter;
import org.vagosduke.andengine.radiance.game.constants.StaticTypes.gstate;
import org.vagosduke.andengine.radiance.game.movement.MoveHandler;
import org.vagosduke.andengine.radiance.game.template.TemplateHandler;
import org.vagosduke.andengine.radiance.program.SystemInterface;

import android.app.Activity;

public class GameState {
	public static gstate gameState;
	
	public static SystemInterface systemInterface = new SystemInterface();
	
	public static enum OSenum { ANDROID, OTHER }
	private static OSenum environmentOS = OSenum.OTHER;
	
	
	
	//////////////////////////////
	//	static Initializer
	//////////////////////////////
	public static void init (Activity activity) {
		environmentOS = getOSenum();
		//systemInterface = new SystemInterface();
		systemInterface.loadAssets(activity);
	}
	
	
	
	public static OSenum getEnvironmentOS() {return environmentOS; }
	public static SystemInterface getProgramInterface() {return systemInterface;}
	
	
	
	
	////////////////////////////
	//	Private Methods
	////////////////////////////
	private static OSenum getOSenum() {
		String os = System.getProperty("java.vm.name").toLowerCase();
		if(os.indexOf("dalvik") >= 0) { return OSenum.ANDROID; }
		else { return OSenum.OTHER; }
	}
}
