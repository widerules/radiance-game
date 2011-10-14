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
