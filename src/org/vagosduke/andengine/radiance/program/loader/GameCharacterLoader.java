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


package org.vagosduke.andengine.radiance.program.loader;

import java.util.ArrayList;

import org.vagosduke.andengine.radiance.game.character.AbilitiesModule;
import org.vagosduke.andengine.radiance.game.character.AttributesModule;
import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.character.CombatModule;
import org.vagosduke.andengine.radiance.game.character.ConditionModule;
import org.vagosduke.andengine.radiance.game.character.InfoModule;
import org.vagosduke.andengine.radiance.game.character.InventoryModule;
import org.vagosduke.andengine.radiance.game.combat.ResistList;
import org.vagosduke.andengine.radiance.game.inventory.CharacterInventory;
import org.vagosduke.andengine.radiance.game.items.BaseItem;
import org.vagosduke.andengine.radiance.game.items.GlobalItemList;
import org.vagosduke.andengine.radiance.game.spells.GlobalSpellList;
import org.vagosduke.andengine.radiance.game.spells.TargetedSpell;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class GameCharacterLoader {
	
	
	
//	protected InfoModule charInfo;					// Manages static character info like name etc.
//	protected SkillsModule charSkills;				// Manages character base and temporary ability scores
//	protected AttributesModule charAttributes;
//	protected InventoryModule characterInventory;	// Manages items and inventory functions
//	protected ConditionModule charCondition;		// Manages temporary character states like buffs etc.
//	protected CombatModule charCombat;				// Manages combat functions 
//	protected AbilitiesModule charMagic;				// Manages spell-list and spellcasting functions
	
	private static Character currentCharacter;
	private static String currentname;
	
	
	
	
	public static Character loadCharacter(DataDictionary dict) {
		currentCharacter = new Character();
		InfoModule charInfo = null;
		AttributesModule charAttributes = null;
		InventoryModule characterInventory = null;
		CombatModule charCombat = null;
		ConditionModule charCondition = null;
		AbilitiesModule charMagic = null;
		
		currentname = "N/A";
		String job = "N/A";
		try {
			job = "info"; charInfo = loadInfoModule(dict.getSubDictionary("info"));
			currentCharacter.setCharInfo(charInfo);
			job = "attributes"; charAttributes = loadAttributesModule(dict.getSubDictionary("attributes"));
			currentCharacter.setCharAttributes(charAttributes);
			job = "inventory"; characterInventory = loadInventoryModule(dict.getSubDictionary("inventory"));
			currentCharacter.setCharacterInventory(characterInventory);
			job = "combat"; charCombat = loadCombatModule(dict.getSubDictionary("combat"));
			currentCharacter.setCharCombat(charCombat);
			job = "condition"; 
			if(dict.exists("condition")) {
				charCondition = loadConditionModule(dict.getSubDictionary("condition"));
			} else { 
				charCondition = new ConditionModule(currentCharacter); 
			}
			currentCharacter.setCharCondition(charCondition);
			job = "abilities"; 
			if(dict.exists("abilities")) {
				charMagic = loadAbilitiesModule(dict.getSubDictionary("abilities"));
			} else { 
				charMagic = new AbilitiesModule(currentCharacter); 
			}
			currentCharacter.setCharMagic(charMagic);
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("CHAR-ERROR", ("loadCharacter, char=" + currentname + " at=" + job), err);
			//throw err;
		}
		return currentCharacter;
	}
	
	
	public static InfoModule loadInfoModule(DataDictionary dict) throws Exception {
		String name = "N/A";
		String title = "N/A"; 
		String bio = "N/A";
		
		String job = "N/A";
		try {
			job = "name"; name = dict.getString("name"); currentname = name;
			job = "title"; title = dict.getString("title");
			job = "bio"; bio = dict.getString("bio");
		} 
		catch (Exception err) {
			FileErrors.globalErrors.addError("CHAR-ERROR", ("loadInfoModule, char=" + currentname + " at=" + job), err);
			throw err;
		}
		return new InfoModule(currentCharacter, name, title, bio);
	}
	
	
	
	
	
	
	public static AbilitiesModule loadAbilitiesModule(DataDictionary dict) throws Exception {
		ArrayList<TargetedSpell> abilityList = new ArrayList<TargetedSpell>();
		ArrayList<Integer[]> unlockLevel = new ArrayList<Integer[]>();		
		String job = "N/A";
		try {
			job = "abilityList"; 
			if(dict.exists("abilityList")) {
				DataDictionary abilityListDict = dict.getSubArray("abilityList");
				for(int i=0; i<abilityListDict.getArraySize(); i++) {
					DataDictionary abilityDict = abilityListDict.getSubDictionary(i);
					job = "name"; String name = abilityDict.getString("name").toUpperCase();
					TargetedSpell ability = GlobalSpellList.globalList.getSpell(name);
					if(ability != null) { abilityList.add(ability); }
					else { FileErrors.globalErrors.addError("CHAR-ERROR", ("loadAbilitiesModule, ability=" + name + " at=" + job), ""); }
					job = "unlockLevel"; unlockLevel.add(getUnlockLevels(abilityDict.getString("unlockLevel"))); 
				}
			}
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("CHAR-ERROR", ("loadAbilitiesModule, char=" + currentname + " at=" + job), err);
			throw err;
		}
		Integer[][] temp = new Integer[unlockLevel.size()][];
		for(int i=0; i<unlockLevel.size(); i++) { temp[i] = unlockLevel.get(i); }
		TargetedSpell[] spellArray = new  TargetedSpell[abilityList.size()];
		for(int i=0; i<unlockLevel.size(); i++) { spellArray[i] = abilityList.get(i); }
		return new AbilitiesModule(currentCharacter, spellArray, temp);
	}
	
	
	
	
	
	
	public static CombatModule loadCombatModule(DataDictionary dict) throws Exception {
		ResistList baseResistList = new ResistList();
		String job = "N/A";
		try {
			job = "baseResistList"; baseResistList = GameMiscLoader.loadResistList(dict.getSubArray("baseResistList"));
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("CHAR-ERROR", ("loadCombatModule, char=" + currentname + " at=" + job), err);
			throw err;
		}
		return new CombatModule(currentCharacter, baseResistList);
	}
	
	
	
	
	
	
	public static InventoryModule loadInventoryModule(DataDictionary dict) throws Exception {
		CharacterInventory inventory = null;
		String job = "N/A";
		try {
			inventory = loadCharacterInventory(dict);
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("CHAR-ERROR", ("loadInventoryModule, char=" + currentname + " at=" + job), err);
			throw err;
		}
		return new InventoryModule(currentCharacter, inventory);
	}
	
	
	
	
	
	public static ConditionModule loadConditionModule(DataDictionary dict) throws Exception {
		return new ConditionModule(currentCharacter);
	}
	public static ConditionModule loadConditionModule() throws Exception {
		return new ConditionModule(currentCharacter);
	}
	
	
	
	public static AttributesModule loadAttributesModule(DataDictionary dict) throws Exception {
		int baseLevel = AttributesModule.defaulBaseLevel;
		int baseOffense = AttributesModule.defaulBaseOffense;
		int baseDefense = AttributesModule.defaulBaseDefense;
		int baseAbility = AttributesModule.defaulBaseAbility;
		int baseSpeed = AttributesModule.defaulBaseSpeed;
		int baseHitPointMax = AttributesModule.defaulBaseHitPointMax;
		int baseAbilityPointMax = AttributesModule.defaulBaseAbilityPointMax;
		int baseArmor = AttributesModule.defaulbaseArmor;
		double staticDodge = AttributesModule.defaultStaticDodge;
		double staticBlock = AttributesModule.defaultStaticBlock;
		double staticCritical = AttributesModule.defaultStaticCritical;
		double staticMagicResist = AttributesModule.defaultStaticMagicResist;
		double staticAttackResist = AttributesModule.defaultStaticAttackResist;
		
		double dodgeOffenseMod = AttributesModule.defaultDodgeOffenseMod;
		double dodgeDefenseMod = AttributesModule.defaultDodgeDefenseMod;
		double dodgeAbilityMod = AttributesModule.defaultDodgeAbilityMod;
		double dodgeSpeedMod = AttributesModule.defaultDodgeSpeedMod;
		double blockOffenseMod = AttributesModule.defaultBlockOffenseMod;
		double blockDefenseMod = AttributesModule.defaultBlockDefenseMod;
		double blockAbilityMod = AttributesModule.defaultBlockAbilityMod;
		double blockSpeedMod = AttributesModule.defaultBlockSpeedMod;
		double criticalOffenseMod = AttributesModule.defaultCriticalOffenseMod;
		double criticalDefenseMod = AttributesModule.defaultCriticalDefenseMod;
		double criticalAbilityMod = AttributesModule.defaultCriticalAbilityMod;
		double criticalSpeedMod = AttributesModule.defaultCriticalSpeedMod;
		double attackResistOffenseMod = AttributesModule.defaultAttackResistOffenseMod;
		double attackResistDefenseMod = AttributesModule.defaultAttackResistDefenseMod;
		double attackResistAbilityMod = AttributesModule.defaultAttackResistAbilityMod;
		double attackResistSpeedMod = AttributesModule.defaultAttackResistSpeedMod;
		double attackResistArmorMod = AttributesModule.defaultAttackResistArmorMod;
		double magicResistOffenseMod = AttributesModule.defaultMagicResistOffenseMod;
		double magicResistDefenseMod = AttributesModule.defaultMagicResistDefenseMod;
		double magicResistAbilityMod = AttributesModule.defaultMagicResistAbilityMod;
		double magicResistSpeedMod = AttributesModule.defaultMagicResistSpeedMod;
		
		String job = "N/A";
		try {
			job = "baseLevel"; baseLevel = dict.getInteger("baseLevel");
			job = "baseOffense"; baseOffense = dict.getInteger("baseOffense");
			job = "baseDefense"; baseDefense = dict.getInteger("baseDefense");
			job = "baseAbility"; baseAbility = dict.getInteger("baseAbility");
			job = "baseSpeed"; baseSpeed = dict.getInteger("baseSpeed");
			job = "baseHitPointMax"; baseHitPointMax = dict.getInteger("baseHitPointMax");
			job = "baseAbilityPointMax"; baseAbilityPointMax = dict.getInteger("baseAbilityPointMax");
			job = "baseArmor"; baseArmor = dict.getInteger("baseArmor");
			job = "staticDodge"; staticDodge = dict.getDouble("staticDodge");
			job = "staticBlock"; staticBlock = dict.getDouble("staticBlock");
			job = "staticCritical"; staticCritical = dict.getDouble("staticCritical");
			job = "staticMagicResist"; staticMagicResist = dict.getDouble("staticMagicResist");
			job = "staticAttackResist"; staticAttackResist = dict.getDouble("staticAttackResist");
			
			job = "dodgeOffenseMod"; if (dict.exists("dodgeOffenseMod")) {
				dodgeOffenseMod = dict.getDouble("dodgeOffenseMod"); }
			job = "dodgeDefenseMod"; if (dict.exists("dodgeDefenseMod")) {
				dodgeDefenseMod = dict.getDouble("dodgeDefenseMod"); }
			job = "dodgeAbilityMod"; if (dict.exists("dodgeAbilityMod")) {
				dodgeAbilityMod = dict.getDouble("dodgeAbilityMod"); }
			job = "dodgeSpeedMod"; if (dict.exists("dodgeSpeedMod")) {
				dodgeSpeedMod = dict.getDouble("dodgeSpeedMod"); }
			
			job = "blockOffenseMod"; if (dict.exists("blockOffenseMod")) {
				blockOffenseMod = dict.getDouble("blockOffenseMod"); }
			job = "blockDefenseMod"; if (dict.exists("blockDefenseMod")) {
				blockDefenseMod = dict.getDouble("blockDefenseMod"); }
			job = "blockAbilityMod"; if (dict.exists("blockAbilityMod")) {
				blockAbilityMod = dict.getDouble("blockAbilityMod"); }
			job = "blockSpeedMod"; if (dict.exists("blockSpeedMod")) {
				blockSpeedMod = dict.getDouble("blockSpeedMod"); }
			
			job = "criticalOffenseMod"; if (dict.exists("criticalOffenseMod")) {
				criticalOffenseMod = dict.getDouble("criticalOffenseMod"); }
			job = "criticalDefenseMod"; if (dict.exists("criticalDefenseMod")) {
				criticalDefenseMod = dict.getDouble("criticalDefenseMod"); }
			job = "criticalAbilityMod"; if (dict.exists("criticalAbilityMod")) {
				criticalAbilityMod = dict.getDouble("criticalAbilityMod"); }
			job = "criticalSpeedMod"; if (dict.exists("criticalSpeedMod")) {
				criticalSpeedMod = dict.getDouble("criticalSpeedMod"); }
			
			job = "attackResistOffenseMod"; if (dict.exists("attackResistOffenseMod")) {
				attackResistOffenseMod = dict.getDouble("attackResistOffenseMod"); }
			job = "attackResistDefenseMod"; if (dict.exists("attackResistDefenseMod")) {
				attackResistDefenseMod = dict.getDouble("attackResistDefenseMod"); }
			job = "attackResistAbilityMod"; if (dict.exists("attackResistAbilityMod")) {
				attackResistAbilityMod = dict.getDouble("attackResistAbilityMod"); }
			job = "attackResistSpeedMod"; if (dict.exists("attackResistSpeedMod")) {
				attackResistSpeedMod = dict.getDouble("attackResistSpeedMod"); }
			job = "attackResistArmorMod"; if (dict.exists("attackResistArmorMod")) {
				attackResistArmorMod = dict.getDouble("attackResistArmorMod"); }

			job = "magicResistOffenseMod"; if (dict.exists("magicResistOffenseMod")) {
				magicResistOffenseMod = dict.getDouble("magicResistOffenseMod"); }
			job = "magicResistDefenseMod"; if (dict.exists("magicResistDefenseMod")) {
				magicResistDefenseMod = dict.getDouble("magicResistDefenseMod"); }
			job = "magicResistAbilityMod"; if (dict.exists("magicResistAbilityMod")) {
				magicResistAbilityMod = dict.getDouble("magicResistAbilityMod"); }
			job = "magicResistSpeedMod"; if (dict.exists("magicResistSpeedMod")) {
				magicResistSpeedMod = dict.getDouble("magicResistSpeedMod"); }
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("CHAR-ERROR", ("AttributesModule, char=" + currentname + " at=" + job), err);
			throw err;
		}
		return new AttributesModule (currentCharacter, baseLevel, baseOffense, baseDefense, baseAbility, 
			baseSpeed, baseHitPointMax, baseAbilityPointMax, baseArmor, staticDodge,
			staticBlock, staticCritical, staticMagicResist, staticAttackResist,
			dodgeOffenseMod, dodgeDefenseMod, dodgeAbilityMod, dodgeSpeedMod,
			blockOffenseMod, blockDefenseMod, blockAbilityMod, blockSpeedMod,
			criticalOffenseMod, criticalDefenseMod, criticalAbilityMod, criticalSpeedMod,
			attackResistOffenseMod, attackResistDefenseMod, attackResistAbilityMod, 
			attackResistSpeedMod, attackResistArmorMod, magicResistOffenseMod,
			magicResistDefenseMod, magicResistAbilityMod, magicResistSpeedMod);

	}
	
	
	
	
	
	
	
	
	
	///////////////////////
	//	Other loaders
	///////////////////////
	public static CharacterInventory loadCharacterInventory(DataDictionary dict) throws Exception {
		CharacterInventory intentory = null;
		int totalEquipmentSlots = CharacterInventory.defaultTotalEquipmentSlots;
		int weaponSlotStart = CharacterInventory.defaultWeaponSlotStart;  
		int weaponSlotEnd = CharacterInventory.defaulDefaultWeaponSlotEnd;
		int shieldSlotStart = CharacterInventory.defaulShieldSlotStart;  
		int shieldSlotEnd = CharacterInventory.defaulShieldSlotEnd;
		int armorSlotStart = CharacterInventory.defaulArmorSlotStart;  
		int armorSlotEnd = CharacterInventory.defaulArmorSlotEnd;
		int accessorySlotStart = CharacterInventory.defaulAccessorySlotStart;  
		int accessorySlotEnd = CharacterInventory.defaulAccessorySlotEnd;
		int movementSlotStart = CharacterInventory.defaulMovementSlotStart;  
		int movementSlotEnd = CharacterInventory.defaulMovementSlotEnd;
		
		String job = "N/A";
		try {
			job = "equipmentSlots";
			if(dict.exists("equipmentSlots")) {
				DataDictionary slotsDict = dict.getSubDictionary("equipmentSlots");
				job = "totalEquipmentSlots"; totalEquipmentSlots = slotsDict.getInteger("totalEquipmentSlots");
				job = "weaponSlotStart"; weaponSlotStart = slotsDict.getInteger("weaponSlotStart");
				job = "weaponSlotEnd"; weaponSlotEnd = slotsDict.getInteger("weaponSlotEnd");
				job = "shieldSlotStart"; shieldSlotStart = slotsDict.getInteger("shieldSlotStart");
				job = "shieldSlotEnd"; shieldSlotEnd = slotsDict.getInteger("shieldSlotEnd");
				job = "armorSlotStart"; armorSlotStart = slotsDict.getInteger("armorSlotStart");
				job = "armorSlotEnd"; armorSlotEnd = slotsDict.getInteger("armorSlotEnd");
				job = "accessorySlotStart"; accessorySlotStart = slotsDict.getInteger("accessorySlotStart");
				job = "accessorySlotEnd"; accessorySlotEnd = slotsDict.getInteger("accessorySlotEnd");
				job = "movementSlotStart"; movementSlotStart = slotsDict.getInteger("movementSlotStart");
				job = "movementSlotEnd"; movementSlotEnd = slotsDict.getInteger("movementSlotEnd");
			}
			intentory = new CharacterInventory(totalEquipmentSlots, weaponSlotStart, weaponSlotEnd,
					shieldSlotStart, shieldSlotEnd, armorSlotStart, armorSlotEnd,
					accessorySlotStart, accessorySlotEnd, movementSlotStart, movementSlotEnd);
			
			job = "itemsList";
			if (dict.exists("itemsList")) {
				DataDictionary itemslist = dict.getSubArray("itemsList");
				for(int i=0; i<itemslist.getArraySize(); i++) {
					String itmStr = itemslist.getString(i);
					if(itmStr.contains("~")) {
						BaseItem baseitm = GlobalItemList.globalList.getItem(itmStr.replace("~", ""));
						intentory.add(baseitm);
						intentory.equipItem(baseitm);
					}
					else {
						intentory.add(GlobalItemList.globalList.getItem(itmStr));
					}
				}
			}
		}
		catch (Exception err) {
			FileErrors.globalErrors.addError("INVENTORY-ERROR", ("loadCharacterInventory, at=" + job), err);
			throw err;
		}
		return intentory;
	}
	
	
	
	
	
	
	
	
	
	///////////////////////
	//	Private methods
	//////////////////////
	private static Integer[] getUnlockLevels(String str) {
		String[] list = str.split("-");
		Integer[] ret = new Integer[list.length];
		for(int i=0; i<list.length; i++) {
			ret[i] = Integer.parseInt(list[i]);
		}
		return ret;
	}
	
	private static String[] getEquipableString(String itemStr) {
		String[] list = itemStr.split(" ");
		return list;
	}

}
