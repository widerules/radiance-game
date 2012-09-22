package org.vagosduke.andengine.radiance.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YAMLutil {

	
	public static Object loadData(InputStream input) {
		Yaml yaml = new Yaml();
		Object data = yaml.load(input);
		return data;	
	}
	
	public static Object loadData(String input) {
		Yaml yaml = new Yaml();
		Object data = yaml.load(input);
		return data;	
	}

	
	public static ArrayList<Object> getArray(Object obj) {
		/** for Lists */
		ArrayList<Object> ret = (ArrayList<Object>) obj;
		return ret;	
	}

	
	public static Map<String,Object> getMap(Object obj) {
		Map<String,Object> dict = (Map<String,Object>) obj;
		return dict;	
	}
	
	public static String rawParse(InputStream input) {
		Yaml yaml = new Yaml();
		Object data = yaml.load(input);
		return data.toString();
	}
}
