package org.anddev.game.combat;

import org.anddev.engine.config.Filepath;
import org.anddev.engine.variables.EnumClass;
import org.anddev.engine.variables.EnumValue;

import android.util.Log;

public class Damage {
	
	/////////////////////////////
	//	Static members
	/////////////////////////////
	public static EnumClass DMGtype; 			// Damage
	
	public static void initDamageType() {
		if ( !initDAMType(Filepath.xmlWeaponTypes) ) {Log.e("initDAMType", "Coult not load DAMType");}
	}

	
	
	

	/////////////////////////////
	//	Member variables
	/////////////////////////////
	private final int value;
	private final EnumValue type;
	
	
	
	/////////////////////////////
	//	Constructor
	/////////////////////////////
	public static Damage create(int ivalue, EnumValue itype) throws IllegalArgumentException {
		if (Damage.DMGtype.memberOf(itype)) {
			throw new IllegalArgumentException();
		}
		else {
			return new Damage(ivalue, itype);
		}
	}
	
	
	
	
	
	//////////////////////////////
	//	Getters/Setters
	//////////////////////////////
	public int value() { return this.value; }
	public EnumValue type() { return this.type; }
	
	
	
	
	
	
	
	
	
	////////////////////////////
	//	Private methods
	////////////////////////////
	private static Boolean initDAMType(String path) {
		Boolean ret = true;
		try { DMGtype = EnumClass.create(path); }
		catch (Exception err) { ret = false; }
		return ret;
	}
	
	private Damage(int ivalue, EnumValue itype)  {
		this.value = ivalue;
		this.type = itype;
	}
}
