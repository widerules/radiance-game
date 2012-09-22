package org.vagosduke.andengine.radiance.game.manager;

import java.util.ArrayList;
import java.util.Stack;

import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.items.BaseItem;
import org.vagosduke.andengine.radiance.game.items.WeaponItem;
import org.vagosduke.andengine.radiance.game.map.TacticalMap;
import org.vagosduke.andengine.radiance.game.map.TacticalMapTile;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;
import org.vagosduke.andengine.radiance.program.input.DigitalInput;

public class GameManager {

	private static enum managerType { MENU, CURSOR, TEMPLATE, COMBAT, MAP };
	public static enum resolveStateType { COMBAT, ABILITY, ITEM }; 
	
	private Stack<ActionManager> managerStack;
	
//	private MenuManager MenuHandler;
//	private CursorManager cursorHandler;
//	private TemplateManager templateHandler;
//	private CombatManager combatHandler;
//	private MapManager mapHandler;
	
	
	private TacticalMap levelMap;
	private TacticalMapTile SelectedCharacterTile;
	private Character selectedCharacter;
	private TargetedSpell selectedAbility;
	private Integer selectedAbilityRank;
	private ArrayList<Character> selectedTargets;
//	private Pathing selectedPath;
	private BaseItem selectedItem;
	private WeaponItem selectedWeapon;
	
	public resolveStateType resolveType = null;
	
	
	
	///////////////////////////////
	// Constructor
	/////////////////////////////
	public GameManager() {
		levelMap = null;
		selectedCharacter = null;
		selectedAbility = null;
		selectedAbilityRank = null;
		selectedTargets = null;
//		selectedPath = null;
		selectedItem = null;
		selectedWeapon = null;
		resolveType = null;
		this.managerStack = new Stack<ActionManager>();
	}
	
	
	
	
	
	////////////////////////////
	//	Public Methods
	///////////////////////////
	public void pushActionManager(ActionManager manager) {
		if(!managerStack.isEmpty()) { this.managerStack.firstElement().hide(); }
		this.managerStack.push(manager);
		manager.show();
	}
	public void pushActionManagerNoHide(ActionManager manager) {
		this.managerStack.push(manager);
		manager.show();
	}
	public void pushActionManagerNoShow(ActionManager manager) {
		if(!managerStack.isEmpty()) { this.managerStack.firstElement().hide(); }
		this.managerStack.push(manager);
	}
	
	
	
	
	public void popActiveManager() {
		this.managerStack.pop().hide();
		this.managerStack.firstElement().show();
	}
	
	
	public void processInput(DigitalInput input) {
		this.managerStack.firstElement().processInput(input);
	}
	
	
	
	
	
	
	
	public void resolve() {
		switch(this.resolveType) {
		case COMBAT: this.resolveCombat();
		case ABILITY: this.resolveSpell();
		//case ITEM: this.resolveInventory();
		}
	}
	
	
	public void resolveCombat() {
		// TODO 
		// check for nulls, and reset if any
	}
	
	public void resolveSpell() {
		// TODO 
		// check for nulls, and reset if any
	}
	
	
	
	
	
	///////////////////////////////
	// Getters/Setters
	//////////////////////////////
	public TacticalMap getLevelMap() { return levelMap; }
	public TacticalMapTile getSelectedCharacterTile() { return SelectedCharacterTile; }
	public Character getSelectedCharacter() { return selectedCharacter; }
	public TargetedSpell getSelectedAbility() { return selectedAbility;}
	public Integer getSelectedAbilityRank() { return selectedAbilityRank; }
	public ArrayList<Character> getSelectedTargets() { return selectedTargets; }
//	public Pathing getSelectedPath() { return selectedPath; }
	public BaseItem getSelectedItem() { return selectedItem; }
	public WeaponItem getSelectedWeapon() { return selectedWeapon; }
	
	public void setLevelMap(TacticalMap levelMap) { this.levelMap = levelMap; }
	public void setSelectedCharacterTile(TacticalMapTile selectedCharacterTile) { SelectedCharacterTile = selectedCharacterTile; }
	public void setSelectedCharacter(Character selectedCharacter) { this.selectedCharacter = selectedCharacter; }
	public void setSelectedAbility(TargetedSpell selectedAbility) { this.selectedAbility = selectedAbility; }
	public void setSelectedAbilityRank(Integer selectedAbilityRank) { this.selectedAbilityRank = selectedAbilityRank;}
	public void setSelectedTargets(ArrayList<Character> selectedTargets) { this.selectedTargets = selectedTargets; }
//	public void setSelectedPath(Pathing selectedPath) { this.selectedPath = selectedPath; }
	public void setSelectedItem(BaseItem selectedItem) { this.selectedItem = selectedItem; }
	public void setSelectedWeapon(WeaponItem selectedWeapon) { this.selectedWeapon = selectedWeapon; }
	public void setResolveType(resolveStateType rtype) { this.resolveType = rtype; }




	
	
	
	
	
	///////////////////////////
	//	Private Methods
	///////////////////////////
	private void resetManagerStack() {
		
	}
	
	private void resetStackToMission() {
		selectedCharacter = null;
		selectedAbility = null;
		selectedAbilityRank = null;
		selectedTargets = null;
//		selectedPath = null;
		selectedItem = null;
		selectedWeapon = null;
		resolveType = null;
		
		for(ActionManager manager: this.managerStack) {
			manager.hide();
		}
		this.managerStack.empty();
		//this.pushActionManagerNoShow(new OptionsMenu());
		//this.pushActionManager(new CharacterSelectCursor());
	}



	
}
