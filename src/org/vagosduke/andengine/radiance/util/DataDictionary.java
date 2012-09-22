package org.vagosduke.andengine.radiance.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataDictionary  {

	/**
	 * 	String -> Object 
	 * dictionary for YAML Parsing
	 * Each dictionary object can be parsed as a 
	 * 			- a java.lang object/value
	 * 			- a (sub)dictionary of objects
	 * 			- an arraylist of objects
	 */
	
	private LinkedHashMap<String, Object> mapping;
	private ArrayList<Object> array;
	
	
	//////////////////////
	//	Constructors
	//////////////////////
	@SuppressWarnings("unchecked")
	public static DataDictionary makeDictionary(Object obj) {
		if(obj instanceof Map) {
			return new DataDictionary((Map<String, Object>) obj);
		}
		else if(obj instanceof LinkedHashMap) {
			return new DataDictionary((LinkedHashMap<String, Object>) obj);
		}
		else if(obj instanceof ArrayList) {
			return new DataDictionary((ArrayList<Object>) obj);
		}
		else {
			return null;
		}
	}
	
	public DataDictionary(Map<String, Object> imap) {
		//System.out.println("Dictionary Creation: Map");
		this.mapping = (LinkedHashMap<String, Object>) imap;
	}
	public DataDictionary(LinkedHashMap<String, Object> imap) {
		//System.out.println("Dictionary Creation: LinkedHashMap");
		this.mapping = imap;
	}
	public DataDictionary(ArrayList<Object> iarray) {
		//System.out.println("Dictionary Creation: ArrayList");
		this.array = iarray;
	}

	
	
	
	
	
	
	
	//////////////////////
	//	Public functions
	//////////////////////
	@SuppressWarnings("unchecked")
	public DataDictionary getSubDictionary(String key) throws Exception {
		Object subDictionary = mapping.get(key);
		if ((subDictionary != null) && (subDictionary instanceof LinkedHashMap)) {
			return new DataDictionary((LinkedHashMap<String, Object>)subDictionary);
		} else {
			throw new Exception("Dictionary: no-subDictionary of:" + key);
		}
	}
	@SuppressWarnings("unchecked")
	public DataDictionary getSubDictionary(int key) throws Exception {
		Object subDictionary = array.get(key);
		if ((subDictionary != null) && (subDictionary instanceof LinkedHashMap)) {
			return new DataDictionary((LinkedHashMap<String, Object>)subDictionary);
		} else {
			throw new Exception("Dictionary: no-subDictionary of:" + key);
		}
	}
	@SuppressWarnings("unchecked")
	public DataDictionary getSubArray(String key) throws Exception {
		Object subArray = mapping.get(key);
		if ((subArray != null) && (subArray instanceof ArrayList)) {
			return new DataDictionary((ArrayList<Object>)subArray);
		} else {
			throw new Exception("Dictionary: no-subArray of:" + key);
		}
	}
	@SuppressWarnings("unchecked")
	public DataDictionary getSubArray(int key) throws Exception {
		Object subArray = array.get(key);
		if ((subArray != null) && (subArray instanceof ArrayList)) {
			return new DataDictionary((ArrayList<Object>)subArray);
		} else {
			throw new Exception("Dictionary: no-subArray of:" + key);
		}
	}

	public int getArraySize() {
		return this.array.size();
	}
	
	
	
	
	public Object getObject(String key) { return this.mapping.get(key); }
	public Object getObject(int key) { 	return this.array.get(key); }
	
	public String getString(String key) throws Exception { 
		String str = (String)this.mapping.get(key);
		if(str == null) { throw new Exception("Dictionary: no-String"); }
		else { return str; }
	}
	public String getString(int key) throws Exception { 
		String str = (String)this.array.get(key);
		if(str == null) { throw new Exception("Dictionary: no-String"); }
		else { return str; }
	}
	
	public int getInteger(String key) throws Exception { 
		Object num = this.mapping.get(key);
		if(num == null) { throw new Exception("Dictionary: no-int"); }
		else {
			if(num instanceof Double || num instanceof Float) {
				return (int)(double)(Double)num; 
			} else {
				return (int)(Integer)num; 
			}
		}
	} 
	public int getInteger(int key) throws Exception { 
		Object num = this.array.get(key);
		if(num == null) { throw new Exception("Dictionary: no-int"); }
		else {
			if(num instanceof Double || num instanceof Float) {
				return (int)(double)(Double)num; 
			} else {
				return (int)(Integer)num; 
			}
		}
	} 
	
	public double getDouble(String key) throws Exception {
		Object num = this.mapping.get(key);
		if(num == null) { throw new Exception("Dictionary: no-double"); }
		else {
			if(num instanceof Double || num instanceof Float) {
				return (double)(Double)num; 
			} else {
				return (double)(Integer)num; 
			}
		}
	}
	public double getDouble(int key) throws Exception {
		Object num = this.array.get(key);
		if(num == null) { throw new Exception("Dictionary: no-double"); }
		else {
			if(num instanceof Double || num instanceof Float) {
				return (double)(Double)num; 
			} else {
				return (double)(Integer)num; 
			}
		}
	}

	public Boolean getBoolean(String key) throws Exception {
		Object bool = this.mapping.get(key);
		if (bool==null) { throw new Exception("Dictionary: no-Boolean"); }
		else { return (Boolean)bool; }
	}
	public boolean getBoolean(int key)  throws Exception {
		Object bool = this.array.get(key);
		if (bool==null) { throw new Exception("Dictionary: no-Boolean"); }
		else { return (Boolean)bool; }
	}
	
	public boolean exists(String key) {
		if(this.mapping.get(key) != null) {
			return true;
		}
		return false;
	}
	public String toString() {
		if(this.mapping != null) {
			return this.mapping.toString();
		}
		if(this.array != null) {
			return this.array.toString();
		}
		return "";
	}
	
	
	
	
	//////////////////////////
	//	Public getters
	/////////////////////////
	public LinkedHashMap<String, Object> getMapping() {
		return this.mapping;
	}

	public ArrayList<Object> getArray() {
		return this.array;
	}
		
	
	////////////////////////////
	//	Private Functions
	////////////////////////////
	
}
