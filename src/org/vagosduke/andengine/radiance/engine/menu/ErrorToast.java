package org.vagosduke.andengine.radiance.engine.menu;

import org.vagosduke.andengine.radiance.game.GameCore;
import org.vagosduke.andengine.radiance.game.manager.ActionManager;
import org.vagosduke.andengine.radiance.program.input.DigitalInput;

public class ErrorToast implements ActionManager {

	private String message;
	
	public ErrorToast(String message) {
		this.message = message;
		// TODO
		// create a new window sprite with the message and add it to the screen
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub	
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void processInput(DigitalInput input) {
		switch(input.getInput()) {
		case OK: GameCore.getGameManager().popActiveManager(); break;
		case BACK: GameCore.getGameManager().popActiveManager(); break;
		default: ;
	}
	}

}
