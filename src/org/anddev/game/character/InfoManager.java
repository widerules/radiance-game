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

package org.anddev.game.character;

public class InfoManager {
	/**
	 * Character-Class module that holds and manages general 
	 * information about the character (static, flags)
	 */
	
	private Character myCharacter;
	
	/////////////////////////////////////////
	// Constructor
	/////////////////////////////////////////
	public InfoManager() {
		this.name = "";
		this.title = "";
		this.bio = "";
		this.aliveFlag = false;
		this.playerChar = false;
	}
	public InfoManager(Character mychar) {
		this.myCharacter = mychar;
		this.name = "";
		this.title = "";
		this.bio = "";
		this.aliveFlag = false;
		this.playerChar = false;
	}
	
	
	// Info
	private String name;
	private String title;
	private String bio;
	
	// Flags
	private Boolean aliveFlag;
	private Boolean playerChar;
	
	
	
	//////////////////////////////////
	//	Getters
	//////////////////////////////////
	public String name() { return this.name; }
	public String title() { return this.title; }
	public String bio() { return this.bio;}
	public Boolean isAlive() { return this.aliveFlag; }
	public Boolean isPlayer() { return this.playerChar; }
	
	
	//////////////////////////////////
	//	Setters
	//////////////////////////////////
	public void setName(String name) { this.name = name; }
	public void setTitle(String title) { this.title = title; }
	public void setBio(String bio) { this.bio = bio; }
	public void setAliveFlag(Boolean aliveFlag) { this.aliveFlag = aliveFlag; }
	public void setPlayerChar(Boolean playerChar) { this.playerChar = playerChar; }
	
}
