package org.anddev.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.anddev.program.GameState;

public class FileUtil {
	
	public static enum opSys {ANDROID, OTHER};
	
	
	private static HashMap<String,InputStream> openFiles = new HashMap<String,InputStream>();
	
	
	//////////////////////////
	//	Public Static Methods
	//////////////////////////
	
	public static InputStream open(String path) {
		boolean android = GameState.getAndroidSystemStatus();
		opSys system;
		if(android) { 	system=opSys.ANDROID; }
		else {			system=opSys.OTHER; }
		
		return open(path, system);
	}
	
	public static InputStream open(String path, opSys system) {
		if(system.equals(opSys.ANDROID)) {
			return getAsset(path);
		}
		if(system.equals(opSys.OTHER)) {
			return getFile(path);
		}
		return null;
	}
	
	
	public static void close(String filename) {
		try{
			openFiles.remove(filename).close();
		}
		catch (IOException err) { }
	}
	
	
	
	
	
	
	
	///////////////////////////
	//	Private Static Methods
	///////////////////////////
	private static InputStream getAsset(String path) {
		try {
			InputStream input = GameState.getProgramInterface().getAssetFile(path);
			openFiles.put(path, input);
			return input;
		}
		catch (IOException err) {
			err.printStackTrace();
		}
		return null;
	}
	private static InputStream getFile(String path) {
		try {
			String fullPath = ("assets/" + path);
			InputStream input = new FileInputStream(fullPath);
			openFiles.put(path, input);
			return input;
		}
		catch (IOException err) {
			err.printStackTrace();
		}
		return null;
	}
	

	
	

}
