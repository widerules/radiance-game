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

package org.vagosduke.andengine.radiance.program;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.vagosduke.andengine.radiance.game.GameState;
import org.vagosduke.andengine.radiance.game.GameState.OSenum;
import org.vagosduke.andengine.radiance.program.log.ProgLog;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

public class SystemInterface {

	private AssetManager asset;
	///////////////////////////////
	//	Constructor
	///////////////////////////////
	
	public SystemInterface() {
		
	}
	
	public void loadAssets(Activity activity) {
		Log.i("ASSETS", "Fetching program's Assets...)");
		asset = activity.getAssets();
		Log.i("ASSETS", "Assets fetched and stored...)");
	}
	
	public InputStream getFile(String path)  throws IOException 	{
		InputStream fileStream = null;
		String fullPath = ("assets/" + path);
		ProgLog.message("FILE", "asset file: " + path + " requested.");
		if(GameState.getEnvironmentOS() == OSenum.ANDROID) {
			fileStream = asset.open(path);
			return fileStream;
		}
		else if (GameState.getEnvironmentOS() == OSenum.OTHER){
			fileStream = new FileInputStream(fullPath);
			return fileStream;
		}
		return null;
	}
	
}
