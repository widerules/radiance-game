package org.vagosduke.andengine.radiance.engine.menu;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.vagosduke.andengine.radiance.engine.cursor.TargetSelectCursor;
import org.vagosduke.andengine.radiance.game.GameCore;
import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.spells.AvailableSpell;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;
import org.vagosduke.andengine.radiance.game.template.Template;
import org.vagosduke.andengine.radiance.program.input.DigitalInput;
import org.vagosduke.andengine.radiance.program.variables.GPoint;
import org.vagosduke.andengine.radiance.util.MenuUtil;
import org.vagosduke.andengine.radiance.util.MenuUtil.MenuItemString;

public class CharacterAbilityMenu extends MenuManager {
	
	private static String noManaToast = "Cannot cast selected spell. Does not have enough ability points";
	private static String noTargetToast = "Cannot cast selected spell. No available targets nearby";
	
	
	private ArrayList<MenuItem> availableAbilities;

	private boolean hasSelectedAbility = false;
	private boolean hasSelectedRank = false;
	private AvailableSpell selectedAbility;
	private int selectedRank;
		
	
	////////////////////////
	//	Constructor
	////////////////////////
	public CharacterAbilityMenu() {
		Character mychar = GameCore.getGameManager().getSelectedCharacter();
		availableAbilities = new ArrayList<MenuItem>();
		for(AvailableSpell ability: mychar.getAbilitiesModule().getAvailableAbilityList()) {
			availableAbilities.add(ability);
		}
		hasSelectedAbility = false;
		hasSelectedRank = false;
		selectedAbility = null;
		selectedRank = 1;
		
		this.addWindow(new MenuWindow(availableAbilities, false));
	}

	
	
	
	
	
	//////////////////////////
	//	Public Functions
	//////////////////////////
	public void processInput(DigitalInput input) {
		super.processInput(input);
		switch(input.getInput()) {
		case OK: 
			if(hasSelectedAbility == false) {
				selectedAbility =  (AvailableSpell) this.getWindow().getSelection();
				GameCore.getGameManager().setSelectedAbility(selectedAbility.getAbility());
				hasSelectedAbility = true;
				this.addWindowNoHide(new MenuWindow(MenuUtil.arrayToList(selectedAbility.getAvailableRanks())));
			}
			else if (hasSelectedRank == false) {
				//MenuItemString selection = (MenuItemString) this.getWindow().getSelection();
				selectedRank = this.getWindow().getCurosr()+1;
				if(GameCore.getGameManager().getSelectedCharacter().getAbilitiesModule().canCast(selectedAbility.getAbility(), selectedRank)) {
					GPoint target = GameCore.getGameManager().getLevelMap().getClosestPoint(
							Template.makeTemplate(selectedAbility.getAbility().getRangeTemplate(selectedRank)),
							GameCore.getGameManager().getSelectedCharacterTile().getPosition());
					if(target == null) {
						GameCore.getGameManager().pushActionManagerNoHide(new ErrorToast(noManaToast));
						hasSelectedRank = false;
						selectedRank = 1;
					}
					hasSelectedRank = true;
					GameCore.getGameManager().setSelectedAbilityRank(selectedRank);
					GameCore.getGameManager().pushActionManager(new TargetSelectCursor(
							selectedAbility.getAbility().getRangeTemplate(selectedRank),
							selectedAbility.getAbility().getEffectTemplate(selectedRank),
							target));
				}
				else {
					GameCore.getGameManager().pushActionManagerNoHide(new ErrorToast(noTargetToast));
					hasSelectedRank = false;
					selectedRank = 1;
				}
			}
			break;
		case BACK: 
			GameCore.getGameManager().popActiveManager(); break;
		default: ;
		}
	}

	
	
	
	
}
