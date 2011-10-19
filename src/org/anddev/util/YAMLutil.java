package org.anddev.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class YAMLutil {

	
	public static Object loadData(InputStream input) {
		Yaml yaml = new Yaml();
		Object data = yaml.load(input);
		//input.close();
		return data;	
	}
//	public static Object loadData(String path) {
//		Yaml yaml = new Yaml();
//		InputStream input = FileUtil.getInput(path);
//		Object data = yaml.load(input);
//		//input.close();
//		return data;	
//	}
	public static Iterable<Object> loadAllData(InputStream input) {
		Yaml yaml = new Yaml();
		Iterable<Object> data = yaml.loadAll(input);
		//input.close();
		return data;	
	}
//	public static Iterable<Object> loadAllData(String path) {
//		Yaml yaml = new Yaml();
//		InputStream input = FileUtil.getInput(path);
//		Iterable<Object> data = yaml.loadAll(input);
//		//input.close();
//		return data;	
//	}
	
	public static ArrayList<Object> getArray(Object obj) {
		/** for Lists */
		ArrayList<Object> ret = (ArrayList<Object>) obj;
		return ret;	
	}

//	public static HashMap<String,Object> loadDictionary(String path) {
//		HashMap<String,Object> dict = (HashMap<String,Object>) loadData(path);
//		return dict;	
//	}
	
	public static Map<String,Object> getDictionary(Object obj) {
		Map<String,Object> dict = (Map<String,Object>) obj;
		return dict;	
	}
	
	public static String parse(InputStream input) {
		Yaml yaml = new Yaml();
		Object data = yaml.load(input);
		return data.toString();
	}
}
