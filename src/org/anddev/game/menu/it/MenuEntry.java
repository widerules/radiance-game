package org.anddev.game.menu.it;

public abstract class MenuEntry {
	protected Boolean enabled;
	protected String name;
	
	public MenuEntry() {
		enabled = false;
		name = "N/A";
	}
	public MenuEntry(String iname) {
		enabled = true;
		name = iname;
	}
	
	////////////////////
	//	Getters
	////////////////////
	public Boolean enabled() { return enabled; }
	public String name() { return name; }
	
	////////////////////
	//	Setters
	////////////////////
	public void setName(String iname) { name = iname; }


	
	public abstract void activate();



}