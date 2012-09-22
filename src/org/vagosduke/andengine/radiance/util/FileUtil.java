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

package org.vagosduke.andengine.radiance.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import org.vagosduke.andengine.radiance.game.GameState;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.log.ProgLog;

public class FileUtil {
	
	public static enum opSys {ANDROID, OTHER};
	
	
	private static HashMap<String,InputStream> openFiles = new HashMap<String,InputStream>();
	
	
	//////////////////////////
	//	Public Static Methods
	//////////////////////////
	public static InputStream open(String path) throws IOException {
		InputStream input = GameState.getProgramInterface().getFile(path);
		openFiles.put(path, input);
		return input;
	}
	
	
	public static void close(String filename)  {
		if(openFiles.containsKey(filename)) {
			try {
				openFiles.remove(filename).close();
			} catch (IOException err) { FileErrors.globalErrors.addWarning("FILE-CLOSE", filename, err); }
		}
	}
	
	
	
	
	
	
	
	///////////////////////////
	//	Private Static Methods
	///////////////////////////

}
