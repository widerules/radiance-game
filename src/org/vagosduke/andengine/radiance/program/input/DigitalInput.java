package org.vagosduke.andengine.radiance.program.input;

public class DigitalInput {
	
	public static enum digitalInputType { OK, BACK, LEFT, RIGHT, UP, DOWN };
	
	private digitalInputType input;
	
	
	
	public DigitalInput(digitalInputType input) {
		this.input = input;
	}

	
	public digitalInputType getInput() {
		return this.input;
	}
}
