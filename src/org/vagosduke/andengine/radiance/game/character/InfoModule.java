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

package org.vagosduke.andengine.radiance.game.character;

import org.vagosduke.andengine.radiance.game.template.Template.snapTarget;

public class InfoModule {
	/**
	 * Character-Class module that holds and manages general 
	 * information about the character (static, flags)
	 */
	
	public static enum friendOrFoe {PLAYER, ALLY, ENEMY}; 
	
	
	private Character myCharacter;
	// Info
	private String name;
	private String title;
	private String bio;
	
	private int team;			// generally 1: nominates friends, 2: enemies, 0: neutral
	private friendOrFoe FoF;

	
	/////////////////////////////////////////
	// Constructor
	/////////////////////////////////////////
	public InfoModule(Character myChar, String name, String title, String bio) {
		this.myCharacter = myChar;
		this.name = name;
		this.title = title;
		this.bio = bio;
		this.team = 0;
		this.FoF = friendOrFoe.PLAYER;
	}

	
	
	
	
	
	
	
	//////////////////////////////////
	//	Getters
	//////////////////////////////////
	public String getName() { return this.name; }
	public String getTitle() { return this.title; }
	public String getBio() { return this.bio;}
	public int getTeam() { return this.team; }
	public friendOrFoe getFoF() { return this.FoF; }
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("=== INFO ===\n");
		str.append("Name: " + this.name + "\n");
		str.append("title: " + this.title + "\n");
		str.append("bio: " + this.bio + "\n");
		return str.toString();
	}
	
	
	
	
	
	//////////////////////////////////
	//	Setters
	//////////////////////////////////
	public void setName(String name) { this.name = name; }
	public void setTitle(String title) { this.title = title; }
	public void setBio(String bio) { this.bio = bio; }
	public void setTeam(int team) { this.team = team; }
	
}
