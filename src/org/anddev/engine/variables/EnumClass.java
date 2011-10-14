package org.anddev.engine.variables;


import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.anddev.util.XMLutil;
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
	public static EnumClass create(String path) {
		/**
		 * create a dynamic enum from xml file
		 */
		EnumClass newEnum = new EnumClass() ;
		try{
			InputStream ifile = XMLutil.getAsset(path);
			newEnum.loadEnumValues(ifile);
			masterEnumID++;
			ifile.close();
		} catch (Exception err) { 
			Log.e("ENUMVAR-FILE", err.getMessage()); 
		}
		return newEnum;
	}
	
	
	
	
	
	
	/////////////////////////////////////
	//	Public Methods
	///////////////////////////////////// 
	public Boolean memberOf(EnumValue oneEnum) {
		if(oneEnum.getEnum() != this.getClass()) {
			return false;
		}
		else { return true; }
	}
	
	public int compare(EnumValue oneEnum, EnumValue otherEnum) throws IllegalArgumentException {
		if ((oneEnum.getEnum() != this.getClass()) &&
				(otherEnum.getEnum() != this.getClass())) {	
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
	
	public Vector<EnumValue> getAllValues() {
		Vector<EnumValue> allValues = new Vector<EnumValue>();
		for(int i=0; i<this.nameTable.size(); i++) {
			allValues.add(this.valueOf(this.nameTable.get(i)));
		}
		return allValues;
	}
	
	public EnumValue valueOf(String str) throws NullPointerException {
		int value = this.valuesTable.get(str);
			return new EnumValue(this.getClass(), value );
	}
	public String nameOf(EnumValue value) {
		return this.nameTable.get(value.getValue());
	}
	
	
	public EnumValue defaultValue() {
		/**
		 * returns the first value of the enum
		 * (that should be the integer value of the first string put into the table)
		 */
		return new EnumValue(this.getClass(), (Integer)valuesTable.values().toArray()[0]);
	}
	
	public int size() {
		
		return this.valuesTable.size();
	}
	
	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append(this.enumID + ">> ");
		strb.append(valuesTable.toString());
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
	private EnumClass(InputStream ifile) {
		this.enumID = masterEnumID;
		this.valuesTable = new HashMap<String, Integer>();
		this.nameTable = new ArrayList<String>();
		this.loadEnumValues(ifile);
	}
	
	
	private void loadEnumValues(InputStream ifile) {
		this.enumID = masterEnumID;
		try {
			NodeList enumRoot = XMLutil.getXMLroot(ifile).getChildNodes();
			int tempValue = 0;
			for(int s=0; s<enumRoot.getLength() ; s++){
				Node enumNode = enumRoot.item(s);
				if(enumNode.getNodeType() == Node.ELEMENT_NODE) {
					try {
						String key = XMLutil.getThisNodeValue(enumNode).toUpperCase();
						int value = tempValue++;
						this.valuesTable.put(key, value);
						this.nameTable.add(key);
					} catch (Exception t) {
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						t.printStackTrace(pw);
						Log.e("ENUM-LOAD", sw.toString());
					}					
				}
			}
		} catch (Exception err) { 
			Log.e("ENUMVAR", err.getMessage()); 
		}
	}
}
