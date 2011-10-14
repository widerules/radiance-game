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
