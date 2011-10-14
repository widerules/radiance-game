package org.anddev.game.template;

import org.anddev.engine.variables.GPoint;
import org.anddev.game.GridRange;
import org.anddev.game.engine.InputListener.inputType;

public class TemplateHandler {
	
	private GPoint position;
	private GridRange range;
	private Template currentTemplate;
	
	
	/////////////////////
	//	Constructor
	/////////////////////
	public TemplateHandler() {
		position = null;
		range = null;
		currentTemplate = null;
	}
	
	
	/////////////////////
	//	Public Methods
	/////////////////////
	public void setTemplate() {
		// TODO
	}
	
	public int moveTemplate(inputType direction) {
		
		// TODO
		GPoint newPosition = new GPoint(position);
		
		if(direction == inputType.UP) {
			newPosition.y++;
			if(range.inGrid(newPosition)) {
				// move up
				return 0;
			}
			else return -1;
		}
		else if(direction == inputType.DOWN) {
			newPosition.y--;
			if(range.inGrid(newPosition)) {
				// move down
				return 0;
			}
			else return -1;
		}
		else if(direction == inputType.LEFT) {
			newPosition.y++;
			if(range.inGrid(newPosition)) {
				// move left
				return 0;
			}
			else return -1;
		}
		else if(direction == inputType.RIGHT) {
			newPosition.y++;
			if(range.inGrid(newPosition)) {
				// move right
				return 0;
			}
			else return -1;
		}
		else {
			return -1;
		}
	}
	
}
