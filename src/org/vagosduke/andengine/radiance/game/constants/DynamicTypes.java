package org.vagosduke.andengine.radiance.game.constants;

import java.io.InputStream;

import org.vagosduke.andengine.radiance.game.combat.Damage;
import org.vagosduke.andengine.radiance.game.items.ItemTypes;
import org.vagosduke.andengine.radiance.program.config.Filepath;
import org.vagosduke.andengine.radiance.program.loader.GameItemLoader;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumClass;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.FileUtil;
import org.vagosduke.andengine.radiance.util.YAMLutil;

import android.util.Log;

public class DynamicTypes {

	///////////////////////////////
	//	General loading function
	///////////////////////////////
	public static void initDynamicTypes() {
		initDamageType(Filepath.getDamageTypes());
		initItemSubtype(Filepath.getItemSubtypes());
	}
	
	
	
	
	

	
	
	
	//////////////////////////////
	// Specific loading functions
	//////////////////////////////
	private static void initDamageType(String path) {
		InputStream enumFile = null;
		try {
			enumFile = FileUtil.open(path);
			DataDictionary dict = DataDictionary.makeDictionary(YAMLutil.loadData(enumFile));
			Damage.DMGtype = EnumClass.create(dict.getArray());
		}
		catch (Exception err) { 
			FileErrors.globalErrors.addError("ENUMVAR-FILE", ("initDamageType, file = " + path), err);
		}
		finally {
			if (enumFile != null) { FileUtil.close(path); }
		}
		
	}
	
	private static void initItemSubtype(String path) {
		InputStream input = null;
		try {
			input = FileUtil.open(path);
			DataDictionary data = DataDictionary.makeDictionary(YAMLutil.loadData(input));
			GameItemLoader.initItemSubTypes(data);
		}
		catch (Exception err) { 
			FileErrors.globalErrors.addError("ENUMVAR-FILE", ("initDamageType, file = " + path), err);
		}
		finally {
			if (input != null) { FileUtil.close(path); }
		}
	}
}

