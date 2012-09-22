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

package org.vagosduke.andengine.radiance.engine.cursor;

import org.vagosduke.andengine.radiance.game.GameCore;
import org.vagosduke.andengine.radiance.game.character.PlayerCharacter;
import org.vagosduke.andengine.radiance.game.engine.InputListener.inputType;
import org.vagosduke.andengine.radiance.game.manager.ActionManager;
import org.vagosduke.andengine.radiance.game.template.Template;
import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.game.template.Template.snapTarget;
import org.vagosduke.andengine.radiance.program.input.DigitalInput;
import org.vagosduke.andengine.radiance.program.variables.GPoint;

import android.graphics.Point;


public class TargetSelectCursor implements ActionManager {
	/** generic cursor handler */	
	 
	
	private Template range;
	private Cursor cursor;	
	
	
	//////////////////////////
	//	Constructor
	//////////////////////////
	public TargetSelectCursor(TemplateInfo rangeTemplate, TemplateInfo effectTemplate, GPoint position) {
		this.range = Template.makeTemplate(rangeTemplate);
		this.cursor = new Cursor(effectTemplate, position);
	}


	
	
	
	////////////////////////
	//	Public Methods
	////////////////////////
	@Override
	public void show() {
		// TODO add Sprite to scene
		
	}


	@Override
	public void hide() {
		// TODO remove sprite from scene
		
	}


	@Override
	public void processInput(DigitalInput input) {
		if(cursor.getTemplate().snaps == snapTarget.NONE) {
			switch(input.getInput()) {
			case UP: moveCursorUP(); break;
			case DOWN: moveCursorDOWN(); break;
			case LEFT: moveCursorLEFT(); break;
			case RIGHT: moveCursorRIGHT(); break;
			case OK: GameCore.getGameManager().resolve(); break;
			case BACK: GameCore.getGameManager().popActiveManager(); break;
			default: ;
			}
		}
		else {
//			switch(input.getInput()) {
//			case UP: snapCursorUP(); break;
//			case DOWN: snapCursorDOWN(); break;
//			case LEFT: snapCursorLEFT(); break;
//			case RIGHT: snapCursorRIGHT(); break;
//			case OK: GameCore.getGameManager().resolve(); break;
//			case BACK: GameCore.getGameManager().popActiveManager(); break;
//			default: ;
//			}
		}
	}
	
	
	
	
	
	
	
	
	
	/////////////////////////////
	//	Private Functions
	//////////////////////////////
	private void moveCursorUP() {
		GPoint position = cursor.getPosition();
		cursor.moveToPosition(new GPoint(position.x,position.y+1)); 
	}
	private void moveCursorDOWN() {
		GPoint position = cursor.getPosition();
		cursor.moveToPosition(new GPoint(position.x,position.y-1));
	}
	private void moveCursorLEFT() {
		GPoint position = cursor.getPosition();
		cursor.moveToPosition(new GPoint(position.x-1,position.y));
	}
	private void moveCursorRIGHT() {
		GPoint position = cursor.getPosition();
		cursor.moveToPosition(new GPoint(position.x+1,position.y));
	}
	
	
//	private void snapCursorUP() {
//		GPoint newPosition = GridRange.getClosestPointUP(this.range, this.cursor.getPosition());
//		if(newPosition.equals(cursor.getPosition()) == false) {
//			cursor.moveToPosition(new GPoint(newPosition.x,newPosition.y));
//		}
//	}
//	private void snapCursorDOWN() {
//		GPoint newPosition = GridRange.getClosestPointUP(this.range, this.cursor.getPosition());
//		if(newPosition.equals(cursor.getPosition()) == false) {
//			cursor.moveToPosition(new GPoint(newPosition.x,newPosition.y));
//		}
//	}
//	private void snapCursorLEFT() {
//		GPoint newPosition = GridRange.getClosestPointUP(this.range, this.cursor.getPosition());
//		if(newPosition.equals(cursor.getPosition()) == false) {
//			cursor.moveToPosition(new GPoint(newPosition.x,newPosition.y));
//		}
//	}
//	private void snapCursorRIGHT() {
//		GPoint newPosition = GridRange.getClosestPointUP(this.range, this.cursor.getPosition());
//		if(newPosition.equals(cursor.getPosition()) == false) {
//			cursor.moveToPosition(new GPoint(newPosition.x,newPosition.y));
//		}
//	}
}
