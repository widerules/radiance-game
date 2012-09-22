package org.vagosduke.andengine.radiance.engine.menu;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.GameCore;
import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.manager.GameManager.resolveStateType;
import org.vagosduke.andengine.radiance.program.input.DigitalInput;
import org.vagosduke.andengine.radiance.util.MenuUtil.MenuItemString;

public class CharacterActionMenu extends MenuManager {
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
	private static ArrayList<MenuItem> CharacerActionList;
	
	
	public CharacterActionMenu() {
		Character schar = GameCore.getGameManager().getSelectedCharacter();
		if (schar.canAttack()) { CharacerActionList.add(new MenuItemString(0, "Attack", "")); }
		if (schar.canCast()) { CharacerActionList.add(new MenuItemString(1, "Ability", "")); }
		CharacerActionList.add(new MenuItemString(2, "Inventory", ""));
		CharacerActionList.add(new MenuItemString(3, "Info", ""));
		CharacerActionList.add(new MenuItemString(4, "Wait", ""));
		
		this.addWindow(new MenuWindow(CharacerActionList));
	}
	
	
	public void processInput(DigitalInput input) {
		super.processInput(input);
		switch(input.getInput()) {
			case OK: 
				int actioncode = ((MenuItemString)this.getWindow().getSelection()).getRetCode();
				switch(actioncode) {
				//case 0:	
				//	GameCore.getGameManager().setResolveType(resolveStateType.ABILITY);
				//	GameCore.getGameManager().pushActionManager(new CharacterAttackMenu()); break;
				case 1:	
					GameCore.getGameManager().setResolveType(resolveStateType.ABILITY);
					GameCore.getGameManager().pushActionManager(new CharacterAbilityMenu()); break;
				//case 2:	GameCore.getGameManager().pushActionManager(new CharacterInventoryMenu()); break;
				//case 3:	GameCore.getGameManager().pushActionManager(new CharacterInfoMenu()); break;
				//case 4:	GameCore.getGameManager().pushActionManager(new CharacterInventoryMenu()); break;
				}
				break;
			case BACK: 
				GameCore.getGameManager().popActiveManager();  break;
			default: ;
		}
	}

}
