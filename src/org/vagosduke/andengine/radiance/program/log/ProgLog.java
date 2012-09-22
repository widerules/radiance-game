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


package org.vagosduke.andengine.radiance.program.log;

import org.vagosduke.andengine.radiance.game.GameState;
import org.vagosduke.andengine.radiance.game.GameState.OSenum;
import org.vagosduke.andengine.radiance.util.TraceUtil;

import android.util.Log;

public class ProgLog {
	
	
	public static void error(String tag, Exception err) {
		String message = TraceUtil.exceptionToString(err); // stack trace as a string

		OSenum android = GameState.getEnvironmentOS();
		if(android == OSenum.ANDROID) {
			Log.e(tag, message);
		}
		else {
			System.err.println(tag + " ||  " + message);
		}
	}
	
	public static void error(String tag, String err) {
		OSenum android = GameState.getEnvironmentOS();
		if(android == OSenum.ANDROID) {
			Log.e(tag, err);
		}
		else {
			System.err.println(tag + " ||  " + err);
		}
	}

	
	public static void message(String tag, String msg) {
		OSenum android = GameState.getEnvironmentOS();
		if(android == OSenum.ANDROID) {
			Log.d(tag, msg);
		}
		else {
			System.out.println(tag + " ||  " + msg);
		}
	}
}
