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

package org.anddev.engine.program;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

public class ProgramInterface {

	private AssetManager asset;
	
	///////////////////////////////
	//	Constructor
	///////////////////////////////
	public ProgramInterface() {
		
	}
	
	public void loadAssets(Activity activity) {
		Log.i("ASSETS", "Fetching program's Assets...)");
		asset = activity.getAssets();
		Log.i("ASSETS", "Assets fetched and stored...)");
	}
	
	public InputStream getAssetFile(String path)  throws IOException 	{
		InputStream fileStream = null;
		Log.i("FILE", "asset file: " + path + " requested.");
		try {
			fileStream = asset.open(path);
		}
		catch (IOException e) {
			Log.e("FILE", ("Cannot open asset: " + path));
		}
		Log.i("FILE", "asset file: " + path + " fetched:" + fileStream.available());
		return fileStream;
	}
	
}
