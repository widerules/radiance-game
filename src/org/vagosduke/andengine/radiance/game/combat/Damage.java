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

package org.vagosduke.andengine.radiance.game.combat;

import java.io.IOException;
import java.io.InputStream;

import org.vagosduke.andengine.radiance.program.config.Filepath;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.program.variables.EnumClass;
import org.vagosduke.andengine.radiance.program.variables.EnumValue;
import org.vagosduke.andengine.radiance.util.DataDictionary;
import org.vagosduke.andengine.radiance.util.FileUtil;
import org.vagosduke.andengine.radiance.util.TraceUtil;
import org.vagosduke.andengine.radiance.util.YAMLutil;

import android.util.Log;

public class Damage {
	
	/////////////////////////////
	//	Static members
	/////////////////////////////
	public static EnumClass DMGtype; 			// Damage

	public static void initDMGtype(String path) {
		InputStream input = null;
		try {
			input = FileUtil.open(path);
			DMGtype = EnumClass.create(DataDictionary.makeDictionary(YAMLutil.loadData(input)));
		} catch (IOException err) {
			FileErrors.globalErrors.addError("FILE-ERROR", ("Damage.initDMGtype(String path), File = " + path), 
					TraceUtil.exceptionToString(err));
		}
		finally {
			if(input==null) { FileUtil.close(path); }
		}
		
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
	public int getValue() { return this.value; }
	public EnumValue getType() { return this.type; }
	
	
	
	
	
	////////////////////////////
	//	Private methods
	////////////////////////////
	public Damage(int ivalue, EnumValue itype)  {
		this.value = ivalue;
		this.type = itype;
	}
}
