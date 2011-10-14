package org.anddev.engine.variables;

public class EnumValue {
	/**
	 * Holds the Value of a custom enum (created at runtime)
	 * 
	 * superID: the custom enum identifier
	 * thisValue: the enum value
	 */
	
	private Class<? extends EnumClass> superID;
	private int thisValue;
	
		
	protected EnumValue(Class<? extends EnumClass> enumClass, int value) {
		this.superID = enumClass;
		this.thisValue = value;
	}
		
	public Class<? extends EnumClass> getEnum() { return superID; }
	public int getValue() { return thisValue; }
	public String toString() { return String.valueOf(thisValue); }
	
}
