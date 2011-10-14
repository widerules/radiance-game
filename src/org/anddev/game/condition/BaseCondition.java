package org.anddev.game.condition;

import org.anddev.game.character.Character;

public interface BaseCondition {
	
	public static enum conditions {POISON, BUFF, DISABLE};
	
	public void onCreate(Character myCharacter);
	// onCreate conditions should register themselves in character's condition list
	// and make the changes on character stats if appropriate
	
	public void onRoundStart();
	// onRoundStart some conditions must take action (like poison) 
	// or reduce the duration counter
	
	public void onExpire();
	// onExpire conditions should unregister themselves and revert they made changes made
	
	public Boolean expired();
	// called to check if condition duration is up
}
