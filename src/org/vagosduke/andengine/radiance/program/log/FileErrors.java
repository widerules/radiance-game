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


package org.vagosduke.andengine.radiance.program.log;

import java.util.LinkedList;

import org.vagosduke.andengine.radiance.util.TraceUtil;

public class FileErrors {
	
	/**
	 * This class holds flags that are set during file reading
	 * These flags include error and warning flags.
	 * Typically, the program can review these flags after file reading
	 * 	and decide either to continue running or terminate.
	 */

	
	public static FileErrors globalErrors = new FileErrors();
	
	private LinkedList<String[]> fileWarnings;
	private LinkedList<String[]> fileErrors;
	
	
	/////////////////////////////
	//	Constructor
	///////////////////////////
	public FileErrors() {
		this.fileWarnings = new LinkedList<String[]>();
		this.fileErrors = new LinkedList<String[]>();
	}
	
	
	
	
	
	
	//////////////////////////////
	//	Public Functions
	//////////////////////////////
	public void addError(String tag, String message, String stackTrace) {
		if(stackTrace == null) { stackTrace = ""; }
		String[] tuple = {tag, message, stackTrace};
		this.fileErrors.add(tuple);
	}
	public void addError(String tag, String message, Exception err) {
		String[] tuple = {tag, message, (err.getMessage()+"\n"+TraceUtil.exceptionToString(err)) };
		this.fileErrors.add(tuple);
	}
	
	public void addWarning(String tag, String message, String stackTrace) {
		if(stackTrace == null) { stackTrace = ""; }
		String[] tuple = {tag, message, stackTrace};
		this.fileWarnings.add(tuple);
	}
	public void addWarning(String tag, String message, Exception err) {
		String[] tuple = {tag, message, (err.getMessage()+"\n"+TraceUtil.exceptionToString(err)) };
		this.fileWarnings.add(tuple);
	}
	
	public void clear() {
		this.fileWarnings.clear();
		this.fileErrors.clear();
	}
	
	public void printAll() {
		if(this.hasErrors()) {
			ProgLog.error("", "=== ERRORS ===");
			for(String[] messages: this.fileErrors) {
				ProgLog.error(messages[0], messages[1]);
				ProgLog.error("STACK-TRACE", messages[2]);
			}
		}
		if(this.hasWarnings()) {
			ProgLog.error("", "\n=== WARNINGS ===");
			for(String[] messages: this.fileWarnings) {
				ProgLog.error(messages[0], messages[1]);
				ProgLog.error("STACK-TRACE", messages[2]);
			}
		}
		
	}
	
	public void printMessages() {
		if(this.hasErrors()) {
			ProgLog.error("", "=== ERRORS ===");
			for(String[] messages: this.fileErrors) {
				ProgLog.error(messages[0], messages[1]);
			}
		}
		if(this.hasWarnings()) {
			ProgLog.error("", "\n=== WARNINGS ===");
			for(String[] messages: this.fileWarnings) {
				ProgLog.error(messages[0], messages[1]);
			}
		}
		
	}
	
	
	
	
	
	public int getWarningsNum() { return this.fileWarnings.size(); }
	public int getErrorsNum() { return this.fileErrors.size(); }
	public boolean hasWarnings() { if(this.fileWarnings.size() > 0) {return true;} else {return false;} }
	public boolean hasErrors() { if(this.fileErrors.size() > 0) {return true;} else {return false;} }
	public LinkedList<String[]> getWarnings() { return this.fileWarnings; }
	public LinkedList<String[]> getErrors() { return this.fileErrors; }
}
