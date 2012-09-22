package org.vagosduke.andengine.radiance.engine.cursor;

import org.vagosduke.andengine.radiance.game.template.Template;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.program.variables.GPoint;

import android.graphics.Point;

public class Cursor {
	
	

	private Template cursorSize;
	private GPoint position;
	
	
	public Cursor(TemplateInfo cursor, GPoint position) {
		this.cursorSize = Template.makeTemplate(cursor);
		this.position = position;
	}
	public Cursor(Template cursor, GPoint position) {
		this.cursorSize = cursor;
		this.position = position;
	}
	


	
	
	
	
	
	
	///////////////////////
	//	Getters/Setters
	//////////////////////////
	public Template getTemplate() { return cursorSize; }
	public GPoint getPosition() { return position; }
	public void setCursor(Template cursor) { this.cursorSize = cursor; }
	public void setPosition(GPoint position) { this.position = position; }
	
	
	
	
	///////////////////////////
	//	Private Functions
	///////////////////////////
	public void moveToPosition(GPoint position) {
		// TODO
	}
	
	
	
}