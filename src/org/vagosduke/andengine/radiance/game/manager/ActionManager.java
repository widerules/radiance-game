package org.vagosduke.andengine.radiance.game.manager;

import org.vagosduke.andengine.radiance.program.input.DigitalInput;


public interface ActionManager {
	
	public void show();
	public void hide();
	
	public void processInput(DigitalInput input);
}
