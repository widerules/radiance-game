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

package org.vagosduke.andengine.radiance.program.variables;


import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Vector;

import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.log.ProgLog;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.FileUtil;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.vagosduke.andengine.radiance.util.YAMLutil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public class EnumClass {

		/**
		 * Provides a custom enum-like class that can be 
		 * loaded with values at runtime. Contains class
		 * for the actual enum data
		 * WARNING: use with caution: the custom types must be loaded 
		 * 							before actually used in the program 
		 */
	//////////////////////////////
	// Variables
	//////////////////////////////
	final private HashMap<String, Integer> valuesTable;
	final private ArrayList<String> nameTable;
	private static int masterEnumID = 0;
	private int enumID = 0;
	
	
	
	
	
	
	//////////////////////////////
	//	Constructors
	//////////////////////////////
//	public static EnumClass create(String path) {
//		/**
//		 * create a dynamic enum from xml or yaml file
//		 */
//		EnumClass newEnum = new EnumClass() ;
//		String ext = path.substring(path.lastIndexOf(".")+1, path.length()).toLowerCase();
//		if(ext.equals("yaml")) {
//			try{
//				InputStream ifile = FileUtil.open(path);
//				DataDictionary dict = DataDictionary.makeDictionary(YAMLutil.loadData(ifile));
//				masterEnumID++;
//				newEnum.loadEnumValues(dict.getArray());
//				FileUtil.close(path);
//			}
//			catch (Exception err) {
//				FileErrors.globalErrors.addError("ENUMVAR-FILE", ("EnumClass.create, file = " + path), err);
//			}
//		}
//		else if(ext.equals("xml")) {
//			try{
//				InputStream ifile = FileUtil.open(path);
//				masterEnumID++;
//				newEnum.loadEnumValues(ifile);
//				FileUtil.close(path);
//			}
//			catch (Exception err) { 
//				FileErrors.globalErrors.addError("ENUMVAR-FILE", ("EnumClass.create, file = " + path), err);
//			}
//		}
//		return newEnum;
//	}
	
	
	public static EnumClass create(DataDictionary dict) {
		/**
		 * create a dynamic enum from yaml arrayList
		 */
		return create(dict.getArray());
	}
	public static EnumClass create(ArrayList<Object> list) {
		/**
		 * create a dynamic enum from xml file
		 */
		EnumClass newEnum = new EnumClass() ;
		newEnum.loadEnumValues(list);
		masterEnumID++;
		return newEnum;
	}
	
	
	
	
	
	
	
	
	/////////////////////////////////////
	//	Public Methods
	///////////////////////////////////// 
	public Boolean memberOf(EnumValue oneEnum) {
		if(oneEnum.getEnum() != this) {
			return false;
		}
		else { return true; }
	}
	
	public int compare(EnumValue oneEnum, EnumValue otherEnum) throws IllegalArgumentException {
		if ((oneEnum.getEnum() != this) && (otherEnum.getEnum() != this)) {	
				throw new IllegalArgumentException();
			}
		else {
			if (oneEnum.getValue() > otherEnum.getValue()) {
				return 1;
			}
			else if (oneEnum.getValue() < otherEnum.getValue()){
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
	public ArrayList<EnumValue> getAllValues() {
		ArrayList<EnumValue> allValues = new ArrayList<EnumValue>();
		for(int i=0; i<this.nameTable.size(); i++) {
			allValues.add(this.valueOf(this.nameTable.get(i)));
		}
		return allValues;
	}
	
	public EnumValue valueOf(String str) throws NullPointerException {
		int value = this.valuesTable.get(str);
			return new EnumValue(this, value );
	}
	public EnumValue valueOf(int key) {
		return this.valueOf(nameTable.get(key));
	}
	public String nameOf(EnumValue value) {
		return this.nameTable.get(value.getValue());
	}
	
	
	public EnumValue defaultValue() {
		/**
		 * returns the first value of the enum
		 * (that should be the integer value of the first string put into the table)
		 */
		return new EnumValue(this, (Integer)valuesTable.values().toArray()[0]);
	}
	
	public int size() {
		
		return this.valuesTable.size();
	}
	
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("Enum No:" + this.enumID + ">> ");
		strb.append(nameTable.toString());
		return strb.toString();
	}
	
	
	
	////////////////
	//	Getters
	///////////////
	public int getEnumID() {
		return this.enumID;
	}
	
	
	
	
	
	
	
	//////////////////////////
	//	private constructors
	//////////////////////////
	private EnumClass() {
		this.enumID = masterEnumID;
		this.valuesTable = new HashMap<String, Integer>();
		this.nameTable = new ArrayList<String>();
	}
		
	
	private void loadEnumValues(ArrayList<Object> input) {
		this.enumID = masterEnumID;
		for(int i=0; i<input.size(); i++) {
			String key = input.get(i).toString();
			Integer value = new Integer(i);
			this.valuesTable.put(key, value);
			this.nameTable.add(key);
		}
	}
}
